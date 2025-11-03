import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A node class representing an element in a QuartlyLinkedList with directional connections.
 *
 * @param <E> The type of value stored in the node, must extend Cloneable.
 */
public class QuartNode<E extends Cloneable> implements Cloneable {
    private E value;
    private QuartNode<E> north;
    private QuartNode<E> south;
    private QuartNode<E> west;
    private QuartNode<E> east;

    private Direction entryDirection;

    /**
     * Constructs a QuartNode with the given value and no connections.
     *
     * @param value The value to be stored in the node.
     */
    public QuartNode(E value) {
        this.value = value;
        this.north = null;
        this.south = null;
        this.west = null;
        this.east = null;
        this.entryDirection = null;
    }

    /**
     * Constructs a QuartNode with the given value and connects it to another node in the specified direction.
     *
     * @param value      The value to be stored in the node.
     * @param direction  The direction in which to connect the new node to the other node.
     * @param other      The node to which the new node will be connected.
     * @throws DirectionIsOccupied if the specified direction is already occupied by another node.
     */
    public QuartNode(E value, Direction direction, QuartNode<E> other) throws DirectionIsOccupied {
        Direction oppositeDirection = getOppositeDirection(direction);
        if (other.getNeighbor(direction) == null) {
            switch (direction) {
                case NORTH:
                    this.entryDirection = Direction.NORTH;
                    this.south = other;
                    other.north = this;
                    break;
                case SOUTH:
                    this.entryDirection = Direction.SOUTH;
                    this.north = other;
                    other.south = this;
                    break;
                case EAST:
                    this.entryDirection = Direction.EAST;
                    this.west = other;
                    other.east = this;
                    break;
                case WEST:
                    this.entryDirection = Direction.WEST;
                    this.east = other;
                    other.west = this;
                    break;
            }
            this.value = value;
        } else {
            throw new DirectionIsOccupied();
        }
    }

    /**
     * Returns the value stored in the node.
     *
     * @return The value stored in the node.
     */
    public E getValue() {
        return this.value;
    }

    /**
     * Sets the value stored in the node.
     *
     * @param value The new value to be stored in the node.
     */
    public void setValue(E value) {
        this.value = value;
    }

    /**
     * Returns the neighboring node in the specified direction.
     *
     * @param direction The direction in which to find the neighboring node.
     * @return The neighboring node in the specified direction, or null if no node exists in that direction.
     */
    public QuartNode<E> getNeighbor(Direction direction) {
        switch (direction) {
            case NORTH:
                return this.north;
            case SOUTH:
                return this.south;
            case EAST:
                return this.east;
            case WEST:
                return this.west;
        }
        return null;
    }

    /**
     * Clones the QuartNode and its value.
     *
     * @return A deep copy of the QuartNode.
     */
    @Override
    public QuartNode<E> clone() {
        try {
            QuartNode<E> copy = (QuartNode<E>) super.clone();
            copy.value = cloneValue(value);
            return copy;
        }catch (CloneNotSupportedException e){
            return null;
        }
    }

    private E cloneValue(E value) throws CloneNotSupportedException {
        try {
            Method cloneMethod = value.getClass().getMethod("clone");
            return (E) cloneMethod.invoke(value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new CloneNotSupportedException("Clone method not found for class " + value.getClass().getName());
        }
    }

    /**
     * Disconnects the node from its neighboring nodes.
     */
    public void disconnect() {
        if (this.getNeighbor(Direction.EAST) != null) {
            this.getNeighbor(Direction.EAST).west = null;
        }
        if (this.getNeighbor(Direction.WEST) != null) {
            this.getNeighbor(Direction.WEST).east = null;
        }
        if (this.getNeighbor(Direction.NORTH) != null) {
            this.getNeighbor(Direction.NORTH).south = null;
        }
        if (this.getNeighbor(Direction.SOUTH) != null) {
            this.getNeighbor(Direction.SOUTH).north = null;
        }

        this.north = null;
        this.south = null;
        this.east = null;
        this.west = null;
    }
    /**
     * Gets the opposite direction of the given direction.
     *
     * @param direction The direction for which to get the opposite direction.
     * @return The opposite direction of the given direction.
     */
    public static Direction getOppositeDirection(Direction direction) {
        switch (direction) {
            case NORTH:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.NORTH;
            case EAST:
                return Direction.WEST;
            case WEST:
                return Direction.EAST;
        }
        return null;
    }

    public Direction getEntryDirection(){
        return this.entryDirection;
    }

    public void setNeighbor(Direction direction, QuartNode<E> clonedNeighbor) {
        switch (direction) {
            case NORTH:
                this.north = clonedNeighbor;
                break;
            case SOUTH:
                this.south = clonedNeighbor;
                break;
            case EAST:
                this.east = clonedNeighbor;
                break;
            case WEST:
                this.west = clonedNeighbor;
                break;
        }

    }
}
