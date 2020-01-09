package heap;

import sort.Sorter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class IndexMinHeap<Item extends Comparable> {
    private Item[] data;
    private int[] index;
    private int[] reverse;
    private int count;
    private int capacity;

    public IndexMinHeap(int capacity){
        data = (Item[]) new Comparable[capacity+1];
        index = new int[capacity+1];
        reverse = new int[capacity+1];
        count = 0;
        this.capacity = capacity;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void getHeap() {
        System.out.println(Arrays.toString(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        System.out.println(Arrays.toString(index));
        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(reverse) + '\n');
    }

    public boolean contain(int i){
        assert i+1 >= 1 && i+1 <= capacity;
        return reverse[i+1] != 0;
    }

    public Item getData(int i) {
        assert (contain(i));
        return data[i+1];
    }

    public void insert(int i, Item newDate){
        assert count+1 <= capacity;//考虑越界问题，最佳解决方案是在堆满了的时候开辟新的空间
        assert i+1 >= 1 && i+1 <= capacity;
        i+=1;
        data[i] = newDate;
        index[count+1] = i;
        reverse[i] = count+1;
        count++;
        shiftUp(count);
//        System.out.println(count);
    }
    private void shiftUp(int k){
        while (k > 1 && data[index[k]].compareTo(data[index[k/2]]) < 0){//索引0不存放数据
            Sorter.swap(index,k,k/2);
            reverse[index[k/2]] = k/2;
            reverse[index[k]] = k;
            k/=2;
        }
    }


    public Item pushOutData(){
        assert count > 0;
        Item outNumber = data[index[1]];
        Sorter.swap(index,1,count);
        reverse[index[1]] = 1;
        reverse[index[count]] = 0;
//        data[count] = 0;
        count--;
        shiftDown(1);
        return outNumber;
    }
    public int pushOutIndex(){
        assert count > 0;
        int outIndex = index[1] - 1;
        Sorter.swap(index,1,count);
        reverse[index[1]] = 1;
        reverse[index[count]] = 0;
//        data[count] = 0;
        count--;
        shiftDown(1);
        return outIndex;
    }
    private void shiftDown(int k) {
        while (k*2 <= count){
            int j = k*2;
            if (j + 1 <= count && data[index[j+1]].compareTo(data[index[j]]) < 0){
                j++;
            }
            if(data[index[k]].compareTo(data[index[j]]) < 0)break;
            Sorter.swap(index,k,j);
            reverse[index[k]] = k;
            reverse[index[j]] = j;
            k = j;
        }
    }

    public void change(int i,Item newData){
        assert (contain(i));
        //这个方法的复杂度是O(n)级！
        i += 1;
        data[i] = newData;
//        for (int j = 0; j <= count; j++) {
//            if (index[j] == i){
//                shiftDown(j);
//                shiftUp(j);
//                return;
//            }
//        }
        int j = reverse[i];
        shiftDown(j);
        shiftUp(j);
    }

    public static void main(String[] args){
        IndexMinHeap indexMaxHeap = new IndexMinHeap(10);
        Random rd = new Random();
        int[] arr = new int[]{15,17,19,13,22,16,28,26,41,62};
        for (int i = 0; i < 10; i++) {
            indexMaxHeap.insert(i,arr[i]);
        }
        indexMaxHeap.getHeap();
//        System.out.println(indexMaxHeap.getData(1));
        indexMaxHeap.pushOutData();
        indexMaxHeap.getHeap();
    }
}
