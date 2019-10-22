package DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registration {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public void insert(String name, String emailId, String password) {
        usersAlreadyExistsOrNot(emailId);
        String sql = "insert into users(name,emailId,password) VALUES(?,?,?)";
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


    private boolean usersAlreadyExistsOrNot(String emailId) {
        return true;
    }

}
