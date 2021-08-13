package app.game;

import io.javalin.http.Handler;
import java.util.Map;

import app.util.Path;
import app.util.ViewUtil;

import static app.Main.*;
import static app.util.RequestUtil.*;

public class GameController {

    public static Handler fetchAllGames = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("games", gameDao.getAllGames());
        ctx.render(Path.Template.GAMES_ALL, model);
    };

    public static Handler fetchOneBook = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("game", gameDao.getBookByIsbn(getParamIsbn(ctx)));
        ctx.render(Path.Template.GAMES_ONE, model);
    };
}
