package com.yan.service;
import com.yan.model.UserDO;
import com.yan.model.UserDTO;

/**
 * @author lwyan on 2018-05-18 10:07
 */
public interface UserService {
	// 根据用户名和密码查询用户
	UserDO getUser(UserDTO userDTO);

	// 根据用户名查询用户
	UserDO getUserByName(String userName);

	// 添加用户
	void insertUser(UserDO userDO);
}
