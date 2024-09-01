import java.util.*;

public class FizzBuzz {
    static boolean isDivisible(int a, int b) {
    	return a % b == 0;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            if (isDivisible(i, x) && isDivisible(i, y)) {
                System.out.println("FizzBuzz");
            } else if (isDivisible(i, x)) {
                System.out.println("Fizz");
            } else if (isDivisible(i, y)) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}
