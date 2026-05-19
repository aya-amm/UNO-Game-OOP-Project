package game;
import java.io.*;
import java.util.List;
public class State implements Serializable{

    //we use Serializable to turn the object we wnat to save into bytes and save it in a file 
    private static final long serialVersionUID = 1L;//version number of the class ,When we save an object, Java saves this number with it  When we load it back, Java checks if the numbers match
    private static final String SAVE_FILE = "uno_game_save.dat";


    private List<Player> players;
    private Deck deck;
    private Card topCard;
    private int getCurrentPlayerIndex;
    private boolean direction;

    public State(List<Player> players, Deck deck, Card toCard, int getCurrentPlayerIndex,boolean direction){
        this.players = players;
        this.deck = deck;
        this.topCard = toCard;
        this.getCurrentPlayerIndex = getCurrentPlayerIndex;
        this.direction = direction;
    }

    public void save(){
        //we will need a file to save 
        //FileOutputStream(SAVE_FILE) Opens "uno_game_save.dat" for writing
        //ObjectOutputStream Wraps it to enable object writing we can say  adds ability to write objects
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            out.writeObject(this); /*Checks if State implements Serializable 
                                    Checks if all fields are serializable 
                                    Converts the entire object graph to bytes
                                    Writes bytes to the file*/
            System.out.println("Game saved successfully to "+ SAVE_FILE);
        } catch (IOException e) {//like disk full
            System.err.println("Error saving game: "+ e.getMessage());
            e.printStackTrace();//Prints the full error details  , Shows exactly where the error occurred
        }
        //try with resource auto close the resource when its done , if we didnt use it we will need to do a finally to close the file
    }

    public static State load(){//static cuz we will put it in a State object so we need to call it without an object
        //we need the saved file to get the game state
        File saveFile = new File(SAVE_FILE);

        if (!saveFile.exists()) {
            System.out.println("No save file found.");
            return null;
        }
        try (ObjectInputStream in =new ObjectInputStream(new FileInputStream(SAVE_FILE))){//read file convert from byte to object 
            Object obj = in.readObject();
            State state = (State) obj;  //Reads the object from file where the return type is object so we do (State)
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

    public int getCurrentPlayerIndex() {
        return getCurrentPlayerIndex;
    }

    public boolean getDirection(){
        return direction;
    }
}
//done