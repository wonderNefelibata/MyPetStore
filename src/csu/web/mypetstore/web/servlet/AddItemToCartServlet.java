package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CartService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//package csu.web.mypetstore.web.servlet;
//
//import csu.web.mypetstore.domain.Cart;
//import csu.web.mypetstore.domain.Item;
//import csu.web.mypetstore.service.CatalogService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class AddItemToCartServlet extends HttpServlet {
//    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String workingItemId = req.getParameter("workingItemId");
//
//        HttpSession session = req.getSession();
//        Cart cart = (Cart) session.getAttribute("cart");
//
//        if(cart == null){
//            cart = new Cart();
//        }
//
//        if (cart.containsItemId(workingItemId)) {
//            cart.incrementQuantityByItemId(workingItemId);
//        } else {
//            CatalogService catalogService = new CatalogService();
//            boolean isInStock = catalogService.isItemInStock(workingItemId);
//            Item item = catalogService.getItem(workingItemId);
//            cart.addItem(item, isInStock);
//        }
//
//        session.setAttribute("cart", cart);
//        req.getRequestDispatcher(CART_FORM).forward(req, resp);
//    }
//}
public class AddItemToCartServlet extends HttpServlet {
    //1. 处理完请求后的跳转页面
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/cart.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/signon.jsp";
    //2. 定义处理该请求所需要的数据
    private String workingItemId;
    private CartService cart;

    //3. 是否需要调用业务逻辑层
    private CatalogService catalogService = new CatalogService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");

        HttpSession session = req.getSession();
        cart = (CartService) session.getAttribute("cart");

        if ((cart == null)){
            cart = new CartService();
        }
        session.setAttribute("workingItemId",workingItemId);
        Account account = (Account)session.getAttribute("loginAccount");
        String userid = account.getUsername();
        session.setAttribute("cart",cart);
        req.getRequestDispatcher(VIEW_CART).forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");

        HttpSession session = req.getSession();
        cart = (CartService) session.getAttribute("cart");

        if ((cart == null)){
            cart = new CartService();
        }

        session.setAttribute("workingItemId",workingItemId);
        Account account = (Account)session.getAttribute("loginAccount");
        if (account == null) {
            req.setAttribute("message", "请先登录");
            req.getRequestDispatcher(SIGNON).forward(req,resp);
        }
        String userid = account.getUsername();

        if(cart.containsItemId(workingItemId)){
            cart.incrementQuantityByItemId(workingItemId);
        }
        else {
            catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock, userid);
        }
        session.setAttribute("cart",cart);
        req.getRequestDispatcher(VIEW_CART).forward(req,resp);
    }
}
