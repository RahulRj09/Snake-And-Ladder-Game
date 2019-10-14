package DatabaseHelper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

@SuppressWarnings("unchecked")
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
        JSONObject currentPositionObject = new JSONObject();
        JSONArray currentPositionArray = new JSONArray();
        String selectQuery = String.format("SELECT * FROM gameCurrentState WHERE emailId = '%s'", emailId);
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject position = new JSONObject();
                position.put("position", resultSet.getInt("position"));
                position.put("emailId", emailId);
                currentPositionArray.add(position);
            }
            currentPositionObject.put("details", currentPositionArray);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return currentPositionObject;
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


    public boolean positionRowExistsOrNotForCurrentUser(String loggedUserEmailId) {
        String selectQuery = String.format("SELECT * FROM gameCurrentState WHERE emailId = '%s'", loggedUserEmailId);
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Boolean.parseBoolean(null);
    }

}