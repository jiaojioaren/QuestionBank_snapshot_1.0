package Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class addToCartTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 数据库连接信息
        String url = "jdbc:mysql://localhost:3306/QuestionBankDemo";
        String username = "sa";
        String password = "password";

        // 声明数据库连接和查询语句对象
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 连接数据库
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM MultipleChoice WHERE Id = ?";
            HttpSession session = req.getSession();

            // 创建一个List<String>
            List<String> idList = new ArrayList<>();

            // 添加元素到List
            idList.add("1");
            idList.add("2");

            stmt = conn.prepareStatement(sql);

            for (String str : idList) {
                stmt.setString(1, str);
                // 执行查询语句
                rs = stmt.executeQuery();
                // 处理查询结果
                while (rs.next()) {
                    // 你可以根据需要获取结果集中的字段值
                    String question = rs.getString("Id");
                    // 例如，这里将问题打印出来
                    System.out.println(question);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭数据库连接
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
