package app.game;

import app.cards.Card;
import app.cards.Deck;
import app.user.UserDao;

import java.text.DecimalFormat;

public class HigherOrLower extends Deck {
    public double playerMoney;
    public boolean UI;
    public double currentBet;
    public Deck game = new Deck();
    public Card firstCard;
    public Card secondCard;
    public Integer firstCardValue;
    public Integer secondCardValue;
    public boolean values;
    public boolean win;
    public boolean lose;
    public boolean draw;

    public boolean getValues() {
        return values;
    }

    public boolean getWin() {
        return win;
    }

    public boolean getLose() {
        return lose;
    }

    public boolean getDraw() {
        return draw;
    }

    public void HigherOrLowerStartPosition() {
        if (playerMoney == 0) {
            UI = false;
            values = false;
            this.playerMoney = UserDao.getBalance();
        }
    }

    public double getPlayerMoney() {
        return playerMoney;
    }

    public String ShowMoney() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        return df2.format(this.playerMoney);
    }

    public boolean getUI() {
        return UI;
    }

    public void ExtraUI1() {
        UI = true;
    }

    public void ExtraUI2() {
        UI = false;
    }

    public void setCurrentBet(String tmp) {
        this.currentBet = Double.parseDouble(tmp);
    }

    public Double getCurrentBet() {
        return currentBet;
    }

    public void Start() {
        win = false;
        lose = false;
        draw = false;
        values = true;
        game.getDeck();
        game.shuffle();
        boolean end = false;

        firstCard = game.pullCard();
        secondCard = game.pullCard();
        firstCardValue = firstCard.getValue();
        secondCardValue = secondCard.getValue();
        Integer nextCard = 0;

    }

    public String getFirstCard() {
        if (values) {
            return firstCard.toString() + ".png";
        } else {
            return "";
        }

    }

    public String FirstCard() {
        if (values) {
            return firstCard.toString() + ".png";
        } else {
            return "";
        }

    }

    public Integer FirstCardValue() {
        if (values) {
            return firstCardValue;
        } else {
            return 0;
        }

    }

    public String getSecondCard() {
        if (values) {
            return secondCard.toString() + ".png";
        } else {
            return "";
        }


    }

    public Integer SecondCardValue() {
        if (values) {
            return secondCardValue;
        } else {
            return 0;
        }

    }

    public void CheckLose() {
        if (firstCardValue < secondCardValue) {
            this.playerMoney -= currentBet;
            UserDao.updateBalance((int) playerMoney);
            lose = true;
        }
    }

    public void CheckWin() {
        if (firstCardValue > secondCardValue) {
            this.playerMoney += currentBet;
            UserDao.updateBalance((int) playerMoney);
            win = true;
        }
    }

    public void CheckDraw() {
        if (firstCardValue == secondCardValue) {
            draw = true;
        }
    }

}