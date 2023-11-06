package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOrdersServlet extends HttpServlet {
    private static final String LIST_ORDERS = "/WEB-INF/jsp/order/listOrders.jsp";
    private List<Order> orderList = new ArrayList<Order>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();

        Account account = (Account) session.getAttribute("loginAccount");

        orderList = orderService.getOrdersByUsername(account.getUsername());
        session.setAttribute("orderList",orderList);

        if(account != null){
            HttpServletRequest httpRequest= request;
            String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

//            LogService logService = new LogService();
//            String logInfo = logService.logInfo(" ") + strBackUrl + " 查看订单 " + orderList;
//            logService.insertLogInfo(account.getUsername(), logInfo);
        }

        request.getRequestDispatcher(LIST_ORDERS).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
