import java.io.*;
import java.util.*;

public class ExactlyElectrical {
    public static void main(String args[]) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(br.readLine());

        if (t > Math.abs(a - c) + Math.abs(b - d) &&
            (t - Math.abs(a - c) - Math.abs(b - d)) % 2 == 0) {
            System.out.println("Y");
        } else {
            System.out.println("N");
        }
    }
}
