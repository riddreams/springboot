package com.yan.service;
import com.yan.model.UserDO;
import com.yan.model.UserDTO;

/**
 * @author lwyan on 2018-05-18 10:07
 */
public interface UserService {
	UserDO getUser(UserDTO userDTO);
}
