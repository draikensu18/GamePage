package app;

import app.game.*;
import app.index.IndexController;
import app.login.LoginController;
import app.register.RegisterController;
import app.user.User;
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
    public static BlackJack blackJack;
    public static UserDao userDao;
    public static User user;
    public static Slots slots;

    public static void main(String[] args) {
    //test
        // Instantiate your dependencies
        gameDao = new GameDao();
        userDao = new UserDao();
        blackJack = new BlackJack();
        slots = new Slots();
        user = UserDao.getUserById(1);

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(HerokuUtil.getHerokuAssignedPort());

        app.routes(() -> {
            before(Filters.handleLocaleChange);
            before(LoginController.ensureLoginBeforeViewingBooks);
            get(Path.Web.INDEX, IndexController.serveIndexPage);
            get(Path.Web.BOOKS, GameController.fetchAllBooks);
            get(Path.Web.SLOTS, SlotController.fetchSlots);
            post(Path.Web.SLOTS, SlotController.fetchSlotsBet);
            get(Path.Web.BLACKJACK, BlackJackController.fetchBlackJack);
            post(Path.Web.BLACKJACK, BlackJackController.fetchCurrentBet);
            get(Path.Web.BLACKJACKGAME, BlackJackController.fetchBlackJackGame);
            post(Path.Web.BLACKJACKGAME, BlackJackController.fetchHitStand);
            get(Path.Web.ONE_BOOK, GameController.fetchOneBook);
            get(Path.Web.REGISTER, RegisterController.serveRegisterPage);
            post(Path.Web.REGISTER, RegisterController.handleRegisterPost);
            get(Path.Web.LOGIN, LoginController.serveLoginPage);
            post(Path.Web.LOGIN, LoginController.handleLoginPost);
            post(Path.Web.LOGOUT, LoginController.handleLogoutPost);
        });

        app.error(404, ViewUtil.notFound);
    }

}
