package sample;

import java.io.Serializable;

/**
 *  Exception Class if Category already exists
 */
public class CategoryAlreadyExistsException extends Exception implements Serializable {
    /**
     * Calling Super Constructor for exception initialisation
     * @param message message to be passed for exception
     */
    CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
