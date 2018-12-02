package sample;

import java.io.Serializable;

public class CategoryAlreadyExistsException extends Exception implements Serializable {
    CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
