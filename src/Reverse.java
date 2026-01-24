
public class Reverse extends ActionCard {

    public Reverse(Color color) {
        super(color, Value.REVERSE);
    }

    @Override
    public void performAction(Game game) {
       //action to reverse the direction of play(reverse the player list)
       System.out.println("Reverse card played ! Directon reversed.");
       
    }

}