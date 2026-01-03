import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.smartcardio.Card;

public class Deck {
    private List<Card> drawPile;
    private List<Card> discardPile;

    public Deck(){
        drawPile = new ArrayList<>();
        discardPile = new ArrayList<>();
        createCard();
        shuffle();

    }

    private void createCard(){

    }

    private void shuffle(){
        Collections.shuffle(drawPile);
    }

    public Card draw(){
        if (drawPile.isEmpty()) {
            resetDiscardPile();
        }
        int last = drawPile.size() - 1;
        Card c = drawPile.get(last);
        drawPile.remove(last);
        return c;
    }

    public void reset(){
        drawPile.clear();
        discardPile.clear();
        createCard();
        shuffle();
    }

    public void addDiscardPile(Card card){
        discardPile.add(card);
    }

    public Card topDiscardPile(){
        return discardPile.get(discardPile.size() - 1);

    }

    private void resetDiscardPile(){
        int n = discardPile.size();
        Card topCard = discardPile.get(n - 1);
        drawPile.addAll(discardPile);

        discardPile.clear();
        discardPile.add(topCard);
        shuffle();

    }


}
