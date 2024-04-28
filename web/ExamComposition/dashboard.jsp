<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>显示数据库中的数据</title>
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
</head>
<body>

<h1>选择题：</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>章</th>
        <th>节</th>
        <th>题干</th>
        <th>选项</th>
        <th>答案</th>
        <th>加入试题篮</th>
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
            String sql = "SELECT * FROM MultipleChoice JOIN MainTable ON MultipleChoice.MainTableId = MainTable.id";
            rs = stmt.executeQuery(sql);

            // 遍历结果集并输出表格行
            while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("Id") %></td>
        <td><%= rs.getString("knowledgeChapters") %></td>
        <td><%= rs.getString("questionSection") %></td>
        <td><%= rs.getString("questionStem") %></td>
        <td><%= rs.getString("questionOptions") %></td>
        <td><%= rs.getString("questionAnswer") %></td>
        <td>
            <center>
                <a href="#" class="btn" onclick="addToCart('<%= rs.getString("Id") %>')"> </a>
            </center>
        </td>
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
<center>
    <a class="btn" href="cart.jsp">查看试题篮</a>
</center>
</body>
<script>
    // 添加至题目篮的函数
    function addToCart(questionId) {
        // 创建一个 XMLHTTPRequest 对象
        var xhr = new XMLHttpRequest();

        // 准备发送的数据
        var data = "id=" + questionId;

        // 设置请求方法和 URL
        xhr.open("POST", "/QuestionBankManagement_Web_exploded/addToCart", true);

        // 设置请求头
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        // 定义请求完成后的回调函数
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // 请求成功，可以根据需要处理响应
                console.log("题目已添加至题目篮");
            }
        };

        // 发送请求
        xhr.send(data);
    }
</script>
</html>
