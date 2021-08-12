package app.game;

import app.cards.Card;
import app.cards.Deck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Locale;
import java.util.Scanner;

public class HigherOrLower extends Deck {
    public enum HigherOrLowerState {
        Win,
        Draw,
        Lose
    }
    private static final Logger logger = LoggerFactory.getLogger(HigherOrLower.class);

    Deck game = new Deck();
    Scanner scanner = new Scanner(System.in);

    public HigherOrLower() {
        game.getDeck();
        game.shuffle();
    }

    public HigherOrLowerState gameLoop() {
        Card firstCard = game.pullCard();
        Card secondCard = game.pullCard();
        int firstCardValue = firstCard.getValue();
        int secondCardValue = secondCard.getValue();

        logger.warn(Integer.toString(firstCardValue), firstCard);
        logger.warn(Integer.toString(secondCardValue), secondCard);

        if (firstCardValue > secondCardValue) {
            return HigherOrLowerState.Win;
        } else if (firstCardValue == secondCardValue) {
            return HigherOrLowerState.Draw;
        } else {
            return HigherOrLowerState.Lose;
        }
    }

    public boolean continueGame() {
        System.out.print("Continue? (y/n): ");
        String s = scanner.nextLine();
        if ("y".equals(s.toLowerCase(Locale.ROOT))) {
            return true;
        } else if ("n".equals(s.toLowerCase(Locale.ROOT))) {
            return false;
        } else {
            return false;
        }
    }

    public void higherOrLower() {
        HigherOrLowerState state = gameLoop();
        if (state == HigherOrLowerState.Win) {
            System.out.println("WIN");
            if (continueGame()) {
                higherOrLower();
            }
        } else if (state == HigherOrLowerState.Draw) {
            System.out.println("DRAW");
        } else if (state == HigherOrLowerState.Lose) {
            System.out.println("LOSE");
        }
    }

    public static void main(String[] args) {
        HigherOrLower game = new HigherOrLower();
        game.higherOrLower();
    }

}