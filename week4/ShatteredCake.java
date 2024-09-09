import java.io.*;
import java.util.*;

public class ShatteredCake {
    public static void main(String arg[]) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));
        int W = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int A = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            A += w * l;
        }

        System.out.println(A / W);
    }
}
