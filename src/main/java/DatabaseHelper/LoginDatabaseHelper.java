package DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDatabaseHelper {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public boolean checkEmailForLogin(String emailId, String password) throws SQLException {
        String sql = String.format("SELECT emailId, password FROM users WHERE emailId = '%s' and password = '%s'", emailId, password);
        try (
                Connection conn = databaseConnection.connect();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }
}
