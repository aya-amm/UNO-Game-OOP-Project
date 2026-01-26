public abstract class Player {

    private String name;
    private Hand hand ;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void DrawCard(Card card){
        hand.addCard(card);
    }

    public boolean PlayCard(Card card , Card topCard){
        if(card.playableOn(topCard)){
            hand.removeCard(card);
            return true;
        }
        return false;
    }

    public Hand getHand(){
        return hand;
    }

    public boolean announceUNO(){
        return hand.getSize() == 1;
    }

    public String getName() {
        return name;
    }
    public boolean hasWon(){
        return hand.getSize() == 0;
    }
}
