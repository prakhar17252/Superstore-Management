package sample;

import java.io.Serializable;

public class OutletAlreadyExistsException extends Exception implements Serializable {
    OutletAlreadyExistsException(String message) {
        super(message);
    }
}
