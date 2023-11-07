package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class RemoveCartItemServlet extends HttpServlet {
    private static String VIEW_CART = "/WEB-INF/jsp/cart/cart.jsp";
    private static String ERROR = "/WEB-INF/jsp/common/error.jsp";

    private String workingItemId;
    private CartService cart;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId = req.getParameter("workingItemId");

        HttpSession session = req.getSession();
        session.setAttribute("workingItemId",workingItemId);
        cart = (CartService) session.getAttribute("cart");

        Item item = cart.removeItemById(workingItemId);

        Account account = (Account)session.getAttribute("loginAccount");
        if(item == null){
            session.setAttribute("msg","Attempted to remove null CartItem from Cart");
            req.getRequestDispatcher(ERROR).forward(req,resp);
        } else {
            req.getRequestDispatcher(VIEW_CART).forward(req,resp);
        }
    }
}
