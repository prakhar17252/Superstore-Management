package sample;

public class CategoryAlreadyExistsException extends Exception {
    CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
