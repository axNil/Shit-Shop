package beans;

import java.util.concurrent.atomic.AtomicBoolean;

public class User {
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String username;
    private String password;
    private final AtomicBoolean hasUnsentMessages;

    public User(String firstName, String lastName, String dob, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.username = username;
        this.password = password;
        hasUnsentMessages = new AtomicBoolean();
     }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasUnsentMessages() {
        return hasUnsentMessages.get();
    }

    public void setHasUnsentMessages(boolean hasUnsentMessages) {
        this.hasUnsentMessages.set(hasUnsentMessages);
    }
}
