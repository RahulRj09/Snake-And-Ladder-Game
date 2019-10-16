package authentication;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.Arrays;
import java.util.List;


public class LoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) {
        String loginInfo = exchange.getRequestURI().getQuery();

    }
}
