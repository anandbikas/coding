## Disjoint Set Data Structure
 
- It is like a disconnected graph for which (instead of graph) we can create different groups (disjoint sets) each 
  represented by one of its elements called the parent.

- **Tree**: A disjoint set can be represented with tree, one of the children being the 
representative/parent/root of the set.

- **Parent Array**: To store the parents of all the elements in all the disjoint sets.

- For example, Facebook can use disjoint sets DS to store friendships of different persons. X and Y being in the 
same set means X is related to Y either directly or indirectly.

- Applications:
    - Kruskal’s Minimum Spanning Tree Algorithm.
    - Job Sequencing Problem.
    - Cycle Detection

#### Operations:

- **Find**: Find the representative/parent of element i
    ```java
    class DisjointSet {
        int find(int i) {
            if (this.Parent[i] == i) { // i itself is the representative
                return i;
            }

            int rep = find(this.Parent[i]); // Else recursively call find on its parent
            return this.Parent[i] = rep; // Path Compression:
        }
    }
    ```
    
    **Improvement**: Path Compression (parent caching).
    1. If the same element is used again and again, we can compress the path
       by simply changing the parent. It's like caching the parent. See the above code.
    

- **Union**: Unite two disjoint sets containing elements i and j respectively.
    ```java
    class DisjointSet{
        boolean union(int i, int j) {
            
            int iRep = this.find(i); // Find rep of the set containing i
            int jRep = this.find(j); // Find rep of the set containing i

            if(iRep==jRep) {
                return false;
            }
            
            this.Parent[irep] = jRep; // Replace parent of iRep to parent of jRep.
            return true;
        }
    }
    ```
   
    **Improvement**: Union By Rank.
    1. Use an extra array Rank same as Parent to store the height of the tree. Rank of the tree represented by i 
       is stored in Rank[i].
       
    2. Find rank of iRep and jRep and merge smaller ranked tree with the larger one so that the final ranking is 
       unaffected.
       
    3. If the ranks are same, merge either one to another. The final ranking will increase by one.
 
    ```java
     class DisjointSet{
        boolean union(int i, int j) {
       
             int iRep = this.find(i);
             int jRep = this.find(j);

             if(iRep==jRep) {
                  return false;
             }

             if(Rank[iRep] < Rank[jRep]) {
                  Parent[iRep] = jRep;
             } else if (Rank[iRep] > Rank[jRep]) {
                  Parent[jRep] = iRep;
             } else {
                  Parent[iRep] = jRep;
                  Rank[jRep]++;
             }
             return true;
         }
     }
     ```