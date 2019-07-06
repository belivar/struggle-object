package graph.src;

import graph.src.api.IGraph;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LDZ
 * @date 2019-06-20 21:15
 */
@Slf4j
public class Solution {
    /**
     * vertex 边
     */
    private static final char[] VERTEX = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
    private static final char[][] EDGE = new char[][]{
            {'A', 'C'},
            {'A', 'D'},
            {'A', 'F'},
            {'B', 'C'},
            {'C', 'D'},
            {'E', 'G'},
            {'D', 'G'},
            {'I', 'J'},
            {'J', 'G'},};

    public static void main(String[] args) {
        IGraph graph = new Graph();

        GraphMatrix graphMatrix = new GraphMatrix(VERTEX, EDGE, true);
        log.info("BFS");
        graph.breadthFirstSearch(graphMatrix);
        System.out.println();
        log.info("DFS");
        graphMatrix = new GraphMatrix(VERTEX, EDGE, true);
        graph.depthFirstSearch(graphMatrix);
    }
}
