package app.cards;

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
        return pulledCards.get(pulledCards.size() - 1);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deckOfCards=" + deckOfCards +
                '}';
    }

    public void addBJ(Card pullCard) {
        this.deckOfCards.add(pullCard);
    }

    public Card getCardBJ(int i) {
        return this.deckOfCards.get(i);
    }
    public int deckSizeBJ(){
        return this.deckOfCards.size();
    }

    public void moveAllToDeckBJ(Deck moveTo){
        int thisDeckSize = this.deckOfCards.size();

        for(int i=0; i < thisDeckSize; i++){
            moveTo.addBJ(this.getCardBJ(i));
        }
        for(int i=0; i < thisDeckSize; i++){
            this.removeCardBJ(0);
        }
    }

    private void removeCardBJ(int i) {
        this.deckOfCards.remove(i);
    }

    //blackjack point system (only for blackJack)
    public int cardsValueBJ() {
        int totalValue = 0;
        int aces = 0;
        for(Card aCard : this.deckOfCards){


            switch(aCard.getFace()){
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;
            }

        }

        for(int i = 0; i < aces; i ++){
            if(totalValue > 10){
                totalValue +=1;
            }
            else{
                totalValue +=11;
            }
        }

        return totalValue;
    }



}
