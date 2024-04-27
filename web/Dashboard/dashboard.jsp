<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>显示数据库中的数据</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<h1>数据库中的数据：</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>题干</th>
        <th>选项</th>
        <th>答案</th>
    </tr>
    <%
        // 数据库连接信息
        String url = "jdbc:mysql://localhost:3306/QuestionBankDemo";
        String username = "sa";
        String password = "password";

        // 声明数据库连接和查询语句对象
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 连接数据库
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);

            // 执行查询语句
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MultipleChoice";
            rs = stmt.executeQuery(sql);

            // 遍历结果集并输出表格行
            while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("Id") %></td>
        <td><%= rs.getString("questionStem") %></td>
        <td><%= rs.getString("questionOptions") %></td>
        <td><%= rs.getString("questionAnswer") %></td>
    </tr>
    <%
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
    %>
</table>
</body>
</html>
