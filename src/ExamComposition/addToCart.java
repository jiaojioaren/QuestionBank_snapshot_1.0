package ExamComposition;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class addToCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println(id);
        HttpSession session = req.getSession();

        // Retrieve the list of IDs from the session
        List<String> idList = (List<String>) session.getAttribute("idList");

        // If the list doesn't exist, create a new one
        if (idList == null) {
            idList = new ArrayList<>();
            session.setAttribute("idList", idList);
        }

        // Add the new ID to the list
        idList.add(id);
    }
}
