package DatabaseHelper;

import org.json.simple.JSONObject;

import java.sql.*;

public class TokenDatabaseHelper {
    DatabaseConnection databaseConnection = new DatabaseConnection();


    public void updatePosition(String emailId, int position) {
        String selectQuery = "SELECT * FROM gameCurrentState WHERE emailId = '" + emailId + "'";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String updateQuery = "UPDATE gameCurrentState SET position=? WHERE emailId=?";
                PreparedStatement preparedStatement1 = conn.prepareStatement(updateQuery);
                preparedStatement1.setInt(1, position);
                preparedStatement1.setString(2, emailId);
                preparedStatement1.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getCurrentPosition(String emailId) {
        JSONObject currentPosition = new JSONObject();
        String selectQuery = "SELECT * FROM gameCurrentState WHERE emailId = '" + emailId + "'";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                currentPosition.put("position", resultSet.getInt("position"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return currentPosition;
    }

    public void tableTruncate() throws SQLException {
        Connection conn = databaseConnection.connect();
        Statement statement = conn.createStatement();
        statement.executeUpdate("TRUNCATE gameCurrentState");
    }

    public void insert(String emailId, int position) {
        String insertQuery = "insert into gameCurrentState(emailId,position) VALUES(?,?)";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setInt(2, position);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
