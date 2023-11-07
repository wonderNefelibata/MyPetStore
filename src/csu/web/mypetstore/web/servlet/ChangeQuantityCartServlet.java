package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
@WebServlet(name = "ChangeQuantityCartServlet", value = "/changeQuantity")
public class ChangeQuantityCartServlet extends HttpServlet {

    private CartService cart = new CartService();
    private int quantity;
    private String itemId;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        cart = (CartService) session.getAttribute("cart");
        quantity = Integer.parseInt(request.getParameter("quantity"));
        itemId = request.getParameter("itemId");

        cart.setQuantityByItemId(itemId,quantity);//更新对应购物车内商品数量
        Iterator<CartItem> cartItems = cart.getAllCartItems();

        CartItem cartItem = new CartItem();
        while (cartItems.hasNext()){
            cartItem = (CartItem) cartItems.next();

            if(cartItem.getItem().getItemId().equals(itemId) )
                break;
        }

        //发送数据到服务端
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String str = "{\"subtotal\":" + cart.getSubTotal() + ",\"total\":" + cartItem.getTotal() + "}";
//        System.out.println(str);
        out.print(str);
        out.flush();
        out.close();
    }
}

