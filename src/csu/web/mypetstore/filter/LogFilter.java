package csu.web.mypetstore.filter;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebFilter("/*")
public class LogFilter implements Filter {
    private LogService logService;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if(!"GET".equals(httpRequest.getMethod())){
            HttpSession session = httpRequest.getSession();
            Account account = (Account)session.getAttribute("loginAccount");
            if(account != null){
                String request = httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());
                String username = account.getUsername();
                String action = getAction(httpRequest.getServletPath(),httpRequest.getQueryString());
                logService.insertLog(username,action,request);
            }
        }
    }

    public void destroy(){
    }

    private String getAction(String servletPath, String queryString) {
        return null;
    }
}
