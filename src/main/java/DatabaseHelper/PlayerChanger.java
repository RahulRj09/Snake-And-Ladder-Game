package DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerChanger {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public int getCurrentIndex() {
        int currentIndex = 0;
        String selectQuery = String.format("SELECT * FROM currentIndex WHERE idcurrentIndex = '%d'", 1);
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                currentIndex = resultSet.getInt("index");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return currentIndex;
    }

    public void updateIndex(int index) {
        String updateQuery = "UPDATE `currentIndex` SET `index`= ?";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, index);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

