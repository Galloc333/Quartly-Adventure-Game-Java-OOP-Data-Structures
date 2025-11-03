/**
 * LargeBag class represents a large bag item in the game environment.
 * It extends the Bag class and provides specific functionality for large bags.
 */
public class LargeBag extends Bag implements Cloneable {

    /**
     * Constructs a LargeBag object with the specified name, value, and maximum number of items.
     * If the specified maximum number of items is less than 5, the maximum number is set to 5.
     * @param name The name of the large bag.
     * @param value The value associated with the large bag.
     * @param max_items The maximum number of items the large bag can hold.
     */
    public LargeBag(String name, int value, int max_items) {
        super(name, value, max_items);
        if (max_items < 5) {
            this.maxItems = 5;
        }
    }

    /**
     * Overrides the addItem method from the Bag class to add an item to the large bag.
     * Large bags cannot contain other large bags.
     * @param item The item to add to the large bag.
     * Returns True if the item was added successfully, false otherwise.
     */
    @Override
    public boolean addItem(Item item) {
        if (item instanceof LargeBag) {
            return false;
        }
        Item[] updatedBag;
        if (this.items == null) {
            updatedBag = new Item[1];
        } else if (this.items.length == this.maxItems) {
            return false;
        } else {
            updatedBag = new Item[this.items.length + 1];
            for (int i = 0; i < this.items.length; i++) {
                updatedBag[i] = this.items[i];
            }
        }
        updatedBag[updatedBag.length - 1] = item;
        this.items = updatedBag;
        return true;
    }

    /**
     * Overrides the removeItem method from the Bag class.
     * @param item The item to remove from the large bag.
     * Returns True if the item was removed successfully, false otherwise.
     */
    @Override
    public boolean removeItem(Item item) {
        return super.removeItem(item);
    }

    /**
     * Overrides the hashCode method to generate a hash code for the LargeBag object.
     * Returns The hash code generated for the LargeBag object.
     */
    @Override
    public int hashCode() {
        return super.hashCode() + 500;
    }

    /**
     * Overrides the equals method to compare two LargeBag objects.
     * @param object The object to compare with.
     * Returns True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof LargeBag) {
            return object.hashCode() == this.hashCode();
        }
        return false;
    }
    /**
     * Clones the LargeBag object.
     *
     * @return A deep copy of the LargeBag object.
     */
    @Override
    public LargeBag clone() {
            return (LargeBag) super.clone();
    }

}
