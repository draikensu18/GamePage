package app.cards;

public class Card {

    private final Face face;
    private final Suit suit;

    public Card(Face face, Suit suit) {
        this.suit = suit;
        this.face = face;
    }

    public Suit getSuit() {
        return suit;
    }

    public Face getFace() {
        return face;
    }

    public int getValue() {
        return face.ordinal() + 2;
    }

    @Override
    public String toString() {
        return "Card{" +
                "face=" + face +
                ", suit=" + suit +
                '}';
    }
}