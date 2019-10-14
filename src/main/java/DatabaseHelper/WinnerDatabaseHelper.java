package DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinnerDatabaseHelper {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public String getWinner() {
        String emailId = "";
        String selectQuery = String.format("SELECT * FROM gameCurrentState WHERE position = '%d'", 100);
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emailId += resultSet.getString("emailId");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return emailId;
    }
}
