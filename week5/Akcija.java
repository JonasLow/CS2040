import java.io.*;
import java.util.*;

public class Akcija {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(br.readLine());
        int[] books = new int[n];

        for (int i = 0; i < n; i++) {
            int price = Integer.parseInt(br.readLine());
            books[i] = price;
        }

        Arrays.sort(books);

        int total = 0;

        for (int i = n; i > 0; i--) {
            if ((n - i) % 3 != 2) {
                total += books[i - 1];
            }
        }

        pw.println(total);
        pw.flush();
    }
}
