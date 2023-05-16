package beans;

import application.ProductTypeSubscriber;
import enums.ProductType;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class User implements ProductTypeSubscriber {
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String username;
    private String password;
    private LinkedList<Message> inbox;
    private AtomicBoolean hasUnsentMessages;
    private Semaphore sem;

    public User(String firstName, String lastName, String dob, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.username = username;
        this.password = password;
        this.inbox = new LinkedList<>();
        hasUnsentMessages = new AtomicBoolean();
        sem = new Semaphore(1);
    }

    public List<Message> getInbox() {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Message> mess = inbox;
        sem.release();
        return mess;
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

    @Override
    public void update(ProductType productType) {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        inbox.add(new WishlistMessage(productType));
        hasUnsentMessages.set(true);
        sem.release();
    }



    public boolean hasUnsentMessages() {
        return hasUnsentMessages.get();
    }

    public void setHasUnsentMessages(boolean hasUnsentMessages) {
        this.hasUnsentMessages.set(hasUnsentMessages);
    }
}
