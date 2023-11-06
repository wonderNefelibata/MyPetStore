package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterFormServlet extends HttpServlet {

    private static final String REGISTER_FORM = "/WEB-INF/jsp/account/newAccount.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");
//        //日志
//        if(account != null){
//            HttpServletRequest httpRequest= request;
//            String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
//                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());
//
//            LogService logService = new LogService();
//            String logInfo = logService.logInfo(" ") + strBackUrl + " 跳转到注册新账号界面";
//            logService.insertLogInfo(account.getUsername(), logInfo);
//        }

        request.getRequestDispatcher(REGISTER_FORM).forward(request,response);
    }
}
