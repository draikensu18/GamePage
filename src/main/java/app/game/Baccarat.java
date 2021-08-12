package app.game;

import app.cards.Card;
import app.cards.Deck;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Baccarat extends Deck {
    Deck game = new Deck();

    public Baccarat() {
        game.getDeck();
        game.shuffle();
    }

    public void baccarat() {
        //create two empty decks
        ArrayList<Card> playerStack = new ArrayList<>();
        ArrayList<Card> bankerStack = new ArrayList<>();

        //create values
        int playerStackValue = 0;
        int bankerStackValue = 0;
        int balance = 100;

        boolean end = false;
        while (!end) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Your balance is: " + balance);
            System.out.println("Place your bet ==> Player(P); Banker(B); Tie(T)");
            String cmd;
            cmd = scanner.nextLine().toLowerCase();
            //pull cards
            for (int i = 0; i < 2; i++) {
                playerStack.add(game.pullCard());
                bankerStack.add(game.pullCard());
            }

            //get card values
            for (Card i : playerStack) {
                playerStackValue += i.getValue();
            }
            //playerStackValue is more or equals 10 (10=0; 11=1; 12=2; 13=3 ect.)
            if (playerStackValue >= 10) {
                playerStackValue %= 10;
            }
            //playerStackValue is less or equals 6 ==> should pull thirdCard
            if (playerStackValue <= 6) {
                playerStack.add(game.pullCard());
                playerStackValue += playerStack.get(2).getValue();
                if (playerStackValue >= 10) {
                    playerStackValue %= 10;
                }
            }
            for (Card i : playerStack) {
                System.out.println("Player cards: " + i.toString());
            }
            System.out.println(playerStackValue);

            for (Card i : bankerStack) {
                bankerStackValue += i.getValue();
            }
            if (bankerStackValue >= 10) {
                bankerStackValue %= 10;
            }
            if (bankerStackValue <= 6) {
                bankerStack.add(game.pullCard());
                try {
                    bankerStackValue += playerStack.get(2).getValue();
                } catch(IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
                if (bankerStackValue >= 10) {
                    bankerStackValue %= 10;
                }
            }
            for (Card i : bankerStack) {
                System.out.println("Banker cards: " + i.toString());
            }
            System.out.println(bankerStackValue);
            try{
                if (playerStackValue == bankerStackValue) {
                    System.out.println("Tie");
                    if (cmd.equals("t") ) {
                        System.out.println("YOU WIN");
                        balance += 100;
                    } else {
                        System.out.println("YOU LOOSE");
                        balance -= 100;
                    }
                } else if (playerStackValue > bankerStackValue) {
                    System.out.println("Player wins");
                    if (cmd.equals("p")) {
                        System.out.println("YOU WIN");
                        balance += 100;
                    } else {
                        System.out.println("YOU LOOSE");
                        balance -= 100;
                    }
                } else {
                    System.out.println("Banker wins");
                    if (cmd.equals("b")) {
                        System.out.println("YOU WIN");
                        balance += 100;
                    } else {
                        System.out.println("YOU LOOSE");
                        balance -= 100;
                    }
                }
                System.out.println("play again? yes/no");
                cmd = scanner.nextLine();
                if (cmd.equals("no")) {
                    System.out.println("EXIT");
                    end = true;
                } else if(cmd.equals("yes") && balance == 0){
                    System.out.println("no money no game");
                    System.out.println("EXIT");
                    end = true;
                } else{
                    baccarat();
                }
                scanner.close();
            }catch (NoSuchElementException e) {
                end = true;
            }
        }
    }
    public static void main(String[] args) {
        Baccarat game = new Baccarat();
        game.baccarat();
    }
}