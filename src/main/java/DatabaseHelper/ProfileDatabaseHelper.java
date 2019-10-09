package DatabaseHelper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDatabaseHelper {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public JSONObject getUserDetails(String emailId) {
        JSONObject userDetails = new JSONObject();

        JSONArray userDetailsArray = new JSONArray();
        String sql = "SELECT * FROM users WHERE emailId = '" + emailId + "'";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject details = new JSONObject();
                details.put("name", resultSet.getString("name"));
                details.put("email", resultSet.getString("emailId"));
                details.put("win", resultSet.getString("win"));
                details.put("loss", resultSet.getString("loss"));
                details.put("total", resultSet.getString("total"));
                userDetailsArray.add(details);
            }
            userDetails.put("Profile", userDetailsArray);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userDetails;
    }

    public void updateTotalPlayedGames(String emailId) {
        String sql = "SELECT * FROM users WHERE emailId = '" + emailId + "'";
        try(Connection connection = databaseConnection.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
