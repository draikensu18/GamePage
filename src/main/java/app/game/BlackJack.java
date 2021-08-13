package app.game;

import app.cards.Deck;
import app.user.UserController;
import app.user.UserDao;

import java.text.DecimalFormat;

public class BlackJack extends Deck{
    public double playerMoney;
    public Deck playingDeck;
    public Deck playerDeck = new Deck();
    public Deck dealerDeck = new Deck();
    public boolean roundEnd;
    public int valuePlayer;
    public int valueDealer;
    public Deck dealerOne;
    public Float currentBet;

    public int getDealerFirst() {
        return dealerDeck.getCardBJ(0).getValue();

    }

    public String getDealerOne() {
        return dealerDeck.getCardBJ(0).toString() + ".png";
    }

    public double getPlayerMoney() {
        return playerMoney;
    }

    public Deck getPlayingDeck() {
        return playingDeck;
    }

    public Deck getPlayerDeck() {
        return playerDeck;
    }

    public Deck getDealerDeck() {
        return dealerDeck;
    }

    public boolean getRoundEnd() {
        return roundEnd;
    }

    public Float getCurrentBet() {
        return currentBet;
    }

    public void setStart() {
        if (playerMoney == 0) {
            this.playerMoney = UserController.databaseBalance();
        }

        this.playerDeck.moveAllToDeckBJ(this.playingDeck);
        this.dealerDeck.moveAllToDeckBJ(this.playingDeck);

        this.playingDeck = new Deck();
        this.playingDeck.getDeck();
        this.playingDeck.shuffle();

        this.playerDeck.addBJ(this.playingDeck.pullCard());
        this.playerDeck.addBJ(this.playingDeck.pullCard());

        this.dealerDeck.addBJ(this.playingDeck.pullCard());
        this.dealerDeck.addBJ(this.playingDeck.pullCard());

        this.roundEnd = false;
    }

    public void setCurrentBet(String tmp) {
        this.currentBet = Float.parseFloat(tmp);
    }

    public int getValuePlayer() {
        this.valuePlayer = this.playerDeck.cardsValueBJ();
        return valuePlayer;
    }

    public int getValueDealer() {
        this.valueDealer = this.dealerDeck.cardsValueBJ();
        return valueDealer;
    }


    public String ShowMoney() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        return df2.format(this.playerMoney);
    }

    public void getCard() {
        this.playerDeck.addBJ(this.playingDeck.pullCard());
    }

    public void getCardDealer() {
        this.dealerDeck.addBJ(this.playingDeck.pullCard());
    }

    public void getLose() {
        this.playerMoney -= this.currentBet;
        UserDao.updateBalance((int) playerMoney);
    }

    public void getWin() {
        this.playerMoney += this.currentBet;
        UserDao.updateBalance((int) playerMoney);
    }

    public void setRoundEnd() {
        this.roundEnd = true;
    }


}
