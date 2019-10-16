package authentication;


import DatabaseHelper.Login;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) {
        String loginInfo = exchange.getRequestURI().getQuery();
        List<String> loginDetails = Arrays.asList(loginInfo.split("="));
        String emailId = getEmailId(loginDetails.get(1));
        String password = loginDetails.get(2);
        Login login = new Login();
        try {
            boolean result = login.checkEmailForLogin(emailId,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String getEmailId(String s) {
        StringBuilder emailId = new StringBuilder();
        for (int i = 0; i < s.length() - 8; i++) {
            emailId.append(s.charAt(i));
        }
        return emailId.toString();
    }
}
