package sort;


import java.util.Arrays;

public class SortTest {
    public static void main(String[] args){
        SortTestHelper sortTestHelper = new SortTestHelper();
//        int[] arr = sortTestHelper.generateRandomArray(1000,1,1000);
//        int[] arr = sortTestHelper.generateOrderArray(10,1);
//        int[] arr = sortTestHelper.generateNearlyOrderArray(1000,1,10);
        int[] arr = new int[]{3,4,2,4,5,2,1,6,2,1,7,5,8};
        int[] arr1 = sortTestHelper.copyArray(arr);
        int[] arr2 = sortTestHelper.copyArray(arr);
        int[] arr3 = sortTestHelper.copyArray(arr);
        int[] arr4 = sortTestHelper.copyArray(arr);
        int[] arr5 = sortTestHelper.copyArray(arr);
        int[] arr6 = sortTestHelper.copyArray(arr);
        int[] arr7 = sortTestHelper.copyArray(arr);
        int[] arr8 = sortTestHelper.copyArray(arr);
        int[] arr9 = sortTestHelper.copyArray(arr);
        int[] arr10 = sortTestHelper.copyArray(arr);
        int[] arr11 = sortTestHelper.copyArray(arr);


        Sorter sorter = new Sorter();
//        sortTestHelper.testSort(arr1,sorter.selectSort);
//        sortTestHelper.testSort(arr2,sorter.insertSort);
//        sortTestHelper.testSort(arr3,sorter.insertSortV2);
//        sortTestHelper.testSort(arr4,sorter.bubbleSort);
//        sortTestHelper.testSort(arr5,sorter.shellSort);
//        sorter.mergeSort(arr6);
//        sorter.quickSort(arr7);
//        sorter.quickSort3Ways(arr8);
//        System.out.println(Arrays.toString(arr));
        sorter.heapSort1(arr9);
        sorter.heapSort2(arr10);
        sorter.heapSort(arr11);
    }
}
