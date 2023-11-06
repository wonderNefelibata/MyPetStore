package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.common.Captcha;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Captcha captcha = new Captcha();
        BufferedImage bfi = captcha.getImage();
        OutputStream os = response.getOutputStream();
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captcha.getText());
        ImageIO.write(bfi, "png", os);
        os.close();
    }
}