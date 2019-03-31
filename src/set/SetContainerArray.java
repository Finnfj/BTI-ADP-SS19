package set;

import java.util.Arrays;

import types.Elem;
import types.Pos;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 1b Set as Array of Containers<br>
 * SetContainerArray is a Set-representing Class. <br>
 * It's working with an Array of Container-Objects<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 24.03.19
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class SetContainerArray implements SetInterface {

    final private int DEFSIZE = 8;
    private Pos<?>[] positions;
    private int posSize;
    private int elemSize;

    public long counter;

    /**
     * Create a SetContainerArray with size=DEFSIZE
     */
    public SetContainerArray() {
        this.positions = (Pos<Elem>[]) new Pos[DEFSIZE];
        this.posSize = 0;
        this.elemSize = 0;
        positions[0] = new Pos<>(null, this, 0, 0);
        positions[0].isValid = false;
        this.counter = 0;
    }

    /**
     * Create a SetContainerArray with chosen size
     * @param size Size of the Array to hold the Container-Objects
     */
    public SetContainerArray(int size) {
        this.positions = (Pos<Elem>[]) new Pos[size];
        this.posSize = 0;
        this.elemSize = 0;
        positions[0] = new Pos<>(null, this, 0, 0);
        positions[0].isValid = false;
        this.counter = 0;
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
        long tmpCounter = this.counter;
        Pos temp = this.find(elem.key);
        if (temp != null) {
            if (temp.isValid) {
                return temp;
            }
        }
        this.counter = tmpCounter;

        // check if the array needs to be enlarged and do so if necessary
        if (posSize == positions.length-2) {
            positions = Arrays.copyOf(positions, positions.length*2);
        }

        // find a pos for the new element
        int i = 0;
        while (positions[positions[i].getNextIndex()] != null) {
        	if (positions[positions[i].getNextIndex()].isValid) {
                i = positions[i].getNextIndex();
        	} else {	// free place found
        		break;
        	}
        }
        int nextFree = positions[i].getNextIndex();
        
        if (nextFree == 0) {
        	// All positions occupied, create new one
            positions[posSize+1] = new Pos<Elem>(elem, this, i, 0);
            positions[posSize+1].isValid = true;
            positions[i].setNextIndex(posSize+1);
            positions[0].setPreviousIndex(posSize+1);
            posSize++;
            elemSize++;
            return positions[posSize];
        } else if (!positions[nextFree].isValid) {            // found empty position, insert
            positions[nextFree].setPointer(elem);
            //REDUNDANT? positions[nextFree].setPreviousIndex(i);
            positions[nextFree].isValid = true;
            elemSize++;
            return positions[nextFree];
        } else {	// Error
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
    	// Check if set is this set
		if (pos.getSet() != this) {
			return;
		}
		
    	for (int i=1; i <= posSize; i++) {
    	    counter++;
    		if (positions[i] == pos) {
    			if (positions[i].isValid) {
    				positions[i].isValid = false;
    				positions[i].setPointer(null);
    				
    				// Link neighbours
    				int pre = positions[i].getPreviousIndex();
    				int next = positions[i].getNextIndex();
    				positions[pre].setNextIndex(next);
    				positions[next].setPreviousIndex(pre);
    				
    				// Append at ending
    				positions[positions[0].getPreviousIndex()].setNextIndex(i);
    				positions[i].setNextIndex(0);
    				positions[0].setPreviousIndex(i);
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
    	this.delete(this.find(key));
    }

    /**
     * Find an element with its key and return its Pos.
     * @param key The key of the element we want to find.
     * @return The Pos of the found element.
     */
    @Override
    public Pos find(int key) {
    	Elem dummy = new Elem<>(key, null);
    	positions[0].setPointer(dummy);
    	
    	int i=0;
    	while(true) {
    	    counter++;
    		int pre = positions[i].getPreviousIndex();
			Elem tmp = (Elem)positions[pre].getPointer();
    		if (tmp != null) {
	    		if (tmp.key == key) {
	    			return positions[pre];
	    		}
    		} else {
    			//error
    			return null;
    		}
    		i = pre;
    	}
    }

    /**
     * Retrieve an Element by its Pos.
     *
     * @param pos The Pos of the element we want to retrieve.
     * @return The element.
     */
    @Override
    public Elem retrieve(Pos pos) {
        return (Elem) pos.getPointer();
    }

    /**
     * Retrieve all Elements of set.
     *
     * @return The elements.
     */
    @Override
    public Elem<?>[] retrieveAll() {
    	Elem<?>[] tmp = new Elem[posSize+1];
    	int i=positions[0].getNextIndex();
    	int j=1;
    	while (positions[i].isValid) {
    		tmp[j] = (Elem) positions[i].getPointer();
    		i = positions[i].getNextIndex();
    		j++;
    	}
        return tmp;
    }

    /**
     * Prints every element of the set.
     */
    @Override
    public void showall() {
    	int i=positions[0].getNextIndex();
    	while (positions[i].isValid) {
    		System.out.println(positions[i].toString());
    	}
    }

    /**
     * Returns the size of the set.
     *
     * @return The size of the set.
     */
    @Override
    public int size() {
        return elemSize;
    }

    /**
     * Unifies two sets and returns them.
     *
     * @param s The first set to be unified.
     * @param t The second set to be unified.
     * @return The unified set.
     */
    public static SetContainerArray unify(SetInterface s, SetInterface t) {
    	Elem<?>[] sElements = s.retrieveAll();
    	Elem<?>[] tElements = t.retrieveAll();
    	int eSize = sElements.length + tElements.length;
    	SetContainerArray temp = new SetContainerArray(eSize);
    	
    	// Add elements of first set
        for (int i=1; i < sElements.length; i++) {
        	if (sElements[i] == null) {	//stop adding when entry is null
        		break;
        	}
            temp.add(sElements[i]);
        }
    	// Add elements of second set
        for (int i=1; i < tElements.length; i++) {
        	if (tElements[i] == null) {	//stop adding when entry is null
        		break;
        	}
            temp.add(tElements[i]);
        }
    	
        return temp;
    }
}