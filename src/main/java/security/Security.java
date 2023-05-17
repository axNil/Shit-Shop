package security;

import beans.Loginbean;
import data.DBI;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Security {
    private DBI DBI;

    public Security(DBI db) {
        DBI = db;
    }

    public boolean authenticateToken(String token) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        try {
            LocalDateTime receivedTime = LocalDateTime.parse(token.split(" ")[1]);
            Duration duration = Duration.between(receivedTime, currentDateTime);
            return duration.toHours() < 1;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String authenticateUser(Loginbean user) {
        if (DBI.getPassword(user.getUsername()).equals(user.getPassword())) {
            return createToken(user.getUsername());
        } else {
            return "Not valid";
        }
    }

    public String createToken(String username) {
        return String.format("%s %s", username, LocalDateTime.now());
    }

}
