package ArtificialIntel.Algo;

class  BinaryHeap{
    private int MAX_SIZE = 0;
    private IBinaryHeapElement [] heap;
    private int size;

    // MAX_SIZE = width * height
    // pass in new Cell[MAX_SIZE]
    // Cell [] array = Cell[ width * length];
    // heap = new BinaryHeap(array);
    // for( i)
    // for j
    // Cell c = grid[i][j];
    // heap.insert(c);
    //
    public BinaryHeap(IBinaryHeapElement [] array) {
        heap = array;
        size = 0;
        MAX_SIZE = heap.length;
    }

    // returns the index of the parent node
    public static int parent(int i) {
        return (i - 1) / 2;
    }

    // return the index of the left child 
    public static int leftChild(int i) {
        return 2*i + 1;
    }

    // return the IBinaryHeapElement of the left child 
    public IBinaryHeapElement leftChildElement(int i) {
        return heap[leftChild(i)];
    }

    // return the index of the right child 
    public static int rightChild(int i) {
        return 2*i + 2;
    }

    // return the IBinaryHeapElement of the right child 
    public IBinaryHeapElement rightChildElement(int i) {
        return heap[rightChild(i)];
    }


    // insert the item at the appropriate position
    public void insert(IBinaryHeapElement data) {
        if (size >= MAX_SIZE) {
            System.out.println("The heap is full. Cannot insert");
            return;
        }

        // first insert the time at the last position of the array 
        // and move it up
        heap[size] = data;
        size = size + 1;


        // move up until the heap property satisfies
        int i = size - 1;
        while (i != 0 && heap[BinaryHeap.parent(i)].LessThan(heap[i])) {
            IBinaryHeapElement temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = BinaryHeap.parent(i);
        }
    }

    // moves the item at position i of array a
    // into its appropriate position
    public void maxHeapify(int i){
        // find left child node
        int left = BinaryHeap.leftChild(i);

        // find right child node
        int right = BinaryHeap.rightChild(i);

        // find the largest among 3 nodes
        int largest = i;

        // check if the left node is larger than the current node
        if (left <= size && heap[left].GreaterThan(heap[largest])) {
            largest = left;
        }

        // check if the right node is larger than the current node 
        // and left node
        if (right <= size && heap[right].GreaterThan( heap[largest])) {
            largest = right;
        }

        // swap the largest node with the current node 
        // and repeat this process until the current node is larger than 
        // the right and the left node
        if (largest != i) {
            IBinaryHeapElement temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            maxHeapify(largest);
        }

    }

    // returns the  maximum item of the heap
    public IBinaryHeapElement getMax() {
        return heap[0];
    }

    // deletes the max item and return
    public IBinaryHeapElement extractMax() {
        IBinaryHeapElement maxItem = heap[0];

        // replace the first item with the last item
        heap[0] = heap[size - 1];
        size = size - 1;

        // maintain the heap property by heapifying the 
        // first item
        maxHeapify(0);
        return maxItem;
    }

    // prints the heap
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

}
