package practice;

public class Recursion {
    static String rescare(int times){
        return "吓得我赶紧抱起了" + recursion(times);
    }
    private static String recursion(int times){
        if(times <= 0){
            return "我的小鲤鱼";
        }
        else{
            return "抱着" + recursion(--times) + "的我";
        }
    }

    public static void main(String[] args){
        System.out.println(Recursion.rescare(2));
    }
}
