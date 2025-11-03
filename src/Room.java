/**
 * Room class represents a room in the game environment.
 */
public class Room implements Cloneable {
    private static final int MAX_ITEMS = 2; // Maximum number of items a room can contain
    private String name; // Name of the room
    private Item[] items; // Array to store items in the room
    private  boolean riddle; // Flag indicating if the room has a puzzle or riddle

    private boolean keyStaus; // Flag indicating if the room has been opened

    private Key key;

    /**
     * Constructs a Room object with a specified name.
     *
     * @param name The name of the room.
     */
    Room(String name) {
        this.name = name;
        this.riddle = false;
        this.items = null;
        this.key = null;
        this.keyStaus = false;
    }


    /**
     * Returns a string representation of the direction.
     *
     * @param direction The direction.
     * Returns a string representing the direction.
     */
    public static String getDirectionString(Direction direction) {
        if (direction == Direction.NORTH)
            return "north";
        else if (direction == Direction.SOUTH)
            return "south";
        else if (direction == Direction.EAST)
            return "east";
        else
            return "west";
    }

    /**
     * Adds an item to the room's inventory.
     *
     * @param item The item to add.
     * Returns true if the item was added successfully, false if the room's inventory is full.
     */
    public boolean addItem(Item item) {
        Item[] temp;
        if (this.items == null) {
            temp = new Item[1];
        }  else if (this.items.length == MAX_ITEMS)
            return false;
        else {
            temp = new Item[items.length + 1];
            for (int i = 0; i < items.length; i++)
                temp[i] = items[i];
        }
        temp[temp.length - 1] = item;
        this.items = temp;
        return true;
    }

    /**
     * Removes an item from the room's inventory.
     *
     * @param item The item to remove.
     * Returns true if the item was removed successfully, false if the item was not found.
     */
    public boolean removeItem(Item item) {
        if (this.items == null)
            return false;
        int i, j = 0;
        boolean isExist = false;
        for (i = 0; i < this.items.length; i++) {
            if (this.items[i].equals(item)) {
                this.items[i] = null;
                isExist = true;
            }
        }
        if (isExist == false)
            return false;
        if (this.items.length == 1) {
            this.items = null;
            return true;
        }
        Item[] temp = new Item[this.items.length - 1];
        for (i = 0; i < this.items.length; i++) {
            if (items[i] != null) {
                temp[j] = items[i];
                j++;
            }
        }
        this.items = temp;
        return true;
    }










    /**
     * Checks if the room has a puzzle.
     * Returns true if the room has a puzzle, false otherwise.
     */
    public boolean getRiddle() {
        return this.riddle;
    }

    /**
     * Sets the presence of a puzzle in the room.
     *
     * @param bool true to set the room with a puzzle, false otherwise.
     */
    public void setRiddle(boolean bool) {
        this.riddle = bool;
    }

    /**
     * Retrieves the name of the room.
     * Returns The name of the room.
     */
    public String getName() {
        return name;
    }



    /**
     * Checks if a specified item exists in the room's inventory.
     *
     * @param item The item to check.
     * Returns true if the item exists in the inventory, false otherwise.
     */
    public boolean isItemExist(Item item) {
        String newName = null;
        boolean isExist = false;
        if (this.items == null)
            return false;
        for (Item item1 : this.items) {
            if (item1.equals(item) && item1.getName().equals(item.getName()))
                return true;
            if (item1.equals(item)) {
               newName = item1.getName();
                isExist = true;
            }
        }
        if(isExist){
            item.setName(newName);
            return true;
        }
        return false;
    }

    /**
     * Checks if the room's inventory is full.
     * Returns true if the inventory is full, false otherwise.
     */
    public boolean isRoomFull() {
        if (this.items == null)
            return false;
        return this.items.length == MAX_ITEMS;
    }

    /**
     * Empties the room from items.
     */
    public void emptyRoom() {
        this.items = null;
    }



    /**
     * Calculates the hash code for the Room object based on its attributes.
     *
     * Returns The calculated hash code.
     */
    @Override
    public int hashCode() {
        return convertNameIntoNumber(this.getName()) * 10000 + 100 * this.sumOfItems() +
                10 * convertBooleanIntoInt(this.keyStaus) + convertBooleanIntoInt(this.riddle);
    }

    /**
     * Checks if this Room object is equal to another object.
     *
     * @param object The object to compare with.
     * Returns True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Room) {
            if (this.hashCode() == object.hashCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the sum of values of items present in the room.
     *
     * Returns The sum of values of items.
     */
    public int sumOfItems() {
        int sum = 0;
        if (this.items != null) {
            for (Item item : this.items) {
                sum += item.value;
            }
        }
        return sum;
    }

    /**
     * Retrieves the status of the room's key.
     *
     * Returns The status of the key (true if the room is locked, false otherwise).
     */
    public boolean getKeyStatus() {
        return this.keyStaus;
    }

    /**
     * Unlocks the room with the specified key.
     *
     * @param key The key used to lock the room.
     * Returns True if the room was successfully unlocked, false otherwise.
     */
    public boolean unlockRoom(Key key) {
        if (this.keyStaus == false) {
            this.riddle = false;
            this.keyStaus = true;
            this.key = key;
            return true;
        }
        return false;
    }

    /**
     * Removes an item from the room if it exists.
     *
     * @param item The item to be removed.
     * Returns True if the item was removed, false if the item was not found.
     */
    public boolean removeItemIfExist(Item item) {
        if (this.isItemExist(item)) {
            this.removeItem(item);
            return true;
        }
        return false;
    }

    /**
     * Converts the name of the room into a numerical representation.
     *
     * @param name The name of the room.
     * Returns The numerical representation of the name.
     */
    public static int convertNameIntoNumber(String name) {
        int factor = 1;
        int number = 0;
        for (int i = 0; i < name.length(); i++) {
            char charValue = name.charAt(i);
            int numericValue = (int) charValue;
            number += numericValue * factor;
            factor *= 10;
        }
        return number;
    }

    /**
     * Converts a boolean value into an integer (0 for false, 1 for true).
     *
     * @param x The boolean value to be converted.
     * Returns The integer representation of the boolean value.
     */
    public static int convertBooleanIntoInt(boolean x) {
        if (x) {
            return 1;
        }
        return 0;
    }

    /**
     * Retrieves the name of the key required to unlock the room.
     *
     * Returns The name of the key.
     */
    public String getKeyName() {
        return this.key.getName();
    }

    /**
     * Activates the puzzle in the room if it is not already active and the room is not locked.
     * If the room is unlocked with a key, it prints a message indicating the unlocking.
     */
    public void activatePuzzle() {
        boolean riddleStatus = this.getRiddle();
        boolean keyStatus = this.getKeyStatus();
        if (!riddleStatus && !keyStatus) {
            this.setRiddle(true);
        } else if (keyStatus) {
            System.out.println(this.getName() + " was unlocked with " + this.getKeyName() + ".");
        }
    }

    /**
     * Deactivates the puzzle in the room if it is active and the room is not locked.
     * If the room is unlocked with a key, it prints a message indicating the unlocking.
     */
    public void deactivatePuzzle() {
        boolean riddleStatus = this.getRiddle();
        boolean keyStatus = this.getKeyStatus();
        if (riddleStatus && !keyStatus) {
            this.setRiddle(false);
        } else if (keyStatus) {
            System.out.println(this.getName() + " was unlocked with key");
        }
    }
    /**
     * Clones the Room object and its contained objects.
     *
     * @return A deep copy of the Room object.
     */
    @Override
    public Room clone()  {
        try {
            Room clonedRoom = (Room) super.clone();

            if (this.items != null) {
                clonedRoom.items = new Item[this.items.length];
                for (int i = 0; i < this.items.length; i++) {
                    clonedRoom.items[i] = this.items[i].clone();
                }
            }

            if (this.key != null) {
                clonedRoom.key = this.key.clone();
            }
            return clonedRoom;
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }
    @Override
   public String toString() {
        return "Room: "+this.name+".";
    }

    /**
     * Resets the room properties to the default value.
     */
    public void reset() {
        this.riddle = false;
        this.items = null;
        this.key = null;
        this.keyStaus = false;
    }

    public Item[] getItems(){
        return this.items;
    }
}