package sort;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

public class SortTestHelper {

    //生成[RangeL,RangeR]间随机数组
    public int[] generateRandomArray(int n, int RangeL, int RangeR){
        int[] arr = new int[n];
        Random rd = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rd.nextInt(RangeR - RangeL + 1) + RangeL;
        }
        return arr;
    }
    //生成[RangeL,RangeL+n)间有序数组
    public int[] generateOrderArray(int n, int RangeL){
        int[] arr = new int[n];
        arr[0] = RangeL;
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i-1] + 1;
        }
        return arr;
    }

    //生成[RangeL,RangeL+n)间近乎有序数组
    public int[] generateNearlyOrderArray(int n,int RangeL,int ecn){
        int[] arr = this.generateOrderArray(n,RangeL);
        Random rd = new Random();
        int m,l;
        for (int i = 1; i <= ecn; i++) {
            do {
                m = rd.nextInt(n);
                l = rd.nextInt(n);
            }while (m == l);
            int t = arr[m];
            arr[m] = arr[l];
            arr[l] = t;
        }
        return arr;
    }

    public void testSort(int[] arr, Sorter.SorterMethod sorterMethod){
//        long startTime=System.nanoTime();   //获取开始时间
        sorterMethod.operate(arr);
//        long endTime=System.nanoTime(); //获取结束时间
//        System.out.println("排序时间： "+(endTime-startTime)+"ns");
    }

    public static void main(String[] args){
        SortTestHelper Helper = new SortTestHelper();
        int[] arr = Helper.generateRandomArray(10,1,10);
        System.out.println(Arrays.toString(arr));
    }

    public int[] copyArray(int[] arr){
        return Arrays.copyOf(arr,arr.length);
    }
}
