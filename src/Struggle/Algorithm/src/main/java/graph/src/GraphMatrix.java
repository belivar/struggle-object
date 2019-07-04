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
        StringBuffer stringBuffer = new StringBuffer();
        for (int[] i : matrix) {
            for (int j : i) {
                stringBuffer.append(j).append("-");
            }
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'D', 'G'},
                {'I', 'J'},
                {'J', 'G'},};
        GraphMatrix pG;
        // 自定义"图"(输入矩阵队列)
        //pG = new MatrixUDG();
        // 采用已有的"图"
        pG = new GraphMatrix(vexs, edges, true);
        System.out.println(pG.toString());

    }
}
