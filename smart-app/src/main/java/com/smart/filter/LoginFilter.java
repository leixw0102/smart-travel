

package com.smart.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.smart.common.Token;
import com.smart.service.UserService;
import com.smart.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Date;

public class LoginFilter implements Filter {
    private static final Logger sLogger = LoggerFactory.getLogger(LoginFilter.class);
    private UserService userService;
    @Override
    public void init(FilterConfig config) throws ServletException {
        sLogger.debug("LoginFilter.init");
        WebApplicationContext webApplicationContext = (WebApplicationContext) config.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        userService = webApplicationContext.getBean(UserService.class);
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

            //验证用户
        HttpServletRequest request = (HttpServletRequest) req;
        try{
            String uri = request.getQueryString();
            if(uri.contains("login")){
                chain.doFilter(req, resp);
                return;
            }
            String header=request.getHeader("user");
            if(Strings.isNullOrEmpty(header)){
                //重定向登陆页面页面
                request.getRequestDispatcher("").forward(req,resp);
                return;
            }
            UserVo vo = JSON.parseObject(header, UserVo.class);
            Date current = new Date();
            if(userService.isValid(vo,current)){
                HttpServletResponse response = (HttpServletResponse) resp;
                vo.setUseTime(current.getTime()&1004L);
                response.setHeader("user",JSON.toJSONString(vo));
                chain.doFilter(req, resp);
                return;
            };
            //error页面
            request.getRequestDispatcher("").forward(req,resp);
        }   catch (Exception e){
            //error页面
            request.getRequestDispatcher("").forward(req,resp);
        }
    }

    @Override
    public void destroy() {
    }

}
