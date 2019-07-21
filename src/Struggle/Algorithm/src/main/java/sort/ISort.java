package sort;

/**
 * @author LDZ
 * @date 2019-07-21 11:47
 */
public interface ISort {

    /**
     * 冒泡排序
     *
     * @return
     */
   void bubbleSort();

    /**
     * 插入排序
     *
     * @return
     */
   void insertSort();

    /**
     * 选择排序
     *
     * @return
     */
   void selectSort();

    /**
     * 归并排序
     *
     * @return
     */
   void mergeSort();

    /**
     * 快速排序
     *
     * @return
     */
   void quickSort();
}
