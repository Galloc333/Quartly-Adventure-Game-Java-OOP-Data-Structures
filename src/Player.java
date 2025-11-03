/**
 * Player class represents a player in the game.
 */
public class Player implements Cloneable {
    private String name; // Name of the player
    private int maxItems; // Maximum number of items the player can carry
    private Bag bag;

    private Room currentRoom;

    /**
     * Constructs a Player object with a specified name and maximum number of items.
     *
     * @param name The name of the player.
     * @param maxItems The maximum number of items the player can carry.
     */
    Player(String name, int maxItems) {
        this.name = name;
        this.maxItems = maxItems;
        this.currentRoom = null;
        this.bag = new Bag("Starting bag", 1, this.maxItems);
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param item The item to be added.
     * Returns true if the item was added successfully, false if the inventory is full.
     */
    public boolean addItem(Item item) {
        if (bag == null) {
            return false; // בעיה
        }
        return bag.addItem(item);
    }

    /**
     * Removes an item from the player's inventory.
     *
     * @param item The item to be removed.
     * Returns true if the item was removed successfully, false if the item was not found.
     */
    public boolean removeItem(Item item) {
        return bag.removeItem(item);
    }

    /**
     * Retrieves the name of the player.
     * Returns The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the player's inventory is full.
     * Returns true if the inventory is full, false otherwise.
     */
    public boolean isFullBag() {
        if (this.bag == null) {
            return false;
        }
        return this.bag.isFullBag();
    }

    /**
     * Checks if a specified item exists in the player's inventory.
     *
     * @param item The item to check.
     * Returns true if the item exists in the inventory, false otherwise.
     */
    public boolean isItemExist(Item item) {
        if (bag == null) {
            return false;
        }
        return this.bag.isItemExist(item);
    }

    /**
     * Empties the players bag.
     */


    public void emptyBag() {
        if (bag == null) {
            return;
        }
        this.bag.emptyBag();
    }

    /**
     * Changes the player's bag if possible.
     * @param item - the item which supposed to be the new bag.
     * Returns true if bag has been changed, false otherwise.
     */

    public boolean changeBag(Item item) {
        if (!(item instanceof Bag))
            return false;
        if (this.bag.getClass().getName().equals("Bag")) {
            boolean flag = this.bag.transferItems(item);
            if (flag) {
                Bag newBag = (Bag) item;
                boolean flag2 = newBag.addItem(this.getBag());
                if (!flag2) {
                    System.out.println(this.getName() + " disassembled " + this.bag.getName() + ".");
                }
                return true;
            } else {
                System.out.println(item.getName() + " is too small");
                return false;
            }


        } else {
            boolean flag = this.bag.transferItems(item);
            if (flag) {
                System.out.println(this.name + " disassembled " + this.bag.getName());
                return true;
            } else {
                System.out.println(item.getName() + " is too small");
                return false;
            }


        }
    }

    /**
     * Hashcode method.
     * Returns a unique hashcode for any player (unique in terms of the conditions of the assigment).
     */

    @Override
    public int hashCode() {
        return this.getBag().sumOfItems();
    }


    /**
     * Checks if this player is equal to another object.
     *
     * @param object The object to compare with.
     * Returns true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null || this.bag == null) {
            return false;
        }
        if (object instanceof Player) {
            return this.hashCode() == object.hashCode();
        }
        return false;
    }

    /**
     * Moves the player to the specified room.
     *
     * @param room The room to move the player to.
     */
    public void movePlayer(Room room) {
        this.currentRoom = room;
    }

    /**
     * Retrieves the current room of the player.
     * Returns The current room of the player.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * Retrieves the bag of the player.
     * Returns The bag of the player.
     */
    public Bag getBag() {
        return this.bag;
    }

    /**
     * Sets the bag of the player.
     *
     * @param bag The bag to set for the player.
     */
    public void setBag(Bag bag) {
        this.bag = bag;
    }

    /**
     * Checks if the player can pick up the specified item.
     *
     * @param item The item to check.
     * Returns true if the player can pick up the item, false otherwise.
     */
    public boolean canPick(Item item) {
        if (item.getClass().getName().equals("Bag") || item instanceof LargeBag) {
            if (item instanceof LargeBag) {
                return false;
            } else return this.bag instanceof LargeBag && item.getClass().getName().equals("Bag");
        } else
            return true;

    }

    /**
     * Picks up the specified item.
     *
     * @param item The item to pick up.
     */
    public void pickUpItem(Item item) {
        String name = item.getName();
        boolean flag1 = this.currentRoom.isItemExist(item);
        boolean flag2 = this.isFullBag();
        boolean flag3 = this.canPick(item);
        if (flag1 && !flag2 && flag3) {
            this.currentRoom.removeItem(item);
            this.addItem(item);
            System.out.println(this.getName() + " picked up " + item.getName() + " from " + this.currentRoom.getName() + ".");
        } else if ((!flag1 && flag2) || (flag1 && flag2)) {
            System.out.println(this.getName() + "'s inventory is full.");
        } else if (!flag1) {
            System.out.println(item.getName() + " is not in " + this.currentRoom.getName() + ".");
        } else if (!flag3) {
            System.out.println(item.getName() + " is not valid for storing.");
        }
        item.setName(name);
    }



    /**
     * Disassembles the specified item.
     *
     * @param item The item to disassemble.
     */
    public void disassembleItem(Item item) {
        String name = item.getName();
        boolean flag = this.isItemExist(item);
        boolean flag1 = this.currentRoom.isItemExist(item);
        if (flag) {
            this.removeItem(item);
            System.out.println(this.getName() + " disassembled " + item.getName() + ".");
        } else if (flag1) {
            this.currentRoom.removeItem(item);
            System.out.println(this.getName() + " disassembled " + item.getName() + ".");
        } else {
            System.out.println(this.getName() + " could not disassemble " + item.getName() + ".");
        }
        item.setName(name);
    }

    /**
     * Drops the specified item.
     *
     * @param item The item to drop.
     */
    public void dropItem(Item item) {
        String name = item.getName();
        boolean roomFull = this.currentRoom.isRoomFull();
        boolean itemsExist = this.isItemExist(item);
        if (!roomFull && itemsExist) {
            this.removeItem(item);
            this.currentRoom.addItem(item);
            System.out.println(this.getName() + " dropped " + item.getName() + " in " + this.currentRoom.getName() + ".");
        } else if ((!itemsExist && roomFull) || (!itemsExist && !roomFull)) {
            System.out.println(item.getName() + " is not in " + this.getName() + "'s inventory.");
        } else {
            System.out.println(this.currentRoom.getName() + " is full.");
        }
        item.setName(name);
    }

    /**
     * Starts the player in the specified room.
     *
     * @param room The room to start the player in.
     */
    public void startPlayer(Room room) {
        if (this.currentRoom == null) {
            this.currentRoom = room;
            this.movePlayer(this.currentRoom);
            System.out.println(this.getName() + " starts in " + room.getName() + ".");
        } else {
            System.out.println(this.getName() + " has already started.");
        }
    }

    /**
     * Solves the puzzle in the current room.
     */
    public void solvePuzzle() {
        boolean riddleStatus = this.currentRoom.getRiddle();
        if (riddleStatus) {
            this.currentRoom.setRiddle(false);
            System.out.println(this.getName() + " is solving the puzzle in " + this.currentRoom.getName() + ".");
        } else
            System.out.println("There is no active puzzle in " + this.currentRoom.getName() + ".");
    }

    /**
     * Resets the player by emptying their bag and setting their current room to null.
     */
    public void resetPlayer() {
        this.emptyBag();
        this.currentRoom = null;
    }
    /**
     * Moves the player to a neighboring room in the specified direction.
     *
     * @param list      The QuartlyLinkedList containing rooms and their connections.
     * @param direction The direction in which to move the player.
     */
    public void movePlayer(QuartlyLinkedList list,Direction direction){
        Room room=this.currentRoom;
        QuartNode<Room> node=list.getNode(this.currentRoom);
        QuartNode<Room>  NewNode= node.getNeighbor(direction);
        if(NewNode !=null) {
            Room newRoom = NewNode.getValue();
            if (newRoom != null) {
                if(!this.currentRoom.getRiddle())
                {
                    this.currentRoom = newRoom;
                    System.out.println(this.name + " moved from " + room.getName() + " to " + this.currentRoom.getName() + " via the " + Room.getDirectionString(direction) + " exit.");
                }  else{
                    System.out.println(this.name+" could not move via the "+Room.getDirectionString(direction)+" exit.");
                }
            }
        }
        else{
           System.out.println(this.name+" could not move via the "+Room.getDirectionString(direction)+" exit.");
        }
    }
    /**
     * Clones the player object.
     *
     * @return A clone of the player object.
     */
    @Override
    public Player clone() {
        try {
            Player clonedPlayer = (Player) super.clone();
            clonedPlayer.currentRoom = this.currentRoom.clone();
            clonedPlayer.bag = this.bag.clone();
            return clonedPlayer;
        }catch (CloneNotSupportedException e){
            return null;
        }

    }




}