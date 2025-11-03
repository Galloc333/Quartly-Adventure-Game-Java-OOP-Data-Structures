/**
 * Bag class represents a bag item in the game.
 */
public class Bag extends Item implements Cloneable {

    protected Item[] items; // Array to store items in the bag
    protected int maxItems; // Maximum number of items the bag can hold

    /**
     * Constructs a Bag object with a specified name, value, and maximum number of items.
     *
     * @param name      The name of the bag.
     * @param value     The value of the bag.
     * @param max_items The maximum number of items the bag can hold.
     */
    public Bag(String name, int value, int max_items) {
        this.name = name;
        this.maxItems = max_items;
        this.value = value;
        this.items = null; // Initialize items array to null
    }

    /**
     * Computes the hash code for the Bag object.
     * Returns the hash code.
     */
    @Override
    public int hashCode() {
        return this.sumOfItems() * 10 ^ 10 + this.maxItems * 100 + this.value;
    }

    /**
     * Checks if two Bag objects are equal.
     *
     * @param object The object to compare.
     *               Returns true if the bags are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Bag) {
            if (object.hashCode() == this.hashCode()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the sum of values of all items in the bag.
     * Returns the sum.
     */
    public int sumOfItems() {
        int sum = 0;
        if (items != null) {
            for (Item item : items) {
                sum += item.value;
            }
        }
        return sum;
    }

    /**
     * Adds an item to the bag.
     *
     * @param item The item to add.
     *             Returns true if the item was added successfully, false otherwise.
     */
    public boolean addItem(Item item) {
        if (item instanceof Bag) {
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
     * Removes an item from the bag.
     *
     * @param item The item to remove.
     *             Returns true if the item was removed successfully, false otherwise.
     */
    public boolean removeItem(Item item) {
        if (this.items == null) {
            return false;
        }
        int i, j = 0;
        boolean isExist = false;
        for (i = 0; i < this.items.length; i++) {
            if (this.items[i].equals(item)) {
                this.items[i] = null;
                isExist = true;
            }
        }
        if (!isExist) {
            return false;
        }
        if (this.items.length == 1) {
            this.items = null;
            return true;
        }
        Item[] temp = new Item[this.items.length - 1];
        for (i = 0; i < this.items.length; i++) {
            if (this.items[i] != null) {
                temp[j] = this.items[i];
                j++;
            }
        }
        this.items = temp;
        return true;
    }


    /**
     * Checks if a specified item exists in the bag.
     *
     * @param item The item to check.
     *             Returns true if the item exists in the bag, false otherwise.
     */
    public boolean isItemExist(Item item) {
        String newName = null;
        if (this.items == null) {
            return false;
        }
        boolean isExist = false;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i].equals(item) && this.items[i].getName().equals(item.getName())) {
                return true;
            }
            if (this.items[i].equals(item)) {
                newName = this.items[i].getName();
                isExist = true;
            }
        }
        if (isExist) {
            item.setName(newName);
        }
        return isExist;
    }

    /**
     * Checks if the bag is full.
     * Returns true if the bag is full, false otherwise.
     */
    public boolean isFullBag() {
        if (this.maxItems == 0) {
            return true;
        }
        if (this.items == null) {
            return false;
        }
        return this.items.length == maxItems;
    }

    /**
     * Empties the bag.
     */
    public void emptyBag() {
        this.items = null;
    }

    /**
     * Transfers items from this bag to another bag.
     *
     * @param item The bag to transfer items to.
     *             Returns true if the transfer was successful, false otherwise.
     */
    public boolean transferItems(Item item) {
        if (item == null) {
            return false;
        }
        if (!(item instanceof Bag)) {
            return false;
        }
        if (this.items == null) {
            return true;
        }
        Bag other = (Bag) item;
        if (other.getSize() + this.getSize() <= other.maxItems) {
            Item[] temp = new Item[this.items.length];
            for (int i = 0; i < this.items.length; i++) {
                temp[i] = this.items[i];
            }
            for (Item item1 : temp) {
                other.addItem(item1);
            }
            this.emptyBag();
            return true;
        } else {
            System.out.println(item.getName() + " is too small.");
            return false;
        }
    }

    /**
     * Gets the size of the bag.
     * Returns the size of the bag.
     */
    public int getSize() {
        if (this.items == null) {
            return 0;
        }
        return this.items.length;
    }


    /**
     * Uses the bag item.
     *
     * @param player The player using the bag.
     */
    @Override
    public void useItem(Player player) {
        String name = this.getName();
        if (player.getCurrentRoom().isItemExist(this) || player.isItemExist(this)) {
            boolean flag = player.changeBag(this);
            if (flag) {
                player.setBag(this);
                player.getCurrentRoom().removeItemIfExist(this); // Added
                System.out.println(player.getName() + " is now carrying " + this.name + ".");
            }
        } else {
            System.out.println(this.getName() + " is not near " + player.getName() + ".");
        }
        this.setName(name);
    }

    @Override
    public Bag clone()  {
        Bag copy = (Bag) super.clone();
        copy.items = new Item[this.items.length];
        for (int i = 0; i < this.items.length; i++) {
            copy.items[i] = this.items[i].clone();
        }
        return copy;
    }
}