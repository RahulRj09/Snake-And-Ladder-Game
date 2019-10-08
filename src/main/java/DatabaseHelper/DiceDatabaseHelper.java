package DatabaseHelper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiceDatabaseHelper {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    public void insert(String emailId, int position) {
        String selectQuery = "SELECT * FROM gameCurrentState WHERE emailId = '" + emailId + "'";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int previousPosition = resultSet.getInt("position");
                String updateQuery = "UPDATE gameCurrentState SET position=? WHERE emailId=?";
                PreparedStatement preparedStatement1 = conn.prepareStatement(updateQuery);
                preparedStatement1.setInt(1, position + previousPosition);
                preparedStatement1.setString(2, emailId);
                preparedStatement1.executeUpdate();

            } else {
                String insertQuery = "insert into gameCurrentState(emailId,position) VALUES(?,?)";
                PreparedStatement preparedStatement1 = conn.prepareStatement(insertQuery);
                preparedStatement1.setString(1, emailId);
                preparedStatement1.setInt(2, position);
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

    public void tableTruncate() {

    }
}
