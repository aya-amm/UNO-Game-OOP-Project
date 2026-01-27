import java.util.List;
import java.io.Serializable;;
public class Turn implements Serializable{
    private static final long serialVersionUID = 1L;

    
    private List<Player> players;
    private boolean direction;//true = clockwise,false = counter-clockwise
    private int currentPlayerIndex;

    public Turn(List<Player> players){
        this.players = players;
        this.direction = true; //clockwise
        this.currentPlayerIndex = 0;
    }


    //go to next player
    public Player nextPlayer(){
        if (direction) {
            currentPlayerIndex = (currentPlayerIndex + 1)% players.size();//so we can back to 0
        }
        else{
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
        return players.get(currentPlayerIndex);
    }

    //reverse direction
    public void reverseOrder(){
        direction = !direction;
    }

    //get the current player
    public Player getCurrentPlaye() {
        return players.get(currentPlayerIndex);
    }

    //saving the game in state will need this
    public boolean isClockwise(){
        return direction;
    }

}
