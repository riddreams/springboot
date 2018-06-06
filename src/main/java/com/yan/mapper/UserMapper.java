package com.yan.mapper;

import com.yan.model.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lwyan on 2018-05-18 12:22
 */
@Repository
public interface UserMapper {

	// 根据用户名查找用户
	UserDO getUserByName(@Param("userName") String userName);

	// 添加用户（注册）
	void insertUser(@Param("userDO") UserDO userDO);

	// 根据用户名修改基本资料
	void updateBaseByName(@Param("oldName") String oldName, @Param("userName") String userName, @Param("intro") String intro);

	// 修改头像
	void changeHeader(@Param("userName") String userName, @Param("header") String header);
}