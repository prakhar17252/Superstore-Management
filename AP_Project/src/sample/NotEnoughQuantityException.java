package sample;

import java.io.Serializable;

/**
 * Exception when customer tries to purchase more than the quantity available inside store
 */
public class NotEnoughQuantityException extends Exception implements Serializable {

    /** Main constructor which calls super constructor
     * @param message Message to be displayed
     */
    public NotEnoughQuantityException(String message) {
        super(message);
    }
}
