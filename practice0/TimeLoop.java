import java.util.Scanner;

public class TimeLoop {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int reps = myObj.nextInt();

        for (int i = 1; i <= reps; i++) {
            System.out.println(i + " Abracadabra");
        }
    }
}
