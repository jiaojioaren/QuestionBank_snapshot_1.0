<%--
  Created by IntelliJ IDEA.
  User: jiang jr
  Date: 2024/4/28
  Time: 下午5:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>

<html>
  <head>
    <title>试题篮</title>
  </head>
  <style>
    body {
      font-family: Arial, sans-serif;
      text-align: center;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }
    .container {
      margin-top: 20vh;
    }
    h1 {
      color: #333;
    }
    table {
      width: 80%;
      margin: 20px auto;
      border-collapse: collapse;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    th, td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }
    th {
      background-color: #007bff;
      color: #fff;
    }
    tr:nth-child(even) {
      background-color: #f2f2f2;
    }
    tr:hover {
      background-color: #ddd;
    }
    .btn {
      display: inline-block;
      padding: 10px 20px;
      background-color: #007bff;
      color: #fff;
      text-decoration: none;
      border-radius: 5px;
      margin-top: 20px;
      transition: background-color 0.3s ease;
    }
    .btn:hover {
      background-color: #0056b3;
    }

  </style>
  <body>

  <table border="1">
    <tr>
      <th>Id</th>
      <th>题干</th>
      <th>选项</th>
      <th>答案</th>
      <th>从试题篮中删除</th>
    </tr>
    <%
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

        List<String> idList = (List<String>) session.getAttribute("idList");
        stmt = conn.prepareStatement(sql);
        for (String str : idList) {
          stmt.setString(1, str);
          // 执行查询语句
          rs = stmt.executeQuery();
          // 处理查询结果
          while (rs.next()) {
    %>
    <tr>
    <tr>
      <td><%= rs.getString("Id") %></td>
      <td><%= rs.getString("questionStem") %></td>
      <td><%= rs.getString("questionOptions") %></td>
      <td><%= rs.getString("questionAnswer") %></td>
      <td><a class="btn" href="/QuestionBankManagement_Web_exploded/delete?deleteItem=<%= rs.getString("Id") %>">Delete</a></td>
    </tr>

    </tr>
    <%
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
    %>
  </table>
  </body>
</html>
