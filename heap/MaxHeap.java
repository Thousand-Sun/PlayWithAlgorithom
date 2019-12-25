package heap;

import sort.Sorter;
import java.util.Arrays;
import java.util.Random;

public class MaxHeap {
    private int[] data;
    private int count;
    private int capacity;

    public MaxHeap(int capacity){
        data = new int[capacity + 1];//索引0不存放数据
        count = 0;
        this.capacity = capacity;
    }

    public MaxHeap(int[] arr){
        capacity = arr.length;
        data = new int[capacity+1];
        System.arraycopy(arr, 0, data, 1, capacity);
        count = capacity;
        for (int i = count/2; i >= 1 ; i--) {
            shiftDown(i);
        }
//        System.out.println(Arrays.toString(data));
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public int[] getData(){
//        return Arrays.copyOfRange(data,1,count);
        return data;
    }

    //----------添加数据----------
    public void insert(int newDate){
        assert count+1 <= capacity;//考虑越界问题，最佳解决方案是在堆满了的时候开辟新的空间
        data[count+1] = newDate;
        count++;
        shiftUp(count);
//        System.out.println(count);
    }
    private void shiftUp(int index){
        while (index > 1 && data[index] > data[index/2]){//索引0不存放数据
            Sorter.swap(data,index,index/2);
            index/=2;
        }
    }


    public int pushOut(){
        assert count > 0;
        int outNumber = data[1];
        Sorter.swap(data,1,count);
//        data[count] = 0;
        count--;
        shiftDown(1);
        return outNumber;
    }
    private void shiftDown(int index) {
        while (index*2 <= count){
            int j = index*2;
            if (j + 1 <= count && data[j+1] > data[j]){
                j++;
            }
            if(data[index] > data[j])break;
            Sorter.swap(data,index,j);
            index = j;
        }
    }

    public static void main(String[] args){
        MaxHeap maxHeap = new MaxHeap(100);
        Random rd = new Random();
        for (int i = 0; i < 10; i++) {
            int newData = rd.nextInt(100);
            maxHeap.insert(newData);
        }
        System.out.println(Arrays.toString(maxHeap.getData()));
        for (int i = 0; i < 10; i++) {
            System.out.print(maxHeap.pushOut() + " ");
        }
        int[] arr = new int[]{8,4,7,5,6,3,25,1};
        MaxHeap temp = new MaxHeap(arr);
    }
}

