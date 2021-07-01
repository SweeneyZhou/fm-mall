package com.sweeney.mall.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sweeney.mall.common.utils.MD5Utils;
import com.sweeney.mall.common.vo.ResStatus;
import com.sweeney.mall.common.vo.ResultVO;
import com.sweeney.mall.dao.UsersMapper;
import com.sweeney.mall.entity.Users;
import com.sweeney.mall.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;

/**
 * @author sweeney
 * @since 2021/06/29 14:46 created.
 */
@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UsersMapper usersMapper;

    @Override
    public ResultVO<Users> register(Users user) {
        Users users = getByUsername(user.getUsername());
        if (users == null) {
            Users newUser = new Users();
            newUser.setUsername(user.getUsername());

            newUser.setPassword(MD5Utils.md5(user.getPassword()));

            newUser.setUserImg("img/default.png");
            newUser.setUserModtime(new Date());
            newUser.setUserRegtime(new Date());
            int result = usersMapper.insert(newUser);
            return result > 0 ? ResultVO.ok("注册成功!", null) : ResultVO.error("注册失败!", null);
        } else {
            return ResultVO.error("用户名已存在!", null);
        }
    }
}
