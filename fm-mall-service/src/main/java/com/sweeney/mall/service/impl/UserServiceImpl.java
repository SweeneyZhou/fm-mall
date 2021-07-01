package com.sweeney.mall.service.impl;

import com.sweeney.mall.common.utils.MD5Utils;
import com.sweeney.mall.common.vo.ResultVO;
import com.sweeney.mall.dao.UsersMapper;
import com.sweeney.mall.entity.Users;
import com.sweeney.mall.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;

/**
 * @author sweeney
 * @since 2021/06/29 14:46 created.
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UsersMapper usersMapper;

    @Override
    public ResultVO<Object> register(Users user) {

        Example example=new Example(Users.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",user.getUsername());

        Users users = usersMapper.selectOneByExample(example);
        if (users==null){
            Users newUser=new Users();
            newUser.setUsername(user.getUsername());

            newUser.setPassword(MD5Utils.md5(user.getPassword()));

            newUser.setUserImg("img/default.png");
            newUser.setUserModtime(new Date());
            newUser.setUserRegtime(new Date());
            int result = usersMapper.insert(newUser);
            return result>0?ResultVO.ok("注册成功!",null):ResultVO.error("注册失败!",null);
        }
        return null;
    }
}
