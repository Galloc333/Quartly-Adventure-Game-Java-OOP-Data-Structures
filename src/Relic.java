/**
 * Represents a relic item in the game.
 * Inherits properties from the Item class.
 */
public class Relic extends Item implements Cloneable {

    /**
     * Constructs a relic object with a specified name and value.
     *
     * @param name  The name of the relic.
     * @param value The value of the relic.
     */
    public Relic(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Checks if this relic is equal to another object.
     *
     * @param object The object to compare with.
     * Returns true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Relic) {
            if (this.hashCode() == object.hashCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates the hash code for this relic.
     * The hash code is based on the value of the relic.
     *
     * @return The hash code of the relic.
     */
    @Override
    public int hashCode() {
        return this.value + 81;
    }

    /**
     * Allows the player to use the relic item.
     * Prints a message indicating the player's action.
     *
     * @param player The player using the relic.
     */
    @Override
    public void useItem(Player player) {
        String name = this.getName();
        if (player.getCurrentRoom().isItemExist(this) || player.getBag().isItemExist(this)) {
            System.out.println(player.getName() + " is inspecting " + this.getName() + ".");
        } else {
            System.out.println(this.getName() + " is not near " + player.getName() + ".");
        }
        this.setName(name);
    }
    /**
     * Clones the Relic object.
     *
     * @return A deep copy of the Relic object.
     */
    @Override
    public Relic clone()  {
        return (Relic) super.clone();
    }
}
