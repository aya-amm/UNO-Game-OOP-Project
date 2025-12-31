import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;
    private int size;

    public Hand() {
        this.cards = new ArrayList<>();
        this.size = 0;
    }

    public void addCard(Card card) {
        cards.add(card);
        size++;
    }
    public boolean removeCard(Card card) {
        boolean removed = cards.remove(card);
        if (removed) {
            size--;
        }
        return removed;
    }

    public List<Card> getCards() {
        return cards;
    }   

    public boolean hasPlayableCard(Card topCard) {
        for (Card c : cards){
            if(c.canBePlayedOn(topCard)){
                return true;
            }
        }
        return false;

    }

    public int getSize() {
        return size;
    }
}
