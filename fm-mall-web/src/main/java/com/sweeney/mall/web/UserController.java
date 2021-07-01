package com.sweeney.mall.web;

import com.sweeney.mall.common.vo.ResultVO;
import com.sweeney.mall.entity.Users;
import com.sweeney.mall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sweeney
 * @since 2021/06/30 14:15 created.
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/user")
@Api(value = "用户操作模块相关接口文档",tags = "用户管理")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册接口")
    public ResultVO<Object> register(@RequestBody Users user){
        return userService.register(user);
    }
}
