package app.user;

public class User {
    public final String username;
    public final String password;
    public final float balance;
    public final int id;

    public User(String username, String password, float balance, int id) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public float getBalance(int id) {
        return balance;
    }

    public int getId() {
        return id;
    }
}

