## B Tree Data Structure
 
- B Tree is a self balancing search tree. It is a fat and dwarf tree. The height of a B-Tree is kept low by putting
maximum possible keys in a B-Tree node. This ensures efficient tree operations than other trees due to lower height.

- Most of the tree operations (search, insert, delete, max, min) has O(h) time complexity.

- Other self-balancing search trees like AVL and Red-Black stores whole data in main memory.
 B Tree is useful when data cannot fit in main memory and has to be read from disk in the form of blocks. 

- Generally, the B Tree node size is kept equal to the disk block size. Since the height of the 
B tree is low, total disk accesses for most of the operations ares reduced significantly.

