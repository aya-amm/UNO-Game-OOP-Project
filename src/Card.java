
public abstract class Card implements Playable {
    public enum Color{
    RED,GREEN,BLUE,YELLOW,WILD
    }
    public enum Value{
    ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,
    DRAW2,SKIP,REVERSE,WILD,WILD_DRAW4
    }
    private  Value value;
    private  Color color;
    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }


    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setValue(Value value) {
        this.value = value;
    }
    
    @Override
    public abstract boolean playableOn(Card top);

    // methode to return string representation of the card
    @Override
    //Card overrides Object s toString() so all cards have a readable printout.
    public String toString() {
    if (getColor() == Color.WILD) {
        return value.toString();
    }
    return color + " " + value;
    }
}
