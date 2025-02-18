public class SLL<T extends Comparable<T>> {
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

    public Comparable<T> delete(int theIndex) {
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

    public Comparable<T> get(int theIndex) {
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
        // Make sure indexA is the smaller one
        if (indexB < indexA) {
            int temp = indexA;
            indexA = indexB;
            indexB = temp;
        }

        if (size() < 100) print("DEBUG: swapping " + indexA + " with " + indexB);

        if (indexA == indexB) {
            return;
        } else if (indexA > COUNT || indexB > COUNT) {
            throw new IndexOutOfBoundsException("Index " + indexA + ", " + indexB + " out of bounds for length " + COUNT);
        } else if (indexA == 0 && indexB == 1) { // Case for adjacent + head
            Node A = HEAD;
            Node B = HEAD.NEXT;

            A.NEXT = B.NEXT;
            B.NEXT = A;
            HEAD = B;
        } else if (indexA == 0) { // case for head node
            Node A = HEAD;
            Node B = HEAD;
            Node prevB;
            for (int i = 0; i < indexB - 1; i++) {
                B = B.NEXT;
            }

            prevB = B;
            B = B.NEXT;

            // 1>2>3>4>5
            prevB.NEXT = B.NEXT; // 1>2>3>5 4>5
            B.NEXT = A.NEXT; // 1>2>3>5 4>2
            HEAD = B; // 4>2>3>5 1>2
            A.NEXT = prevB.NEXT; // 4>2>3>5 1>5
            prevB.NEXT = A; // 4>2>3>1>5
        } else if (indexB - indexA == 1) { // case for adjacent nodes
            Node A = HEAD;
            Node B = HEAD;
            Node prevA;
            for (int i = 0; i < indexA - 1; i++) {
                A = A.NEXT;
            }
            prevA = A;
            A = A.NEXT;
            B = A.NEXT;

            // 1>2>3>4
            prevA.NEXT = A.NEXT; // 1>3>4 2>3
            A.NEXT = B.NEXT; // 1>3>4 2>4
            B.NEXT = A; // 1>3>2>4
        } else {
            Node A;
            Node B;
            Node prevA = HEAD;
            Node prevB = HEAD;
            for (int i = 0; i < indexB - 1; i++) {
                if (i < indexA - 1) {
                    prevA = prevA.NEXT;
                }
                prevB = prevB.NEXT;
            }
            A = prevA.NEXT;
            B = prevB.NEXT;

            prevB.NEXT = B.NEXT; // 18->79->46->99 75-99
            B.NEXT = A.NEXT; //  18->79->46->99 75->46

            prevA.NEXT = A.NEXT; // 18->46->99 75-46 79->46
            A.NEXT = prevB.NEXT; // 18->46->99 75-46 79->99

            prevA.NEXT = B; // 18->75->46->99 79->99
            prevB.NEXT = A; // 18->75->46->79->99
        }
        if (size() < 100) print("Swapped: " + toString());
    }

    private void print(Object a) {
        System.out.println(a);
    }

    @Override
    public String toString() {
        Node current = HEAD;
        String out = "[" + HEAD.VALUE;
        int i = 0;
        while (current.NEXT != null) {
            current = current.NEXT;
            out += ", " + current.VALUE;
            if (i > 100) break;
            i++;
        }
        out += "]";
        return out;
    }

    private class Node {
        public Comparable VALUE;
        public Node NEXT = null;

        private Node(Comparable theData, Node theNext) {
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