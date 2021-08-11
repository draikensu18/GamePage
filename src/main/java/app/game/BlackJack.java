package app.game;

import app.cards.Deck;

import java.text.DecimalFormat;


public class BlackJack {
    public double playerMoney;
    public Deck playingDeck;
    public Deck playerDeck = new Deck();
    public Deck dealerDeck = new Deck();
    public boolean roundEnd;
    public int valuePlayer;
    public int valueDealer;
    public String dealerOne;

    public Double currentBet;


    public double getPlayerMoney(){return playerMoney;}
    public Deck getPlayingDeck(){
        return playingDeck;
    }
    public Deck getPlayerDeck(){
        return playerDeck;
    }
    public Deck getDealerDeck(){
        return dealerDeck;
    }
    public boolean getRoundEnd(){
        return roundEnd;
    }
    public Double getCurrentBet(){return currentBet;}

    public void setStart(){
        if(playerMoney==0){
            this.playerMoney = 100.00;
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

        this.roundEnd=false;
    }

    public void setCurrentBet(String tmp){
        this.currentBet = Double.parseDouble(tmp);
    }

    public int getValuePlayer(){
        this.valuePlayer = this.playerDeck.cardsValueBJ();
        return valuePlayer;
    }
    public int getValueDealer(){
        this.valueDealer = this.dealerDeck.cardsValueBJ();
        return valueDealer;
    }

    public String getDealerOne(){
        this.dealerOne = dealerDeck.getCardBJ(0).toString();
        return dealerOne;
    }
    public String ShowMoney(){
        DecimalFormat df2 = new DecimalFormat("#.##");
        return df2.format(this.playerMoney);
    }

    public void getCard(){
        this.playerDeck.addBJ(this.playingDeck.pullCard());
    }
    public void getCardDealer(){
        this.dealerDeck.addBJ(this.playingDeck.pullCard());
    }
    public void getLose(){
        this.playerMoney-=this.currentBet;
    }
    public void getWin(){
        this.playerMoney+=this.currentBet;
    }
    public void setRoundEnd(){
        this.roundEnd=true;
    }



}
