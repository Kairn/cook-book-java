package io.esoma.cbj.algo;

/**
 * Class for illustrating Dijkstra's algorithm. This is an algorithm for finding
 * the shortest paths between nodes in a graph, and it was conceived by computer
 * scientist Edsger W. Dijkstra in 1956. Dijkstra's original algorithm found the
 * shortest path between two given nodes, but a more common variant fixes a
 * single node as the source node and finds shortest paths from the source to
 * all other nodes in the graph, producing a shortest-path tree.
 * 
 * 
 * @author Eddy Soma
 *
 */
public class Dijkstra {

	/**
	 * Traverses through the graph and finds the shortest distances between the
	 * first node (index 0) and all other nodes in the graph. There might be nodes
	 * disconnected from the rest of the graph. If a node cannot be reached from the
	 * starting node, -1 will be returned.
	 * 
	 * @param graph a 2-dimensional array (Each row represents the ith (0th being
	 *              the first row) node of the graph, and it contains the distance
	 *              values from itself to the ith node. If there is no direct path
	 *              between two nodes, -1 is stored.)
	 * @return an array that contains the shortest distances between the 0th node
	 *         and all other nodes
	 */
	public static int[] djFind(int[][] graph) {
		int n = graph.length;
		// Create the result set
		int[] results = new int[n];

		return results;
	}

}
