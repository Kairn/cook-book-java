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

    private BellmanFord() {}

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
        if (n < 2 || edges.length < 1 || src >= n || dest >= n) {
            throw new IllegalArgumentException("Invalid input parameter(s)");
        }

        if (src == dest) {
            return 0;
        }

        // Build weight caches for n vertices.
        int[] priCache = new int[n];
        int[] altCache = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i == src) {
                priCache[i] = 0;
                altCache[i] = 0;
            } else {
                priCache[i] = Integer.MAX_VALUE;
                altCache[i] = Integer.MAX_VALUE;
            }
        }

        int stops = 0;
        while (stops <= k) {
            for (int[] edge : edges) {
                int start = edge[0];
                int end = edge[1];
                int weight = edge[2];

                if (priCache[start] == Integer.MAX_VALUE) {
                    // No path to the start vertex.
                    continue;
                }

                int toEndCost = priCache[start] + weight;
                altCache[end] = Math.min(altCache[end], toEndCost);
            }

            // Swap caches.
            int[] tempCache = altCache;
            altCache = priCache;
            priCache = tempCache;

            ++stops;
        }

        return priCache[dest] == Integer.MAX_VALUE ? -1 : priCache[dest];
    }
}
