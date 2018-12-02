package sample;

import java.io.Serializable;

/**
 *  Exception if Subcategory already exists
 */
public class SubcategoryAlreadyExistsException extends Exception implements Serializable {
    /**
     * Constructor of Exception calling Super Exception
     * @param message message to be displayed
     */
    SubcategoryAlreadyExistsException(String message) {
        super(message);
    }
}
