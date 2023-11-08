package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NewOrderFormServlet", value = "/newOrderForm")
public class NewOrderFormServlet extends HttpServlet {
    private static final String ERROR = "/WEB-INF/jsp/common/error.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/signon.jsp";
    private static final String NEW_ORDER = "/WEB-INF/jsp/order/newOrderForm.jsp";

    private Order order;
    private List<Order> orderList;
    private String msg;
    private String workingItemId;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        CartService cart = (CartService) session.getAttribute("cart");
        workingItemId = (String) session.getAttribute("workingItemId");
        order = new Order();
        orderList = null;
        session.setAttribute("order",order);

        //如果没有登录转到登录
        if(loginAccount == null || loginAccount.getUsername() == null){
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }
        //初始化订单
        else if(cart != null) {
            order.initOrder(loginAccount,cart);
            session.setAttribute("order",order);
            request.getRequestDispatcher(NEW_ORDER).forward(request,response);
        }
        else {
            msg = "An order could not be created because a cart could not be found.";
            request.setAttribute("msg",msg);
            if (loginAccount != null) {
                request.getRequestDispatcher(ERROR).forward(request,response);
            }
        }
    }
}
