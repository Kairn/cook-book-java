package io.esoma.cbj.algo;

/**
 * Class for studying the Minimum Spanning Tree (MST) problem. A MST is a subset
 * of the edges of a connected, edge-weighted undirected graph that connects all
 * the vertices together, without any cycles and with the minimum possible total
 * edge weight.
 * 
 * @author Eddy Soma
 *
 */
public class MinSpanTree {

	/**
	 * Calculates the total weight of a MST with the given edges in a connected
	 * graph using Kruskal's algorithm. The idea behind this algorithm is to
	 * repeatedly check the edge with the smallest weight, and if this edge does not
	 * create a circle in the tree, then it is added to the MST and removed from the
	 * set. Edges that form a circle in the tree will be ignored. A disjoint-set
	 * data structure is used for optimal performance.
	 * 
	 * @param n     the number of vertices in the graph
	 * @param edges a 2-dimensional array containing the from, to, and weight data
	 *              of all available edges
	 * @return the total weight of all edges in the final MST
	 */
	public static int calcKruskal(int n, int[][] edges) {
		return 0;
	}

}
