package heap;

import sort.Sorter;
import java.util.Arrays;

public class MinHeap<Item extends Comparable> {
    private Item[] data;
    private int count;
    private int capacity;

    public MinHeap(int capacity){
        data = (Item[]) new Comparable[capacity + 1];//索引0不存放数据
        count = 0;
        this.capacity = capacity;
    }

    public MinHeap(Item[] arr){
        capacity = arr.length;
        data = (Item[]) new Comparable[capacity + 1];
        System.arraycopy(arr, 0, data, 1, capacity);
        count = capacity;
        for (int i = count/2; i >= 1 ; i--) {
            shiftDown(i);
        }
//        System.out.println(Arrays.toString(data));
    }

    private void swap(Item[] arr,int a,int b){
        Item temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public Item[] getData(){
//        return Arrays.copyOfRange(data,1,count);
        return data;
    }

    //----------添加数据----------
    public void insert(Item newDate){
        assert count+1 <= capacity;//考虑越界问题，最佳解决方案是在堆满了的时候开辟新的空间
        data[count+1] = newDate;
        count++;
        shiftUp(count);
//        System.out.println(count);
    }
    private void shiftUp(int index){
        while (index > 1 && data[index].compareTo(data[index/2]) < 0){//索引0不存放数据
            swap(data,index,index/2);
            index/=2;
        }
    }

    public Item pushOut(){
        assert count > 0;
        Item outNumber = data[1];
        swap(data,1,count);
//        data[count] = null;
        count--;
        shiftDown(1);
        return outNumber;
    }
    private void shiftDown(int index) {
        while (index*2 <= count){
            int j = index*2;
            if (j + 1 <= count && data[j+1].compareTo(data[j]) < 0){
                j++;
            }
            if(data[index].compareTo(data[j]) < 0)break;
            swap(data,index,j);
            index = j;
        }
    }

    public static void main(String[] args){
        Integer[] arr = new Integer[]{8,4,7,5,6,3,25,1};
        MinHeap<Integer> minHeap = new MinHeap<Integer>(arr);
        System.out.println(Arrays.toString(minHeap.getData()));
        minHeap.pushOut();
        System.out.println(Arrays.toString(minHeap.getData()));
    }
}

