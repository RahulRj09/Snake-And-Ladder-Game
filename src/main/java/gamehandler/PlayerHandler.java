package gamehandler;

import DatabaseHelper.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerHandler {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public int getCurrentIndex() {
        int currentIndex = 0;
        String selectQuery = String.format("SELECT * FROM currentIndex WHERE idcurrentIndex = '%d'", 1 );
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
}

