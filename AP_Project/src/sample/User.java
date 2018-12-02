package sample;

import java.io.Serializable;

/**
 *  Main User abstract class for all types of users
 */
public abstract class User implements Serializable {
    protected String username;
    protected String password;

    /** constructor of user which will be used as super inside classes implementing it
     * @param username
     * @param password
     */
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /** Login try to check if username and password are equal
     * @param id id of user
     * @param pass pasword of user
     * @return true or false depending upon check
     */
    public boolean tryLogin(String id, String pass) {
        return username.equals(id) && password.equals(pass);
    }

    /** returns username of existing user
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /** changes password of user
     * @param old old password  of  user
     * @param pass new password to be set
     * @throws IncorrectPasswordException
     */
    public void changePassword(String old, String pass) throws IncorrectPasswordException {
        if(!old.equals(password)) throw new IncorrectPasswordException("incorrect password");
        this.password = pass;
    }
}
