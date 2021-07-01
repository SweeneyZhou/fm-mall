package com.sweeney.mall.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sweeney
 * @since 2021/06/29 14:12 created.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "接口统一返回对象",description = "该接口用于统一接口的返回格式")
public class ResultVO<T> {

    @ApiModelProperty(value = "接口返回值中的响应码",dataType = "int")
    private Integer code;

    @ApiModelProperty(value = "接口返回值中的响应信息",dataType = "String")
    private String msg;
    //TODO swagger、knife4j不显示这个提示
    @ApiModelProperty(value = "接口返回值中的响应数据",dataType = "Object")
    private T Data;

    public static <T> ResultVO<T> ok(String msg,T data) {
        return new ResultVO<>(ResStatus.OK,msg,data);
    }

    public static <T> ResultVO<T> error(String msg,T data) {
        return new ResultVO<>(ResStatus.NO,msg,data);
    }

}
