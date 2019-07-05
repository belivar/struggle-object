package graph.src;

import graph.src.api.IGraph;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author LDZ
 * @date 2019-07-04 20:42
 */
@Slf4j
public class Graph implements IGraph {

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


    public void depthFirstSearch(GraphMatrix graphMatrix) {
        int i;
        for (i = 0; i < graphMatrix.size; i++) {
            if (!graphMatrix.visited[i]) {
                dFSTre(graphMatrix, i);
            }
        }
    }

    private static void dFSTre(GraphMatrix graphMatrix, int i) {
        graphMatrix.visited[i] = true;
        System.out.print(graphMatrix.vertex[i] + "-");
        for (int j = 0; j < graphMatrix.size; j++) {
            if (1 == graphMatrix.matrix[i][j] && !graphMatrix.visited[j]) {
                dFSTre(graphMatrix, j);
            }
        }
    }


    public void breadthFirstSearch(GraphMatrix graphMatrix) {

        LinkedList<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < graphMatrix.size; i++) {
            if (!graphMatrix.visited[i]) {
                graphMatrix.visited[i] = true;
                System.out.print(graphMatrix.vertex[i] + "-");
                queue.add(i);
                while (!queue.isEmpty()) {
                    int k = queue.remove();
                    for (int j = 0; j < graphMatrix.size; j++) {
                        if (graphMatrix.matrix[k][j] == 1 && !graphMatrix.visited[j]) {
                            graphMatrix.visited[j] = true;
                            System.out.print(graphMatrix.vertex[j] + "-");
                            queue.add(j);
                        }
                    }
                }
            }
        }
    }

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
