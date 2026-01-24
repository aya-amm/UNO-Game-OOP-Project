public abstract class ActionCard extends Card implements Actionable {

    public ActionCard(Color color,Value value) {
        super(color, value);
    }

    @Override
    public abstract void performAction(Game game);

    @Override
    public boolean playableOn(Card top) {
        if (top == null) return true;

        return getColor() == top.getColor() || getValue() == (top.getValue());
    }


}
