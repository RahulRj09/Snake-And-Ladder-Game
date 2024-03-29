package DatabaseHelper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;

@SuppressWarnings("unchecked")
public class Token {
    private DatabaseConnection databaseConnection = new DatabaseConnection();


    public void updatePosition(String emailId, int position, int numberOnDice) {
        String updateQuery = "UPDATE gameCurrentState SET position = ? , numberOnDice = ? WHERE emailId=?";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, position);
            preparedStatement.setInt(2, numberOnDice);
            preparedStatement.setString(3, emailId);
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
                position.put("numberOnDice",resultSet.getInt("numberOnDice"));
                currentPositionArray.add(position);
            }
            currentPositionObject.put("details", currentPositionArray);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return currentPositionObject;
    }

    public void insert(String emailId, int position) {
        String insertQuery = "insert into gameCurrentState(emailId,position,numberOnDice) VALUES(?,?,?)";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setInt(2, position);
            preparedStatement.setInt(3, 1);
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