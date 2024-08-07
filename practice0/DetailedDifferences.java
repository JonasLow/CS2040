import java.util.*;

public class DetailedDifferences {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int tries = myObj.nextInt();
    
        for (int i = 0; i < tries; i++) {
            String str1 = myObj.next();
            String str2 = myObj.next();
            System.out.println(str1);
            System.out.println(str2);
            
            for (int j = 0; j < str1.length(); j++) {
                if (str1.charAt(j) == str2.charAt(j)) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
            System.out.println();
        }
    }
}
