package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="ViewOrderServlet",value="/viewOrder")
public class ViewOrderServlet extends HttpServlet {

    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";
    private int orderId;
    private String msg;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");

        OrderService orderService = new OrderService();

        Order order = (Order) session.getAttribute("order");
        if(request.getParameter("orderId")!=null){
            orderId = Integer.parseInt(request.getParameter("orderId"));
        }else {
            orderId = order.getOrderId();
        }
        order = orderService.getOrder(orderId);

        session.setAttribute("order",order);

        if(loginAccount.getUsername() != null && loginAccount.getUsername().equals(order.getUsername())){

            request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
        }
        else {
            order = null;
            msg = "You may only view your own orders.";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher(ERROR).forward(request,response);
        }
    }
}

