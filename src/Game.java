import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

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
    

    //Start the game (create the players and draw the cards )
    public void start(){
        System.out.println("==== WELCOME TO UNO ====");

        //we saved the game before case
        if (State.saveExists()) {
            System.out.println("Save file found! Do you want to load it? (yes/no):" );
            String respone = scanner.nextLine();
            if (respone.equalsIgnoreCase("yes")) {
                if (loadGame()) {
                    playGame();
                }
                else{
                    System.out.println("Failed to load. Starting new game...");  
                }
            }
        }

        createListPlayer();//create players

        for(Player player : players){//draw 7 to each player
            for (int i = 0; i < 7; i++) {
                Card card = deck.draw();
                if (card != null) {
                    player.drawCard(card);
                }
            }
        }

        //get the top card (cant be wild)
        do {
            topCard = deck.draw();
        } while (topCard instanceof WildCard);

        deck.addDiscardPile(topCard);
        System.out.println("Starting Card : "+ topCard);

        turn = new Turn(players);
        gameRunning = true;

        playGame();
    }
    
    private void createListPlayer(){
        int numPlayers = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter number of players (2-4): ");//max 4 players we can change it later
                numPlayers = scanner.nextInt();

                if (numPlayers >= 2 && numPlayers <= 4) {
                    validInput = true;
                }
                else {
                    System.out.println("Invalid number. Please enter 2-4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }

        //player information

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter name for Player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            players.add(new Human(name));
        }

        System.out.println("Add bot players? (yes/no): ");
        String addBot = scanner.nextLine();

        if (addBot.equalsIgnoreCase("yes")) {
            int numBot = 0;
            validInput = false;

            while (!validInput) {
                try {
                    System.out.print("How many bots? ");
                    numBot = scanner.nextInt();
                    scanner.nextLine();

                    if (numBot >= 1 && numBot <=4) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid number. Please enter 1 or more (not more than 4).");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }

            for (int i = 0; i < numBot; i++) {
                players.add(new Bot("Bot " + (i + 1)));
            }
        }

        System.out.println("Players in game:");
        for (Player p : players) {
            System.out.println("- " + p.getName());
        }
    }


    private void playGame(){}//the game play
    //play game we will check if he want to leave game and we will use other methods to play the game (playerTurn methods and other methods)

    private void playHumanTurn(Human player) { // for human player
        System.out.println("\nTop Card: " + topCard);
        player.getHand().displayHand();
        System.out.println("Choose card number to play, D to draw, S to save and quit:");

        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("S")) {
            state = new State(players, deck, topCard, player, turn.isClockwise());
            state.save();
            gameRunning = false;
            return;
        }

        if (input.equalsIgnoreCase("D")) {
            Card drawn = deck.draw();
            if (drawn != null) {
                player.drawCard(drawn);
                System.out.println("Drew: " + drawn);
                if (drawn.playableOn(topCard)) {
                    System.out.println("Play drawn card? (yes/no): ");
                    String resp = scanner.nextLine().trim();
                    if (resp.equalsIgnoreCase("yes") || resp.equalsIgnoreCase("y")) {
                        if (player.PlayCard(drawn, topCard)) {
                            deck.addDiscardPile(drawn);
                            playCardAction(player, drawn);
                            topCard = drawn;
                            if (player.announceUNO()) System.out.println("UNO! " + player.getName());
                        }
                    }
                }
            } else {
                System.out.println("No cards to draw.");
            }
            return;
        }

        try {
            int idx = Integer.parseInt(input);
            List<Card> handCards = player.getHand().getCards();
            if (idx >= 1 && idx <= handCards.size()) {
                Card selected = handCards.get(idx - 1);
                if (player.PlayCard(selected, topCard)) {
                    deck.addDiscardPile(selected);
                    playCardAction(player, selected);
                    topCard = selected;
                    if (player.announceUNO()) System.out.println("UNO! " + player.getName());
                } else {
                    System.out.println("Cannot play this card.");
                }
            } else {
                System.out.println("Invalid card number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private void playBotTurn(Bot bot) { //the bot turn
        System.out.println("\nTop Card: " + topCard);
        Card toPlay = bot.makeMove(topCard);

        if (toPlay != null) {
            if (bot.PlayCard(toPlay, topCard)) {
                deck.addDiscardPile(toPlay);
                playCardAction(bot, toPlay);
                topCard = toPlay;
                System.out.println(bot.getName() + " played: " + toPlay);
                if (bot.announceUNO()) {
                    System.out.println("UNO! " + bot.getName());
                return;
                }
            }
        }

        Card drawn = deck.draw();
        if (drawn != null) {
            bot.drawCard(drawn);
            System.out.println(bot.getName() + " drew a card.");
            if (drawn.playableOn(topCard)) {
                if (bot.PlayCard(drawn, topCard)) {
                    deck.addDiscardPile(drawn);
                    playCardAction(bot, drawn);
                    topCard = drawn;
                    System.out.println(bot.getName() + " played drawn: " + drawn);
                    if (bot.announceUNO()) System.out.println("UNO! " + bot.getName());
                }
            }
        } else {
            System.out.println(bot.getName() + " could not draw a card.");
        }
    }

    private void playCardAction(Player player , Card card){}//we call the perform action of the card we played and announce UNO 

    private boolean checkWin(){ // check if he said uno and if he have no more cards
        return false;
    }

    //we ask player to announce UNO Type it
    private void checkUNOChallenge(Player currentPlayer) {

        // Check if current player has exactly 1 card

        if (currentPlayer.shouldAnnounceUNO()) {
        
            if (currentPlayer instanceof Human && !currentPlayer.hasAnnouncedUNO()) {
                // Give current human player chance to announce UNO
                System.out.println(currentPlayer.getName() + " has 1 card left!");
                System.out.print(currentPlayer.getName() + ", do you have something to say? ");
                String response = scanner.nextLine().trim();//a string whose value is this string, with all leading and trailing space removed, or this string if it has no leading or trailing space.
            
                if (response.equalsIgnoreCase("UNO")) {
                    currentPlayer.announceUNO();
                    System.out.println("Wohooo " + currentPlayer.getName() + " says UNO!");
                } else {
                    if (!response.isEmpty()) {
                        System.out.println(currentPlayer.getName() + " said: \"" + response + "\"");
                    }
                    System.out.println("Oh noo  " + currentPlayer.getName() + " did not say UNO!");
                }
            } else if (currentPlayer instanceof Bot && !currentPlayer.hasAnnouncedUNO()) {
                // Bot automatically announces
                currentPlayer.announceUNO();
                System.out.println("Wohooo " + currentPlayer.getName() + " says UNO!");
            }
        
            // Now check if other players want to challenge (if they didn't announce)
            if (!currentPlayer.hasAnnouncedUNO()) {
                for (Player p : players) {
                    if (p != currentPlayer && p instanceof Human) {
                        System.out.print("\n" + p.getName() + ", do you want to call out " + 
                                   currentPlayer.getName() + " for not saying UNO? (yes/no): ");
                        String response = scanner.nextLine();
                    
                        if (response.equalsIgnoreCase("yes")) {
                            System.out.println("OH noo! " + currentPlayer.getName() + " forgot to say UNO! Drawing 2 penalty cards!");
                        
                            for (int i = 0; i < 2; i++) {
                                Card penaltyCard = deck.draw();
                                if (penaltyCard != null) {
                                    currentPlayer.drawCard(penaltyCard);
                                }
                            }
                        
                            currentPlayer.resetUNOAnnouncement();
                            return;
                        }
                    }
                }
            }
        }
    }

    public void saveGame(){
        state = new State(players, deck, topCard, getCurrentPlayerIndex(), turn.isClockwise());
        state.save();

    }
    public boolean loadGame(){
        // if we saved the game and we want to continue we need to use it
        State loadedState = State.load();
        if (loadedState != null) {
            players = loadedState.getPlayers();
            deck = loadedState.getDeck();
            topCard = loadedState.getTopCard();
            turn = new Turn(players);//in the state we saved direction not turn so we have to create new turn

            restoreTurnState(loadedState.getCurrentPlayerIndex(), loadedState.getDirection());

            gameRunning = true;

            System.out.println(" Game restored successfully.");

            System.out.println("Players:");
            for (Player p : players) {
                System.out.println("  - " + p.getName() + " (" + p.getHand().getSize() + " cards)");
            }

            System.out.println("Current player: " + turn.getCurrentPlayer().getName());
            System.out.println("Top card: " + topCard);
            return true;
        } 
        return false;
    }
    
    //part of the load game

    private void restoreTurnState(int playerIndex, boolean direction){
        for (int i = 0; i < playerIndex; i++) {
            turn.nextPlayer();
        }
        if (!direction) {//if when we saved the direction was reverse thats mean its have to be reveresed in the new turn we created, if direction = false ,!direction = true
            turn.reverseOrder();
        }
    }

    private int getCurrentPlayerIndex(){
        Player current = turn.getCurrentPlayer();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) == current) {
                return i;
            }
        }
        return 0 ;
    }

    //with load game
    public void resume(){
        if (loadGame()) {
            playGame();
        } else{
            System.out.println("No save file found or failed to load.");
        }
    }

    public State getCurrentState(){
        return new State(players, deck, topCard, getCurrentPlayerIndex(), turn.isClockwise());
    }



    //getters
    public Deck getDeck() {
        return deck;
    }

    public Card getTopCard() {
        return topCard;
    }
    
    public Turn getTurn() {
        return turn;
    }


    public Player getCurrentPlayer() {
        return turn.getCurrentPlayer();
    }

    public void cleanup() {
        if (scanner != null) {
            scanner.close();
        }
    }

}
