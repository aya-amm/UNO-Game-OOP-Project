import java.io.Serializable;
public abstract class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Hand hand ;
    private boolean hasAnnouncedUNO;//tracks if player announced UNO

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.hasAnnouncedUNO = false;
    }

    public void drawCard(Card card){
        hand.addCard(card);
        // If player draws and now has more than 1 card, reset UNO announcement
        if (hand.getSize() > 1) {
            hasAnnouncedUNO = false;
        }
    }

    public boolean playCard(Card card , Card topCard){
        if(card.playableOn(topCard)){
            hand.removeCard(card);
            return true;
        }
        return false;
    }

    public Hand getHand(){
        return hand;
    }
    //Player announces UNO
    public void announceUNO() {
        this.hasAnnouncedUNO = true;
    }

    // Check if player has announced
    public boolean hasAnnouncedUNO() {
        return hasAnnouncedUNO;
    }

    //Check if player have to announce (has 1 card)
    public boolean shouldAnnounceUNO() {
        return hand.getSize() == 1;
    }

    //Reset announcement (for penalties)
    public void resetUNOAnnouncement() {
        this.hasAnnouncedUNO = false;
    }

    public String getName() {
        return name;
    }
    public boolean hasWon(){
        return hand.getSize() == 0 && hasAnnouncedUNO;
    }
}
//done
