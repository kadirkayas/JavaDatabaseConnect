import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://kala.db.elephantsql.com:5432/iknbhltc";
        String username = "iknbhltc";
        String password = "8RZUarK1Z6HEYADkc5MTVxEYDGd-m8WE";

        try {
            // PostgreSQL JDBC driver'ını yükle
            Class.forName("org.postgresql.Driver");

            // Veritabanına bağlantı oluştur
            Connection connection = DriverManager.getConnection(url, username, password);

            // SQL sorgusu oluştur
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            // Sonuçları işle
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " +
                        resultSet.getString("name") + " " +
                        resultSet.getInt("age"));
            }

            // Bağlantıyı kapat
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
