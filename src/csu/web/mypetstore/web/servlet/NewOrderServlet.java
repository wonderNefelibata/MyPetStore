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

public class NewOrderServlet extends HttpServlet {
    private static final String SHIPPING = "/WEB-INF/jsp/order/shippingForm.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/confirmOrderForm.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/error.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";


    private Order order;
    private boolean shoppingAddressRequired;
    private boolean confirmed;//默认为false
    private String msg;
    private String workingItemId;

    public Order getOrder(){
        return this.order;
    }
    public boolean isConfirmed(){
        return this.confirmed;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        shoppingAddressRequired = false;
        confirmed=false;

        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();
        order = (Order) session.getAttribute("order");

        if(request.getParameter("shippingAddressRequired") != null){
            shoppingAddressRequired = true;
        }

        if(request.getParameter("confirmed")!=null){
            confirmed = (request.getParameter("confirmed").equals("1"));
        }


        if(shoppingAddressRequired){
//            shoppingAddressRequired = false;
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
            Account loginAccount = (Account)session.getAttribute("loginAccount");
            String userid = loginAccount.getUsername();
            cart.removeAllCartItemsByUserid(userid);

            session.setAttribute("cart",cart);
            session.setAttribute("workingItemId",workingItemId);

            session.setAttribute("order",order);
//            //保存日志
//            if (account != null) {
//                HttpServletRequest httpRequest = request;
//                String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
//                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());
//
//                LogService logService = new LogService();
//                String logInfo = logService.logInfo(" ") + strBackUrl + " 完成了新订单";
//                logService.insertLogInfo(account.getUsername(), logInfo);
//            }

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

    public void getParameter(HttpServletRequest request,Order order){
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
