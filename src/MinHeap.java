<<<<<<< HEAD
=======

>>>>>>> cc6b5e9cdf01138176b2ef94cf19148063f06662


// class  MinHeap {
//     IBinaryHeapElement [] heap;
//     int size;
//     private int maxsize;
 
//     // Initializing front as static with unity
//     private static final int FRONT = 0;
 
//     // MAX_SIZE = width * height
//     // pass in new Cell[MAX_SIZE]
//     // Cell [] array = Cell[ width * length];
//     // heap = new BinaryHeap(array);
//     // for( i)
//     // for j
//     // Cell c = grid[i][j];
//     // heap.insert(c);
//     //
//     // Constructor of this class
//     public MinHeap(IBinaryHeapElement [] array)
//     {
//         // This keyword refers to current object itself
//         heap = array;
//         this.maxsize = heap.length;
//         this.size = 0;
//         heap[0] = Grid.Instance().getStart(); //Root first adds the start then inserts its neighbor nodes
//     } 
 
//     // Method 1
//     // Returning the position of
//     // the parent for the node currently
//     // at pos
//     private int parent(int pos) { return pos / 2; }
 
//     // Method 2
//     // Returning the position of the
//     // left child for the node currently at pos
//     private int leftChild(int pos) { return (2 * pos); }
 
//     // Method 3
//     // Returning the position of
//     // the right child for the node currently
//     // at pos
//     private int rightChild(int pos)
//     {
//         return (2 * pos) + 1;
//     }
 
//     // Method 4
//     // Returning true if the passed
//     // node is a leaf node
//     private boolean isLeaf(int pos)
//     {
 
//         if (pos > (size / 2) && pos <= size) {
//             return true;
//         }
 
//         return false;
//     }
 
//     // Method 5
//     // To swap two nodes of the heap
//     private void swap(int fpos, int spos)
//     {
 
//         IBinaryHeapElement tmp;
//         tmp = heap[fpos];
 
//         heap[fpos] = heap[spos];
//         heap[spos] = tmp;
//     }


//     // return the IBinaryHeapElement of the left child 
//     public IBinaryHeapElement leftChildElement(int i) {
//         return heap[leftChild(i)];
//     }

//     // return the IBinaryHeapElement of the right child 
//     public IBinaryHeapElement rightChildElement(int i) {
//         return heap[rightChild(i)];
//     }

//     // return the IBinaryHeapElement of the right child 
//     public IBinaryHeapElement parentElement(int i) {
//         return heap[parent(i)];
//     }

//     // moves the item at position i of array a
//     // into its appropriate position
//     private void minHeapify(int pos)
//     {
//         // If the node is a non-leaf node and greater
//         // than any of its child
//         if (!isLeaf(pos)) {
//             IBinaryHeapElement cell = heap[pos];
//             IBinaryHeapElement cellLeft = this.leftChildElement(pos);
//             IBinaryHeapElement cellRight = this.rightChildElement(pos);
//             if (cell.GreaterThan(cellLeft)  || cell.GreaterThan(cellRight)) {
 
//                 // Swap with the left child and heapify
//                 // the left child
//                 if (cellLeft.LessThan(cellRight)) { 
//                     swap(pos, leftChild(pos));
//                     minHeapify(leftChild(pos));
//                 }
                
//                 // Swap with the right child and heapify
//                 // the right child
//                 else {
//                     swap(pos, rightChild(pos));
//                     minHeapify(rightChild(pos));
//                 }
//             }
//         }
//     }
    
//     // returns the  maximum item of the heap
//     public IBinaryHeapElement getMin() {
//         return heap[0];
//     }

//     // Method 7
//     // To insert a node into the heap
//     public void insert(IBinaryHeapElement element)
//     {
//         if (size >= maxsize) {
//             return;
//         }
 
//         heap[++size] = element;
//         int current = size;
//         IBinaryHeapElement cellParent = this.parentElement(current);
//         while (element.LessThan(cellParent)  ) {
//             swap(current, parent(current));
//             current = parent(current);
//         }
//     } 
    
//     // deletes the min item and return
//     public IBinaryHeapElement extractMin() {
//         IBinaryHeapElement minItem = heap[0];

//         // replace the first item with the last item
//         heap[0] = heap[size - 1];
//         size = size - 1;

//         // maintain the heap property by heapifying the 
//         // first item
//         // minHeapify(0);
//         return minItem;
//     }


//     // Method 8
//     // To print the contents of the heap
//     public void print()
//     {
//         for (int i = 1; i <= size / 2; i++) {
 
//             // Printing the parent and both childrens
//             System.out.print(
//                 " PARENT : " + heap[i]
//                 + " LEFT CHILD : " + heap[2 * i]
//                 + " RIGHT CHILD :" + heap[2 * i + 1]);
 
//             // By here new line is required
//             System.out.println();
//         }
//     }
 
//     // Method 9
//     // To remove and return the minimum
//     // element from the heap
//     public IBinaryHeapElement remove()
//     {
 
//         IBinaryHeapElement popped = heap[FRONT];
//         heap[FRONT] = heap[size--];
//         minHeapify(FRONT);
//         return popped;
//     }

// }
