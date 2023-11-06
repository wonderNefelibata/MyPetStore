package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditAccountFormServlet", value = "/editAccountForm")
public class EditAccountFormServlet extends HttpServlet {

    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/editAccount.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");

        request.getRequestDispatcher(EDIT_ACCOUNT).forward(request,response);

    }
}