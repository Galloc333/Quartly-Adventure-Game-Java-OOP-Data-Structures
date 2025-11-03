import java.util.*;

public class QuartlyLinkedList<E extends Cloneable> implements Iterable<QuartNode<E>>, Cloneable {
    private QuartNode<E> root;

    /**
     * Constructs an empty QuartlyLinkedList.
     */
    public QuartlyLinkedList() {
        this.root = null;
    }

    /**
     * Returns the opposite direction of the given direction.
     *
     * @param direction the direction to find the opposite of
     * @return the opposite direction
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

    /**
     * Adds a new node with the specified value in the specified direction relative to the target value.
     *
     * @param toInsert  the value to insert
     * @param target    the value to insert relative to
     * @param direction the direction relative to the target value
     * @throws NoSuchElement     if the target value does not exist in the list
     * @throws DirectionIsOccupied if the direction is already occupied in the target node
     */
    public void add(E toInsert, E target, Direction direction) throws NoSuchElement, DirectionIsOccupied {
        if (isEmpty()) {
            this.root = new QuartNode<>(toInsert);
        } else {
            QuartNode<E> targetNode = getNode(target);
            if (targetNode == null) {
                throw new NoSuchElement();
            }
            try {
                new QuartNode<>(toInsert, direction, targetNode);
            } catch (DirectionIsOccupied e) {
                throw e;
            }
        }
    }

    /**
     * Retrieves the node containing the specified value.
     *
     * @param value the value to search for
     * @return the node containing the value, or null if not found
     */
    public QuartNode<E> getNode(E value) {
        for (QuartNode<E> node : this) {
            if (node.getValue().equals(value)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty, otherwise false
     */
    private boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Removes the node containing the specified value from the list.
     *
     * @param toRemove the value to remove
     * @throws NoSuchElement if the specified value does not exist in the list
     */
    public void remove(E toRemove) throws NoSuchElement {
        QuartNode<E> targetNode = getNode(toRemove);
        if (targetNode != null) {
            targetNode.disconnect();
            targetNode.setValue(null);
        } else {
            throw new NoSuchElement();
        }
    }

    /**
     * Retrieves the root node of the list.
     *
     * @return the root node
     */
    public QuartNode<E> getRoot() {
        return this.root;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an iterator
     */
    @Override
    public Iterator<QuartNode<E>> iterator() {
        return new QuartlyLinkedListIterator<>(this.root);
    }

    /**
     * Creates and returns a copy of this list.
     *
     * @return a cloned instance of this list
     */
    @Override
    public QuartlyLinkedList<E> clone() {

            QuartlyLinkedList<E> clonedList = new QuartlyLinkedList<>();
            if (this.root != null) {
                Map<QuartNode<E>, QuartNode<E>> clonedNodesMap = new HashMap<>();
                QuartNode<E> clonedRoot = this.root.clone();
                clonedList.root = clonedRoot;
                clonedNodesMap.put(this.root, clonedRoot);
                for (QuartNode<E> originalNode : this) {
                    QuartNode<E> clonedNode = clonedNodesMap.get(originalNode);
                    for (Direction direction : Direction.values()) {
                        QuartNode<E> originalNeighbor = originalNode.getNeighbor(getOppositeDirection(direction));
                        if (originalNeighbor != null) {
                            QuartNode<E> clonedNeighbor = clonedNodesMap.get(originalNeighbor);
                            if (clonedNeighbor == null) {
                                clonedNeighbor = originalNeighbor.clone();
                                clonedNodesMap.put(originalNeighbor, clonedNeighbor);
                            }
                            clonedNode.setNeighbor(getOppositeDirection(direction), clonedNeighbor);
                            clonedNeighbor.setNeighbor(direction, clonedNode);
                        }
                    }
                }
            }
            return clonedList;
    }

    /**
     * Checks if a node containing the specified value exists in the list.
     *
     * @param value the value to check for existence
     * @return true if a node with the specified value exists, otherwise false
     */
    public boolean isExist(E value) {
        for (QuartNode<E> node : this) {
            if (node.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
