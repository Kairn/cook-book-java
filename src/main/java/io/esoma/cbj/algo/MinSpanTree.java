package io.esoma.cbj.algo;

import java.util.Arrays;
import java.util.Objects;

/**
 * Class for studying the Minimum Spanning Tree (MST) problem. A MST is a subset of the edges of a
 * connected, edge-weighted undirected graph that connects all the vertices together, without any
 * cycles and with the minimum possible total edge weight.
 *
 * @author Eddy Soma
 */
public class MinSpanTree {

  private MinSpanTree() {}

  /**
   * Calculates the total weight of a MST with the given edges in a connected graph using Kruskal's
   * algorithm. The idea behind this algorithm is to repeatedly check the edge with the smallest
   * weight, and if this edge does not create a circle in the tree, then it is added to the MST and
   * removed from the set. Edges that form a circle in the tree will be ignored. A disjoint-set data
   * structure is used for optimal performance.
   *
   * @param n the number of vertices in the graph
   * @param edges a 2-dimensional array containing the from, to, and weight data of all available
   *     edges
   * @return the total weight of all edges in the final MST
   */
  public static int calcKruskal(int n, int[][] edges) {
    // Collect and sort the edges.
    Edge[] edgeArr = new Edge[edges.length];
    for (int i = 0; i < edges.length; ++i) {
      int from = edges[i][0];
      int to = edges[i][1];
      int weight = edges[i][2];
      edgeArr[i] = new Edge(from, to, weight);
    }
    Arrays.sort(edgeArr);

    // Create and initialize the disjoint-set.
    DsNode[] nSet = new DsNode[n];
    for (int i = 0; i < n; ++i) {
      nSet[i] = new DsNode(1, null);
    }

    int total = 0;
    for (Edge e : edgeArr) {
      // Find the topmost parents for both ends of the edge.
      DsNode fNode = nSet[e.from - 1];
      while (fNode.parent != null) {
        fNode = fNode.parent;
      }
      DsNode tNode = nSet[e.to - 1];
      while (tNode.parent != null) {
        tNode = tNode.parent;
      }

      // Check if the edge connects an extra vertex.
      if (fNode != tNode) {
        // The edge will be included in the MST.
        total += e.weight;

        // Create a new parent.
        DsNode parent = new DsNode(fNode.count + tNode.count, null);
        fNode.parent = parent;
        tNode.parent = parent;

        // Terminate the loop if the parent has all the vertices.
        if (parent.count == n) {
          break;
        }

        // Path compression.
        nSet[e.from - 1].parent = parent;
        nSet[e.to - 1].parent = parent;
      }
    }

    return total;
  }
}

/**
 * Helper class for storing data of weighted graph edges.
 *
 * @author Eddy Soma
 */
class Edge implements Comparable<Edge> {

  int from;
  int to;
  int weight;

  Edge(int from, int to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Edge edge = (Edge) o;
    return from == edge.from && to == edge.to && weight == edge.weight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to, weight);
  }

  @Override
  public int compareTo(Edge other) {
    return this.weight - other.weight;
  }
}

/**
 * Helper class for storing data of nodes in a disjoint-set.
 *
 * @author Eddy Soma
 */
class DsNode {

  int count;
  DsNode parent;

  DsNode(int count, DsNode parent) {
    this.count = count;
    this.parent = parent;
  }
}
