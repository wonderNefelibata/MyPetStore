package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewItemServlet extends HttpServlet {
    private static String VIEW_ITEM = "/WEB-INF/jsp/catalog/item.jsp";

    private String itemId;
    private String productId;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("itemId")!=null){
            itemId = req.getParameter("itemId");
        }
        else{
            itemId = req.getParameter("cartItemId");
        }
        if(req.getParameter("productId")!=null) {
            productId = req.getParameter("productId");
        }
        else {
            CatalogService service = new CatalogService();
            Item item = service.getItem(itemId);
            productId = item.getProduct().getProductId();
        }

        /*itemId = req.getParameter("itemId");
        productId = req.getParameter("productId");*/

        CatalogService service = new CatalogService();
        Item item = service.getItem(itemId);
        Product product = service.getProduct(productId);

        HttpSession session = req.getSession();
        session.setAttribute("item",item);
        session.setAttribute("product",product);

        //HttpSession session = request.getSession();
        Account loginAccount = (Account)session.getAttribute("loginAccount");

        if(loginAccount != null){
            // HttpServletRequest httpRequest= request;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() +req.getServletPath() + "?" + (req.getQueryString());

//            LogService logService = new LogService();
//            String logInfo = logService.logInfo(" ") + strBackUrl + " 查看具体动物 " + item + "的信息";
//            logService.insertLogInfo(loginAccount.getUsername(), logInfo);
        }


        req.getRequestDispatcher(VIEW_ITEM).forward(req,resp);
    }
}
