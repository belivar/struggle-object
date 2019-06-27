package graph.src;

import lombok.Data;

/**
 * 图
 *
 * @author LDZ
 * @date 2019-06-20 21:52
 */
@Data
public class GraphMatrix {

    private static final int MAX_NUM = 20;

    private static final int MAX_VALUE = 0x8000 - 1;
    /**
     * 类型
     * 0 无向图
     * 1 有向图
     */
    int gType;

    /**
     * 顶点数量
     */
    int vertexNum;

    /**
     * 边的数量
     */
    int edgeNum;

    /**
     * 顶点信息
     */
    char[] vertex = new char[MAX_NUM];

    /**
     * 保存权
     */
    int[][] edgeWeight = new int[MAX_VALUE][MAX_VALUE];

}
