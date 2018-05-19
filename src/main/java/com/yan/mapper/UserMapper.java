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
	@Select("select id,username,password,role from user where username=#{userDTO.userName} and password=#{userDTO.passWord}")
	UserDO getUser(@Param("userDTO") UserDTO userDTO);
}