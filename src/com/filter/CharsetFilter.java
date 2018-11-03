package com.filter;

import java.io.IOException;

public class CharsetFilter implements javax.servlet.Filter {
    public void destroy() {
        System.out.println("Filter destroy");
    }

    public void doFilter(javax.servlet.ServletRequest req, javax.servlet.ServletResponse resp, javax.servlet.FilterChain chain) throws javax.servlet.ServletException, IOException {
        //设置请求为utf8格式
        req.setCharacterEncoding("utf-8");
        //设置响应为json的utf8格式
        resp.setContentType("application/json;charset=utf8");
        /*Do the next filter*/
        chain.doFilter(req, resp);
    }

    public void init(javax.servlet.FilterConfig config) throws javax.servlet.ServletException {
        System.out.println("Filter init.");
    }

}
