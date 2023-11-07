package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.CatalogService;
import csu.web.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductServlet extends HttpServlet {
    private CatalogService catalogService;
    private String keyword;
    private static final String SEARCH_PRODUCTS = "/WEB-INF/jsp/catalog/searchProducts.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        keyword=req.getParameter("keyword");
        catalogService = new CatalogService();
        List<Product> productlist = catalogService.searchProductList(keyword);

        HttpSession session = req.getSession();
        session.setAttribute("productList", productlist);

        Account account = (Account)session.getAttribute("account");
        req.getRequestDispatcher(SEARCH_PRODUCTS).forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}

