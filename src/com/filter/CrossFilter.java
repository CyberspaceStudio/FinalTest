package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CrossFilter")
public class CrossFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        /*Use it to cast the variables to the right class*/
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        /*Allow cross-domain request*/
        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        /*Set Allow-Methods*/
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
