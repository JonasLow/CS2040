import java.util.HashSet;

public class BoatParts {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int p = io.getInt();
        int n = io.getInt();
        HashSet<String> bought = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String item = io.getWord();
            if (!bought.contains(item)) {
                bought.add(item);
                p--;
            }
            if (p == 0) {
                io.println(i + 1);
                io.flush();
                break;
            }
        }

        if (p > 0) {
            io.println("paradox avoided");
        }
        io.close();
    }
}
