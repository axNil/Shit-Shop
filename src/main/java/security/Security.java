package security;

import beans.Loginbean;
import data.Database;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Security {
    private Database database;
    public Security(Database db) {
        database = db;
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
        if (database.getPassword(user.getUsername()).equals(user.getPassword())) {
            return createToken(user.getUsername());
        } else {
            return "Not valid";
        }
    }

    public String createToken(String username) {
        return String.format("%s %s", username, LocalDateTime.now());
    }

}
