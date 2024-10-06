import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class GrandpaBernie {
    public static void main(String[] args) throws IOException{
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        HashMap<String, ArrayList<Integer>> trips = new HashMap<>();
        HashSet<String> visited = new HashSet<String>();

        for (int i = 0; i < n; i++) {
            String place = io.getWord();
            int year = io.getInt();
            if (!trips.containsKey(place)) {
                trips.put(place, new ArrayList<Integer>());
            }
            trips.get(place).add(year);
        }

        int m = io.getInt();

        for (int i = 0; i < m; i++) {
            String place = io.getWord();
            int order = io.getInt();

            if (!visited.contains(place)){
                Collections.sort(trips.get(place));
                visited.add(place);
            }
            int year = trips.get(place).get(order - 1);

            io.println(year);
        }
        io.close();
    }
}
