# Applications :hotsprings:

In this section, we will enumerate data structures and algorithms and how they can be utilized to solve particular
problems efficiently.

## Data Structures

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
   root.

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

## Algorithms

### ?

#### Implementation

#### When to use
