package com.yan.mapper;

import com.yan.model.Game;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lwyan on 2018-06-06 10:55
 */
@Repository
public interface GameMapper {
	List<Game> listGame();

	Game getGame(@Param("userName") String userName);

	void insertGame(@Param("userName") String userName, @Param("score") int score);

	void updateGame(@Param("userName") String userName, @Param("score") int score);
}
