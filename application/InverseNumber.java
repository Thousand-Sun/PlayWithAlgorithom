package application;

import java.util.Arrays;

public class InverseNumber {

    private int count = 0;

    public int numberOfInverse(int[] arr){
       __numberOfInverse(arr,0,arr.length-1);
        return 0;
    }

    private void __numberOfInverse(int[] arr,int l,int r) {
        if (l>=r) return;
        int m = (l+r)/2;
        __numberOfInverse(arr,l,m);
        __numberOfInverse(arr,m+1,r);
        if (arr[m] > arr[m+1]){ //只有arr的第m项>第m+1项才需要运行归并操作
           __merge(arr,l,m,r); //归并操作
        }
    }

    private void __merge(int[] arr, int l, int m, int r) {
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
            else if (arrCopy[i-l] <= arrCopy[j-l]){
                arr[k] = arrCopy[i-l];
                i++;
            }
            else{
                arr[k] = arrCopy[j-l];
                count += m-i+1;
                j++;
            }
        }
    }

    public int getCount(){
        return count;
    }
}
