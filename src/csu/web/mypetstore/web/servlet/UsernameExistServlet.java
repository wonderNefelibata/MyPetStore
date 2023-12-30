package csu.web.mypetstore.web.servlet;

//import com.alibaba.fastjson2.modules.ObjectReaderModule;

import com.alibaba.fastjson.JSON;
import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UsernameExistServlet", value = "/UsernameExist")
public class UsernameExistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        AccountService accountService = new AccountService();
        Account account = accountService.getAccount(username);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        accountRes accountRes = new accountRes();

        if(account == null){
            accountRes.setCode(0);
            accountRes.setMsg("Not Exist");
        }
        else {
            accountRes.setCode(1);
            accountRes.setMsg("Exist");
        }
        String str = JSON.toJSONString(accountRes);
        out.print(str);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

class accountRes {
    private int code;//状态码
    private String msg;//信息


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
