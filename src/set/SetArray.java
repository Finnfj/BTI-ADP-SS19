package set;

import types.Elem;
import types.Pos;

/**
 * A set that internally works with an array.
 */
public class SetArray implements SetInterface {

    final private int DEFSIZE = 8;
    private Elem<?>[] elements;
    private Pos<Integer>[] positions;
    private int size;

    public SetArray() {
        this.elements = new Elem[DEFSIZE];
        this.positions = new Pos[DEFSIZE];
        this.size = 0;
        positions[0] = new Pos(0, this);
        positions[0].isValid = false;
    }

    /**
     * Add an element to the set.
     *
     * @param elem The element that is going to be added.
     * @return The Pos of the added element.
     */
    @Override
    public Pos add(Elem elem) {
        // check if the array needs to be enlarged and do so if necessary
        if (size == elements.length) {
            Elem[] newElements = new Elem[size * 2]; // create a new array with twice the size of the old one
            Pos[] newPositions = new Pos[size * 2];
            // copy over the old elements
            for(int i = 0; i < elements.length; i++) {
                newElements[i] = elements[i];
                newPositions[i] = positions[i];
            }
            elements = newElements;
            positions = newPositions;
        }

        elements[size] = elem;
        Pos<Integer> newPos = new Pos<>(size, this);
        positions[size] = newPos;
        size++;

        return newPos;
    }

    /**
     * Deletes an element with its Pos from the list.
     *
     * @param pos The Pos of the element.
     */
    @Override
    public void delete(Pos pos) {

    }

    /**
     * Deletes an element with its Key from the list.
     *
     * @param key The key of the element.
     */
    @Override
    public void delete(int key) {

    }

    /**
     * Find an element with its key and return its Pos.
     *
     * @param key The key of the element we want to find.
     * @return The Pos of the found element.
     */
    @Override
    public Pos find(int key) {
        return null;
    }

    /**
     * Retrieve an Element by its Pos.
     *
     * @param pos The Pos of the element we want to retrieve.
     * @return The element.
     */
    @Override
    public Elem retrieve(Pos pos) {
        return null;
    }

    /**
     * Prints every element of the set.
     */
    @Override
    public void showall() {

    }

    /**
     * Returns the size of the set.
     *
     * @return The size of the set.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Unifies two sets and returns them.
     *
     * @param s The first set to be unified.
     * @param t The second set to be unified.
     * @return The unified set.
     */
    @Override
    public SetInterface unify(SetInterface s, SetInterface t) {
        return null;
    }
}