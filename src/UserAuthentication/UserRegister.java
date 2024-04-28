package UserAuthentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserOperate userOperate = new UserOperate();
        userOperate.connect2DB();
        boolean flag = userOperate.userFindByName(username);
        if(flag){
            resp.sendRedirect("register.html");
        }
        else {
            userOperate.userAdd(username, password);
            resp.sendRedirect("login.html");
        }
        userOperate.closeDB();
    }
}
