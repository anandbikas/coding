## B Tree Data Structure
 
### Description
- B Tree is a self balancing search tree. It is a fat and dwarf tree. The height of a B-Tree is kept low by putting
maximum possible keys in a B-Tree node. This ensures efficient tree operations than other trees due to lower height.

- Most of the tree operations (search, insert, delete, max, min) has O(h) time complexity.

- Other self-balancing search trees like AVL and Red-Black stores whole data in main memory.
 B Tree is useful when data cannot fit in main memory and has to be read from disk in the form of blocks. 

- Generally, the B Tree node size is kept equal to the disk block size. Since the height of the 
B tree is low, total disk accesses for most of the operations are reduced significantly.

### Applications of B Trees
- Records in a database are stored in random order. Searching through this requires lots of disc accesses 
in O(n).
- B tree is used to create index on a data field to facilitate faster access to the actual data stored on the disks.

- The drawback of B tree index is that it stores data pointer (a pointer to the disk file block containing the key 
value record) along with the key value in its node. This reduces the number of entries in a node thereby increasing
the number of levels in the B tree, hence increasing the search time of a record.

- B+ tree eliminates the drawback by storing data pointers only at the leaf nodes of the tree.


### B+ Tree
- Its like BTree except...
    1. Internal nodes only contain keys.
    2. Leaf nodes contain both key and value.
    3. The keys in internal nodes are also stored in leaf nodes along with their values.
            (Note: Best way to store an internal key at the beginning of its right leaf child)
    4. All leaf nodes are connected in the form of a singly Linked List to facilitate linear traversal.
    5. We can access the data stored in a B+ tree sequentially as well as directly.
    6. The leaf nodes form the first level of index, while the internal nodes form the other levels of a multilevel index.
    7. Faster search queries as the data is stored only on the leaf nodes.


### Applications of B+ Trees
- B+ Tree are used to store the large amount of data which can not be stored in the main memory. 
As main memory is always limited, the internal nodes (keys to access records) of the B+ tree are stored in 
the main memory whereas leaf nodes (key-value) are stored in the secondary memory.

### B Tree Page:
- B trees are a common data structure for very large collections, such as found in databases. They are often too large to 
be stored in memory at once, so they are stored in a file on disk, and only the portions necessary for the current 
operation are read into memory.

- A piece of data that is stored to disk (and read into memory) as a unit is called a page. It is typical for a B tree to
store the number of records in a single node that make the node size equal to the natural page size of the file-system.
In this way, the disk accesses can be optimized. 

- For example, if the file system operates on 16 kb blocks of data, and if the size of a record in the B tree is 500 bytes
(including the link to the next level of nodes) then 32 records could be stored in the node, making the node size equal 
to the page size, and allowing the disk accesses to be optimized.

### References:
     * https://www.javatpoint.com/b-tree
     * https://www.javatpoint.com/b-plus-tree
     * https://www.geeksforgeeks.org/introduction-of-b-tree-2/
     * https://www.geeksforgeeks.org/introduction-of-b-tree/
     * https://stackoverflow.com/questions/2502551/what-is-a-b-tree-page/2502662#2502662