# Cook Book (Java) :books:

[![Kairn](https://circleci.com/gh/Kairn/cook-book-java.svg?style=svg)](https://app.circleci.com/pipelines/github/Kairn/cook-book-java)

The purpose of this repository is to collect many algorithmic problems and provide implementations for them. Test cases
are bundled in the source code, and they will be run when making a project build. The collection will be updated from
time to time.

## Topics

### Sorting :cyclone:

1. Insertion sort with binary search
2. Merge sort
3. Heap sort
4. Quick sort
5. Tim sort with galloping
6. Three-way string sort

### Searching :mag_right:

1. Knapsack with divide and conquer
2. Quickselect (find kth smallest element)

### Dynamic Programming :rocket:

1. Greatest common divisor
2. Longest common subsequence
3. Decimal-binary numbers (from HackerRank)
4. Maximal square (from LeetCode)
5. Interleave string (from LeetCode)

### Graph Theory :globe_with_meridians:

1. Dijkstra's algorithm
2. Minimum spanning tree
3. Bellman-Ford algorithm

### Data Structure :six_pointed_star:

1. AVL tree
2. Fibonacci heap
3. LRU cache
4. 2-3 tree
5. Red-black tree
6. Linear probing hash table

### Cryptography :key:

1. AES-128 in ECB mode and associated attacks
2. AES-128 in CBC mode and associated attacks
3. AES-128 in CTR mode and fixed-nonce attack
4. Various basic encryption schemes and attacks
5. Other crypto related functions

### Systems :pager:

1. Lsof aggregator
2. Host log reporter

### Miscellaneous :balloon:

1. Sorted sum (from HackerRank)
2. Sub-array imbalance (from Amazon test)
3. Permutation generator
4. Sub-array sum equals k (from LeetCode)
5. Partition equal subset sum (from LeetCode)
6. Sudoku solver

## Test

### Prerequisites

* [JDK 17](https://docs.aws.amazon.com/corretto/)
* [Apache Maven (wrapper available)](https://maven.apache.org/wrapper/)

### Build

1. Clone or download this repository and change into the project's root directory (where `pom.xml` is located).
2. Run `./mvnw clean verify` to compile, test, and check format.
3. Run `./mvnw spotless:apply` to format the code.
