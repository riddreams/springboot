package com.yan.service.impl;

import com.yan.mapper.UserMapper;
import com.yan.model.UserDO;
import com.yan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lwyan on 2018-05-18 12:31
 */
@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	// 根据用户名查找用户
	@Override
	public UserDO getUserByName(String userName) {
		return userMapper.getUserByName(userName);
	}

	// 添加用户
	@Override
	public void insertUser(UserDO userDO) {
		userMapper.insertUser(userDO);
	}

	// 根据用户名修改基本资料
	@Override
	public void updateBaseByName(String oldName, String userName, String intro) {
		userMapper.updateBaseByName(oldName, userName, intro);
	}

	// 修改头像
	@Override
	public void changeHeader(String userName, String header) {
		userMapper.changeHeader(userName,header);
	}
}