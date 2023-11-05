package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewProductServlet extends HttpServlet {
    private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/product.jsp";
    private CatalogService catalogService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catalogService=new CatalogService();

        String productId = req.getParameter("productId");

        List<Item> itemList = catalogService.getItemListByProduct(productId);

        Product product = catalogService.getProduct(productId);

        HttpSession session= req.getSession();
        session.setAttribute("itemList",itemList);
        session.setAttribute("product",product);
        req.getRequestDispatcher(VIEW_PRODUCT).forward(req,resp);
    }
}
