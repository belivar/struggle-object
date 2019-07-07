package graph.src.api;

import graph.src.GraphMatrix;

/**
 * @author LDZ
 * @date 2019-06-20 22:06
 */
public interface IGraph {

    /**
     * DFS
     *
     * @param graphMatrix 图
     */
    void depthFirstSearch(GraphMatrix graphMatrix);

    /**
     * BFS
     *
     * @param graphMatrix 图
     */
    void breadthFirstSearch(GraphMatrix graphMatrix);


    /**
     * 最小生成树 prim (普里姆算法)
     *
     *
     * @param graphMatrix
     */
    void miniSpanTreePrim(GraphMatrix graphMatrix);

}
