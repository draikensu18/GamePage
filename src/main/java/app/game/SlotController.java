package app.game;

import app.util.Path;
import app.util.ViewUtil;
import io.javalin.http.Handler;

import java.util.Map;

import static app.Main.blackJack;
import static app.Main.slots;
import static app.util.RequestUtil.getParamCurrentBet;
import static app.util.RequestUtil.getParamReSpin;

public class SlotController {
    public static Handler fetchSlots = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        slots.SlotsStartPosition();
        model.put("slots", slots);
        ctx.render(Path.Template.SLOTSTEMPLATE, model);
    };
    public static Handler fetchSlotsBet = ctx -> {
        if(getParamReSpin(ctx)!=null){
            if (slots.getCurrentBet() > slots.getPlayerMoney() || slots.getCurrentBet() <= 0) {
                slots.ExtraUI2();
                ctx.redirect(Path.Web.SLOTS);
            } else {
                slots.setWin();
                slots.StartRound();
                if(slots.CheckFullWin()){

                }
                else{
                    slots.CheckHalfWin();
                }
                ctx.redirect(Path.Web.SLOTS);
            }
        }
        else {
            try {
                slots.setCurrentBet(getParamCurrentBet(ctx));
                if (slots.getCurrentBet() > slots.getPlayerMoney() || slots.getCurrentBet() <= 0) {

                    ctx.redirect(Path.Web.SLOTS);
                } else {
                    slots.ExtraUI();


                    ctx.redirect(Path.Web.SLOTS);
                }
            } catch (Exception e) {
                ctx.redirect(Path.Web.SLOTS);
            }
        }
    };

    //getParamCurrentBet
}
