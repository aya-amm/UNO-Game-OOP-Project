// Interface to determine if a card can be played on top of another card
public interface Playable {
    boolean playableOn(Card top);
}