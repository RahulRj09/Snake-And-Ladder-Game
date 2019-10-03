package DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDatabaseHelper {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public void getUserDetails(String emailId) {
        String sql = "SELECT * FROM user WHERE emailId = '" + emailId +"'";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
             ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()){
                 System.out.println(resultSet.getString("emailId"));
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
