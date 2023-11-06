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

    private Account account;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.account = new Account();
        account.setUsername(req.getParameter("username"));
        account.setPassword(req.getParameter("password"));
        account.setFirstName(req.getParameter("account.firstName"));
        account.setLastName(req.getParameter("account.lastName"));
        account.setEmail(req.getParameter("account.email"));
        account.setPhone(req.getParameter("account.phone"));
        account.setAddress1(req.getParameter("account.address1"));
        account.setAddress2(req.getParameter("account.address2"));
        account.setCity(req.getParameter("account.city"));
        account.setState(req.getParameter("account.state"));
        account.setZip(req.getParameter("account.zip"));
        account.setCountry(req.getParameter("account.country"));
        account.setLanguagePreference(req.getParameter("account.languagePreference"));
        account.setFavouriteCategoryId(req.getParameter("preference"));
        account.setStatus("OK");//
        account.setLanguagePreference("english");//
        account.setListOption(true);//
        account.setBannerOption(true);//

        this.captcha = req.getParameter("captcha");
        HttpSession session = req.getSession();
        this.storeCaptcha = (String) session.getAttribute("captcha");
        if(!validate()){
            req.setAttribute("registerMsg", this.msg);
            req.getRequestDispatcher(REGISTER_FORM).forward(req,resp);
        }else{
            AccountService accountService = new AccountService();
            accountService.insertAccount(account);
            Account loginAccount = accountService.getAccount(account.getUsername(),account.getPassword());
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
        if (account.getUsername() == null || account.getUsername().equals("")) {
            this.msg = "用户名不能为空";
            return false;
        }
        if (account.getPassword() == null || account.getPassword().equals("")) {
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