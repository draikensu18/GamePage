package app.register;

import app.util.Path;
import app.util.ViewUtil;
import io.javalin.http.Handler;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import static app.util.RequestUtil.*;
import static app.util.RequestUtil.getQueryLoginRedirect;
import static org.jooq.generated.tables.Users.USERS;

public class RegisterController {

    public static Handler serveRegisterPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("loggedOut", removeSessionAttrLoggedOut(ctx));
        model.put("loginRedirect", removeSessionAttrLoginRedirect(ctx));
        ctx.render(Path.Template.REGISTER, model);
    };

    public static boolean registerUser(String userName, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root")) {
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
            context.insertInto(USERS, USERS.USERNAME, USERS.PASSWORD).values(userName, password).execute();
            return true;

        } catch (DataAccessException | SQLException throwables) {
            System.out.println("User already exists");
            return false;
        }
    }

    public static Handler handleRegisterPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if (!registerUser(getQueryUsername(ctx), getQueryPassword(ctx))) {
            model.put("authenticationFailed", true);
            ctx.render(Path.Template.REGISTER, model);
        } else {
            ctx.sessionAttribute("currentUser", getQueryUsername(ctx));
            model.put("authenticationSucceeded", true);
            model.put("currentUser", getQueryUsername(ctx));
            if (getQueryLoginRedirect(ctx) != null) {
                ctx.redirect(getQueryLoginRedirect(ctx));
            }
            ctx.render(Path.Template.INDEX, model);
        }
    };

}
