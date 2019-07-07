package graph.src;


import graph.src.api.IGraph;
import org.junit.Test;

public class GraphMatrixTest {
    /**
     * vertex 边
     */ //                                 0    1    2    3    4    5    6    7    8
    private static final char[] VERTEX = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
    private static final char[][] EDGE = new char[][]{
            {'A', 'B', '0' + 1},
            {'A', 'C', '0' + 5},

            {'B', 'C', '0' + 3},
            {'B', 'D', '0' + 7},
            {'B', 'E', '0' + 5},

            {'C', 'E', '0' + 1},
            {'C', 'F', '0' + 7},

            {'D', 'E', '0' + 2},
            {'D', 'G', '0' + 3},

            {'E', 'F', '0' + 3},
            {'E', 'G', '0' + 6},
            {'E', 'H', '0' + 9},

            {'F', 'H', '0' + 5},

            {'G', 'H', '0' + 2},
            {'G', 'I', '0' + 7},

            {'H', 'I', '0' + 4},

    };


    @Test
    public void createGraphMatrix() {
        IGraph graph = new Graph();

        GraphMatrix graphMatrix = new GraphMatrix(VERTEX, EDGE, false);
        graph.shortestPathDijkstra(graphMatrix, 0);
        System.out.println(graphMatrix.toString());
    }


}
