package authentication;

import DatabaseHelper.LoginDatabaseHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String query = bufferedReader.readLine();
        int indexOfAnd = query.indexOf("&");
        StringBuffer emailId = getEmailId(query, indexOfAnd);
        StringBuffer password = getPassword(indexOfAnd + 10, query);
        LoginDatabaseHelper loginDatabaseHelper = new LoginDatabaseHelper();
        try {
            boolean result = loginDatabaseHelper.checkEmailForLogin(emailId.toString(), password.toString());
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private StringBuffer getPassword(int i, String query) {
        StringBuffer password = new StringBuffer();
        for (int j = i; j < query.length(); j++) {
            password.append(query.charAt(j));
        }
        return password;
    }

    private StringBuffer getEmailId(String query, int indexOfAnd) {
        StringBuffer emailId = new StringBuffer();
        for (int i = 8; i < indexOfAnd; i++) {
            if (query.charAt(i) == '&') {
                break;
            } else if (query.charAt(i) == '%' || query.charAt(i) == '4' || query.charAt(i) == '0') {
                if (query.charAt(i) == '%') {
                    emailId.append('@');
                }
            } else {
                emailId.append(query.charAt(i));
            }
        }
        return emailId;
    }
}
