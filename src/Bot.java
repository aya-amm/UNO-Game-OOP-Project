public class Bot extends Player{

    public Bot(String name){
        super(name);
    }

    public Card makeMove(Card topCard){
        for(Card card : hand.getCards()){
            if(card.playableOn(topCard)){
                hand.removeCard(card);
                return card;
            }
        }   

        return null;
    }
}
