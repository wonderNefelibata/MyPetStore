package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.CartService;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//@WebServlet(name = "NewOrderServlet", value = "/newOrder")
public class NewOrderServlet extends HttpServlet {
    private static final String ERROR = "/WEB-INF/jsp/common/error.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";

    private String msg;
    private String workingItemId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();
        Order order = (Order) session.getAttribute("order");
        getParameter(request,order);//把request中的订单的参数传到这个order对象中
        session.setAttribute("order",order);
        orderService.insertOrder(order);//把订单放进数据库
        CartService cart = (CartService) session.getAttribute("cart");
        workingItemId = (String) session.getAttribute("workingItemId");
        cart.clear();
        //清空购物车
        Account loginAccount = (Account)session.getAttribute("loginAccount");
        String userid = loginAccount.getUsername();
        cart.removeAllCartItemsByUserid(userid);
        session.setAttribute("cart",cart);
        session.setAttribute("workingItemId",workingItemId);
        session.setAttribute("order",order);
        request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
    }

    public void getParameter(HttpServletRequest request,Order order){
        order.setOrderDate(order.getOrderDate());
        order.setCardType(request.getParameter("order.cardType"));
        order.setCreditCard(request.getParameter("order.creditCard"));
        order.setExpiryDate(request.getParameter("order.expiryDate"));
        order.setBillToFirstName(request.getParameter("order.billToFirstName"));
        order.setBillToLastName(request.getParameter("order.billToLastName"));
        order.setBillAddress1(request.getParameter("order.billAddress1"));
        order.setBillAddress2(request.getParameter("order.billAddress2"));
        order.setBillCity(request.getParameter("order.billCity"));
        order.setBillState(request.getParameter("order.billState"));
        order.setBillZip(request.getParameter("order.billZip"));
        order.setBillCountry(request.getParameter("order.billCountry"));
    }
}

