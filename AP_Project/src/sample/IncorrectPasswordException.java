package sample;

import java.io.Serializable;

public class IncorrectPasswordException extends Exception implements Serializable {
    IncorrectPasswordException(String message) {
        super(message);
    }
}
