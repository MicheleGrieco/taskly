package com.michelegrieco.taskly.exception;

public class UserNotFoundException extends RuntimeException {

    /**
     * Constructor for UserNotFoundException.
     * @param username the username of the user that was not found.
     */
    public UserNotFoundException(String username) {
        super("User with username '" + username + "' not found.");
    }
}
