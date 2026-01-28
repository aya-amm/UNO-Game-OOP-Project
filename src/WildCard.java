
public class WildCard extends Card {
    // Wild cards can be WILD or WILD_DRAW4
    public WildCard(Value value) {
        super(Color.WILD, value);
    }


    public void chooseColor(Color newColor) {
        //method to choose color when both WILD and WILD_DRAW4 are played
        setColor(newColor);
    }


    public void performAction(Game game) {
    
         System.out.println("Wild card played!");

        // Handle Wild Draw 4
        if (getValue() == Value.WILD_DRAW4) {
            System.out.println("Wild Draw 4 played!");

            Player nextPlayer = game.getTurn().getCurrentPlayer();

            // Draw 4 cards
            for (int i = 0; i < 4; i++) {
                Card drawn = game.getDeck().draw();
                if (drawn != null) {
                    nextPlayer.drawCard(drawn);
                }
            }

            // Skip next player s turn
            game.getTurn().skipNextPlayer();
        }
    }
       
    

    @Override
    public boolean playableOn(Card top) {
        
        return true;//always playable
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

}
//done