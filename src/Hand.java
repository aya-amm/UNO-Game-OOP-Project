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
            if(c.playableOn(topCard)){
                return true;
            }
        }
        return false;

    }

    public int getSize() {
        return size;
    }

    public void displayHand(){
        System.out.println("Your hand ( "+ size+" cards ): ");
        for (int i = 0; i < cards.size(); i++) {
            System.out.println((i+1)+ ". "+cards.get(i));
        }
    }

    //to select card by index 
    public Card getCardAt(int index){
        if (index >=1 && index <= cards.size()) {
            return cards.get(index);
        }
        return null;
    }
}
