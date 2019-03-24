package set;

import java.util.Arrays;

import types.Elem;
import types.Pos;

@SuppressWarnings({"rawtypes", "unchecked"})
public class SetContainerArray implements SetInterface {

    final private int DEFSIZE = 8;
    private Pos<?>[] positions;
    private int posSize;


    public SetContainerArray() {
        this.positions = (Pos<?>[]) new Pos[DEFSIZE];
        this.posSize = 0;
        positions[0] = new Pos<>(0, this);
        positions[0].isValid = false;
    }

    public SetContainerArray(int size) {
        this.positions = (Pos<?>[]) new Pos[size];
        positions[0] = new Pos<>(null, this, -1, 1);
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
        // Check if element already exists
        Pos temp = this.find(elem.key);
        if (temp.isValid) {
            return temp;
        }

        // find a pos for the new element
        int i = 1;
        while (i <= posSize) {
            if (!positions[i].isValid) {
                // found empty position, stop searching
                break;
            }
            i++;
        }

        // check if the array needs to be enlarged and do so if necessary
        if (i == posSize && positions[i].isValid) {
            positions = Arrays.copyOf(positions, positions.length*2);
        }

        // first position only
        if (positions[i] == null) {
            positions[i] = new Pos<Elem>(elem, this, 0, 2);
            positions[i].isValid = true;
            posSize++;
            return positions[i];
        }
        // i is either posSize or a position with isValid=false here
        else if (i == posSize) {        	// reached end of positions, create new one
            positions[i+1] = new Pos<Elem>(elem, this, posSize, posSize + 2);
            positions[i+1].isValid = true;
            posSize++;
            return positions[i+1];
        } else if (!positions[i].isValid) {            // found empty position, insert
            positions[i].setPointer(elem);
            positions[i].isValid = true;
            return positions[i];
            // Error
        } else {
            return null;
        }
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
     * Retrieve all Elements of set.
     *
     * @return The elements.
     */
    @Override
    public Elem<?>[] retrieveAll() {
        return new Elem[0];
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
        return 0;
    }

    /**
     * Unifies two sets and returns them.
     *
     * @param s The first set to be unified.
     * @param t The second set to be unified.
     * @return The unified set.
     */
    public static SetContainerArray unify(SetInterface s, SetInterface t) {
        return null;
    }
}