package app.book;

import app.cards.Card;
import app.cards.Deck;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Scanner;

public class BlackJack {

    public static void blackJack(){
        //start
        System.out.println("Start");

        //create playing deck
        Deck playingDeck = new Deck();
        playingDeck.getDeck();

        //shuffle the deck
        playingDeck.shuffle();

        //create deck for a player and dealer
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        //creating money for player
        double playerMoney = 100.00;

        //user input
        Scanner userInput = new Scanner(System.in);

        //game loop
        while(playerMoney > 0 ) {
            //can play have money
            //Place the bet
            System.out.println("Balance: " + playerMoney);
            System.out.println("Bet");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You cant bet what you dont have!");
                break;
            }
            boolean endRound = false;
            //dealing starts
            //player
            playerDeck.addBJ(playingDeck.pullCard());
            playerDeck.addBJ(playingDeck.pullCard());

            //dealer
            dealerDeck.addBJ(playingDeck.pullCard());
            dealerDeck.addBJ(playingDeck.pullCard());

            while (true) {
                System.out.println("Youre hand is: ");
                System.out.print(playerDeck.toString());

                System.out.println("Deck value: " + playerDeck.cardsValueBJ());

                System.out.println("Dealer hand is: ");
                System.out.println(dealerDeck.getCardBJ(0).toString() + " and second one you cant see");


                //player choice
                System.out.println("Hit(1) or Stand(2)");
                int response = userInput.nextInt();

                //they hit
                if (response == 1) {
                    playerDeck.addBJ(playingDeck.pullCard());
                    System.out.println("You draw: " + playerDeck.getCardBJ(playerDeck.deckSizeBJ() - 1).toString());
                    if (playerDeck.cardsValueBJ() > 21) {
                        System.out.println("Bust. Current value: " + playerDeck.cardsValueBJ());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }

                }
                if (response == 2) {
                    break;
                }
            }
            //reveal dealer
            System.out.println("Dealers cards: " + dealerDeck.toString());
            //check score
            if ((dealerDeck.cardsValueBJ() > playerDeck.cardsValueBJ()) && endRound == false) {
                System.out.println("dealer beats you");
                playerMoney -= playerBet;
                endRound = true;
            }
            while((dealerDeck.cardsValueBJ() < 17) && endRound == false){
                dealerDeck.addBJ(playingDeck.pullCard());
                System.out.println("Dealer Draws: " + dealerDeck.getCardBJ(dealerDeck.deckSizeBJ()-1).toString());
            }
            System.out.println("Dealer value: "+ dealerDeck.cardsValueBJ());
            //dealer busts
            if((dealerDeck.cardsValueBJ() > 21)&& endRound==false){
                System.out.println("Dealer busts");
                playerMoney += playerBet;
                endRound=true;
            }
            //push
            if((playerDeck.cardsValueBJ() == dealerDeck.cardsValueBJ()) && endRound == false){
                System.out.println("Push");
                endRound=true;
            }
            if((playerDeck.cardsValueBJ() > dealerDeck.cardsValueBJ()&& endRound==false)){
                System.out.println("Win");
                playerMoney += playerBet;
                endRound=true;
            }
            else if (endRound==false){
                System.out.println("You lose");
                playerMoney -= playerBet;
                endRound =true;
            }
            playerDeck.moveAllToDeckBJ(playingDeck);
            dealerDeck.moveAllToDeckBJ(playingDeck);
            System.out.println("round over");
        }





        System.out.println("No money no game!");

    }
    public static void main(String[] args) {
        blackJack();
    }


}
