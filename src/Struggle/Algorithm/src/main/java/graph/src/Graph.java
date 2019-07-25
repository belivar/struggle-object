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
    @Override
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

    public void miniSpanTreeKruskal(GraphMatrix graphMatrix) {

    }

    /**
     * 1 shortestPathTable 计算结点到哪最短路径的结果 多短
     * 2 pathMatrix 前驱结点 前一个是啥才能这么短
     *
     * @param graphMatrix 图
     * @param vertex
     */
    public void shortestPathDijkstra(GraphMatrix graphMatrix, int vertex) {
        int size = graphMatrix.size;
        // 最短路径
        int[] shortestPathTable = new int[size];
        int[] pathMatrix = new int[size];
// 表征 计算完 v0 到 某顶点的最短路径
        boolean[] result = new boolean[size];

        // 初始化
        for (int i = 0; i < graphMatrix.size; i++) {
            // 全都没开始
            result[i] = false;
            // 当前最短的就是第一个顶点连的所有点的权值 没有连的算 MAX_VALUE
            shortestPathTable[i] = graphMatrix.matrix[vertex][i] > 0 ? graphMatrix.matrix[vertex][i] : Integer.MAX_VALUE;
            // 前驱节点初始化
            pathMatrix[i] = 0;
        }
        // 当前结点不用算
        shortestPathTable[vertex] = 0;
        result[vertex] = true;

        // 计算最短路径
        int min;
        int k = 0;
        for (int v = 1; v < size; v++) {
            min = Integer.MAX_VALUE;
            // 找到当前下没算过的结点里最短的路径
            for (int w = 0; w < size; w++) {
                if (!result[w] && shortestPathTable[w] < min) {
                    k = w;
                    min = shortestPathTable[w];
                }
            }
            result[k] = true;
            System.out.println("第" + v + "个顶点 = " + graphMatrix.vertex[k]);
            // 修正最短路径 最短的更新一下
            for (int w = 0; w < size; w++) {
                if (!result[w] && graphMatrix.matrix[k][w] > 0 && (min + graphMatrix.matrix[k][w]) < shortestPathTable[w]) {
                    shortestPathTable[w] = min + graphMatrix.matrix[k][w];
                    pathMatrix[w] = k;
                }
            }
        }
        for (int i : shortestPathTable) {
            System.out.println(i);
        }
    }

    /**
     * BFS
     *
     * @param graphMatrix 图
     */
    @Override
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
