/**
 * Item class represents an item in the game environment.
 */
public abstract class Item implements Cloneable {

    // Name of the item
    protected String name;

    // Value of the item
    protected int value;

    /**
     * Retrieves the name of the item.
     * @return The name of the item.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets the name of the item.
     * @param name The name to be set for the item.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Represents the action of using the item by a player.
     * @param player The player using the item.
     */
    abstract public void useItem(Player player);
    @Override
    public Item clone() {
        try {
            return (Item) super.clone();
        }catch (CloneNotSupportedException e){
            return null;
        }

    }
}
