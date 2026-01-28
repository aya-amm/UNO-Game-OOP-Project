public class Skip extends ActionCard {

    public Skip(Color color) {
        super(color, Value.SKIP);
    }

    @Override
    public void performAction(Game game ) {
        //action to skip next player's turn in the player list
        System.out.println("Skip card played ! Next player is skipped.");
        
        
        // Skip the next player's turn
        game.getTurn().nextPlayer();
    }

}
//done
