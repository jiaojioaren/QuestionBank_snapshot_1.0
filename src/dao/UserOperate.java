package dao;

import java.sql.*;

public class UserOperate {
    static String url = "jdbc:mysql://localhost:3306/QBMS";
    static String DBuser = "sa";
    static String DBpassword = "password";

    static Connection connection = null;
    static PreparedStatement statement = null;
    static ResultSet resultSet = null;



    public void connect2DB(){
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            connection = DriverManager.getConnection(url, DBuser, DBpassword);
            /*连接数据库*/
            System.out.println("成功连接到数据库!");
        } catch (SQLException e) {
            System.err.println("连接数据库时发生错误: " + e.getMessage());
        }
    }

    public void closeDB(){
        if(connection != null){
            try{connection.close();}
            catch(Exception e){e.printStackTrace();}
        }

        if(statement != null){
            try{statement.close();}
            catch(Exception e){e.printStackTrace();}
        }

        if(resultSet != null){
            try{resultSet.close();}
            catch(Exception e){e.printStackTrace();}
        }
    }

    public void userAdd(String name, String password){
        String sql = "insert into Users(username,password) values(?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);

            //执行sql语句
            int rowsInserted = statement.executeUpdate();

            if(rowsInserted > 0){
                System.out.println("数据添加成功");
            }
            else {
                System.out.println("数据添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void userDelete(String name){
        String sql = "delete from Users where username = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            // 执行sql语句
            int rowsDeleted = statement.executeUpdate();

            if(rowsDeleted > 0){
                System.out.println("数据删除成功");
            } else {
                System.out.println("没有找到该用户，因此无法删除");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void userFindAll() {
        String sql = "SELECT * FROM Users";
        try {
            statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No users found in the database");
            } else {
                System.out.println("Users found in the database:");
                while (resultSet.next()) {
                    System.out.println("Username: " + resultSet.getString("username"));
                    System.out.println("Password: " + resultSet.getString("password"));
                    System.out.println("----------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean userFind(String name, String password){
        String sql = "select * from Users where username = ? and password = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);

            // 执行sql语句
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                String foundName = resultSet.getString("username");
                String foundPassword = resultSet.getString("password");

                System.out.println("用户名: " + foundName);
                System.out.println("密码: " + foundPassword);
                return true;
            } else {
                System.out.println("未找到该用户");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean userFindByName(String name){
        String sql = "select * from Users where username = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            // 执行sql语句
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                String foundName = resultSet.getString("username");
                String foundPassword = resultSet.getString("password");

                System.out.println("该用户名已被注册");
                return true;
            } else {
                System.out.println("该用户名可用");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
