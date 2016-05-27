#Cut the tree

Atul is into graph theory, and he is learning about trees nowadays. He observed that the removal of an edge from a given tree T will result in the formation of two separate trees, T1 and T2.

Each vertex of the tree T is assigned a positive integer. Your task is to remove an edge, such that the Tree_diff of the resultant trees is minimized. Tree_diff is defined as the following:

 F(T) = Sum of numbers written on each vertex of a tree T
 Tree_diff(T) = abs(F(T1) - F(T2))

####Input Format 

The first line will contain an integer N, i.e. the number of vertices in the tree.
The next line will contain N integers separated by a single space, i.e. the values assigned to each of the vertices (where the first one is the root of the tree).
The next N-1 lines contain a pair of integers each, separated by a single space, that denote the edges of the tree.
In the above input, the vertices are numbered from 1 to N.

####Output Format 

A single line containing the minimum value of Tree_diff.

####Sample Input

6
100 200 100 500 100 600
1 2
2 3
2 5
4 5
5 6

####Sample Output

400

####Source

https://www.hackerrank.com/challenges/cut-the-tree
