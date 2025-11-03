/**
 * Key class represents a key item in the game environment.
 * It extends the Item class and provides specific functionality related to keys.
 */
public class Key extends Item implements Cloneable {

    /**
     * Constructs a Key object with the specified name and value.
     * @param name The name of the key.
     * @param value The value associated with the key.
     */
    public Key(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Overrides the equals method to compare two Key objects.
     * @param object The object to compare with.
     * Returns True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Key) {
            return this.hashCode() == object.hashCode();
        }
        return false;
    }

    /**
     * Overrides the hashCode method to generate a hash code for the Key object.
     * Returns The hash code generated for the Key object.
     */
    @Override
    public int hashCode() {
        return this.value + 11;
    }

    /**
     * Implements the useItem method from the Item class.
     * This method specifies how the key item is used by a player.
     * It unlocks the current room if the key is nearby and the current room of the player is currently locked.
     * @param player The player who is using the key.
     */
    @Override
    public void useItem(Player player) {
        String name = this.getName();
        boolean unlockedRoom = player.getCurrentRoom().getKeyStatus();
        boolean isNearBy = (player.isItemExist(this)) || (player.getCurrentRoom().isItemExist(this));
        if (!unlockedRoom && isNearBy) {
            player.getCurrentRoom().unlockRoom(this);
            System.out.println(player.getName() + " used " + this.getName() + " in " + player.getCurrentRoom().getName() + ".");
            if (player.isItemExist(this))
                player.removeItem(this);
            else if (player.getCurrentRoom().isItemExist(this))
                player.getCurrentRoom().removeItem(this);
        } else if (unlockedRoom) {
            System.out.println(player.getCurrentRoom().getName() + " was already unlocked.");
        } else {
            System.out.println(this.getName() + " is not near " + player.getName() + ".");
        }
        this.setName(name);
    }
    /**
     * Clones the Key object.
     *
     * @return A deep copy of the Key object.
     */
    @Override
    public Key clone()  {
        return (Key) super.clone();
    }
}
