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
}
//done