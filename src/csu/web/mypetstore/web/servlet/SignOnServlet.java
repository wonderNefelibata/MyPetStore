package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CartService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SignOnServlet extends HttpServlet {
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    private String username;
    private String password;
    private String captcha;
    private String storeCaptcha;

    private String msg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        this.captcha = req.getParameter("captcha");
        HttpSession session = req.getSession();
        this.storeCaptcha = (String) session.getAttribute("captcha");

        if(!validate()){
            req.setAttribute("signOnMsg", this.msg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else{
            AccountService accountService = new AccountService();
            Account loginAccount = accountService.getAccount(username, password);
            if(loginAccount == null){
                this.msg = "用户名或密码错误";
                session.setAttribute("signOnMsg",msg);
                //更改密码后不报错了??
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
            }else {
                this.msg = null;
                session.setAttribute("signOnMsg",msg);
                session.setAttribute("loginAccount" , loginAccount);
                CartService cart = new CartService();
                String userid = loginAccount.getUsername();
                List<CartItem> cartItemList = cart.getCartItemListByUserid(userid);
                for (int i = 0; i < cartItemList.size(); i++){
                    CartItem cartItem = cartItemList.get(i);
                    cart. addItem1(cartItem);
                }
                session.setAttribute("cart", cart);
                loginAccount.setPassword(null);//?

                if(loginAccount.isListOption()){
                    CatalogService catalogService = new CatalogService();
                    List<Product> myList = catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                    session.setAttribute("myList", myList);
                }
                resp.sendRedirect("mainForm");
            }
        }
    }

    private boolean validate(){
        if(this.username == null || this.username.isEmpty()){
            this.msg = "用户名不能为空";
            return false;
        }
        if(this.password == null || this.password.isEmpty()){
            this.msg = "密码不能为空";
            return false;
        }
//        if(this.captcha == null || !captcha.equalsIgnoreCase(storeCaptcha)){
//            this.msg = "验证码错误";
//            return false;
//        }
        return true;
    }
}
