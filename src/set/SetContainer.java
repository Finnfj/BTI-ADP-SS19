package set;

import types.*;

import java.util.Arrays;

/**<br>
 * Kurs: BTI3-ADP <br>
 * Aufgabe: 1c Set as List<br>
 * SetContainer is a Set-representing Class. <br>
 * It's working with a List organized using Nodes<br>
 * @author Finn Jannsen
 * @author Philipp Schwarz
 * @version 1.0, 24.03.19
 *
 */
public class SetContainer implements SetInterface {

    final private int DEFSIZE = 8;
    private Node head;
    private Node tail;
    private Pos<Node>[] positions;
    private int posSize;
    private int elemSize;

    public long counter;

    /**
     * Constructor for a SetContainer with a default size.
     */
    public SetContainer() {
        this.head = new Node(new Elem(-1, -1));
        this.tail = new Node(new Elem(-2, -2));
        this.head.setNext(this.tail);
        this.positions = (Pos<Node>[]) new Pos[DEFSIZE];
        this.posSize = 0;
        this.counter = 0;
        this.elemSize = 0;
    }

    /**
     * Constructor for a SetContainer with a custom size.
     * @param size The size of the set
     */
    public SetContainer(int size) {
        this.head = new Node(new Elem(-1, -1));
        this.tail = new Node(new Elem(-2, -2));
        this.head.setNext(this.tail);
        this.positions = (Pos<Node>[]) new Pos[size];
        this.posSize = 0;
        this.counter = 0;
        this.elemSize = 0;
    }

    /**
     * Add an element to the set.
     *
     * @param elem The element that is going to be added.
     * @return The Pos of the added element.
     */
    @Override
    public Pos add(Elem elem) {
        //look if we already have the element in the list
        Pos foundElemPos = find(elem.key);
        if (foundElemPos != null) {
            return foundElemPos;
        }

        // create a new node and put it at the end of the list
        Node newNode = new Node(elem);
        newNode.setNext(tail);
        Node worker = head;
        do {
            worker = worker.getNext();

        } while (worker.getNext() != null && worker.getNext() != tail);
        worker.setNext(newNode);

        // look if there is a free pos available
        int i = 0;
        boolean freePosFound = false;
        while(!freePosFound) {
            if(!positions[i].isValid) {
                freePosFound = true;
            }
            i++;
        }

        Pos returnPos = null;
        if(freePosFound) {
            positions[i].setPointer(newNode);
            positions[i].isValid = true;
            returnPos = positions[i];
        } else {
            // create a new Pos for the node
            Pos newPos = new Pos(newNode, this);
            newPos.isValid = true;
            returnPos = newPos;

            // put the new pos in the pos array
            // check if there is enough space in the pos array and if not create a new one with a larger length
            if (posSize == positions.length - 1) {
                positions = Arrays.copyOf(positions, positions.length * 2);
                positions[posSize] = newPos;
            } else { // if there is enough space just put it at the end
                int j = 0;
                boolean found = false;
                while ((j < positions.length) && !found) {
                    if (positions[j] == null) {
                        positions[j] = newPos;
                        found = true;
                    }
                    j++;
                }
            }
        }

        posSize++;
        elemSize++;
        return returnPos;
    }

    /**
     * Deletes an element with its Pos from the list.
     *
     * @param pos The Pos of the element.
     */
    @Override
    public void delete(Pos pos) {
        for (int i = 0; i < posSize; i++) {
            counter++;
            Pos<Node> deletePos = positions[i];
            if (deletePos == pos) {
                Node deleteNode = deletePos.getPointer();
                Node worker = head;
                do {
                    counter++;
                    worker = worker.getNext();
                } while (worker.getNext() != deleteNode);
                worker.setNext(deleteNode.getNext());

                positions[i].isValid = false;
                elemSize--;
                return;
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
     * @return The Pos of the found element.
     */
    @Override
    public Pos find(int key) {
        // find the Elem with its key and set it to worker
        tail.getElem().key = key;
        Node worker = head;
        do {
            counter++;
            worker = worker.getNext();
        } while (worker.getElem().key != key);

        // find the Pos of worker
        for (Pos<Node> p : positions) {
            counter++;
            if (p != null && p.getPointer().getElem() != null && p.getPointer().getElem().key == worker.getElem().key) {
                return p;
            }
        }
        // TODO: add check if worker = tail?
        return null;
    }

    /**
     * Retrieve an Element by its Pos.
     *
     * @param pos The Pos of the element we want to retrieve.
     * @return The element or null it if could not be found.
     */
    @Override
    public Elem retrieve(Pos pos) {
        for (Pos<Node> position : positions) {
            if (position == pos) {
                return position.getPointer().getElem();
            }
        }
        return null;
    }

    /**
     * Retrieve all Elements of set.
     *
     * @return The elements.
     */
    @Override
    public Elem<?>[] retrieveAll() {
        Elem[] elemAll = new Elem[positions.length];
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] != null) {
                elemAll[i] = positions[i].getPointer().getElem();
            }
        }
        return elemAll;
    }

    /**
     * Prints every element of the set.
     */
    @Override
    public void showall() {
        Node worker = head;
        while (worker.getNext() != tail) {
            System.out.println(worker.getElem());
        }
    }

    /**
     * Returns the size of the set.
     *
     * @return The size of the set.
     */
    @Override
    public int size() {
        return posSize;
    }

    /**
     * Unifies two sets and returns them.
     *
     * @param s The first set to be unified.
     * @param t The second set to be unified.
     * @return The unified set.
     */
    public static SetContainer unify(SetInterface s, SetInterface t) {
        Elem<?>[] sElements = s.retrieveAll();
        Elem<?>[] tElements = t.retrieveAll();
        int eSize = sElements.length + tElements.length;
        SetContainer temp = new SetContainer(eSize);

        // Add elements of first set
        for (int i = 0; i < sElements.length; i++) {
            if (sElements[i] == null) {    //stop adding when entry is null
                break;
            }
            temp.add(sElements[i]);
        }
        // Add elements of second set
        for (int i = 0; i < tElements.length; i++) {
            if (tElements[i] == null) {    //stop adding when entry is null
                break;
            }
            temp.add(tElements[i]);
        }

        return temp;
    }
}