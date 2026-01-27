import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.Serializable;

public class Deck implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<Card> drawPile;
    private List<Card> discardPile;

    public Deck(){
        drawPile = new ArrayList<>();
        discardPile = new ArrayList<>();
        createCard();
        shuffle();

    }

    private void createCard(){
        Card.Color[] colors = {Card.Color.RED,Card.Color.YELLOW,Card.Color.GREEN,Card.Color.BLUE};


        //Create number card
        for(Card.Color color: colors){


            // create the number 0 card just one for each color  
            drawPile.add(new NumberCard(color, Card.Value.ZERO));


            //create the other numbers ,Two for each color
            Card.Value[] numbers = {Card.Value.ONE, Card.Value.TWO, Card.Value.THREE, Card.Value.FOUR, Card.Value.FIVE, Card.Value.SIX,Card.Value.SEVEN, Card.Value.EIGHT, Card.Value.NINE };

            for (Card.Value num : numbers) {
                drawPile.add(new NumberCard(color,num ));
                drawPile.add(new NumberCard(color, num));
            }

            //create Action Cards 2 card for each color
            for (int i = 0; i < 2; i++) {
                drawPile.add(new Skip(color));
                drawPile.add(new Reverse(color));
                drawPile.add(new Draw2(color));
            }

        }

        //Create wild cards 4 cards for each type
        for (int i = 0; i < 4; i++) {
            drawPile.add(new WildCard(Card.Value.WILD));
            drawPile.add(new WildCard(Card.Value.WILD_DRAW4));
        }
    }

    private void shuffle(){
        Collections.shuffle(drawPile);
    }

    public Card draw(){
        if (drawPile.isEmpty()) {
            resetDiscardPile();
        }

        //if there is a lot of plyers and the discared is empty and the draw pile is empty
        if (drawPile.isEmpty()) {
            System.out.println("No cards left to draw.");
            return null;
        }

        int last = drawPile.size() - 1;
        Card c = drawPile.get(last);
        drawPile.remove(last);
        return c;
    }

    public void reset(){
        drawPile.clear();
        discardPile.clear();
        createCard();
        shuffle();
    }

    public void addDiscardPile(Card card){
        discardPile.add(card);
    }

    public Card topDiscardPile(){
        if (discardPile.isEmpty()) {
            return null;
        }
        return discardPile.get(discardPile.size() - 1);
    }

    private void resetDiscardPile(){
        if (discardPile.size() < 1) {
            return;
        }

        int n = discardPile.size();
        Card topCard = discardPile.get(n - 1);

        drawPile.addAll(discardPile);

        discardPile.clear();
        discardPile.add(topCard);
        shuffle();
        System.out.println("Discard pile reshuffled into draw pile.");
    }

    public int getDrawPileSize() {
        return drawPile.size();
    }
}
//done
