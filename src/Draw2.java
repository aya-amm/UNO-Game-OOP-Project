
public class Draw2 extends ActionCard {

    public Draw2(Color color) {
        super(color, Value.DRAW2);
    }

    @Override
    public void  performAction(Game game) {
        //action to make next player draw 2 cards
        System.out.println("Draw 2 card played!");

        //Get the next player from game s turn 
        Player nextPlayer = game.getTurn().nextPlayer();

        //Make the next player draw 2 cards
         for (int i = 0; i < 2; i++) {
            Card drawn = game.getDeck().draw();
            if (drawn != null) {
                nextPlayer.drawCard(drawn);
            }
        }
        

        // Skip next player's turn
        game.getTurn().skipNextPlayer();
    }

}
//done