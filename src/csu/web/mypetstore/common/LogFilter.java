package csu.web.mypetstore.common;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

//@WebFilter("/*")
public class LogFilter {
//public class LogFilter implements Filter {
//    private LogService logService;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        logService = new LogService();
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        filterChain.doFilter(servletRequest,servletResponse);
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpSession session = httpRequest.getSession();
//        Account account = (Account)session.getAttribute("loginAccount");
//        if(account != null){
//            String action = getAction(httpRequest.getServletPath(),httpRequest.getQueryString());
//            if(action == null) return;
//            String request = httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());
//            String username = account.getUsername();
//            logService.insertLog(username,action,request);
//        }
//    }
//
//    public void destroy(){
//    }
//
//    /**
//     * 判断执行的操作是什么
//     * @param servletPath
//     * @param queryString
//     * @return String
//     */
//    private String getAction(String servletPath, String queryString) {
//        if(servletPath.equals("/categoryForm")){
//            return "浏览category:" + getValueAfterEquals(queryString);
//        }else if(servletPath.equals("/productForm")){
//            return "浏览product:" + getValueAfterEquals(queryString);
//        }else if(servletPath.equals("/itemForm")){
//            return "浏览item:" + getValueAfterEquals(queryString);
//        }else if(servletPath.equals("/addItemToCart")){
//            return getValueAfterEquals(queryString) + "被添加至购物车";
//        }else if(servletPath.equals("/newOrder")){
//            return "完成新订单";
//        }
//        return null;
//    }
//
//    /**
//     * 获取请求的的值
//     * @param input
//     * @return
//     */
//    private String getValueAfterEquals(String input){
//        int equalsIndex = input.indexOf("=");
//        if(equalsIndex != -1 && equalsIndex < input.length() - 1){
//            return input.substring(equalsIndex+1);
//        }else{
//            return null;
//        }
//    }
}
