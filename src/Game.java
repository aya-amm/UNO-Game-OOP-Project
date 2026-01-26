import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private Deck deck;
    private Card topCard;
    private Turn turn;
    private State state;
    private Scanner scanner;
    private boolean gameRunning;

    public Game(){
        this.players = new ArrayList<>();
        this.deck = new Deck();
        this.scanner = new Scanner(System.in);
        this.gameRunning = false;
    }
    private void createListPlayer(){

    }

    //Start the game (create the players and draw the cards )
    public void start(){
        System.out.println("==== WELCOME TO UNO ====");
        createListPlayer();

        for(Player player : players){
            for (int i = 0; i < 7; i++) {
                Card card = deck.draw();
                if (card != null) {
                    player.DrawCard(card);
                }
            }
        }

        //get the top card (cant be wild)
        do {
            topCard = deck.draw();
        } while (topCard instanceof WildCard);

        deck.addDiscardPile(topCard);
        System.out.println("Starting Card : "+ topCard);

        turn = new Turn();
        gameRunning = true;

        playGame();
    }


    public Card getTopCard() {
        return topCard;
    }
    private void playGame(){}

}
