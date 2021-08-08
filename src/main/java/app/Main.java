package app;

import app.game.GameController;
import app.game.GameDao;
import app.index.IndexController;
import app.login.LoginController;
import app.user.UserDao;
import app.util.Filters;
import app.util.HerokuUtil;
import app.util.Path;
import app.util.ViewUtil;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import static io.javalin.apibuilder.ApiBuilder.before;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;


public class Main {

    // Declare dependencies
    public static GameDao gameDao;
    public static UserDao userDao;

    public static void main(String[] args) {
    //test
        // Instantiate your dependencies
        gameDao = new GameDao();
        userDao = new UserDao();

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(HerokuUtil.getHerokuAssignedPort());

        app.routes(() -> {
            before(Filters.handleLocaleChange);
            before(LoginController.ensureLoginBeforeViewingBooks);
            get(Path.Web.INDEX, IndexController.serveIndexPage);
            get(Path.Web.BOOKS, GameController.fetchAllBooks);
            get(Path.Web.ONE_BOOK, GameController.fetchOneBook);
            get(Path.Web.LOGIN, LoginController.serveLoginPage);
            post(Path.Web.LOGIN, LoginController.handleLoginPost);
            post(Path.Web.LOGOUT, LoginController.handleLogoutPost);
        });

        app.error(404, ViewUtil.notFound);
    }

}
