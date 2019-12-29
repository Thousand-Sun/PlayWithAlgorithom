package practice;

public class MainTest {
    public static void main(String[] args ){
//        InverseNumber inverseNumber = new InverseNumber();
        int[] arr = new int[]{6,5,3,6,4,2,3,7};
//        inverseNumber.numberOfInverse(arr);
//        System.out.println(inverseNumber.getCount());
        Nth nth = new Nth();
        int k = nth.solve(arr,8);
        System.out.println(k);
    }
}
