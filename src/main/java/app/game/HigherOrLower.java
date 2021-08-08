package app.game;

import app.cards.Card;
import app.cards.Deck;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class HigherOrLower extends Deck {
    Deck game = new Deck();

    public HigherOrLower() {
        game.getDeck();
        game.shuffle();
    }

    public void higherOrLower() {
        boolean end = false;
        while (!end) {
        Card firstCard = game.pullCard();
        Card secondCard = game.pullCard();
        Integer firstCardValue = firstCard.getValue();
        Integer secondCardValue = secondCard.getValue();
        Integer nextCard = 0;
        System.out.println(firstCard.toString() + firstCardValue);
        System.out.println(secondCard.toString() + secondCardValue);
        String cmd = "";
        try {
            Scanner sc = new Scanner(System.in);
            if (firstCardValue > secondCardValue) {
                System.out.println("you win");
                System.out.println("pick another card? yes/no");
                cmd = sc.nextLine().toLowerCase();
                if (cmd.equals("no")) {
                    end = true;
                } else {
                    higherOrLower();
                }
            } else if (firstCardValue.equals(secondCardValue)) {
                System.out.println("equal cards");
                end = true;
            } else {
                System.out.println("you lose, better luck next time");
                end = true;
            }

            sc.close();

        }catch (NoSuchElementException e) {
            end = true;
        }

        }
    }

    public static void main(String[] args) {
        HigherOrLower game = new HigherOrLower();
        game.higherOrLower();
      }

}
