# Applications :hotsprings:

In this section, we will look at abstract data structures (ADTs) and related algorithms and see how they can be utilized
to solve particular problems efficiently.

## Abstract Data Structures

### Symbol table

An abstract mechanism for storing values which are retrieved by keys.

#### Implementation

1. Unordered symbol table can be implemented by hash tables.
2. Ordered symbol table can be implemented by red-black BSTs.

#### When to use

1. Use ordered symbol table when range search, rank, or select (min or max) is called for.
2. Use unordered symbol table for all other general use cases, and when the keys are sufficiently simple to compute a
   hash.
3. Symbol tables can be used for dictionary clients to do generic lookup, indexing clients to build inverted index, or
   store sparse vectors/matrices efficiently (e.g. PageRank calculation).

### Push-down stack

A collection based on the last-in-first-out (LIFO) policy.

#### Implementation

1. Use a dynamically sized array with a pointer keeping track of the last pushed element's index.
2. Build a (reversed) linked list with the last pushed element being the head pointing to the previous head.

#### When to use

1. An interpreter application to evaluate expressions from left to right.

### Disjoint set

A collection of disjoint sets.

#### Implementation

1. Use a set of linked nodes.
    * Every element in the collection is represented by a node with a pointer to its parent and a size.
    * Initially every node points to itself and has a size of 1.
    * A find operation recursively searches from the bottom node to its root.
    * Every union operation finds the root of the two nodes and updates one's root to match the other (if different).
      The tree that has a smaller size is always connected to the one with a larger size.
    * In each find operation, path compression can be used by updating each intermediate node's parent to the final
      root.

#### When to use

1. Solve a dynamic connectivity problem with union find.
2. Finding the minimum spamming tree by iteratively connecting the next cheapest edge until all nodes belong to a single
   root, also known as Kruskal's algorithm.

### Priority queue

A queue-like collection where the maximum/minimum element can be extracted efficiently.

#### Implementation

1. Use a binary heap (complete binary tree where the parent node is always larger/equal to its children, if max heap).
    * Efficiently store tree nodes in an array in level-order.
    * To insert, add the key at the bottom on the next available slot and let it swim up to restore heap order.
    * To delete a key, replace it with the last element and let it sink down to restore heap order.
    * Arbitrary data in an array can be initialized to a heap by constructing subtrees that are heap ordered from the
      bottom up. To do this, start at the middle (lowest parent) and sink this key, repeat until the root is processed.

#### When to use

1. Sorting data by iteratively removing elements.
2. Find the running median (or top K) of a data stream using two of these queues to partition the stream into a higher (
   min heap) and a lower (max heap) part.
3. Combinatorial search. Put the start configuration into the queue; repeatedly removes the highest priority item from
   the queue and add the next possible configurations back into the queue until the goal configuration is reached.

### Adjacency-lists

In the context of representing an undirected graph, where a linked list is maintained for each vertex containing other
vertices connected to it. Lists can be replaced by sets to get a faster check connection operation when the graph is
dense (many edges) with more overhead in add connection operation.

In the context of representing a directed graph, the same structure can be used. But instead of representing a
connection, each list element is merely considered reachable from the source vertex.

#### Implementation

1. Use one linked list or array to store lists of connected vertices. Vertices are identified by their indexes from 0 to
   V - 1, assuming V is the number of vertices in the graph.
2. Use a symbol table to store the mapping between vertices and their adjacency lists. Vertices can be represented by
   any type instead of only integer indexes.

#### When to use

1. Find (transitive) connectivity/path between two vertices in an undirected graph using DFS/BFS.
2. Find unreachable objects in a mark-and-sweep garbage collection cycle.
3. Solve a precedence-constrained scheduling problem and detect a directed cycle.
4. Find connected components in an undirected graph or strongly connected components in a digraph (Kosaraju’s
   algorithm).

### Character-indexed array (alphabet)

A data structure for associating characters to numerical indices and from indices back to characters.

#### Implementation

Use an character array to store index-char mapping, and use a reverse integer array (size of 65556 for supporting all
2-byte unicode chars) to store char-index mapping. With a limited alphabet, the size of the reverse array can be reduced
to the largest character.

#### When to use

1. In algorithms where there is the need to efficiently access information by characters. Such as a character counting
   algorithm.

### Trie

A tree-like data structure build from characters of strings.

#### Implementation

1. A full search trie can be represented by nodes and links to next nodes. Each node corresponds to a character (or a
   prefix if path is taken into consideration) and points to R nodes in an array, where R is the cardinality of the
   alphabet. Each node also contains a stored value that can indicate the presence of the full string and/or the value
   associated to that string key.
2. When the alphabet and/or the length of input strings are huge, an alternative is to use a ternary search trie where
   each node contains 3 links, pointing to nodes whose character is less than, equal to, and greater than the current
   character of the node. To keep the trie balanced, the structure can be built from the knowledge of the alphabet,
   instead of from the input as in a BST.

#### When to use

1. Search strings by prefixes and wildcards.
2. General efficient string matching for small alphabets and short keys.
3. To represent Huffman encoding.

### ?

#### Implementation

#### When to use

## Algorithms

### Prim's algorithm

Prim's algorithm is a greedy algorithm that finds a minimum spanning tree for a weighted undirected graph.

#### Implementation

1. Pick an arbitrary vertex to start with, create a vertex-indexed array, with each element being the current "known"
   best edge that connects that vertex to the existing MST.
2. Create a priority queue of edges that supports finding the edge with minimum weight.
3. Each edge contains source and destination vertices as well as the weight.
4. At the starting vertex, add all edges in the adjacency-list to the priority queue and update the array with these
   initial best edges. Then, poll the "best" edge to add that edge and associated vertex to the MST.
5. After adding a vertex, enumerate all edges in that vertex's adjacency-list and check if the weight is less than the "
   known" best edge stored. If yes, update the array and push that edge to the priority queue and continue.
6. The algorithm stops after collecting all vertices.

### Dijkstra's algorithm

Dijkstra’s algorithm solves the single-source shortest-paths problem in edge-weighted digraphs with non-negative
weights.

#### Implementation

1. Initialize a vertex-indexed array of all vertices. Each vertex element tracks the current shortest distance from
   source to it and whether it has been marked.
2. Each vertex element is initialized to having a distance of infinity, and unmarked.
3. Initializes a priority queue of these vertices supporting finding the vertex with minimum distance from source.
4. Set the source vertex's distance to itself to 0, and push it to the queue.
5. While the queue is not empty, as each vertex is polled from queue, it must be marked first. Then, its edges are
   iterated, and the distances of the edge targets can be updated if traveling from the given edge (after going from
   source to the current vertex first) is faster than the currently recorded shortest distance. When updating, if the
   target vertex is not yet in the queue, it will be added, and if in the queue, its priority is also updated to reflect
   the better distance.
6. In the above operation, if a target vertex is already marked, it doesn't need examination. As weights are
   non-negative, every polled vertex is guaranteed to have the shortest path from source.
7. When the queue is empty, the algorithm ends and all vertex elements have stored their shortest distances from source.

### Topological sort

Topological ordering of a directed graph is a linear ordering of its vertices such that for every directed edge u-v from
vertex u to vertex v, u comes before v in the ordering. Precisely, a topological sort is a graph traversal in which each
node v is visited only after all its dependencies are visited, with each directed edge pointing to a node's dependent. A
digraph may contain many topological orderings, but there must be no directed cycles.

#### Implementation

1. A simple DFS solution can be used by visiting each node in arbitrary order with adjacency-lists.
2. Initialize an empty queue for storing ordered nodes, and each node has a temporary mark and a permanent mark.
3. Loop through (visit) each node, if a node is permanently marked, return, otherwise, mark it temporarily and visit its
   neighbors by checking each directed edge originating from it.
4. If a visited node is already marked temporarily, the process fails because a cycle is detected.
5. After visiting the children, each parent node is marked permanently and added to the HEAD of the queue.
6. When the DFS ends, the queue contains sorted nodes from the least dependent to the most dependent (head to tail).

### Critical path method

The critical path method finds the minimum time required to complete a parallelized schedule with precedence
constraints. It is equivalent to finding the longest path in an edge-weighted acyclic digraph, where edge weights
represent time requirement and the direction represents precedence.

#### Implementation

1. Create an edge-weighted DAG with a virtual source vertex and a virtual sink vertex.
2. For each job, create two vertices representing the start and end, and an edge connecting from the start to the end
   with the time as weight.
3. For each constraint, add a zero-weight edge from the end of the dependency to the start of the dependent.
4. There is one zero-weight edge from source to each start node, and from each end node to sink.
5. The longest path from source to sink is the critical path.
6. To find the longest path, convert the digraph into a shortest path tree by first negating all of the weights.
7. Using the strategy similar to Dijkstra's with edge relaxation, but processing the vertices in topological order, the
   shortest path can be found in linear time.

### Radix string sort

A sorting algorithm for strings based on key-indexed counting. Instead of doing full string comparisons, it processes
one character at a time for all strings (at the same position), and it uses counting sort to rank strings in each pass.
Most suitable for random strings with a small Alphabet (character set).

#### Implementation

1. Starting at the first character (most significant character), in each character pass, create a count array for each
   possible character and count the occurrence of them in strings at that position.
2. After counting, re-slot strings into "buckets" based on the counts. At the end of the pass, the original string array
   will be divided into numerous sub-arrays each containing the same character at the examined position.
3. Recursively do the same work on each sub-array by proceeding to the next character until the sub-array only has one
   element. The sort naturally terminates when all recursive calls have returned.
4. If all strings have equal length, an alternative is to start with the least significant character, and rank all
   strings one character at a time while maintaining stability. Strings will be sorted once all characters are ranked.

### Boyer-Moore substring search

A sublinear substring search algorithm based on mismatched character heuristic.

#### Implementation

1. Preprocess the substring pattern by building a "right" array which stores the rightmost index of each alphabet
   character in the pattern.
2. First, line up the beginning of the patten to the text, and start comparing from right to left.
3. If the character matches, continue the verification to the left until a mismatch or the entire pattern is matched.
4. For a mismatch, slide the pattern to the right so that the mismatch character in text lines up with the same
   character in the rightmost position in the pattern. The sliding distance can be found with the "right" array.
5. If the mismatched character is not in the pattern, shift by the length of the entire pattern.
6. This heuristic works because shifting any less on a mismatch will cause the mismatched character to again line up
   with a different character in the pattern.

### Huffman compression/code

A form of lossless compression achieved by constructing an optimal prefix-free code for characters in the data stream.
More frequently used characters are encoded with fewer bits, and the frequency distribution affects the compression
ratio.

#### Implementation

1. Start with a forest of single-leaf trees (one for each character), and do a read pass on the input and store the
   frequencies of each leaf.
2. Repeatedly combine two of the least used leaves into a single node (by appending a parent with the sum of the
   counts). The trie is complete once there is one tree left.
3. To compress, read the trie and create a mapping of character to code, and then write the code.
4. To expand, walk down the trie given the bit stream and write down each character once reached and go back to top.
