public class SLL<T> {
    private int COUNT;
    private Node HEAD;
    private Node REAR;

    public SLL() {
        super();

        COUNT = 0;
        HEAD = null;
        REAR = null;
    }

    private boolean isEmpty() {
        return COUNT == 0;
    }

    private void addEmpty(T theValue) {
        Node temp = new Node(theValue, null);
        HEAD = temp;
        REAR = temp;
        COUNT++;
    }

    public void prepend(T theValue) {
        // Blank method with name that's intuitive to me
        addFront(theValue);
    }

    public void addFront(T theValue) {
        if (isEmpty()) {
            addEmpty(theValue);
        } else {
            Node temp = new Node(theValue, HEAD);
            HEAD = temp;
            COUNT++;
        }
    }

    public void append(T theValue) {
        // Blank method with name that's intuitive to me
        addRear(theValue);
    }

    public void addRear(T theValue) {
        if (isEmpty()) {
            addEmpty(theValue);
        } else {
            Node temp = new Node(theValue, null);
            REAR.NEXT = temp;
            REAR = temp;
            COUNT++;
        }
    }

    public void insert(T theValue, int theIndex) {
        // Blank method with name that's intuitive to me
        add(theValue, theIndex);
    }

    public void add(T theValue, int theIndex) {
        if (isEmpty()) {
            addEmpty(theValue);
        } else if (theIndex == COUNT) {
            // check if we're adding to the end
            addRear(theValue);
        } else if (theIndex == 0) {
            // check if we're adding to the front
            addFront(theValue);
        } else if (theIndex >= COUNT) {
            // check if the index is out of bounds for our size
            throw new IndexOutOfBoundsException("Index " + theIndex + " out of bounds for length " + COUNT);
        } else {
            Node current = HEAD;
            // Get to the node just before our target position
            for (int i = 0; i < theIndex - 1; i++) {
                current = current.NEXT;
            }
            Node temp = new Node(theValue, current.NEXT);
            current.NEXT = temp;
            COUNT++;
        }
    }

    public T delete(int theIndex) {
        // check that the index is within our list size
        if (theIndex > COUNT) {
            throw new IndexOutOfBoundsException("Index " + theIndex + " out of bounds for length " + COUNT);
        } else {
            // find the node just before the target
            Node current = HEAD;
            for (int i = 0; i < theIndex; i++) {
                current = current.NEXT;
            }
            // save the target node, then de-link it and decrement COUNT
            Node temp = current.NEXT;
            current.NEXT = current.NEXT.NEXT;
            COUNT--;
            return temp.VALUE;
        }
    }

    public T get(int theIndex) {
        // check that the index is within our list size
        if (theIndex >= COUNT) {
            throw new IndexOutOfBoundsException("Index " + theIndex + " out of bounds for length " + COUNT);
        } else {
            // find the target node and return it's value
            Node current = HEAD;
            for (int i = 0; i < theIndex; i++) {
                current = current.NEXT;
            }
            return current.VALUE;
        }
    }

    public int size() {
        return COUNT;
    }    

    public void swap(int indexA, int indexB) {
        if (indexA == indexB) {
            return;
        } else if (indexA > COUNT || indexB > COUNT) {
            throw new IndexOutOfBoundsException("Index " + indexA + ", " + indexB + " out of bounds for length " + COUNT);
        } else if (indexA == 0 || indexB == 0) {
            // TODO: Special case for index 0
        } else {
            Node A;
            Node B;
            Node temp;
            Node prevA = HEAD;
            Node prevB = HEAD;
            for (int i = 0; i < Math.max(indexA, indexB); i++) {
                if (i < indexA - 1) {
                    prevA = prevA.NEXT;
                } else if (i < indexB - 1) {
                    prevB = prevB.NEXT;
                }
            }
            A = prevA.NEXT;
            temp = A.NEXT;
            B = prevB.NEXT;

            System.out.println("\nShuffling! " + prevA + ", " + prevB + ", " + A + ", " + B);
            // shuffle it all around
            /*
            * 18->79->46->75->99
            * A0  t1  B2  t3   4
            * 
            * 18->75
            * 75->46
            * 79->99
            * 46->79
            */
            // this is wrong
            delete(indexA - 1);
            print(toString());
            delete(indexB - 2);
            print(toString());
            insert(A.VALUE, indexB - 1);
            print(toString());
            insert(B.VALUE, indexA);
            print(toString());
        }
    }

    private void print(Object a) {
        System.out.println(a);
    }

    @Override
    public String toString() {
        Node current = HEAD;
        String out = "[" + HEAD.VALUE;
        while (current.NEXT != null) {
            current = current.NEXT;
            out += ", " + current.VALUE;
        }
        out += "]";
        return out;
    }

    private class Node {
        public T VALUE;
        public Node NEXT;

        private Node(T theData, Node theNext) {
            super();
            VALUE = theData;
            NEXT = theNext;
        }

        @Override
        public String toString() {
            return VALUE.toString();
        }
    }
}