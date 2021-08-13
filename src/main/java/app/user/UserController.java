package app.user;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static app.Main.*;
import static org.jooq.generated.tables.Users.USERS;

public class UserController {

    public static boolean authenticate(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        UserDao.getUserById(user.getId());
        return user != null;
    }

    public static float databaseBalance(){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root")) {
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
            Record record = context.fetchOne(USERS, USERS.ID.like(String.valueOf(user.getId())));

            assert record != null;
            return record.getValue(USERS.BALANCE);

        } catch (DataAccessException | SQLException throwables) {
            System.out.println("no such id");
        }
        return 0;
    }
}
