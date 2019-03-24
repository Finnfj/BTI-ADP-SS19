package set;

import types.Elem;
import types.Pos;

import java.util.Arrays;

/**
 * A set that internally works with an array.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class SetArray<T> implements SetInterface {

    final private int DEFSIZE = 8;
    private Elem<?>[] elements;
    private Pos<Integer>[] positions;
    private int elemSize;
    private int posSize;

	public SetArray() {
        this.elements = new Elem[DEFSIZE];
        this.positions = (Pos<Integer>[]) new Pos[DEFSIZE];
        this.elemSize = 0;
        this.posSize = 0;
        positions[0] = new Pos<>(0, this);
        positions[0].isValid = false;
    }

    /**
     * Add an element to the set.
     *
     * @param elem The element that is going to be added.
     * @return The Pos of the added element.
     */
    @Override
    public Pos<Integer> add(Elem elem) {
        // check if the array needs to be enlarged and do so if necessary
        if (elemSize == elements.length-1) {
            elements = Arrays.copyOf(elements, elements.length*2);
            positions = Arrays.copyOf(positions, elements.length*2);
        }
        
        elements[elemSize + 1] = elem;
        elemSize++;
        // from here on elemSize=index of added element

        // find a pos for the new element
        int i = 1;
        boolean posFound = false;
        while (!posFound) {
            if (positions[i] != null) {
                if (!positions[i].isValid) { // go to the next one if the current is not valid
                    i++;
                } else { // if it is valid we can assign the element to it
                    posFound = true;
                }
            } else { // if there are no Pos left, create a new one
                positions[i] = new Pos<>(elemSize + 1, this);
            }
        }

        return positions[i];
    }

    /**
     * Deletes an element with its Pos from the list.
     *
     * @param pos The Pos of the element.
     */
    @Override
    public void delete(Pos pos) {
    	for (int i=1; i < positions.length; i++) {
    		if (positions[i] == pos) {
    			if (positions[i].isValid) {
    				positions[i].isValid = false;
    				positions[i].setPointer(null);
    				Integer gap = positions[i].getPointer();
                    if (elements.length - 1 - gap >= 0)
                        System.arraycopy(elements, gap + 1, elements, gap, elements.length - 1 - gap);
    				elemSize--;
    			}
    		}
    	}
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
     * @return The Pos of the found element. Position of dummy Element if not found
     */
    @Override
    public Pos<T> find(int key) {
    	Elem<T> dummy = new Elem<>(key);
    	elements[0] = dummy;
    	positions[0].setPointer(0);
    	
    	for (int i=posSize; i >= 0; i--) {
			if (elements[positions[i].getPointer()].key == key) {
				return (Pos<T>) positions[i];
			}
    	}
        return null;
    }

    /**
     * Retrieve an Element by its Pos.
     *
     * @param pos The Pos of the element we want to retrieve.
     * @return The element.
     */
	@Override
    public Elem<T> retrieve(Pos pos) {
        return (Elem<T>) elements[(int) pos.getPointer()];
    }

    /**
     * Prints every element of the set.
     */
    @Override
    public void showall() {
    	for (int i=1; i <= posSize; i++) {
    		if (positions[i].isValid) {
    			System.out.println(elements[positions[i].getPointer()]);
    		}
    	}
    }

    /**
     * Returns the size of the set.
     *
     * @return The size of the set.
     */
    @Override
    public int size() {
        return this.elemSize;
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