import java.util.Scanner;

public class TwoStones {
    public static void main(String[] args) {
        Scanner total = new Scanner(System.in);
        int totalStones = total.nextInt();

        if (totalStones % 2 == 1) {
            System.out.println("Alice");
        } else {
            System.out.println("Bob");
        }
    }
}
