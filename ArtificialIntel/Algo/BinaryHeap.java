package ArtificialIntel.Algo;

import ArtificialIntel.Data.Grid;

class  MinHeap{
    private IBinaryHeapElement [] heap;
    private int size;
    private int maxsize;
 
    // Initializing front as static with unity
    private static final int FRONT = 0;
 
    // MAX_SIZE = width * height
    // pass in new Cell[MAX_SIZE]
    // Cell [] array = Cell[ width * length];
    // heap = new BinaryHeap(array);
    // for( i)
    // for j
    // Cell c = grid[i][j];
    // heap.insert(c);
    //
    // Constructor of this class
    public MinHeap(IBinaryHeapElement [] array)
    {
 
        // This keyword refers to current object itself
        heap = array;
        this.maxsize = heap.length;
        this.size = 0;
        heap[0] = Grid.Instance().getStart(); //Root first adds the start then inserts its neighbor nodes
    } 
 
    // Method 1
    // Returning the position of
    // the parent for the node currently
    // at pos
    private int parent(int pos) { return pos / 2; }
 
    // Method 2
    // Returning the position of the
    // left child for the node currently at pos
    private int leftChild(int pos) { return (2 * pos); }
 
    // Method 3
    // Returning the position of
    // the right child for the node currently
    // at pos
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }
 
    // Method 4
    // Returning true if the passed
    // node is a leaf node
    private boolean isLeaf(int pos)
    {
 
        if (pos > (size / 2) && pos <= size) {
            return true;
        }
 
        return false;
    }
 
    // Method 5
    // To swap two nodes of the heap
    private void swap(int fpos, int spos)
    {
 
        IBinaryHeapElement tmp;
        tmp = heap[fpos];
 
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }


    // return the IBinaryHeapElement of the left child 
    public IBinaryHeapElement leftChildElement(int i) {
        return heap[leftChild(i)];
    }

    // return the IBinaryHeapElement of the right child 
    public IBinaryHeapElement rightChildElement(int i) {
        return heap[rightChild(i)];
    }

    // moves the item at position i of array a
    // into its appropriate position
    private void minHeapify(int pos)
    {
        // If the node is a non-leaf node and greater
        // than any of its child
        if (!isLeaf(pos)) {
            if (heap[pos] > heap[leftChild(pos)]
                || heap[pos] > heap[rightChild(pos)]) {
 
                // Swap with the left child and heapify
                // the left child
                if (heap[leftChild(pos)] < heap[rightChild(pos)]) { 
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }
 
                // Swap with the right child and heapify
                // the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }
    
    // returns the  maximum item of the heap
    public IBinaryHeapElement getMax() {
        return heap[0];
    }

    // Method 7
    // To insert a node into the heap
    public void insert(IBinaryHeapElement element)
    {
 
        if (size >= maxsize) {
            return;
        }
 
        heap[++size] = element;
        int current = size;
 
        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    } 
    
    // deletes the min item and return
    public IBinaryHeapElement extractMin() {
        IBinaryHeapElement minItem = heap[0];

        // replace the first item with the last item
        heap[0] = heap[size - 1];
        size = size - 1;

        // maintain the heap property by heapifying the 
        // first item
        // minHeapify(0);
        return minItem;
    }

    // prints the heap
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

}
