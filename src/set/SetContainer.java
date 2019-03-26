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

    /**
     * Constructor for a SetContainer with a default size.
     */
    public SetContainer() {
        this.head = new Node();
        this.tail = new Node();
        this.head.setNext(this.tail);
        this.positions = (Pos<Node>[]) new Pos[DEFSIZE];
        this.positions[0] = new Pos<>(new Node(), this);
        this.positions[0].isValid = false;
        this.posSize = 0;
    }

    /**
     * Constructor for a SetContainer with a custom size.
     * @param size The size of the set
     */
    public SetContainer(int size) {
        this.head = new Node();
        this.tail = new Node();
        this.head.setNext(this.tail);
        this.positions = (Pos<Node>[]) new Pos[size];
        this.positions[0] = new Pos<>(new Node(), this);
        this.positions[0].isValid = false;
        this.posSize = 0;
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
        newNode.setPrevious(tail.getPrevious());
        newNode.setNext(tail);
        tail.getPrevious().setNext(newNode);
        tail.setPrevious(newNode);

        // create a new Pos for the node
        Pos newPos = new Pos(newNode, this);
        newPos.isValid = true;

        // put the new pos in the pos array
        // check if there is enough space in the pos array and if not create a new one with a larger length
        if (posSize == positions.length - 1) {
            positions = Arrays.copyOf(positions, positions.length * 2);
            positions[posSize] = newPos;
        } else { // if there is enough space just put it at the end
            int i = 1;
            boolean found = false;
            while ((i < positions.length) && !found) {
                if (positions[i] == null) {
                    positions[i] = newPos;
                    found = true;
                }
                i++;
            }
        }

        posSize++;
        return newPos;
    }

    /**
     * Deletes an element with its Pos from the list.
     *
     * @param pos The Pos of the element.
     */
    @Override
    public void delete(Pos pos) {
        for (int i = 1; i < positions.length; i++) {
            Pos<Node> position = positions[i];
            if (position == pos) {
                Node n = position.getPointer();
                n.getPrevious().setNext(n.getNext());
                n.getNext().setPrevious(n.getPrevious());

                positions[i] = null;

                posSize--;
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
        Node worker = head.getNext();
        while (worker != tail) {
            if (worker.getElem() != null) {
                if (worker.getElem().key == key) {
                    for (Pos p : positions) {
                        if (p.getPointer() == worker) {
                            return p;
                        }
                    }
                }
            }
            worker = worker.getNext();
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
        for (int i = 1; i < positions.length; i++) {
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
        for (int i = 1; i < sElements.length; i++) {
            if (sElements[i] == null) {    //stop adding when entry is null
                break;
            }
            temp.add(sElements[i]);
        }
        // Add elements of second set
        for (int i = 1; i < tElements.length; i++) {
            if (tElements[i] == null) {    //stop adding when entry is null
                break;
            }
            temp.add(tElements[i]);
        }

        return temp;
    }
}