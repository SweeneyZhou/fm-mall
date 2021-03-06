package com.sweeney.mall.service;

import com.sweeney.mall.common.vo.ResultVO;
import com.sweeney.mall.entity.Users;

/**
 * @author sweeney
 * @since 2021/06/29 14:45 created.
 */
public interface UserService {

     ResultVO<Users> register(Users user);

     ResultVO<Users> login(String username, String password);
}
