package com.sweeney.mall.web;

import com.sweeney.mall.common.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sweeney
 * @since 2021/06/30 9:30 created.
 */
@RestController
@RequestMapping
//Api注解，描述信息 可通过tag进行分类
@Api(value = "HelloController")
public class HelloController {
    @PostMapping("/hello")
    //方法描述
    @ApiOperation(notes = "Hello Swagger", value = "测试接口")
    public ResultVO<String> hello(){
        return new ResultVO<>();
    }
}
