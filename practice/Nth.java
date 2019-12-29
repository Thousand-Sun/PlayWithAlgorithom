package practice;

import java.util.Random;
import sort.Sorter;


public class Nth {

    public int solve(int[] arr, int n){
        n--;
        return __solve(arr,0,arr.length-1,n);
    }

    private int __solve(int[] arr,int l,int r,int n) {
        if (l==r)
            return arr[l];
        int p = __partition(arr,l,r);
        if (n < p)
            return __solve(arr,l,p-1,n);
        else if (n > p)
            return __solve(arr,p+1,r,n);
        else
            return arr[p];
    }
    private int __partition(int[] arr,int l,int r) {
        Random rd = new Random();
        int v = rd.nextInt(r-l+1)+l;
        Sorter.swap(arr,v,l);
        int j = l;
        for (int i = l+1; i <= r; i++) {
            if(arr[i] < arr[l]){
                Sorter.swap(arr,i,j+1);
                j++;
            }
        }
        Sorter.swap(arr,j,l);
        return j;
    }
}
