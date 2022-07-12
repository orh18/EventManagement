package ch.bzz.veranstaltungverwaltung.data;

import ch.bzz.veranstaltungverwaltung.model.User;
import ch.bzz.veranstaltungverwaltung.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * data handler for reading the users
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-07-11
 * @version : 1e
 */
public class UserData {
    private static final UserData instance = new UserData();

    /**
     * finds a user by username / password
     * @param username the username of the user
     * @param password the password of the user
     * @return User object / null=not found
     */

    public static User readUser(String username, String password) {
        User user = new User();
        List<User> userList = readJSON();

        for (User entry : userList) {
            if(entry.getUsername().equals(username) &&
            entry.getPassword().equals(password)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * finds a user by username / password
     * @param username the username of the user
     * @return User object / null=not found
     */

    public static User readUser(String username) {
        User user = new User();
        List<User> userList = readJSON();
        for (User entry : userList) {
            if(entry.getUsername().equals(username)) {
                user = entry;
                break;
            }
        }
        return user;
    }

    /**
     * reads the json file as a List
     * @return userList
     */

    public static List<User> readJSON() {
        List<User> userList = new ArrayList<>();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("userJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for(User user: users) {
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
}

