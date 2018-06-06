package com.yan.service;
import com.yan.model.UserDO;

/**
 * @author lwyan on 2018-05-18 10:07
 */
public interface UserService {

	// 根据用户名查询用户
	UserDO getUserByName(String userName);

	// 添加用户
	void insertUser(UserDO userDO);

	// 根据用户名修改基本资料
	void updateBaseByName(String oldName, String userName, String intro);

	// 修改头像
	void changeHeader(String userName, String header);
}
