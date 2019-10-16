package DatabaseHelper;

import java.sql.*;

public class Winner {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public String getWinner() {
        String name = "";
        String selectQuery = String.format("SELECT * FROM gameCurrentState WHERE position = '%d'", 100);
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String emailId = resultSet.getString("emailId");
                String query = String.format("SELECT * FROM users WHERE emailId = '%s'", emailId);
                PreparedStatement preparedStatement1 = conn.prepareStatement(query);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()) {
                    name += resultSet1.getString("name");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    public void tableTruncate() throws SQLException {
        Connection conn = databaseConnection.connect();
        Statement statement = conn.createStatement();
        statement.executeUpdate("TRUNCATE gameCurrentState");
    }
}
