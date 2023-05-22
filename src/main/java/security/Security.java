package security;

import beans.Loginbean;
import data.DBI;
import io.javalin.http.Context;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Security {
    private DBI DBI;

    public Security(DBI db) {
        DBI = db;
    }

    public static boolean authenticateToken(String token) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        try {
            LocalDateTime receivedTime = LocalDateTime.parse(token.split(" ")[1]);
            Duration duration = Duration.between(receivedTime, currentDateTime);
            return duration.toHours() < 1;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String extractUsernameFromToken(Context ctx) {
        return ctx.header("auth_token").split(" ")[0];
    }

    public String authenticateUser(Loginbean user) {
        String pw = DBI.getPassword(user.getUsername());
        if (pw.equals("")) {
            return "Username doesn't exist";
        } else if (pw.equals(user.getPassword())) {
            return createToken(user.getUsername());
        } else {
            return "Wrong password";
        }
    }

    public String createToken(String username) {
        return String.format("%s %s", username, LocalDateTime.now());
    }

}
