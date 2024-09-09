import java.io.*;
import java.util.*;

public class SumKindOfProblem {
    public static void main(String args[]) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));
        int p = Integer.parseInt(br.readLine());

        for (int i = 0; i < p; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int s1 = (N * (N + 1)) / 2;
            int s2 = N * N;
            int s3 = N * (N + 1);

            System.out.println(K + " " + s1 + " " + s2 + " " + s3);
        }
        br.close();
        pw.close();
    }
}
