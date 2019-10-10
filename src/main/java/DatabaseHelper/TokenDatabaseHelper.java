package DatabaseHelper;

import org.json.simple.JSONObject;

import java.sql.*;

public class TokenDatabaseHelper {
    DatabaseConnection databaseConnection = new DatabaseConnection();


    public void updatePosition(String emailId, int position) {
        String updateQuery = "UPDATE gameCurrentState SET position=? WHERE emailId=?";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, position);
            preparedStatement.setString(2, emailId);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public JSONObject getCurrentPosition(String emailId) {
        JSONObject currentPosition = new JSONObject();
        String selectQuery = String.format("SELECT * FROM gameCurrentState WHERE emailId = '%s'", emailId);
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


    public boolean positionRowExistsOrNotForCurrentUser(String loggedUserEmailId) throws SQLException {
        String selectQuery = String.format("SELECT * FROM gameCurrentState WHERE emailId = '%s'", loggedUserEmailId);
        ResultSet resultSet = null;
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(resultSet == null){
            return true;
        }
        return false;
    }
}