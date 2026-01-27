import java.io.*;
import java.util.List;
public class State implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final String SAVE_FILE = "uno_game_save.dat";


    private List<Player> players;
    private Deck deck;
    private Card topCard;
    private Player currentPlayer;
    private boolean direction;

    public State(List<Player> players, Deck deck, Card toCard, Player currentPlayer,boolean direction){
        this.players = players;
        this.deck = deck;
        this.topCard = toCard;
        this.currentPlayer = currentPlayer;
        this.direction = direction;
    }

    public void save(){
        //we will need a file to save 
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            out.writeObject(this);
            System.out.println("Game saved successfully to "+ SAVE_FILE);
        } catch (IOException e) {
            System.err.println("Error saving game: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    public static State load(){
        //we need the saved file to get the game state
        File saveFile = new File(SAVE_FILE);

        if (!saveFile.exists()) {
            System.out.println("No save file found.");
            return null;
        }
        try (ObjectInputStream in =new ObjectInputStream(new FileInputStream(SAVE_FILE))){
            State state = (State) in.readObject();
            System.out.println("Game loaded successfully from " + SAVE_FILE);
            return state;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    //saving the file
    public static boolean saveExists(){
        return new File(SAVE_FILE).exists();
    }

    //delete the file
    public static boolean deleteSave() {
        File saveFile = new File(SAVE_FILE);
        if (saveFile.exists()) {
            return saveFile.delete();
        }
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public Card getTopCard() {
        return topCard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getDirection(){
        return direction;
    }
}
