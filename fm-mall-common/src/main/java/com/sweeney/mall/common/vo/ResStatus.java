package com.sweeney.mall.common.vo;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author sweeney
 * @since 2021/06/29 15:34 created.
 */
public class ResStatus {
    public static final int OK = 10000;

    public static final int NO = 10001;

    /**
     * 认证成功
     */
    public static final int LOGIN_SUCCESS = 20000;

    /**
     * 用户未登录
     */
    @Value("")
    public static final int LOGIN_FAIL_NOT = 20001;

    /**
     * 用户登录失效,token过期
     */
    public static final int LOGIN_FAIL_OVERDUE = 20002;
    /**
     * token签名错误
     */
    public static final int LOGIN_SIGN = 20003;

}
