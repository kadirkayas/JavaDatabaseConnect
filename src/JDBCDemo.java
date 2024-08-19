import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/jdbcdemo";
        String username="root";
        String password="";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement= connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select * From student");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)+ " " +
                        resultSet.getString("name")+ " "+
                        resultSet.getInt("age"));
            }
            connection.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }
    }
}