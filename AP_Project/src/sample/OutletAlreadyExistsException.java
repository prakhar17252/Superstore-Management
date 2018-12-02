package sample;

import java.io.Serializable;

/**
 *   Outlet Already Exists Exception
 */
public class OutletAlreadyExistsException extends Exception implements Serializable {
    /** Main Constructor
     * @param message Message for display
     */
    OutletAlreadyExistsException(String message) {
        super(message);
    }
}
