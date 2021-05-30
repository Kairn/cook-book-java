# Cook Book (Java) :books:
[![Build Status](https://travis-ci.org/Kairn/cook-book-java.svg?branch=master)](https://travis-ci.org/Kairn/cook-book-java)

The purpose of this repository is to collect many algorithmic problems and provide implementations for them. Test cases are bundled in the source code, and they will be run when making a project build. The collection will be updated from time to time.

## Topics
### Sorting :cyclone:
1. Insertion sort with binary search
2. Merge sort
3. Heap sort
4. Quick sort
5. Tim sort with galloping

### Searching :mag_right:
1. Knapsack with divide and conquer

### Dynamic Programming :rocket:
1. Greatest common divisor
2. Longest common subsequence
3. Decimal-binary numbers (from HackerRank)

### Graph Theory :globe_with_meridians:
1. Dijkstra's algorithm
2. Minimum spanning tree

### Cryptography :key:
1. AES-128 in ECB mode and associated attacks
2. AES-128 in CBC mode and associated attacks
3. AES-128 in CTR mode and fixed-nonce attack
4. Various basic encryption schemes and attacks
5. Other crypto related functions

### Miscellaneous :balloon:
1. Sorted sum (from HackerRank)

## Test
### Prerequisites
* [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Apache Maven](https://maven.apache.org/download.cgi)

### Build
1. Clone or download this repository and change into the project's root directory (where `pom.xml` is located).
2. Run `mvn clean test` to execute all test cases.