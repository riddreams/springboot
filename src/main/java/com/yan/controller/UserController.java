package com.yan.controller;

import com.yan.model.Note;
import com.yan.model.UserDO;
import com.yan.service.NoteService;
import com.yan.service.UserService;
import com.yan.util.MD5Utils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author lwyan on 2018-05-18 10:07
 */
@Controller
public class UserController{

	private NoteService noteService;
	@Autowired
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 处理登录
	@RequestMapping(value = "/doLogin",method = RequestMethod.POST)
	public String doLogin(@Param("userName")String userName, @Param("passWord")String passWord, HttpSession session, RedirectAttributes attr){
		// 根据用户名和密码查询用户
		UserDO userDO = userService.getUserByName(userName);
		if(userDO != null){
			String salt = userDO.getSalt();
			String md5PassWord = MD5Utils.md5Encode(passWord+salt);
			if(userDO.getPassWord().equals(md5PassWord)){
				// 将用户存入session
				session.setAttribute("userDO",userDO);
				return "redirect:/index";
			}else{
				attr.addFlashAttribute("message","密码错误");
				return "redirect:/login";
			}
		}else {
			attr.addFlashAttribute("message","用户不存在");
			return "redirect:/login";
		}
	}

	// 处理退出登录
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session){
		// 清除session中的用户
		session.removeAttribute("userDO");
		return "redirect:/login";
	}

	// ajax查询用户名是否已被注册
	@ResponseBody
	@RequestMapping(value = "/checkUser",method = RequestMethod.POST)
	public String checkUser(@Param("userName")String userName){
		UserDO userDO = userService.getUserByName(userName);
		String message = null;
		if(userDO != null){
			message = "用户名不可用";
		}
		return message;
	}

	// 处理注册
	@RequestMapping(value = "/doRegister",method = RequestMethod.POST)
	public String doRegister(UserDO userDO, RedirectAttributes attr){
		userDO.setRole("user");
		userDO.setHeader("header.jpg");
		userDO.setIntro("介绍下自己吧");
		if(!"".equals(userDO.getUserName()) && !"".equals(userDO.getPassWord())){
			String salt = MD5Utils.getSalt();
			userDO.setSalt(salt);
			userDO.setPassWord(MD5Utils.md5Encode(userDO.getPassWord()+salt));
			//根据用户名进行查找，看用户名是否重复
			if(userService.getUserByName(userDO.getUserName())!=null) {//如果用户名已注册
				attr.addFlashAttribute("message", "用户名已经被注册");
				return "redirect:/register";
			}else {//否则
				userService.insertUser(userDO);
				return "redirect:/login";
			}
		}else{
			attr.addFlashAttribute("message","用户名或密码不能为空");
			return "redirect:/register";
		}
	}

	// 修改基本资料
	@RequestMapping(value = "/updateBase",method = RequestMethod.POST)
	public String updateBase(@Param("userName")String userName, @Param("intro")String intro, HttpSession session,RedirectAttributes attr){
		UserDO userDO = (UserDO) session.getAttribute("userDO");
		UserDO exitUser = userService.getUserByName(userName);
		if("".equals(userName) || (exitUser!=null)&&(!userDO.getUserName().equals(userName)) ){
			attr.addFlashAttribute("message","用户名不可用");
		}else {
			userService.updateBaseByName(userDO.getUserName(),userName,intro);
			userDO = userService.getUserByName(userName);
			session.setAttribute("userDO",userDO);
			attr.addFlashAttribute("message","修改成功");
		}
		return "redirect:/setting/settingbase";
	}

	// 修改头像
	@RequestMapping(value = "/changeHeader",method = RequestMethod.POST)
	public String changeHeader(MultipartFile newHeader,HttpSession session){
		UserDO userDO = (UserDO) session.getAttribute("userDO");
		// 获取文件名
		String fileName = newHeader.getOriginalFilename();
		if (fileName.length() > 0) {
			// 设置文件存放路径
			File directory = null;
			try {
				directory = new File(ResourceUtils.getURL("classpath:").getPath()+File.separator+"static"+File.separator+"header");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				// 设置包含路径的文件名
				File file = new File(directory + File.separator + fileName);
				// 将文件存放到磁盘
				newHeader.transferTo(file);
				userService.changeHeader(userDO.getUserName(),fileName);
				session.setAttribute("userDO",userService.getUserByName(userDO.getUserName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "redirect:/setting/home";
	}

	// index页面
	@RequestMapping(value = {"/index","/"})
	public String index(Model model){
		int begin = 0;
		int page = 5;
		List<Note> notes = noteService.listNote(begin,page);
		model.addAttribute("notes",notes);
		return "index";
	}

	// 测试一对多级联
	@RequestMapping(value = "/users/{userName}")
	@ResponseBody
	public UserDO test(@PathVariable("userName")String userName){
		UserDO userDO = userService.getUserByName(userName);
		List<Note> notes = userDO.getNotes();
		for (Note note:notes){
			System.out.println(note.getText());
		}
		return userDO;
	}
}