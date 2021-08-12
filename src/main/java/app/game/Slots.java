package app.game;

import java.text.DecimalFormat;
import java.util.*;

public class Slots {
    public double playerMoney;
    public double currentBet;
    public double win;
    public boolean UI;
    public int slot1, slot2, slot3;
    Random generator = new Random();


    public int getSlot1() {
        return slot1;
    }

    public int getSlot2() {
        return slot2;
    }

    public int getSlot3() {
        return slot3;
    }

    public double getWin() {
        return win;
    }

    public void SlotsStartPosition() {
        if (playerMoney == 0) {
            UI = false;
            win = 0;
            this.playerMoney = 100.00;
        }
    }

    public double getPlayerMoney() {
        return playerMoney;
    }

    public boolean getUI() {
        return UI;
    }

    public String ShowMoney() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        return df2.format(this.playerMoney);
    }

    public void setCurrentBet(String tmp) {
        this.currentBet = Double.parseDouble(tmp);
    }

    public Double getCurrentBet() {
        return currentBet;
    }

    public void ExtraUI() {
        UI = true;

    }

    public void setWin() {
        win = 0;
        win -= currentBet;
    }

    public void StartRound() {
        playerMoney -= currentBet;
        slot1 = generator.nextInt(9) + 1;
        slot2 = generator.nextInt(9) + 1;
        slot3 = generator.nextInt(9) + 1;
    }

    public boolean CheckHalfWin() {
        if (slot1 == slot2 || slot1 == slot3 || slot2 == slot3) {
            win += currentBet * 3;
            playerMoney += currentBet * 2;
            return true;
        }
        return false;
    }

    public boolean CheckFullWin() {
        if (slot1 == slot2 && slot1 == slot3) {
            win += currentBet * 4;
            playerMoney += currentBet * 3;
            return true;
        }
        return false;
    }

}