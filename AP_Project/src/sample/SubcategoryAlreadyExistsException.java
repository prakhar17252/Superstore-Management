package sample;

import java.io.Serializable;

public class SubcategoryAlreadyExistsException extends Exception implements Serializable {
    SubcategoryAlreadyExistsException(String message) {
        super(message);
    }
}
