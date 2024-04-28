package Test;

import java.sql.*;

public class BankOperate {
    // 数据库连接信息
    static String url = "jdbc:mysql://localhost:3306/QuestionBankDemo";
    static String dbName = "";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String userName = "sa";
    static String password = "password";
    static Connection conn = null;
    static PreparedStatement stmt = null;
    static ResultSet rs = null;

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url , userName, password);
            printTableData("MultipleChoice");

        } catch (SQLException e) {
            System.out.println("更新数据失败：" + e.getMessage());
        }

    }

    public static void InsertAllDataToMultipleChoice() throws ClassNotFoundException {
        //InsertDataToMainTable();
        Class.forName(driver);

        // 打开一个连接

        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(url , userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        InsertDataToMultipleChoice("1", "以下哪种动物是被称为“陆地之王”？", "A. 老虎\nB. 狮子\nC. 大象\nD. 熊", "B");
        InsertDataToMultipleChoice("2", "哪种动物是世界上体型最大的陆地动物？", "A. 大象\nB. 河马\nC. 长颈鹿\nD. 鲸鱼", "A");
        InsertDataToMultipleChoice("3", "下列哪种动物是唯一能飞的哺乳动物？", "A. 狐獴\nB. 树懒\nC. 蝙蝠\nD. 袋鼠", "C");
        InsertDataToMultipleChoice("4", "以下哪种动物被称为“驼鹿”？", "A. 长颈鹿\nB. 骆驼\nC. 鹿\nD. 鸟类", "A");
        InsertDataToMultipleChoice("5", "哪种动物被称为“树懒”？", "A. 猴子\nB. 猫科动物\nC. 海洋哺乳动物\nD. 爬行动物", "B");
        InsertDataToMultipleChoice("6", "以下哪种动物是非常珍贵且濒临灭绝的？", "A. 熊猫\nB. 猴子\nC. 狮子\nD. 老虎", "A");
        InsertDataToMultipleChoice("7", "哪种动物拥有“黑白”斑纹？", "A. 熊\nB. 大象\nC. 斑马\nD. 鲨鱼", "C");
        InsertDataToMultipleChoice("8", "以下哪种动物是属于水生哺乳动物？", "A. 海豚\nB. 海狮\nC. 海龟\nD. 海象", "A");
        InsertDataToMultipleChoice("9", "哪种动物以其长长的颈部而闻名？", "A. 马\nB. 大象\nC. 长颈鹿\nD. 猩猩", "C");
        InsertDataToMultipleChoice("10", "哪种动物以其智力和记忆力而著名？", "A. 狗\nB. 猩猩\nC. 狐狸\nD. 大熊猫", "B");
        printTableData("MultipleChoice");
    }

    public static void InsertDataToMultipleChoice(String Id, String questionStem, String questionOptions, String questionAnswer){
        //使用该函数需要在main函数中提前初始化stmt, conn等
        try {
            // 创建 SQL 插入语句，使用占位符 '?' 表示参数
            String sql = "INSERT INTO MultipleChoice (Id, questionStem, questionOptions, questionAnswer) VALUES (?, ?, ?, ?)";

            // 创建 PreparedStatement 对象
            stmt = conn.prepareStatement(sql);

            // 设置占位符的值
            stmt.setString(1, Id);
            stmt.setString(2, questionStem);
            stmt.setString(3, questionOptions);
            stmt.setString(4, questionAnswer);

            // 执行 SQL 插入语句
            stmt.executeUpdate();

            System.out.println("数据插入成功！");
        } catch (SQLException e) {
            System.out.println("数据插入失败：" + e.getMessage());
        }

    }

    public static void printTableData(String tableName) {
        // SQL query to select all data from the table
        String query = "SELECT * FROM " + tableName;

        try (
                // Establishing a connection to the database
                Connection connection = DriverManager.getConnection(url, userName, password);
                // Creating a statement to execute the query
                Statement statement = connection.createStatement();
                // Executing the query and retrieving the result set
                ResultSet resultSet = statement.executeQuery(query);
        ) {
            // Printing the column names
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
            }
            System.out.println();

            // Printing the data
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void InsertDataToMainTable(){

        String insertQuery = "INSERT INTO MainTable (questionTypes, knowledgeChapters, questionSection) VALUES (?, ?, ?)";
        try (
                // Establishing a connection to the database
                Connection connection = DriverManager.getConnection(url, userName, password);
                // Creating a PreparedStatement to execute the insert query
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {
            // Inserting data for each row
            String[][] data = {
                    {"选择题", "1", "1"},
                    {"选择题", "1", "2"},
                    {"选择题", "1", "3"},
                    {"选择题", "1", "4"},
                    {"选择题", "1", "1"},
                    {"选择题", "1", "2"},
                    {"选择题", "1", "3"},
                    {"选择题", "1", "4"},
                    {"选择题", "1", "1"},
                    {"选择题", "1", "2"},
                    {"填空题", "1", "3"},
                    {"填空题", "1", "4"},
                    {"填空题", "1", "1"},
                    {"判断题", "1", "2"},
                    {"判断题", "1", "3"},
                    {"判断题", "1", "4"},
                    {"简答题", "1", "1"},
                    {"简答题", "1", "2"},
                    {"简答题", "1", "3"},
                    {"简答题", "1", "4"}
            };

            for (String[] row : data) {
                // Setting parameters for the prepared statement
                preparedStatement.setString(1, row[0]);
                preparedStatement.setInt(2, Integer.parseInt(row[1]));
                preparedStatement.setInt(3, Integer.parseInt(row[2]));

                // Executing the insert statement
                preparedStatement.executeUpdate();
            }

            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void CreateTables(){
        Connection conn = null;
        Statement stmt = null;

        try {
            // 注册JDBC驱动
            Class.forName(driver);

            // 打开一个连接
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(url, userName, password);

/*            // 创建数据库
            System.out.println("Creating database...");

            String sql = "CREATE DATABASE " + dbName;
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully");

            // 切换到新创建的数据库
            conn.setCatalog(dbName);*/

            // 创建主表
            stmt = conn.createStatement();
            System.out.println("Creating MainTable...");
            String sql = "CREATE TABLE MainTable (" +
                    "Id INT PRIMARY KEY AUTO_INCREMENT," +
                    "questionTypes VARCHAR(50)," +
                    "knowledgeChapters VARCHAR(50)," +
                    "questionSection VARCHAR(50)" +
                    ")";
            stmt.executeUpdate("USE QuestionBankDemo");

            stmt.executeUpdate(sql);
            System.out.println("MainTable created successfully");

            // 创建选择题表
            System.out.println("Creating MultipleChoice table...");
            sql = "CREATE TABLE MultipleChoice (" +
                    "Id INT PRIMARY KEY AUTO_INCREMENT," +
                    "MainTableId INT," +
                    "questionStem TEXT," +
                    "questionOptions TEXT," +
                    "questionAnswer VARCHAR(255)," +
                    "FOREIGN KEY (MainTableId) REFERENCES MainTable(Id)" +
                    ")";
            stmt.executeUpdate(sql);
            System.out.println("MultipleChoice table created successfully");

            // 创建填空题表
            System.out.println("Creating FillInTheBlanks table...");
            sql = "CREATE TABLE FillInTheBlanks (" +
                    "Id INT PRIMARY KEY AUTO_INCREMENT," +
                    "MainTableId INT," +
                    "questionStem TEXT," +
                    "questionAnswer TEXT," +
                    "FOREIGN KEY (MainTableId) REFERENCES MainTable(Id)" +
                    ")";
            stmt.executeUpdate(sql);
            System.out.println("FillInTheBlanks table created successfully");

            // 创建简答题表
            System.out.println("Creating ShortAnswer table...");
            sql = "CREATE TABLE ShortAnswer (" +
                    "Id INT PRIMARY KEY AUTO_INCREMENT," +
                    "MainTableId INT," +
                    "questionStem TEXT," +
                    "questionAnswer TEXT," +
                    "FOREIGN KEY (MainTableId) REFERENCES MainTable(Id)" +
                    ")";
            stmt.executeUpdate(sql);
            System.out.println("ShortAnswer table created successfully");

            // 创建判断题表
            System.out.println("Creating TrueOrFalse table...");
            sql = "CREATE TABLE TrueOrFalse (" +
                    "Id INT PRIMARY KEY AUTO_INCREMENT," +
                    "MainTableId INT," +
                    "questionStem TEXT," +
                    "questionAnswer VARCHAR(5)," +
                    "FOREIGN KEY (MainTableId) REFERENCES MainTable(Id)" +
                    ")";
            stmt.executeUpdate(sql);
            System.out.println("TrueOrFalse table created successfully");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
