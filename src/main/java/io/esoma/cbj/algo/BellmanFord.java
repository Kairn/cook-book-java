package io.esoma.cbj.algo;

/**
 * Class for illustrating the Bellman-Ford algorithm. This is an algorithm that computes shortest
 * paths from a single source vertex to all of the other vertices in a weighted digraph. It is
 * slower than Dijkstra's algorithm for the same problem, but more versatile, as it is capable of
 * handling graphs in which some of the edge weights are negative numbers. It is also more efficient
 * when the problem limits the total number of stops that one can travel from the source. The
 * algorithm was first proposed by Alfonso Shimbel (1955), but is instead named after Richard
 * Bellman and Lester Ford Jr., who published it in 1958 and 1956, respectively.
 */
public class BellmanFord {

  /**
   * Finds the shortest (with the least weight) path from a source vertex to a destination vertex in
   * a directed weighted graph with at most k stops (k + 1 edges). All weights are positive numbers
   * only. If there is no solution, then -1 is returned.
   *
   * @param n the total number of vertices (labeled from 0 to n - 1)
   * @param edges a 2-dimensional array in which each element represents an edge (contains the start
   *     vertex, the end vertex, and the weight of the path, respectively) in a directed weighted
   *     graph
   * @param src the source vertex
   * @param dest the destination vertex
   * @param k the maximum number of stops (vertices between the source and the destination)
   * @return the cumulative weight of the shortest path
   */
  public static int bfFind(int n, int[][] edges, int src, int dest, int k) {
    return 0;
  }
}
