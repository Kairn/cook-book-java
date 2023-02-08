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

### ?

#### Implementation

#### When to use

## Algorithms

### ?

#### Implementation

#### When to use
