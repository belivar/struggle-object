package graph.src;


import graph.src.api.IGraph;
import org.junit.Test;

public class GraphMatrixTest {
    /**
     * vertex 边
     */ //                                 0    1    2    3    4    5    6    7    8
    private static final char[] VERTEX = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
    private static final char[][] EDGE = new char[][]{
            {'A', 'B', '0' + 10},
            {'A', 'F', '0' + 11},

            {'B', 'C', '0' + 18},
            {'B', 'G', '0' + 16},
            {'B', 'I', '0' + 12},

            {'C', 'D', '0' + 22},
            {'C', 'I', '0' + 8},

            {'D', 'E', '0' + 20},
            {'D', 'F', '0' + 16},
            {'D', 'I', '0' + 21},

            {'E', 'F', '0' + 26},
            {'E', 'H', '0' + 7},

            {'F', 'G', '0' + 17},

            {'G', 'H', '0' + 19},

    };


    @Test
    public void createGraphMatrix() {
        IGraph graph = new Graph();

        GraphMatrix graphMatrix = new GraphMatrix(VERTEX, EDGE, false);
        graph.miniSpanTreePrim(graphMatrix);
        System.out.println(graphMatrix.toString());
    }


}
