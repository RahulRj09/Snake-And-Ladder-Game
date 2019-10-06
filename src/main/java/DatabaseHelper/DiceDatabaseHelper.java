package DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiceDatabaseHelper {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    public void insert(String emailId, int numberOnDice) {
        String selectQuery = "SELECT * FROM user WHERE emailId = '" + emailId + "'";
        try (Connection conn = databaseConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

            }else {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
