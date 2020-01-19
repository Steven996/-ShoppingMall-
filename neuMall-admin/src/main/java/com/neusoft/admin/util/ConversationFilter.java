package com.neusoft.admin.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.ognl.EnumerationIterator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ConversationFilter
 * @Description: 后台拦截
 * @Author: hanjz
 * @Date: 2019/4/23
 */
@Slf4j
public class ConversationFilter implements Filter {
    public static WebApplicationContext web = null;
    RedisTemplate<String,String> redisTemplate ;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         web = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
         redisTemplate = (RedisTemplate<String,String>)web.getBean("redisTemplate");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        //不需要拦截地址
        String[] path={"/backend/login/userLogin"
        };
        boolean a=false;
        try {
             res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
             res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");
             res.setHeader("Access-Control-Max-Age", "3600");
             res.setHeader("Access-Control-Allow-Headers", "x-requested-with,Cache-Control,Pragma,Content-Type,Token");
             res.setHeader("Access-Control-Allow-Credentials", "true");
                 //是否支持cookie跨域
                 // 如果是要拦截的地址
            int j = 0;
            for(int i=0;i<path.length;i++){
                if(path[i].equals(req.getServletPath())){
                    break;
                }
               j++;
            }
            if(j==path.length){
                a = true;
            }
            if("OPTIONS".equals(req.getMethod())){
                return;
            }
            String token = req.getHeader("token");
            System.out.println(token);
           /* Enumeration<String> enumeration=req.getHeaderNames();
            while(enumeration.hasMoreElements()){
                Object obj = enumeration.nextElement();
                if(obj.toString().trim().equals("token")){
                    token =req.getHeader(obj.toString());
                    break;
                }
            }*/

            if (a){
                    if(token==null){
                        res.setContentType("text/html;charset=utf-8");
                        PrintWriter prw = res.getWriter();
                        prw.print("没在header中放token");
                    }
                    if( redisTemplate.opsForValue().get(token)==null){
                        res.setContentType("text/html;charset=utf-8");
                        PrintWriter prw = res.getWriter();
                        prw.print("token失效，请重新登录");
                        log.info("token失效，请重新登录");
                    }else {
                        log.info("已登录");
                        redisTemplate.expire(token,30, TimeUnit.MINUTES);
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
            } else {
                log.info("无需校验token");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            log.info("系统异常");
            throw e;
        }
    }

    @Override
    public void destroy() {

    }
}