import java.util.Scanner;
import java.util.Random;
public class WildCard extends Card {
    // Wild cards can be WILD or WILD_DRAW4
    public WildCard(Value value) {
        super(Color.WILD, value);
    }


    public void chooseColor(Color newColor) {
        //method to choose color when both WILD and WILD_DRAW4 are played
        setColor(newColor);
    }


     public void performAction(Game game, Scanner scanner) {
        if (game.getCurrentPlayer() instanceof Bot) {
            Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
            Random random = new Random();
            int randColor = random.nextInt(4);
            Color randomColor = colors[randColor];
            chooseColor(randomColor);
            System.out.println(game.getCurrentPlayer().getName() + " changes color to " + randomColor);
        } else {
            System.out.println("Choose a color:");
            System.out.println("1. RED");
            System.out.println("2. YELLOW");
            System.out.println("3. GREEN");
            System.out.println("4. BLUE");
            
            int choice = 0;
            boolean validInput = false;
            
            while (!validInput) {
                try {
                    System.out.print("Enter choice (1-4): ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (choice >= 1 && choice <= 4) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid choice. Please enter 1-4.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }
            
            Color newColor;
            switch (choice) {
                case 1: newColor = Color.RED; break;
                case 2: newColor = Color.YELLOW; break;
                case 3: newColor = Color.GREEN; break;
                case 4: newColor = Color.BLUE; break;
                default: newColor = Color.RED; break;
            }
             
            chooseColor(newColor);
            System.out.println("Color changed to " + newColor);
        }
        
        if (getValue() == Value.WILD_DRAW4) {
            System.out.println("Wild Draw Four played!");
            game.getTurn().nextPlayer();
            Player nextPlayer = game.getCurrentPlayer();
            System.out.println(nextPlayer.getName() + " draws 4 cards.");
            
            for (int i = 0; i < 4; i++) {
                Card drawnCard = game.getDeck().draw();
                if (drawnCard != null) {
                    nextPlayer.drawCard(drawnCard);
                }
            }
            game.getTurn().nextPlayer();
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