package DatabaseHelper;

import java.sql.*;

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
    public void tableTruncate() throws SQLException {
        Connection conn = databaseConnection.connect();
        Statement statement = conn.createStatement();
        statement.executeUpdate("TRUNCATE gameCurrentState");
    }
}
