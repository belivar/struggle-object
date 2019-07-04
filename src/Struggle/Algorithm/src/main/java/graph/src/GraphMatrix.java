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
     * 图顶点个数
     */
    int size;

    /**
     * 图顶点个数
     */
    char[] vertex;

    /**
     * 图关系矩阵
     */
    int[][] matrix;

    GraphMatrix(char[][] )
}
