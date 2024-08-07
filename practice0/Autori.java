import java.util.*;

public class Autori {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String word = myObj.nextLine();

        System.out.print(word.charAt(0));

        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) == '-') {
                System.out.print(word.charAt(i + 1));
            }
        }
    }
}
