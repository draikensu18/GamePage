package app.user;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jooq.Record;
import static org.jooq.impl.DSL.*;
import static org.jooq.generated.tables.Users.USERS;

public class UserDao {

    private static float balance;
   // public float balance;
    public static final ArrayList<User> users = getAllUsers();

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> allUsers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root")) {
            DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
            Result<Record> result = create.select().from(USERS).fetch();

            for (Record r : result) {
                String username = r.getValue(USERS.USERNAME);
                String password = r.getValue(USERS.PASSWORD);
                float balance = r.getValue(USERS.BALANCE);
                int id = r.getValue(USERS.ID).intValue();
                allUsers.add(new User(username,password,balance,id));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allUsers;

    }

    public static User getUserById(int id) {
        for(User i : users) {
                if(Objects.equals(i.getId(), id)) {
                    return i;
            }
        }
       return null;
    }

    public User getUserByUsername(String username) {
        return users.stream().filter(b -> b.username.equals(username)).findFirst().orElse(null);
    }


    public Iterable<String> getAllUserNames() {
        return users.stream().map(user -> user.username).collect(Collectors.toList());
    }

    public void getBalance(User user){
        balance = user.balance;
    }

    public static float getBalance(){
       return balance;
    }

    public static void updateBalance(Integer balance){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root")) {
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
            context.update(USERS)
                    .set(USERS.BALANCE, balance.longValue())
                    .where(USERS.USERNAME.eq(currentUser()))
                    .execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
