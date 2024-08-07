import java.util.*;

public class NumberFun {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int tries = myObj.nextInt();

        for (int i = 0; i < tries; i++) {
            int a = myObj.nextInt();
            int b = myObj.nextInt();
            int c = myObj.nextInt();

            if (c - b == a) {
                System.out.println("Possible");
            } else if (a * b == c) {
                System.out.println("Possible");
            } else if (a - b == c || b - a == c) {
                System.out.println("Possible");
            } else if (c * a == b || c * b == a) {
                System.out.println("Possible");
            } else {
                System.out.println("Impossible");
            }
        }
    }
}
