public class Skip extends ActionCard {

    public Skip(Color color) {
        super(color, Value.SKIP);
    }

    @Override
    public void performAction(Game game) {
        //action to skip next player's turn in the player list
        System.out.println("Skip card played !! Next player is skipped.");
    }

}
//still the skip action missing we need to do the game first
