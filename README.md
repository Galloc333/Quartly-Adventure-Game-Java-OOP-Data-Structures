Overview

This project implements a text-based adventure game in Java, focusing on data structures, cloning, and iterators.
It extends previous assignments by introducing a new custom data structure — QuartlyLinkedList — which connects rooms in four directions (north, east, south, west) using QuartNode elements.

The project demonstrates proficiency in object-oriented programming, generic classes, deep copying, and DFS-based iteration through interconnected nodes.

Key Components

QuartNode<T> – Represents a single node connected in up to four directions. Implements Cloneable.

QuartlyLinkedList<T extends Cloneable> – A generic linked structure enabling directional navigation, addition, removal, and deep cloning.

QuartlyLinkedListIterator – Performs a depth-first search (DFS) traversal starting from the root node.

Room – Represents rooms in the adventure game, now stored within a QuartlyLinkedList.

GameManager – Handles room connections, player actions, and cloning of the game state.

Notable Features

Full support for deep copy (clone) of complex linked structures.

Custom unchecked exceptions such as:

NoSuchElement

DirectionIsOccupied

RoomDoesNotExist

ExitIsOccupied

Integration of iterable traversal and covariant return types.

Structured according to the Technion course’s Java coding conventions and includes proper Javadoc documentation.

How to Run

Open the project in IntelliJ IDEA (Java 9.0.4 or higher).

Compile all files under the src folder.

Run the Main class to start the test suite or the adventure simulation.

QuartlyAdventureGame/
├── src/
│   ├── QuartNode.java
│   ├── QuartlyLinkedList.java
│   ├── QuartlyLinkedListIterator.java
│   ├── Room.java
│   ├── GameManager.java
│   ├── Main.java
│   └── exceptions/
│       ├── NoSuchElement.java
│       ├── DirectionIsOccupied.java
│       ├── RoomDoesNotExist.java
│       └── ExitIsOccupied.java
├── README.md
└── HW4_output.txt
