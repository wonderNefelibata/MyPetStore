package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RegisterServlet extends HttpServlet {
    private final static String REGISTER_FORM = "/WEB-INF/jsp/account/register.jsp";
    private String username;
    private String password;
    private String captcha;
    private String storeCaptcha;
    private String msg;
    private String preference;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        this.captcha = req.getParameter("captcha");
        this.preference = req.getParameter("preference");
        HttpSession session = req.getSession();
        this.storeCaptcha = (String) session.getAttribute("captcha");
        if(!validate()){
            req.setAttribute("registerMsg", this.msg);
            req.getRequestDispatcher(REGISTER_FORM).forward(req,resp);
        }else{
            AccountService accountService = new AccountService();
            accountService.insertAccount(username, preference, password);
            Account loginAccount = accountService.getAccount(username,password);
            if(loginAccount == null){
                this.msg = "注册失败,用户名已存在";
            }else {
                session.setAttribute("loginAccount", loginAccount);
                if (loginAccount.isListOption()) {
                    CatalogService catalogService = new CatalogService();
                    List<Product> myList = catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                    session.setAttribute("myList", myList);
                }
                resp.sendRedirect("mainForm");
            }
        }
    }

    private boolean validate() {
        if (this.username == null || this.username.equals("")) {
            this.msg = "用户名不能为空";
            return false;
        }
        if (this.password == null || this.password.equals("")) {
            this.msg = "密码不能为空";
            return false;
        }
        if(captcha ==null || !captcha.equalsIgnoreCase(storeCaptcha)){
            this.msg = "验证码错误";
            return false;
        }
        this.msg = "注册成功!";
        return true;
    }
}