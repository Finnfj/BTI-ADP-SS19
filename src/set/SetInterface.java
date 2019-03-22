package set;

import types.*;

/**
 * An interface that defines a set.
 */
public interface SetInterface {

    /**
     * Add an element to the set.
     * @param elem The element that is going to be added.
     * @return The Pos of the added element.
     */
    Pos add(Elem elem);

    /**
     * Deletes an element with its Pos from the list.
     * @param pos The Pos of the element.
     */
    void delete(Pos pos);

    /**
     * Deletes an element with its Key from the list.
     * @param key The key of the element.
     */
    void delete(int key);

    /**
     * Find an element with its key and return its Pos.
     * @param key
     * @return The Pos of the found element.
     */
    Pos find(int key);

    /**
     * Retrieve an Element by its Pos.
     * @param pos
     * @return The element.
     */
    Elem retrieve(Pos pos);

    /**
     * Prints every element of the set.
     */
    void showall();

    /**
     * Prints the size of the set.
     * @return The size of the set.
     */
    int size();

    /**
     * Unifies two sets and returns them.
     * @param s The first set to be unified.
     * @param t The second set to be unified.
     * @return The unified set.
     */
    SetInterface unify(SetInterface s, SetInterface t);
}