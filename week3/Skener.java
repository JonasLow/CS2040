import java.util.*;

public class Skener {
    static String duplicate(char ch, int n){
        String finalString = "";
        for (int i = 0; i < n; i++) {
            finalString += ch;
        }
        return finalString;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int zr = sc.nextInt();
        int zc = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < r; i++) {
            String entry = sc.nextLine();
            for (int k = 0; k < zr; k++) {
                for (int j = 0; j < c; j++) {
                    System.out.print(duplicate(entry.charAt(j), zc));
                }
                System.out.println();
            }
        }
    }
}
