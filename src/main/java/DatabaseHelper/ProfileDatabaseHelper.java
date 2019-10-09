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
        String sql = String.format("SELECT * FROM users WHERE emailId = '%s'", emailId);
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

    public void updateWinningGames(String emailId) {
        String sql = String.format("SELECT * FROM users WHERE emailId = '%s'", emailId);
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int win = resultSet.getInt("win");
                String updateQuery = "UPDATE users SET win=? WHERE emailId=?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
                preparedStatement1.setInt(1, win + 1);
                preparedStatement1.setString(2, emailId);
                preparedStatement1.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLostGames(String emailId) {
        String sql = "SELECT * FROM users WHERE emailId = '" + emailId + "'";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int loss = resultSet.getInt("loss");
                String updateQuery = "UPDATE users SET loss=? WHERE emailId=?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
                preparedStatement1.setInt(1, loss + 1);
                preparedStatement1.setString(2, emailId);
                preparedStatement1.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void totalPlayedGame(String loggedUserEmailId) {
        String sql = String.format("SELECT * FROM users WHERE emailId = '%s'", loggedUserEmailId);
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int win = resultSet.getInt("win");
                int loss = resultSet.getInt("loss");
                String updateQuery = "UPDATE users SET total=? WHERE emailId=?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
                preparedStatement1.setInt(1, win+loss);
                preparedStatement1.setString(2, loggedUserEmailId);
                preparedStatement1.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
