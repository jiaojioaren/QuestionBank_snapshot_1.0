package ExamComposition;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class showCart extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();

        // Retrieve the list of IDs from the session
        List<String> idList = (List<String>) session.getAttribute("idList");

        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();

        if (idList != null && !idList.isEmpty()) {
            out.println("IDs stored in the session:");
            for (String id : idList) {
                out.println(id);
            }
        } else {
            out.println("No IDs stored in the session.");
        }

        out.close();
    }
}
