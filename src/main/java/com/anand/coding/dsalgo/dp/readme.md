## Dynamic Programming
 
- DP solves exponential time problems in polynomial time by breaking it into subproblems and storing their results for further reuse.
 
- DP is used when a problem contains these two properties:
   1. Overlapping subproblems:
       When to solve a problem, down the line, the solution of the subproblems are required again and again. <br>
       Eg: fib(n) = fib(n-1) + fib(n-2);
 
   2. Optimal substructure:
       If optimal solution of the problem can be obtained using optimal solutions of its subproblems. <br>
       Eg: shortestPath(U,V) = sP(U,X) + sP(X,V) if node x lies in the shortestPath from U to V in a graph.
 
       **Note**: All pair shortest path algorithms (Floyd-Warshall, Bellman-Ford) use DP.
 
- There are two ways to store values for further reuse in a DP solution:
   1. Memoization (Top Down):
       We think the main problem first and subsequent subproblems down the line in a top down approach.
       In this approach, the result of a solved subproblem is stored which will be reused if the same problem is encountered again.
       Mostly solved using recursion.
 
   2. Tabulation (Bottom Up):
       We think the smallest possible problems first and combine the results to solve upper-level subproblems to reach the solution of the final problem.
       In this approach, we keep storing the results of subproblems in a table and use the same to solve upper-level subproblems.
       Solved using loop.
 
- In tabulation all subproblems need to solved whereas in memoization only specific subproblems need to be solved.
 
### How to solve:
 DP problems are all about state and their transition. i.e; using calculated results to formulate the final result.
   1. Make a state expression with least number of parameters.
        1. A state is a set of parameters uniquely identifying a certain prosition in a given problem.
        2. In a knapsack problem, index and weight can uniquely identify a subproblem. DP[index][weight] contains 
            the max profit by taking items (all or some) from range 0 to index having sack capacity w.
        
   2. Formulate state relationship.
        1. Recursive Knapsack_0_1 solution: <br>
            **Knapsack(n,w) = knapsack(n-1,w) if wt[n]>w <br>
                            = max(val[n] + knapsack(n-1, w-wt[n]), knapsack(n-1,w)) otherwise**
                            
   3. Solve using tabulation or memoization.
 