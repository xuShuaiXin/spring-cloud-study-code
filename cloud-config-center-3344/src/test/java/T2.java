public class T2 {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        int c = 1;
        int d = 1;
        int e = 2;

        System.out.println((a==b)?(a==c)?(a==d)?(a==e)?(a=10):(a=9):(a=8):(a=7):0);
        System.out.println("a == " + a);
        d = 1;
        System.out.println((a==b)?(a==c)?(a==d)?(a==e)?(a=10):(a=9):(a=8):(a=7):0);
    }
}
