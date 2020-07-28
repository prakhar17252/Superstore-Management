public abstract class User {
    protected String username;
    protected String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean tryLogin(String id, String pass) {
        return username.equals(id) && password.equals(pass);
    }
    public void changePassword(String old, String pass) throws IncorrectPasswordException {
        if(!old.equals(password)) throw new IncorrectPasswordException("incorrect password");
        this.password = pass;
    }
}
