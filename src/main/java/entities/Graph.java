package entities;

import java.util.ArrayList;

/**
 * Graph class represented a directed, weighted graph.
 * This class is purely mathematical.
 * There should be no references to airports or iatacodes.
 */
public class Graph {
    private final int node_count; // number of vertices in the graph
    private final int[][] matrix; // Adjacency Matrix

    public Graph(int vertices) {
        this.node_count = vertices;
        this.matrix = new int[vertices][vertices];
    }

    /**
     * Add a new directed edge to graph
     *
     * @param src starting point of edge
     * @param dest ending point of edge
     */
    public void addEdge(int src, int dest) {
        this.matrix[src][dest] = 1;
    }

    /**
     * Return all possible paths from <src> to <dest>
     *
     * @param src starting point of path
     * @param dest ending point of path
     * @return Arraylist of all possible paths (each individual path is an array list of visited nodes)
     */
    public ArrayList<ArrayList<Integer>> allPaths(int src, int dest) {
        // Mark all vertices as not visited
        boolean[] visited = new boolean[this.node_count];
        for (int i = 0; i < this.node_count; i++) {
            visited[i] = false;
        }

        // Current path
        ArrayList<Integer> path = new ArrayList<>();
        path.add(src);

        return allPathsHelper(src, dest, visited, path);
    }

    private ArrayList<ArrayList<Integer>> allPathsHelper(int src, int dest, boolean[] visited, ArrayList<Integer> path) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();

        // Base Case
        if (src == dest) {
            output.add(path);
            return output;
        }

        // Inductive Step
        boolean[] copy_visited = visited.clone();
        copy_visited[src] = true;

        for (int target = 0; target < this.node_count; target++) {
            if (this.matrix[src][target] != 0 && !copy_visited[target]) {
                // path from src -> target exists, and target has not been visited

                ArrayList<Integer> copy_path = new ArrayList<>(path);

                copy_path.add(target);

                // <paths_from_target> contains all the possible paths from <target> to <dest>
                ArrayList<ArrayList<Integer>> paths_from_target = allPathsHelper(target, dest, copy_visited, copy_path);
                output.addAll(paths_from_target);
            }
        }
        return output;
    }

    /**
     * Provide number of nodes
     *
     * @return Integer representing the number of nodes
     */
    public int getNodeCount() {
        return this.node_count;
    }

    /**
     * Matrix value at point
     *
     * @param x first index
     * @param y second index
     * @return value matrix at (x, y)
     */
    public int getMatrixElement(int x, int y) {
        return this.matrix[x][y];
    }

    /**
     * Determines if this graph is equal to another
     *
     * @param toCompare the other graph to compare with
     * @return true if both graphs are equal, false otherwise
     */
    public boolean equals(Graph toCompare) {
        if (toCompare == null) {
            return false;
        }
        if (this.node_count != toCompare.getNodeCount()) {
            return false;
        }
        for (int i = 0; i < this.node_count; i++) {
            for (int j = 0; j < this.node_count; j++) {
                if (this.matrix[i][j] != toCompare.getMatrixElement(i, j)) {
                    return false;
                }

            }
        }
        return true;
    }


}


