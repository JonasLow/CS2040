import java.util.*;

public class Pot {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int tries = myObj.nextInt();
        int totalSum = 0;

        for (int i = 0; i < tries; i++) {
            int numEntry = myObj.nextInt();
            totalSum += Math.pow(numEntry / 10, numEntry % 10);
        }

        System.out.println(totalSum);
    }
}
