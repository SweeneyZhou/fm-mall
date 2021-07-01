package com.sweeney.mall.intercepter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweeney.mall.common.vo.ResStatus;
import com.sweeney.mall.common.vo.ResultVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author sweeney
 * @since 2021/07/01 11:59 created.
 */
@Slf4j
@AllArgsConstructor
@Component
public class CheckLoginInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取请求方法：GET,POST...OPTIONS
        String method = request.getMethod();

        log.info("登录令牌校验");
        //对跨域请求进行放行处理
        if ("OPTIONS".equals(method)) {
            log.info("跨域请求");
            return true;
        }

        String token = request.getHeader("token");
        if (token == null) {
            log.info("无令牌");
            return true;
        }

        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("fm-mall")).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            log.info("令牌校验通过,令牌信息:");
            log.info(decodedJWT.getId());
            log.info(String.valueOf(decodedJWT.getClaim("username")));
        } catch (JWTVerificationException e) {
            //TODO 测试令牌校验
            if (e instanceof AlgorithmMismatchException) {
                log.info("令牌有误,token格式不支持");
                ResultVO<Object> vo = new ResultVO<>(ResStatus.LOGIN_FAIL_NOT, "token格式不支持", null);
                sendResponse(response, vo);
            }
            if (e instanceof SignatureVerificationException) {
                log.info("令牌有误,签名异常");
                ResultVO<Object> vo = new ResultVO<>(ResStatus.LOGIN_FAIL_NOT, "签名异常", null);
                sendResponse(response, vo);
            }
            if (e instanceof TokenExpiredException) {
                log.info("令牌有误,登录已过期");
                ResultVO<Object> vo = new ResultVO<>(ResStatus.LOGIN_FAIL_NOT, "登录已过期", null);
                sendResponse(response, vo);
            }
            if (e instanceof InvalidClaimException) {
                log.info("令牌有误,token异常");
                ResultVO<Object> vo = new ResultVO<>(ResStatus.LOGIN_FAIL_NOT, "token不符合预期", null);
                sendResponse(response, vo);
            }
        }

        return false;
    }

    /**
     * 处理响应信息
     */
    private void sendResponse(HttpServletResponse response, ResultVO<Object> vo) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            String json = objectMapper.writeValueAsString(vo);
            writer.print(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
