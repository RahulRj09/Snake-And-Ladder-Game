package DatabaseHelper;

import authentication.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationDatabaseHelper {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    public void insert(Registration registration) {
        String sql = "insert into user(name,emailId,password) VALUES(?,?,?)";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, registration.getName());
            preparedStatement.setString(2, registration.getEmailId());
            preparedStatement.setString(3, registration.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
