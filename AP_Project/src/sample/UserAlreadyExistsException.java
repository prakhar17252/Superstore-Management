package sample;

import java.io.Serializable;

public class UserAlreadyExistsException extends Exception implements Serializable {
    UserAlreadyExistsException(String message) {
        super(message);
    }
}
