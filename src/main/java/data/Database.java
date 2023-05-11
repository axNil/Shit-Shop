package data;

import java.util.HashMap;
import java.util.LinkedList;

public class Database {
    private HashMap<String, LinkedList<String>> users;
    private HashMap<String, String> connectedUsers;


    public boolean checkIfUsernameExist(String username) {
        return users.containsKey(username);
    }

    public boolean checkIfEmailExist(String email) {
        for (LinkedList<String> l : users.values())
            if (l.get(3).equals(email)) return true;

        return false;
    }

    public void addUser(String username, LinkedList<String> info) {
        users.put(username, info);

        //TODO this user should also be added to connectedusers?
    }



}
