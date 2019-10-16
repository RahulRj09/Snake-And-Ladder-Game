package authentication;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.Arrays;
import java.util.List;


public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) {
        String loginInfo = exchange.getRequestURI().getQuery();
        List<String> loginDetails = Arrays.asList(loginInfo.split("="));
        String emailId = getEmailId(loginDetails.get(1));
    }

    private String getEmailId(String s) {
        return null;
    }
}
