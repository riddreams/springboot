package com.yan.service.impl;

import com.yan.mapper.GameMapper;
import com.yan.model.Game;
import com.yan.service.EnjoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lwyan on 2018-06-06 10:53
 */
@Service
public class EnjoyServiceImpl implements EnjoyService{

	private GameMapper gameMapper;

	@Autowired
	public void setGameMapper(GameMapper gameMapper) {
		this.gameMapper = gameMapper;
	}

	@Override
	public List<Game> listGame() {
		return gameMapper.listGame();
	}

	@Override
	public Game getGame(String userName) {
		return gameMapper.getGame(userName);
	}

	@Override
	public void insertGame(String userName, int score) {
		gameMapper.insertGame(userName, score);
	}

	@Override
	public void updateGame(String userName, int score) {
		gameMapper.updateGame(userName,score);
	}
}