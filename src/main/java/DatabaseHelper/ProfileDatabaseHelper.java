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
        String sql = "SELECT * FROM user WHERE emailId = '" + emailId + "'";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject details = new JSONObject();
                details.put("name", resultSet.getString("name"));
                details.put("email", resultSet.getString("emailId"));
                userDetailsArray.add(details);
            }
            userDetails.put("Profile", userDetailsArray);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userDetails;
    }
}