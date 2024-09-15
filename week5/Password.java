import java.io.*;
import java.util.*;

public class Password {
    public static void main(String args[]) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));

        double tries = 0.0;
        int n = Integer.parseInt(br.readLine());
        double[] pq = new double[n];

        for (int i = 0; i < n; i++) {
            String password = br.readLine();
            String[] part = password.split(" ");

            double p = Double.parseDouble(part[1]);
            pq[i] = p;
        }

        List<Double> pqList = new ArrayList<>();
        for (double p : pq) {
            pqList.add(p);
        }

        Collections.sort(pqList);
        Collections.reverse(pqList);

        for (int j = 1; j <= n; j++) {
            tries += j * pqList.get(j - 1);
        }
        pw.println(tries);
        pw.flush();
    }
}
