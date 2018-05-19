package com.yan.service.impl;

import com.yan.mapper.UserMapper;
import com.yan.model.UserDO;
import com.yan.model.UserDTO;
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

	@Override
	public UserDO getUser(UserDTO userDTO) {
		return userMapper.getUser(userDTO);
	}
}