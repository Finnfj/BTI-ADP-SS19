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
     * @param key The key of the element we want to find.
     * @return The Pos of the found element.
     */
    Pos find(int key);

    /**
     * Retrieve an Element by its Pos.
     * @param pos The Pos of the element we want to retrieve.
     * @return The element.
     */
    Elem retrieve(Pos pos);

    /**
     * Retrieve all Elements of set.
     * @return The elements.
     */    
    Elem<?>[] retrieveAll();

    /**
     * Prints every element of the set.
     */
    void showall();

    /**
     * Returns the size of the set.
     * @return The size of the set.
     */
    int size();

    /**
     * Unifies two sets and returns them.
     * @param s The first set to be unified. Defines type of Set to be created
     * @param t The second set to be unified.
     * @return The unified set.
     */
    static SetInterface unify(SetInterface s, SetInterface t) {
    	SetInterface temp = null;
    	if (s instanceof SetArray) {
    		temp = SetArray.unify(s, t);
    	} else if (s instanceof SetContainer) {
    		temp = SetContainer.unify(s, t);
    	} else if (s instanceof SetContainerArray) {
    		temp = SetContainerArray.unify(s, t);
    	}
		return temp;
	}
}