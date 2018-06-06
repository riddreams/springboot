package com.yan.service;

import com.yan.model.Game;

import java.util.List;

/**
 * @author lwyan on 2018-06-06 10:50
 */
public interface EnjoyService {

	List<Game> listGame();

	Game getGame(String userName);

	void insertGame(String userName, int score);

	void updateGame(String userName, int score);
}