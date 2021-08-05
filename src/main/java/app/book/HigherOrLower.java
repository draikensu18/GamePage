package app.book;

import app.cards.Card;
import app.cards.Deck;

import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class HigherOrLower extends Deck {
    Deck game = new Deck();

    public HigherOrLower() {
        game.getDeck();
        game.shuffle();
    }

    public void higherOrLower() {
        Card firstCard = game.pullCard();
        Card secondCard = game.pullCard();
        Integer firstCardValue = firstCard.getValue();
        Integer secondCardValue = secondCard.getValue();
        Integer nextCard = 0;
        System.out.println(firstCard.toString() + firstCardValue);
        System.out.println(secondCard.toString() + secondCardValue);
        Scanner sc = new Scanner(System.in);
        String cmd = "";
        boolean end = false;

        while (!end) {
            if (firstCardValue > secondCardValue) {
                System.out.println("you win");
                System.out.println("pick another card? yes/no");
                cmd = sc.next().toLowerCase();
                if (cmd.equals("no")){
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
        }

    }

    public static void main(String[] args) {
        HigherOrLower game = new HigherOrLower();
        game.higherOrLower();
      }

}
