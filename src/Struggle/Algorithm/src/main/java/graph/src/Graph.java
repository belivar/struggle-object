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

    /**
     * DFS
     *
     * @param graphMatrix 图
     */
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

    /**
     * prim 算法
     * 1 初始化 low cost 将纳入顶点的边距离初始化 不存在的置为 Integer.MAX_VALUE
     * 2 循环
     * 3 找到 已纳入顶点中边权值最小的
     * 4 在 lost cost 标记完成的顶点
     * 5 将新的顶点纳入 更新 lost cost
     *
     * @param graphMatrix 图
     */
    public void miniSpanTreePrim(GraphMatrix graphMatrix) {
        int size = graphMatrix.size;
        // 保存相关顶点权值
        int[] lowcost = new int[size];
        // 初始化第一个权值
        lowcost[0] = 0;
        // 初始化第一个订单坐标 0
        for (int i = 1; i < graphMatrix.size; i++) {
            lowcost[i] = (graphMatrix.matrix[0][i]) > 0 ? graphMatrix.matrix[0][i] : Integer.MAX_VALUE;
        }
        for (int i = 1; i < graphMatrix.size; i++) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            int k = 0;
            while (j < graphMatrix.size) {
                if (0 != lowcost[j] && lowcost[j] < min) {
                    min = lowcost[j];
                    k = j;
                }
                j++;
            }
            System.out.println("当前最小边" + min + "完成顶点" + graphMatrix.vertex[k]);
            // 将当前顶点权值设置为 0 完成任务
            lowcost[k] = 0;

            for (j = 1; j < graphMatrix.size; j++) {
                if (0 != lowcost[j] && 0 != graphMatrix.matrix[k][j] && graphMatrix.matrix[k][j] < lowcost[j]) {
                    lowcost[j] = graphMatrix.matrix[k][j];
                }
            }
        }
    }

    /**
     * BFS
     *
     * @param graphMatrix 图
     */
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
}
