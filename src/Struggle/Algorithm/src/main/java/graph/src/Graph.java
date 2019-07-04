package graph.src;

import graph.src.api.IGraph;

/**
 * @author LDZ
 * @date 2019-07-04 20:42
 */
public class Graph implements IGraph {

    private static final char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
    private static final char[][] edges = new char[][]{
            {'A', 'C'},
            {'A', 'D'},
            {'A', 'F'},
            {'B', 'C'},
            {'C', 'D'},
            {'E', 'G'},
            {'D', 'G'},
            {'I', 'J'},
            {'J', 'G'},};

    public static final GraphMatrix graphMatrix = new GraphMatrix(vexs, edges, true);

    public void depthFirstSearch(GraphMatrix graphMatrix) {
        int i;
//        for (i = 0; i < graphMatrix.size; i++) {
//            graphMatrix.visited[i] = false;
//        }
        for (i = 0; i < graphMatrix.size; i++) {
            System.out.println(graphMatrix.visited[i]);
            System.out.println(graphMatrix.vertex[i]);
        }
    }

    public static void main(String[] args) {
        IGraph graph = new Graph();
        graph.depthFirstSearch(graphMatrix);
    }
}
