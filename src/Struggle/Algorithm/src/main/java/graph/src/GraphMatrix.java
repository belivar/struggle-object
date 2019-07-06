package graph.src;

import lombok.Data;

import java.util.Arrays;

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
    public char[] vertex;

    /**
     * 访问标志
     */
    public boolean[] visited;
    /**
     * 图关系矩阵
     */
    int[][] matrix;

    boolean direction;

    GraphMatrix(char[] vertex, char[][] edges, boolean direction) {
        size = vertex.length;
        matrix = new int[size][size];
        visited = new boolean[size];
        this.vertex = vertex;

        for (char[] c : edges) {
            int p0 = getPosition(c[0]);
            int p1 = getPosition(c[1]);

            matrix[p0][p1] = 1;
            if (!direction) {
                matrix[p1][p0] = 1;
            }
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertex.length; i++) {
            if (ch == vertex[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] i : matrix) {
            for (int j : i) {
                stringBuilder.append(j).append("-");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
