package DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDatabaseHelper {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public void insert(String name, String emailId, String password) {
        String sql = "insert into user(name,emailId,password) VALUES(?,?,?)";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, emailId);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
