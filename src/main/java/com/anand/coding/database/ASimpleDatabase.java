package com.anand.coding.database;

/**
 * A Simple Database: storage, retrieval, updation, indexing(b/b+ tree).
 *
 * Databases should have an efficient way to store, read, and modify data. B-tree provides an efficient way to insert
 * and read data. In actual Database implementation, the database uses both B-tree and B+tree together to store data.
 * B-tree used for indexing and B+tree used to store the actual records.
 * B+tree provides sequential search capabilities in addition to the binary search, which gives the database more
 * control to search non-index values in a database.
 *
 * Note:
 * 1. HashMap can also be used for indexing, but it's not worth for queries like all the records less than <VALUE>.
 * 2. B-Tree is a single dimensional indexing. General use.
 * 3. Quad-Tree is a 2 dimensional indexing. Spatial data use (longitude/latitude)
 * 4. R-Tree is a multidimensional indexing. Spatial data use and many more.
 *
 *
 */
public class ASimpleDatabase {


}
