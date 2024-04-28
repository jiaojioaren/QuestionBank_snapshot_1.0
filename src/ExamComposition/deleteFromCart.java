package ExamComposition;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class deleteFromCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteFromCart = req.getParameter("deleteItem");
        HttpSession session = req.getSession();

        // 获取存储在会话中的 idList
        List<String> idList = (List<String>) session.getAttribute("idList");

        // 如果 idList 为空，则创建一个新的空列表
        if (idList == null) {
            idList = new ArrayList<>();
        } else {
            // 在 idList 中查找要删除的项，并将其移除
            idList.remove(deleteFromCart);
        }

        // 将更新后的 idList 重新设置到会话中
        session.setAttribute("idList", idList);

        resp.sendRedirect("ExamComposition/cart.jsp");
    }
}
