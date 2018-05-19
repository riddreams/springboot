package com.yan.controller;

import com.yan.model.UserDO;
import com.yan.model.UserDTO;
import com.yan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author lwyan on 2018-05-18 10:07
 */
@Controller
public class UserController{

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private UserService userService;

	@RequestMapping(value = "/")
	public String index(){
		return "index";
	}

	// 跳转到用户登录界面
	@RequestMapping(value = "/login")
	public String login(){
		return "login";
	}

	@RequestMapping(value = "/home")
	public String home(){
		return "home";
	}

	// 处理登录
	@RequestMapping(value = "/doLogin")
	public String doLogin(UserDTO userDTO, HttpSession session, RedirectAttributes attr){
		UserDO userDO = userService.getUser(userDTO);
		if(userDO!=null){
			session.setAttribute("userDO",userDO);
			return "redirect:/home";
		}else {
			attr.addFlashAttribute("message","用户名或密码错误");
			return "redirect:/login";
		}
	}

	// 处理退出登录
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session){
		session.removeAttribute("userDO");
		return "redirect:/";
	}
}