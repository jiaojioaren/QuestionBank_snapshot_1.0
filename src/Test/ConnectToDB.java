package Test;


import java.sql.*;

public class ConnectToDB {
    public static void main(String[] args) {
        // MySQL数据库连接信息
        final String url = "jdbc:mysql://localhost:3306/QBMS";
        final String user = "sa";
        final String password = "password";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        // 尝试建立连接
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            connection = DriverManager.getConnection(url, user, password);
            /*连接数据库*/
            System.out.println("成功连接到数据库!");
            /*实例化statement对象*/
            statement = connection.createStatement();

/*            String createDatabase = "CREATE DATABASE IF NOT EXISTS QBMS";//创建数据库

            statement.executeUpdate(createDatabase);
            System.out.println("数据库创建成功");*/

/*            String sql_1 = "CREATE TABLE Users (\n" +
                    "    user_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    username VARCHAR(50) NOT NULL,\n" +
                    "    password VARCHAR(50) NOT NULL\n" +
                    ");\n";//创建数据表，其中用户id为主键且自增


            statement.executeUpdate(sql_1);
            System.out.println("数据表创建成功");*/

/*            String sql_2 = "INSERT INTO Users (username, password) VALUES \n" +
                    "('Alice', 'password123'),\n" +
                    "('Bob', 'password456'),\n" +
                    "('Charlie', 'password789'),\n" +
                    "('David', 'password101'),\n" +
                    "('Eva', 'password102'),\n" +
                    "('Fiona', 'password103'),\n" +
                    "('George', 'password104'),\n" +
                    "('Hannah', 'password105'),\n" +
                    "('Ian', 'password106'),\n" +
                    "('Julia', 'password107');\n";//插入10条数据

            statement.executeUpdate(sql_2);
            System.out.println("数据插入成功");*/

            String sql_3 = "SELECT * FROM Users";


            resultSet = statement.executeQuery(sql_3);

            boolean flag = resultSet.next();
            while (flag) {
                System.out.print(resultSet.getString("user_id"));
                System.out.print(resultSet.getString("username"));
                System.out.println(resultSet.getString("password"));
                flag = resultSet.next();
            }

        } catch (SQLException e) {
            System.err.println("连接数据库时发生错误: " + e.getMessage());
        }
        finally {
            try{connection.close();}
            catch(Exception e){e.printStackTrace();}
            try{statement.close();}
            catch(Exception e){e.printStackTrace();}
            try{resultSet.close();}
            catch(Exception e){e.printStackTrace();}
        }
    }
}

