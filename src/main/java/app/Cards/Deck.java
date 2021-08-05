package app.Cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deckOfCards;
    private ArrayList<Card> pulledCards;

    public Deck() {
        pulledCards = new ArrayList<Card>();
        deckOfCards = new ArrayList<Card>();
    }

    public ArrayList<Card> getDeck() {

        for (Suit suit : Suit.values()) {
            for(Face face: Face.values()){
                deckOfCards.add(new Card(face, suit));
            }
        }
        return deckOfCards;
    }

    public void shuffle(){
        Collections.shuffle(deckOfCards);
    }

    public Card pullCard(){
        pulledCards.add(deckOfCards.get(0));
        deckOfCards.remove(0);
        return pulledCards.get(0);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deckOfCards=" + deckOfCards +
                '}';
    }
}
