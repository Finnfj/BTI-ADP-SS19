package set;

import types.Elem;
import types.Pos;

public class SetArray implements SetInterface {

    private Elem[] elements;
    private int size;

    public SetArray() {
        this.elements = new Elem[8];
        this.size = 0;
    }

    /**
     * Add an element to the set.
     *
     * @param elem The element that is going to be added.
     * @return The Pos of the added element.
     */
    @Override
    public Pos add(Elem elem) {
        if (this.size() >= this.getElements().length) {
            // TODO: increase array size
        }
        return null;
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
     * @param key
     * @return The Pos of the found element.
     */
    @Override
    public Pos find(int key) {
        return null;
    }

    /**
     * Retrieve an Element by its Pos.
     *
     * @param pos
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

    private Elem[] getElements() {return this.elements;}
}