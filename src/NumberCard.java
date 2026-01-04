public class NumberCard extends Card {

    public NumberCard(Color color, Value value) {
        super(color, value);
    }

    @Override
    public boolean playableOn(Card top) {
        if (top == null) return true;

        return getColor() == top.getColor() || getValue() == top.getValue();
    }
}
