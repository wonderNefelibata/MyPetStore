package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 这个servlet好像没有用
 */
public class ConfirmOrderFormServlet extends HttpServlet {
    private static final String SHIPPING = "/WEB-INF/jsp/order/shippingForm.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/confirmOrderForm.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";


    private Order order;
    private boolean shoppingAddressRequired;
    private boolean confirmed;//默认为false
    private String msg;
    private String workingItemId;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();
        order=new Order();

        /**
         * 用户第一次登录，session中的order为null
         */
//        order = (Order) session.getAttribute("order");

        getParameter(request,order);

        session.setAttribute("order",order);

        if(request.getParameter("shippingAddressRequired") != null){
            shoppingAddressRequired = true;
        }


        if(request.getParameter("confirmed")!=null){
            confirmed = (request.getParameter("confirmed").equals("1"));
        }

        if(shoppingAddressRequired){
            shoppingAddressRequired = false;
            request.getRequestDispatcher(SHIPPING).forward(request,response);
        }
        else if(!isConfirmed()){
            request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);
        }

        else if(getOrder() != null){
            orderService.insertOrder(order);

            Cart cart = (Cart) session.getAttribute("cart");

            //清空购物车
            workingItemId = (String) session.getAttribute("workingItemId");
            cart.clear(cart,workingItemId);
            Account account = (Account)session.getAttribute("account");
            String userid = account.getUsername();
            cart.removeAllCartItemsByUserid(userid);

            session.setAttribute("cart",cart);
            session.setAttribute("workingItemId",workingItemId);

            session.setAttribute("order",order);

            msg = "Thank you, your order has been submitted.";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
        }
        else {
            msg = "An error occurred processing your order (order was null).";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher(ERROR).forward(request,response);
        }

    }

    public Order getOrder() {
        return this.order;
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }

    public void getParameter(HttpServletRequest request, Order order) {
        order.setOrderDate(order.getOrderDate());
        order.setCardType(request.getParameter("order.creditCard"));
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
