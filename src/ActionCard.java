
public abstract class ActionCard extends Card {

    public ActionCard(Color color,Value value) {
        super(color, value);
    }


    public abstract void performAction();

    @Override
    public boolean playableOn(Card top) {
        if (top == null) return true;

        return getColor() == top.getColor() || getValue() == (top.getValue());
    }


}
