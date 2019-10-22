package DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public void insert(String name, String emailId, String password) {
        boolean result = usersAlreadyExistsOrNot(emailId);
        if (result) {
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
        System.out.println("done");
    }


    private boolean usersAlreadyExistsOrNot(String emailId) {
        String selectQuery = String.format("SELECT * FROM users WHERE emailId = '%s'", emailId);
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
