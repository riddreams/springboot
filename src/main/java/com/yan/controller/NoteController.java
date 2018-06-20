package com.yan.controller;

import com.yan.model.Note;
import com.yan.model.UserDO;
import com.yan.service.NoteService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author lwyan on 2018-06-06 11:48
 */
@Controller
@RequestMapping("/note")
public class NoteController {

	private NoteService noteService;

	@Autowired
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

	@RequestMapping(value = "/sendNote",method = RequestMethod.POST)
	public String sendNote(@Param("says")String says, @SessionAttribute("userDO")UserDO userDO){
		noteService.insertNote(userDO.getUserName(),says,new Timestamp(new Date().getTime()));
		return "redirect:/index";
	}

	@ResponseBody
	@RequestMapping(value = "/listNote",method = RequestMethod.POST)
	public List<Note> listNote(@Param("begin")int begin){
		int page = 5;
		int count = noteService.countNote();
		if(begin>=count){
			return null;
		}
		return noteService.listNote(begin,page);
	}
}