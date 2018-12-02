package sample;

import java.io.Serializable;

public class NotEnoughQuantityException extends Exception implements Serializable {
    public NotEnoughQuantityException(String message) {
        super(message);
    }
}
