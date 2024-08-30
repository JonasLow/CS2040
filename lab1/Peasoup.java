import java.util.*;

public class Peasoup {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int numOfR = sc.nextInt();
        String restaurant = null;

        for (int i = 0; i < numOfR; i++) {
            boolean hasPeasoup = false;
            boolean hasPancakes = false;
            int numOfI = sc.nextInt();
            sc.nextLine();
            String rName = sc.nextLine();
            
            for (int j = 0; j < numOfI; j++) {
                String menuItem = sc.nextLine();

                if (menuItem.equals("pea soup")) {
                    hasPeasoup = true;
                } else if (menuItem.equals("pancakes")) {
                    hasPancakes = true;
                }
            }

            if (hasPeasoup && hasPancakes && restaurant == null) {
                restaurant = rName;
            }
        }

        if (restaurant != null) {
            System.out.println(restaurant);
        } else {
            System.out.println("Anywhere is fine I guess");
        }
    }
}
