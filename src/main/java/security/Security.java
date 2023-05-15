package security;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Security {
    public boolean authenticateToken(String token) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        try {
            LocalDateTime receivedTime = LocalDateTime.parse(token);
            Duration duration = Duration.between(receivedTime, currentDateTime);
            return duration.toHours() < 1;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean authenticateUser(String username, String password) {
        return true;
    }

    public String createToken() {
        return LocalDateTime.now().toString();
    }

}
