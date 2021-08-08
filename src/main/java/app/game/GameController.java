package app.game;

import io.javalin.http.Handler;
import java.util.Map;

import app.util.Path;
import app.util.ViewUtil;

import static app.Main.*;
import static app.util.RequestUtil.*;

public class GameController {

    public static Handler fetchAllBooks = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("books", gameDao.getAllBooks());
        ctx.render(Path.Template.BOOKS_ALL, model);
    };

    public static Handler fetchOneBook = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("book", gameDao.getBookByIsbn(getParamIsbn(ctx)));
        ctx.render(Path.Template.BOOKS_ONE, model);
    };
}
