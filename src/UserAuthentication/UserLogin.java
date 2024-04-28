package UserAuthentication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLogin extends HttpServlet {
    UserOperate userOperate = new UserOperate();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        userOperate.connect2DB();
        if(userOperate.userFind(username, password)){
            Cookie user = new Cookie("username", username);
            response.addCookie(user);
        }
        userOperate.closeDB();

        response.sendRedirect("UserAuthentication/LoginSucess.html");
    }
}
