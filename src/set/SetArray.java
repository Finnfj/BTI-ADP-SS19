package set;

import types.Elem;
import types.Pos;

import java.util.Arrays;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 1a Set as Array<br>
 * SetArray is a Set-representing Class. <br>
 * It's working with an Array of Elements<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 24.03.19
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class SetArray<T> implements SetInterface {

    final private int DEFSIZE = 8;
    private Elem<?>[] elements;
    private Pos<Integer>[] positions;
    private int elemSize;
    private int posSize;

    public long counter;

	public SetArray() {
        this.elements = new Elem[DEFSIZE];
        this.positions = (Pos<Integer>[]) new Pos[DEFSIZE];
        this.elemSize = 0;
        this.posSize = 0;
        positions[0] = new Pos<>(0, this);
        positions[0].isValid = false;
        this.counter = 0;
    }
	
	public SetArray(int size) {
        this.elements = new Elem[size];
        this.positions = (Pos<Integer>[]) new Pos[size];
        this.elemSize = 0;
        positions[0] = new Pos<>(0, this);
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
    public Pos<Integer> add(Elem elem) {
    	// Check if element already exists
    	Pos temp = this.find(elem.key);
    	if (temp.isValid) {
    		return temp;
    	}
    	
        // check if the array needs to be enlarged and do so if necessary
        if (elemSize == elements.length-1) {
            elements = Arrays.copyOf(elements, elements.length*2);
            positions = Arrays.copyOf(positions, positions.length*2);
        }
        
        elements[elemSize + 1] = elem;
        elemSize++;
        // from here on elemSize=index of added element

        // find a pos for the new element
        int i = 1;
        while (i <= posSize) {
        	if (!positions[i].isValid) {
        		// found empty position, stop searching
        		break;
        	}
            i++;
        }
        
        if (positions[i] == null) {
        	positions[i] = new Pos<>(elemSize, this);
        	positions[i].isValid = true;
        	posSize++;
        	return positions[i];
        }
        else if (i == posSize) {
        	// reached end of positions, create new one
        	positions[i+1] = new Pos<>(elemSize, this);
        	positions[i+1].isValid = true;
        	posSize++;
        	return positions[i+1];
        // i is either posSize or a position with isValid=false here
        } else if (!positions[i].isValid) {
        	// found empty position, insert
        	positions[i].setPointer(elemSize);
        	positions[i].isValid = true;
        	return positions[i];
        } else {
        	// Error
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
		
    	for (int i=1; i < positions.length; i++) {
    	    counter++;
    		if (positions[i] == pos) {
    			if (positions[i].isValid) {
    				positions[i].isValid = false;
    				Integer gap = positions[i].getPointer();
    				positions[i].setPointer(null);
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
    	this.delete(this.find(key));
    }

    /**
     * Find an element with its key and return its Pos.
     *
     * @param key The key of the element we want to find.
     * @return The Pos of the found element. Position of dummy Element if not found
     */
    @Override
    public Pos<T> find(int key) {
    	Elem<T> dummy = new Elem<>(key, null);
    	elements[0] = dummy;
    	positions[0].setPointer(0);
    	
    	for (int i=posSize; i >= 0; i--) {
    	    counter++;
    		if (positions[i].getPointer() != null) {
			    if (elements[positions[i].getPointer()].key == key) {
				    return (Pos<T>) positions[i];
			    }
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
		// Check if set is this set
		if (pos.getSet() != this) {
			return null;
		}
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
    public static SetArray unify(SetInterface s, SetInterface t) {
    	Elem<?>[] sElements = s.retrieveAll();
    	Elem<?>[] tElements = t.retrieveAll();
    	int eSize = sElements.length + tElements.length;
    	SetArray temp = new SetArray(eSize);
    	
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

    /**
     * Retrieve all Elements of set.
     * @return The elements.
     */    
    @Override
    public Elem<?>[] retrieveAll() {
//		Elem<?>[] temp = new Elem<?>[elemSize];
//        System.arraycopy(elements, 1, temp, 0, elemSize);
//		return temp;
    	return elements;
    }
}