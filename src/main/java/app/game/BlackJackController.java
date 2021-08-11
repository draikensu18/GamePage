package app.game;

import app.util.Path;
import app.util.ViewUtil;
import io.javalin.http.Handler;

import java.util.Map;

import static app.Main.blackJack;
import static app.util.RequestUtil.*;

public class BlackJackController {
    public static Handler fetchBlackJack = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        blackJack.setStart();
        model.put("blackjack", blackJack);
        ctx.render(Path.Template.BLACKJACKTEMPLATE, model);
    };
    public static Handler fetchCurrentBet = ctx -> {

        try{
            blackJack.setCurrentBet(getParamCurrentBet(ctx));
            if (blackJack.getCurrentBet() > blackJack.getPlayerMoney() || blackJack.getCurrentBet()<=0){
                ctx.redirect(Path.Web.BLACKJACK);
            }
            else{
                ctx.redirect(Path.Web.BLACKJACKGAME);
            }        }
        catch (Exception e){
            ctx.redirect(Path.Web.BLACKJACK);
        }



    };
    public static Handler fetchBlackJackGame = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("blackjack", blackJack);
        ctx.render(Path.Template.BLACKJACKGAMETEMPLATE, model);
    };
    public static Handler fetchHitStand = ctx -> {
        if(getParamReset(ctx)!=null){
            System.out.print("hello");
            ctx.redirect(Path.Web.BLACKJACK);//fix this
        }
        if(getParamHit(ctx)!=null){
            blackJack.getCard();
            if(blackJack.getValuePlayer() > 21){
                blackJack.getLose();
                blackJack.setRoundEnd();
                ctx.redirect(Path.Web.BLACKJACKGAME);
            }
            ctx.redirect(Path.Web.BLACKJACKGAME);

        }
        else{
            while((blackJack.getValueDealer() < 17)&& blackJack.roundEnd == false){
                blackJack.getCardDealer();
            }
            if((blackJack.getValueDealer() > blackJack.getValuePlayer())&& blackJack.roundEnd == false){
                blackJack.getLose();
                blackJack.setRoundEnd();
                ctx.redirect(Path.Web.BLACKJACKGAME);
            }

            if((blackJack.getValueDealer() > 21)&& blackJack.roundEnd == false){
                blackJack.getWin();
                blackJack.setRoundEnd();
                ctx.redirect(Path.Web.BLACKJACKGAME);
            }
            if((blackJack.getValueDealer() == blackJack.getValuePlayer())&& blackJack.roundEnd == false){
                blackJack.setRoundEnd();
                ctx.redirect(Path.Web.BLACKJACKGAME);
            }
            if((blackJack.getValueDealer() < blackJack.getValuePlayer())&& blackJack.roundEnd == false){
                blackJack.getWin();
                blackJack.setRoundEnd();
                ctx.redirect(Path.Web.BLACKJACKGAME);
            }

        }

    };

}
