import java.util.Scanner;

public class WildCard extends Card {
    // Wild cards can be WILD or WILD_DRAW4
    public WildCard(Value value) {
        super(Color.WILD, value);
    }


    public void chooseColor(Color newColor) {
        //method to choose color when both WILD and WILD_DRAW4 are played
        setColor(newColor);
    }


    public void performAction(Game game,Scanner scanner) {
        //choose new color


        //if wild draw 4 make next player draw 4 cards
        if (getValue() == Value.WILD_DRAW4) {
            System.out.println("Wild Draw Four played!");
        }
    }

    @Override
    public boolean playableOn(Card top) {
        
        return true;//always playable
    }

}
//still the wild and wild draw 4 action missing we need to do the game first
//and we can over ride the to string so we can print