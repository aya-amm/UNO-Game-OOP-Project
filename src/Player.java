public abstract class Player {

    protected String name;
    protected Hand hand ;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void DrawCard(Card card){
        hand.addCard(card);
    }

    public boolean PlayCard(Card card , Card topCard){
        if(card.canBePlayedOn(topCard)){
            hand.removeCard(card);
            return true;
        }
        return false;
    }

    public Hand GetHand(){
        return hand;
    }

    public boolean announceUNO(){
        return hand.getSize() == 1;
    }

    public String getName() {
        return name;
    }

}
