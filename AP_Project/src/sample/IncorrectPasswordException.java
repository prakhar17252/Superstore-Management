package sample;

import java.io.Serializable;

/**
 *  Exception for Incorrect password while logging in
 */
public class IncorrectPasswordException extends Exception implements Serializable {
    /**
     * Main Constructor which calls Super Constructor
     * @param message Message to be displayed
     */
    IncorrectPasswordException(String message) {
        super(message);
    }
}
