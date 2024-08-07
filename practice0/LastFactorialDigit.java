import java.util.*;

public class LastFactorialDigit {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int tries = myObj.nextInt();
        
        for (int i = 0; i < tries; i++) {
            int newNum = myObj.nextInt();
            if (newNum >= 5) {
                System.out.println(0);
            } else if (newNum == 1) {
                System.out.println(1);
            } else if (newNum == 2) {
                System.out.println(2);
            } else if (newNum == 3) {
                System.out.println(6);
            } else if (newNum == 4) {
                System.out.println(4);
            }
        }
    }
}
