package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "EditAccountServlet", value = "/editAccount")
public class EditAccountServlet extends HttpServlet {

    private static final String MAIN = "/WEB-INF/jsp/catalog/main.jsp";
    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/editAccount.jsp";

    private Account account ;
    private String msg;
    private String username;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.account = new Account();

        AccountService accountService = new AccountService();
        HttpSession session = request.getSession();
        account = (Account) session.getAttribute("loginAccount");
        //为啥这样写呢，直接username = account.getUsername();不行吗
        account = accountService.getAccount(account.getUsername());
        username = account.getUsername();

//        this.account = new Account();
        account.setUsername(username);
        account.setPassword(request.getParameter("password"));
        account.setFirstName(request.getParameter("account.firstName"));
        account.setLastName(request.getParameter("account.lastName"));
        account.setEmail(request.getParameter("account.email"));
        account.setPhone(request.getParameter("account.phone"));
        account.setAddress1(request.getParameter("account.address1"));
        account.setAddress2(request.getParameter("account.address2"));
        account.setCity(request.getParameter("account.city"));
        account.setState(request.getParameter("account.state"));
        account.setZip(request.getParameter("account.zip"));
        account.setCountry(request.getParameter("account.country"));
        account.setLanguagePreference(request.getParameter("account.languagePreference"));
        account.setFavouriteCategoryId(request.getParameter("account.favouriteCategoryId"));
        account.setListOption(request.getParameter("account.listOption")!=null);
        account.setBannerOption(request.getParameter("account.bannerOption")!=null);

        if(account.getPassword() == null){
            msg = "password can't be empty";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher(EDIT_ACCOUNT).forward(request,response);
        }
        else {
            accountService = new AccountService();
            accountService.updateAccount(account);
            account = accountService.getAccount(account.getUsername());
            CatalogService catalogService = new CatalogService();
            List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            session.setAttribute("loginAccount",account);
            session.setAttribute("myList",myList);

            request.getRequestDispatcher(EDIT_ACCOUNT).forward(request,response);
//            response.sendRedirect("mainForm");
        }
    }
}