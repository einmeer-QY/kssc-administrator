package com.einmeer.util;

import com.einmeer.entity.Administrators;
import com.einmeer.vo.ResultJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @author 芊嵛
 * @date 2024/3/11
 */
@WebFilter("*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            // 请求头中如果放置了内容会先发一个OPTIONS请求，遇到该请求直接通过
            if ("OPTIONS".equals(request.getMethod())) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            // 获取token
            String token = request.getHeader("token");
            // 获取请求地址，如果是登录地址不必校验
            String requestURI = request.getRequestURI();
            if ("/administrators/login".equals(requestURI)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
//            System.out.println(requestURI);
            // 对token进行校验,如果校验不通过会抛出异常
            try {
                Administrators administrators = MyUtil.doToken(token);
                MyRequest myRequest = new MyRequest(request);
                myRequest.addAttribute("overallId", String.valueOf(administrators.getAdministratorsId()));
                filterChain.doFilter(myRequest, servletResponse);

            } catch (Exception ex) {
                ex.printStackTrace();
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setContentType("application/json;charset=utf-8");
                ObjectMapper objectMapper = new ObjectMapper();
                response.getWriter().write(objectMapper.writeValueAsString(ResultJson.unlogin("非法请求")));
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
