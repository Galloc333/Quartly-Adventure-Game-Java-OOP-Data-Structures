/**
 * GameManager class manages the game environment, including players, rooms, items, and interactions.
 */
public class GameManager implements Cloneable {
    private QuartlyLinkedList<Room> rooms; // linked list to store the rooms in the game environment
    private Player currentPlayer; // Current player in the game

    /**
     * Constructor for GameManager class.
     * Initializes currentPlayer, currentRoom, and rooms to null.
     */
    GameManager() {
        this.currentPlayer = null;
        this.rooms = new QuartlyLinkedList<>();
    }

    /**
     * Adds a player to the game.
     *
     * @param player The player to be added.
     */
    public void addPlayer(Player player) {
        if (this.currentPlayer == null) {
            currentPlayer = player;
            System.out.println(player.getName() + " was added to the game.");
        } else {
            System.out.println("Could not add " + player.getName() + " to the game.");
        }
    }

    /**
     * Adds a room to the game environment.
     *
     * @param toInsert The room to be added.
     *  @param target The room which toInsert is added
     *    @param direction The direction of the room.
     * throw execptions if it is not possible to add the room
     */
    public void addRoom(Room toInsert, Room target, Direction direction) {
        if (this.rooms.isExist(toInsert)) {
            System.out.println("Could not add " + toInsert.getName() + " to the game.");
        } else {
            try {
                this.rooms.add(toInsert, target, direction);
                if (target != null)
                    System.out.println(toInsert.getName() + " was added and is connected to " + target.getName() + " from the " + Room.getDirectionString(direction) + " exit.");
                else
                    System.out.println(toInsert.getName() + " was added.");
            } catch (NoSuchElement e) {
                throw new RoomDoesNotExist();
            } catch (DirectionIsOccupied e) {
                throw new ExitIsOccupied();
            }
        }
    }


    /**
     * Adds an item to a specific room in the game environment.
     *
     * @param room The room where the item is to be added.
     * @param item The item to be added.
     */
    public void addItem(Room room, Item item) {
        boolean roomExist = this.rooms.isExist(room);
        if (roomExist) {
            boolean addedItem = room.addItem(item);
            if (addedItem) {
                System.out.println(item.getName() + " was added to the game.");
            } else
                System.out.println("Could not add " + item.getName() + " to the game.");

        } else {
            System.out.println("Could not add " + item.getName() + " to the game.");
        }

    }


    /**
     * Removes a player from the game.
     *
     * @param player The player to be removed.
     */
    public void removePlayer(Player player) {
        if (player == null) {
            return;
        }
        if (this.currentPlayer.equals(player)) {
            player.resetPlayer();
            String playerName = player.getName();
            this.currentPlayer = null;
            System.out.println(playerName + " was removed from the game.");
        } else {
            System.out.println(player.getName() + " does not exist.");
        }
    }

    /**
     * Removes a room from the game environment.
     *
     * @param room The room to be removed.
     * Returns true if the room was successfully removed, false otherwise.
     */
    public void removeRoom(Room room) {
        try {
            this.rooms.remove(room);
            System.out.println(room.getName() + " was removed from the game.");
            room.reset();
        } catch (NoSuchElement e) { // Corrected catch block
            throw new RoomDoesNotExist();
        }
    }




    /**
     * Starts the player in a specified room.
     *
     * @param room The room where the player starts.
     */
    public void startPlayer(Room room) {
        this.currentPlayer.startPlayer(room);
    }

    /**
     * Moves the player in a specified direction.
     *
     * @param direction The direction in which the player moves.
     */
    public void movePlayer(Direction direction) {
        this.currentPlayer.movePlayer(this.rooms,direction);
    }

    /**
     * Picks up an item from the current room and adds it to the player's inventory.
     *
     * @param item The item to be picked up.
     */
    public void pickUpItem(Item item) {

        this.currentPlayer.pickUpItem(item);

    }

    /**
     * Drops an item from the player's inventory into the current room.
     *
     * @param item The item to be dropped.
     */
    public void dropItem(Item item) {

        this.currentPlayer.dropItem(item);

    }

    /**
     * Disassembles an item either from the player's inventory or the current room.
     *
     * @param item The item to be disassembled.
     */
    public void disassembleItem(Item item) {

        this.currentPlayer.disassembleItem(item);

    }

    /**
     * Solves the puzzle in the current room.
     */
    public void solvePuzzle() {
        this.currentPlayer.solvePuzzle();
    }

    /**
     * Activates the puzzle in a specified room.
     *
     * @param room The room where the puzzle is to be activated.
     */
    public void activatePuzzle(Room room) {
        room.activatePuzzle();
    }

    /**
     * Deactivates the puzzle in a specified room.
     *
     * @param room The room where the puzzle is to be deactivated.
     */
    public void deactivatePuzzle(Room room) {
        room.deactivatePuzzle();
    }



    /**
     * A wrapper method responsible for using the selected item.
     *
     * @param item - the item which the player will use
     */
    public void useItem(Item item) {
        item.useItem(this.currentPlayer);
    }

    /**
     * Clones the GameManager object.
     *
     * @return A deep copy of the GameManager object.
     */
    @Override
    public GameManager clone()  {
        try {
            GameManager clonedManager = (GameManager) super.clone();
            if (this.rooms != null) {
                clonedManager.rooms = this.rooms.clone();
            }if(currentPlayer!=null) {
                clonedManager.currentPlayer = currentPlayer.clone();
            }
            return clonedManager;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public QuartlyLinkedList<Room> getRooms(){
        return this.rooms;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }


}
