package tree;

public class BinarySearch {
    public static int binarySearch(int[] arr,int target){
        int l = 0 , r = arr.length-1;
        while(l <= r){
//            int mid = (l+r)/2;//当r在数据类型边界时计算l+r会溢出
            int mid = l + (r-l)/2;
            if(target == arr[mid])
                return mid;
            if (target < mid)
                r = mid - 1;
            else
                l = mid + 1;
        }
        return -1;
    }

    public static int binarySearchV2(int[] arr, int target, int l,int r){
        while (l <= r){
            int mid = l + (r-l)/2;
            if(target == arr[mid])
                return mid;
            if (target < mid)
                binarySearchV2(arr,target,l,mid-1);
            else
                binarySearchV2(arr,target,mid+1,r);
        }
        return -1;
    }
    public static int binarySearchV2(int[] arr, int target){
        return binarySearchV2(arr,target,0,arr.length-1);
    }

    public static void main(String[] args){
        int[] arr = new int[]{1,2,4,5,6};
        int t1 = BinarySearch.binarySearch(arr,4);
        System.out.println(t1);
        int t2 = BinarySearch.binarySearchV2(arr,4);
        System.out.println(t2);
    }
}
