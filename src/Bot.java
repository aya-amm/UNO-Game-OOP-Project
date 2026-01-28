public class Bot extends Player{

    public Bot(String name){
        super(name);
    }

    public Card makeMove(Card topCard){
        for(Card card : getHand().getCards()){
            if(card.playableOn(topCard)){// we dont remove here we handle it in the game
                return card;
            }
        }   

        return null;
    }

    public Card.Color chooseColor() {
    //choose the first color of cards in hand, or random
    for (Card c : getHand().getCards()) {
        if (!(c instanceof WildCard)) {
            return c.getColor(); 
        }
    }
    return Card.Color.RED; // default if no other cards
}
}
//added choose color method for bot to choose color when wild or wild draw4 is played
//done