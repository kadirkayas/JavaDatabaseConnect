import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.io.InputStream;
import java.util.Properties;

public class JDBCDemo {
    public static void main(String[] args) {

        Properties properties = new Properties();
        String url, username, password;

        try(InputStream inputStream= JDBCDemo.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(inputStream);
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.user");
            password = properties.getProperty("db.password");

            System.out.println("Database URL: " + url);
            System.out.println("Database User: " + username);
            System.out.println("Database Password: " + password);

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // install JDBC driver
            Class.forName("org.postgresql.Driver");
            // Database connection
            try(Connection connection = DriverManager.getConnection(url, username, password)){


//            String sqlInsert="INSERT INTO student VALUES(?,?,?)";
//            try(PreparedStatement statement = connection.prepareStatement(sqlInsert)) {
//                statement.setInt(1, 1);
//                statement.setString(2, "username");
//                statement.setInt(3, 34);
//                statement.executeUpdate();
//                System.out.println(statement.getUpdateCount());
//            }catch (SQLException e) {
//                e.printStackTrace();
//            }


//            String sqlUpdate="UPDATE student SET name = ? WHERE id = ?";
//            try(PreparedStatement statement = connection.prepareStatement(sqlUpdate)) {
//                statement.setString(1,"John");
//                statement.setInt(2,1);
//                statement.executeUpdate();
//                System.out.println("Update successful");
//            }catch (SQLException e){
//                e.printStackTrace();
//            }



//            String sqlDelete= "DELETE FROM student WHERE id = ?";
//            try(PreparedStatement statement = connection.prepareStatement(sqlDelete)){
//                statement.setInt(1, 1);
//                statement.executeUpdate();
//                System.out.println("Student deleted successfully");
//
//            }catch (SQLException e){
//                e.printStackTrace();
//            }

                String sqlSelect ="SELECT * FROM student WHERE id=? ";
                try(PreparedStatement statement = connection.prepareStatement(sqlSelect)) {
                    statement.setInt(1,3);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()){
                        System.out.println(
                                resultSet.getInt("id")+
                                resultSet.getString("name")+
                                resultSet.getInt("age")
                        );
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
    }
}
