package data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Database {
    //Username, [firstname, lastname, dob, email, password, id]
    private HashMap<String, LinkedList<String>> users;

    //username, token
    private HashMap<String, String> connectedUsers;



    public Database() {
        users = new HashMap<>();
        connectedUsers = new HashMap<>();
        fill();
    }

    public boolean checkIfUsernameExist(String username) {
        return users.containsKey(username);
    }

    public boolean checkIfEmailExist(String email) {
        for (LinkedList<String> l : users.values())
            if (l.get(3).equals(email)) return true;

        return false;
    }

    public String getPassword(String username) {
        return users.get(username).get(4);
    }

    public boolean addToConnectedUsers(String user, String token) {
        connectedUsers.put(user, token);
        return true;
    }

    public void addUser(String username, LinkedList<String> info) {
        users.put(username, info);

        //TODO this user should also be added to connectedusers?
    }

    private void fill() {
        users.put("admin", new LinkedList<>(List.of("admin", "admin", "2023-05-12", "admin@shitshop.com", "admin", "admin", "1")));
    }


}
