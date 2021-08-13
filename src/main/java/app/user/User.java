package app.user;

public class User {
    public final String username;
    public final String password;
    public final float balance;
    public static int id;

    public User(String username, String password, float balance, int id) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        User.id = id;
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

