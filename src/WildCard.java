
public class WildCard extends Card {
    // Wild cards can be WILD or WILD_DRAW4
    public WildCard(Color color,Value value) {
        super(color, value);
    }


    public void chooseColor(Color newColor) {
        this.color = newColor;//method to choose color when both WILD and WILD_DRAW4 are played
    }

    public void performAction() {
        if (WildCard.Value.WILD_DRAW4 == this.value) {
             //action to make next player draw 4 cards and skip their turn when the card is WILD_DRAW4
        }
    }

    @Override
    public boolean playableOn(Card top) {
        
        return true;//always playable
    }

}
