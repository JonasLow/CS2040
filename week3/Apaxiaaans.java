import java.util.*;

public class Apaxiaaans {
    public static String nameCompactor(String name) {
        String compactName = "";
        for (int i = 0; i < name.length(); i++) {
            if (i == 0 || (i > 0 && name.charAt(i - 1) != name.charAt(i))) {
                compactName += name.charAt(i);
            }
        }

        return compactName;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println(nameCompactor(name));
    }
}
