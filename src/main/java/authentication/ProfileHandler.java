package authentication;

import DatabaseHelper.ProfileDatabaseHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProfileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
       String emailId = exchange.getRequestURI().getQuery();
        List<String> emailIdA = Arrays.asList(emailId.split("="));
        emailIdA.get(1);
        ProfileDatabaseHelper profileDatabaseHelper = new ProfileDatabaseHelper();
    }
}
