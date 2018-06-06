package com.yan.controller;

import com.yan.model.Game;
import com.yan.model.UserDO;
import com.yan.service.EnjoyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * @author lwyan on 2018-06-06 10:48
 */
@Controller
@RequestMapping("/enjoy")
public class GameController {

	private EnjoyService enjoyService;

	@Autowired
	public void setEnjoyService(EnjoyService enjoyService) {
		this.enjoyService = enjoyService;
	}

	// 获取分数排行榜
	@ResponseBody
	@RequestMapping(value = "/getRange",method = RequestMethod.POST)
	public List<Game> getRange(){
		return enjoyService.listGame();
	}

	// 更新排行榜
	@ResponseBody
	@RequestMapping(value = "/updateRange",method = RequestMethod.POST)
	public void updateRange(@Param("score")int score, @SessionAttribute("userDO")UserDO userDO){
		String userName = userDO.getUserName();
		Game game = enjoyService.getGame(userName);
		if(game == null){
			enjoyService.insertGame(userName,score);
		}else if(game.getScore() < score){
			enjoyService.updateGame(userName,score);
		}
	}
}