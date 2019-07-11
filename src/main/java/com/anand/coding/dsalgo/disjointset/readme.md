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

- **Find**: Find the representative/parent of element i.
    ```java
    class DisjointSet{
        // Find the representative of element i
        int find(int i) {
            int rep;
            // i itself is the representative
            if (this.Parent[i] == i){
                rep = i;
            } 
            // Else recursively call find on its parent
            else {
                rep =  find(this.Parent[i]);
              
                // Path Compression: 
                this.Parent[i] = rep;
            }
            return rep;
        }
    }
    ```
    
    **Improvement**: Path Compression (parent caching).
    1. If the same element is used again and again we can compress the path
       by simply changing the parent. Its like caching the parent. See the above code.
    

- **Union**: Unite two disjoint sets containing elements i and j respectively.
    ```java
    class DisjointSet{

        //Unite two disjoint sets containing elements i and j respectively.
        void union(int i, int j) {
      
            // Find representative of of the set containing i
            int iRep = this.find(i);
        
            // Find representative of of the set containing i    
            int jRep = this.find(j);
        
            // Replace parent of iRep to parent of jRep.
            this.Parent[irep] = jRep;
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
 
         //Unite two disjoint sets containing elements i and j respectively.
         void union(int i, int j) {
       
             // Find representative of of the set containing i
             int iRep = this.find(i);
         
             // Find representative of of the set containing i    
             int jRep = this.find(j);    
           
             if(iRep==jRep){
                  return;
             }

             if(Rank[iRep] < Rank [jRep]){
                  this.parent[iRep] = jRep;
             } else if (Rank[iRep] > Rank [jRep]){
                  this.parent[jRep] = iRep;
             } else {
                  this.parent[iRep] = jRep;
                  Rank[jRep]++;
              }
         }
     }
     ```