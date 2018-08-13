package com.oti.util;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 

public class CrossFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        String origin= servletRequest.getRemoteHost()+":"+servletRequest.getRemotePort();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Authentication");
     
        //response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
        //response.setHeader("Access-Control-Max-Age","3600");
        //response.setHeader("Access-Control-Allow-Credentials","true");
        filterChain.doFilter(servletRequest,servletResponse);
    }


/*
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        res.setContentType("textml;charset=UTF-8");
        res.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "0");
        res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("XDomainRequestAllowed","1");
        filterChain.doFilter(servletRequest,servletResponse);
    }
*/

    public void destroy() {
 
    }
}