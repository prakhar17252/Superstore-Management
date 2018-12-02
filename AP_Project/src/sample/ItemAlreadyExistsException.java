package sample;

import java.io.Serializable;

/**
 *  This is an exception which is used to tell if an Item already exists before being Added.
 */
public class ItemAlreadyExistsException extends Exception implements Serializable {
    /**
     *  Main Constructor which calls Super constructor
     * @param message Custom Message
     */
    ItemAlreadyExistsException(String message) {
        super(message);
    }
}
