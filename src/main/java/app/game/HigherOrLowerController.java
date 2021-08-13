package app.game;

import app.util.Path;
import app.util.ViewUtil;
import io.javalin.http.Handler;

import java.util.Map;

import static app.Main.higherOrLower;
import static app.Main.slots;
import static app.util.RequestUtil.*;

public class HigherOrLowerController {
    public static Handler fetchHigherOrLower = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        higherOrLower.HigherOrLowerStartPosition();
        model.put("higherOrLower", higherOrLower);
        ctx.render(Path.Template.HIGHERORLOWERTEMPLATE, model);
    };
    public static Handler fetchHigherOrLowerBet = ctx -> {
        System.out.println("echo");
        if(getParamPlay(ctx)!=null){
            if (higherOrLower.getCurrentBet() > higherOrLower.getPlayerMoney() || higherOrLower.getCurrentBet() <= 0) {
                higherOrLower.ExtraUI2();
                ctx.redirect(Path.Web.HIGHERORLOWER);
            } else {
                higherOrLower.Start();
                higherOrLower.CheckWin();
                higherOrLower.CheckLose();
                higherOrLower.CheckDraw();
                ctx.redirect(Path.Web.HIGHERORLOWER);
            }
        }
        else {
            try {
                higherOrLower.setCurrentBet(getParamCurrentBet(ctx));
                if (higherOrLower.getCurrentBet() > higherOrLower.getPlayerMoney() || higherOrLower.getCurrentBet() <= 0) {

                    ctx.redirect(Path.Web.HIGHERORLOWER);
                } else {

                    higherOrLower.ExtraUI1();

                    ctx.redirect(Path.Web.HIGHERORLOWER);
                }
            } catch (Exception e) {
                ctx.redirect(Path.Web.HIGHERORLOWER);
            }
        }
    };

}