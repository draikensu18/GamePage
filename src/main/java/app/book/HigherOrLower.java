package app.book;
import app.cards.Card;
import app.cards.Deck;

public class HigherOrLower extends Deck {

    public HigherOrLower() {
    }

    public static void main(String[] args) {

        HigherOrLower game = new HigherOrLower();

        game.getDeck();
        game.shuffle();

        Integer firstCard = game.pullCard().getValue();

        System.out.println(firstCard);

        Integer secondCard = game.pullCard().getValue();

        System.out.println(secondCard);
        System.out.println(game.pullCard().getValue());
        System.out.println(game.pullCard().getValue());
        System.out.println(game.pullCard().getValue());
        System.out.println(game.pullCard().getValue());


        if(firstCard > secondCard){
            System.out.println("you win");
        } else {
            System.out.println("you lose");
        }

    }

}
