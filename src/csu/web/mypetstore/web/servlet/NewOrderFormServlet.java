package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class NewOrderFormServlet extends HttpServlet {
//    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/newOrderForm.jsp";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        Account loginAccount = (Account) session.getAttribute("loginAccount");
//        if(loginAccount == null){
//            resp.sendRedirect("signonForm");
//        }else{
//            req.getRequestDispatcher(NEW_ORDER_FORM).forward(req,resp);
//        }
//    }

    private static final String ERROR = "/WEB-INF/jsp/common/error.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/signon.jsp";
    private static final String NEW_ORDER = "/WEB-INF/jsp/order/newOrderForm.jsp";

    private Order order;
    private boolean shippingAddressRequired;
    private boolean confirmed;
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
        Cart cart = (Cart) session.getAttribute("cart");
        workingItemId = (String) session.getAttribute("workingItemId");
        order = new Order();
        shippingAddressRequired = false;
        confirmed = false;
        orderList = null;
        session.setAttribute("order",order);

        if(loginAccount == null || loginAccount.getUsername() == null || !request.authenticate(response)){
            msg = "You must sign on before attempting to check out.  Please sign on and try checking out again.";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher(SIGNON).forward(request,response);
        }
        //初始化订单
        else if(cart != null) {
            order.initOrder(loginAccount,cart);
            session.setAttribute("order",order);

            //保存日志
//            if (account != null) {
//                HttpServletRequest httpRequest = request;
//                String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
//                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());
//
//                LogService logService = new LogService();
//                String logInfo = logService.logInfo(" ") + strBackUrl + " 跳转到新订单页面";
//                logService.insertLogInfo(account.getUsername(), logInfo);
//            }



            request.getRequestDispatcher(NEW_ORDER).forward(request,response);
        }
        else {
            msg = "An order could not be created because a cart could not be found.";
            request.setAttribute("msg",msg);

            if (loginAccount != null) {
                HttpServletRequest httpRequest = request;
//                String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
//                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());
//
//                LogService logService = new LogService();
//                String logInfo = logService.logInfo(" ") + strBackUrl + " 生成订单页面信息错误";
//                logService.insertLogInfo(account.getUsername(), logInfo);

                request.getRequestDispatcher(ERROR).forward(request,response);
            }
        }
    }
}
