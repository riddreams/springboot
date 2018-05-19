package com.yan.mapper;

import com.yan.model.UserDO;
import com.yan.model.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author lwyan on 2018-05-18 12:22
 */
@Repository
public interface UserMapper {
	// 根据用户名和密码查找用户
	// 如果采用UserMapper.xml，则注释下面这句，相关sql语句xml文件中书写
	@Select("select id,username,password,role from user where username=#{userDTO.userName} and password=#{userDTO.passWord}")
	UserDO getUser(@Param("userDTO") UserDTO userDTO);

	// 根据用户名查找用户
	@Select("select id,username,password,role from user where username=#{userName}")
	UserDO getUserByName(@Param("userName") String userName);

	// 添加用户（注册）
	@Select("insert into user values(null,#{userDO.userName},#{userDO.passWord},#{userDO.role})")
	void insertUser(@Param("userDO") UserDO userDO);
}