package sort;

import java.util.Arrays;
import java.util.Random;

public class Sorter {

    private Random rd = new Random();

    interface SorterMethod{

        void operate(int[] arr);
    }

    public static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
/*------------------------------------------O(n^2)级---------------------------------------------------*/
    //选择排序
    SorterMethod selectSort = (int[] arr) -> {
        System.out.println(Arrays.toString(arr));
//        System.out.println("选择排序");
        long startTime=System.nanoTime();   //获取开始时间
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println(Arrays.toString(arr));
        System.out.println("选择排序时间： "+(endTime-startTime)+"ns");

    };

    SorterMethod insertSort = (int[] arr) -> {
        System.out.println(Arrays.toString(arr));
//        System.out.println("插入排序");
        long startTime=System.nanoTime();   //获取开始时间
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if (arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
                else
                    break;
            }
        }
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println(Arrays.toString(arr));
        System.out.println("插入排序时间： "+(endTime-startTime)+"ns");
    };

    SorterMethod insertSortV2 = (int[] arr) -> {
        System.out.println(Arrays.toString(arr));
//        System.out.println("改进插入排序");
        long startTime=System.nanoTime();   //获取开始时间
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j;
            for (j = i-1; j >= 0 && temp < arr[j] ; j--) {
                arr[j+1] = arr[j];
//                if (temp < arr[j-1]){
//                    arr[j] = arr[j-1];
//                    if(j == 1){
//                        arr[0] = temp;
//                    }
//                }
//                else{
//                    arr[j] = temp;
//                    break;
//                }
            }
            arr[j+1] = temp;
        }
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println(Arrays.toString(arr));
        System.out.println("改进插入排序时间： "+(endTime-startTime)+"ns");
    };

    SorterMethod bubbleSort = (int[] arr) -> {
        System.out.println(Arrays.toString(arr));
        long startTime=System.nanoTime();   //获取开始时间
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]){
//                    int temp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = temp;
                    swap(arr,j,j+1);
                }
            }
        }
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println(Arrays.toString(arr));
        System.out.println("冒泡排序时间： "+(endTime-startTime)+"ns");
    };

    SorterMethod shellSort = (int[] arr) -> {
        System.out.println(Arrays.toString(arr));
        long startTime=System.nanoTime();   //获取开始时间
        int n = arr.length;
        for (int groupN = n/2; groupN > 0 ; groupN/=2) {
            for (int i = groupN; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i-groupN; j >= 0 && temp < arr[j] ; j-=groupN) {
                    arr[j+groupN] = arr[j];
                }
                arr[j+groupN] = temp;
            }
        }
        long endTime=System.nanoTime(); //获取结束时间
        System.out.println(Arrays.toString(arr));
        System.out.println("希尔排序时间： "+(endTime-startTime)+"ns");
    };

    /*---------------------------------归并排序-----------------------------------------*/

    public void mergeSort(int[] arr){
        System.out.println(Arrays.toString(arr));
        long startTime=System.nanoTime();
//        __mergeSortUB(arr,0,arr.length-1);
        __mergeSortBU(arr);
        long endTime=System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println("归并排序时间： "+(endTime-startTime)+"ns");
    }

    //自顶向下递归实现
    private void __mergeSortUB(int[] arr,int l,int r){
        if (l>=r) return;
//        if(r-l < 15){
//            __insertionSort(arr,l,r);
//            return;
//        }
        int m = (r+l)/2;
        __mergeSortUB(arr,l,m);
        __mergeSortUB(arr,m+1,r); //递归调用
        if (arr[m] > arr[m+1]){ //只有arr的第m项>第m+1项才需要运行归并操作
            __merge(arr,l,m,r); //归并操作
        }
    }
    //自底向上迭代实现
    private void __mergeSortBU(int[] arr){
        for (int sz = 1; sz < arr.length; sz += sz) {
            for (int i = 0; i + sz < arr.length; i += sz + sz) {
                __merge(arr,i,i+sz-1,Integer.min(i+sz+sz-1,arr.length-1));
            }
        }
    }

    //归并算法
    private void __merge(int[] arr,int l,int m,int r){
        int[] arrCopy = Arrays.copyOfRange(arr,l,r+1);
        int i = l;
        int j = m+1;
        for (int k = l; k <= r; k++) {
            if (i > m){
                arr[k] = arrCopy[j-l];
                j++;
            }
            else if (j > r){
                arr[k] = arrCopy[i-l];
                i++;
            }
            else if (arrCopy[i-l] < arrCopy[j-l]){
                    arr[k] = arrCopy[i-l];
                    i++;
                }
            else{
                arr[k] = arrCopy[j-l];
                j++;
            }
        }
    }

    private void __insertionSort(int[] arr, int l,int r){
        for (int i = l+1; i <= r; i++) {
            int temp = arr[i];
            int j;
            for (j = i-1; j >= l && temp < arr[j] ; j--) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
        }
    }

    /*---------------------------------快速排序-----------------------------------------*/
    public void quickSort(int[] arr){
        System.out.println(Arrays.toString(arr));
        long startTime=System.nanoTime();
        __quickSort(arr,0,arr.length-1);
        long endTime=System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println("快速排序时间： "+(endTime-startTime)+"ns");
    }

    private void __quickSort(int[] arr,int l,int r) {
        if (l>=r)
            return;
        int p = __partitionV2(arr,l,r);
        __quickSort(arr,l,p-1);
        __quickSort(arr,p+1,r);
    }

    private int __partition(int[] arr,int l,int r) {
        int v = this.rd.nextInt(r-l+1)+l;
        int temp;
//        temp = arr[v];
//        arr[v] = arr[l];
//        arr[l] = temp;
        swap(arr,v,l);
        int j = l;
        for (int i = l+1; i <= r; i++) {
            if(arr[i] < arr[l]){
                temp = arr[i];
                arr[i] = arr[j+1];
                arr[j+1] = temp;
                j++;
            }
        }
        temp = arr[l];
        arr[l] = arr[j];
        arr[j] = temp;
        return j;
    }



    private int __partitionV2(int[] arr,int l,int r) {
        int v = this.rd.nextInt(r-l+1)+l;
        swap(arr,v,l);
        int j = r;
        int i = l+1;
        while(true){
            while(i <= r && arr[i] < arr[l]){
                i++;
            }
            while(j >= l+1 && arr[j] > arr[l]){
                j--;
            }
            if(i>j) break;
            swap(arr,i,j);
            i++;
            j--;
        }
        swap(arr,l,j);
        return j;
    }
    public void quickSort3Ways(int[] arr){
        System.out.println(Arrays.toString(arr));
        long startTime=System.nanoTime();
        __quickSort3Ways(arr,0,arr.length-1);
        long endTime=System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println("快速排序时间： "+(endTime-startTime)+"ns");
    }

    private void __quickSort3Ways(int[] arr, int l, int r){
//        if(r-l < 15){
//            __insertionSort(arr,l,r);
//            return;
//        }
        if (l>=r) return;
        int v = this.rd.nextInt(r-l+1)+l;
        swap(arr,v,l);
        int i = l+1;
        int lt = l;
        int gt = r+1;
        while(i<gt){
            if (arr[i] < arr[l]){
                swap(arr,i,lt+1);
                lt++;
                i++;
            }
            else if (arr[i] == arr[l]){
                i++;
            }
            else{
                swap(arr,i,gt-1);
                gt--;
            }
        }
        swap(arr,l,lt);
        __quickSort3Ways(arr,l,lt-1);
        __quickSort3Ways(arr,gt,r);
    }
}




