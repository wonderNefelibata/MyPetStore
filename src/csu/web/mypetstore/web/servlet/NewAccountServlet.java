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

public class NewAccountServlet extends HttpServlet {

    private static final String MAIN = "/WEB-INF/jsp/catalog/main.jsp";
    private static final String NEW_ACCOUNT = "/WEB-INF/jsp/account/newAccount.jsp";

    private AccountService accountService = new AccountService();


    private List<Product> myList;
    private boolean authenticated;
    private Account account;
    private String repeatedPassword;
    private String msg;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        CatalogService catalogService = new CatalogService();
        getParameters(req);//获取参数
        if(!validation()){
            req.setAttribute("msg",msg);
            req.getRequestDispatcher(NEW_ACCOUNT).forward(req,resp);
        }
        else{
            accountService.insertAccount(account);
            myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            authenticated = true;
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            session.setAttribute("myList", myList);
            session.setAttribute("authenticated", authenticated);
//            //日志part
//            if(account != null){
//
//                String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
//                        + request.getContextPath() + request.getServletPath() + "?" + (request.getQueryString());
//
//                LogService logService = new LogService();
//                String logInfo = logService.logInfo(" ") + strBackUrl + "完成了新账号注册";
//                logService.insertLogInfo(account.getUsername(), logInfo);
//            }
//            request.getRequestDispatcher(MAIN).forward(request,response);
            resp.sendRedirect(req.getContextPath()+"/main");
        }
    }

    private boolean validation(){
        if(account.getUsername() == null || account.getUsername().trim().equals("")){
            msg = "Your name is required.";
            return false;
        }
        if(accountService.getAccount(account.getUsername()) != null){
            msg = "Your name is occupied.";
            return false;
        }
        if(account.getPassword() == null || account.getPassword().trim().equals("")){
            msg = "Your password is required.";
            return false;
        }
        if (!account.getPassword().equals(repeatedPassword)){
            msg = "Two passwords do not match.";
            return false;
        }
        return true;
    }

    private void getParameters(HttpServletRequest request){
        this.account = new Account();
        account.setUsername(request.getParameter("username"));
        account.setPassword(request.getParameter("password"));
        repeatedPassword = request.getParameter("repeatedPassword");
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

    }

}
