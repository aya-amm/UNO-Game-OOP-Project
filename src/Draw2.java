
public class Draw2 extends ActionCard {

    public Draw2(Color color) {
        super(color, Value.DRAW2);
    }

    @Override
    public void  performAction(Game game) {
        //action to make next player draw 2 cards
        System.out.println("Draw 2 card played!");

    }

}