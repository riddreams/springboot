package com.yan.controller;

import com.yan.model.UserDO;
import com.yan.model.UserDTO;
import com.yan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lwyan on 2018-05-18 10:07
 */
@Controller
public class UserController{

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 处理登录
	@RequestMapping(value = "/doLogin")
	public String doLogin(UserDTO userDTO, HttpSession session, RedirectAttributes attr){
		// 根据用户名和密码查询用户
		UserDO userDO = userService.getUser(userDTO);
		if(userDO!=null){
			// 将用户存入session
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
		// 清除session中的用户
		session.removeAttribute("userDO");
		return "redirect:/";
	}

	// ajax查询用户名是否已被注册
	@ResponseBody
	@RequestMapping(value = "/checkUser")
	public void checkUser(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("userName");
		UserDO userDO = userService.getUserByName(userName);
		if(userDO!=null){
			String message = "用户名不可用";
			try {
				response.getWriter().write(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 处理注册
	@RequestMapping(value = "/doRegister")
	public String doRegister(UserDO userDO, RedirectAttributes attr){
		userDO.setRole("user");
		if(!"".equals(userDO.getUserName()) && !"".equals(userDO.getPassWord())){
			userService.insertUser(userDO);
			return "redirect:/login";
		}else{
			attr.addFlashAttribute("message","用户名或密码不能为空");
			return "redirect:/register";
		}
	}
}