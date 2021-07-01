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

    @Override
    public ResultVO<Users> login(String username, String password) {
        Users record = getByUsername(username);

        if (record == null) {
            return new ResultVO<>(ResStatus.LOGIN_FAIL_NOT, "用户名不存在!", null);
        } else {
            String md5pwd = MD5Utils.md5(password);
            if (record.getPassword().equals(md5pwd)) {
                String token = JWT.create()
                        .withIssuer("auth0")
                        //签发时间
                        .withIssuedAt(new Date())
                        //过期时间
                        .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                        //jwt的id
                        .withJWTId(String.valueOf(record.getUserId()))
                        //传递用户信息
                        .withClaim("username", username)
                        //签名防止伪造令牌
                        .sign(Algorithm.HMAC256("fm-mall"));
                log.warn("token=" + token);

                return ResultVO.ok(token, record);
            } else {
                return new ResultVO<>(ResStatus.LOGIN_FAIL_NOT, "密码错误!", null);
            }
        }
    }

    private Users getByUsername(String username) {
        Example example = new Example(Users.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return usersMapper.selectOneByExample(example);
    }
}
