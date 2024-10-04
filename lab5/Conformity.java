import java.io.*;
import java.util.*;

public class Conformity {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> H = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] inputs = br.readLine().split(" ");
            Arrays.sort(inputs);
            String combine = String.join(",", inputs);

            H.put(combine, H.getOrDefault(combine, 0) + 1);
        }

        int max = 0;
        for (int f : H.values()) {
            if (f > max) {
                max = f;
            }
        }

        int students = 0;
        for (int f : H.values()) {
            if (f == max) {
                students += f;
            }
        }

        pw.println(students);
        pw.flush();
    }
}
