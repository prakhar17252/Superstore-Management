package sample;

import java.io.Serializable;

public class ItemAlreadyExistsException extends Exception implements Serializable {
    ItemAlreadyExistsException(String message) {
        super(message);
    }
}
