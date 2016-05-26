#Maximize Sum

You are given an array of size N and another integer M. Your target is to find the maximum value of sum of subarray modulo M.

Subarray is a continuous subset of array elements.

Note that we need to find the maximum value of (Sum of Subarray)%M , where there are `N x (N+1)/2`possible subarrays.

For a given array A[] of size N, subarray is a contiguous segment from i to j where 0<=i<=j<=N

####Input Format 
First line contains T , number of test cases to follow. Each test case consists of exactly 2 lines. First line of each test case contain 2 space separated integers  and , size of the array and modulo value M. 
Second line contains N space separated integers representing the elements of the array.

####Output Format 
For every test case output the maximum value asked above in a newline.

####Sample Input

modulo: 7
array: 3 3 9 9 5

####Sample Output

6
