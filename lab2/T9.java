import java.io.*;
import java.util.*;

public class T9 {
    public static void main(String args[]) throws IOException {
        BufferedReader sc =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(sc.readLine());

        int[] mapping = new int[26];
        mapping[0] = 2;
        mapping[1] = 22;
        mapping[2] = 222;
        mapping[3] = 3;
        mapping[4] = 33;
        mapping[5] = 333;
        mapping[6] = 4;
        mapping[7] = 44;
        mapping[8] = 444;
        mapping[9] = 5;
        mapping[10] = 55;
        mapping[11] = 555;
        mapping[12] = 6;
        mapping[13] = 66;
        mapping[14] = 666;
        mapping[15] = 7;
        mapping[16] = 77;
        mapping[17] = 777;
        mapping[18] = 7777;
        mapping[19] = 8;
        mapping[20] = 88;
        mapping[21] = 888;
        mapping[22] = 9;
        mapping[23] = 99;
        mapping[24] = 999;
        mapping[25] = 9999;

        for (int i = 1; i <= n; i++) {
            String text = sc.readLine();
            pw.print("Case #" + i + ": ");
            for (int j = 0; j < text.length(); j++) {
                if (text.charAt(j) == ' ') {
                    if (j > 0 && text.charAt(j - 1) == ' ') {
                        pw.print(" ");
                    }
                    pw.print("0");
                } else if (j == 0 || text.charAt(j - 1) == ' ' ||
                           mapping[text.charAt(j) % 'a'] % 10 !=
                               mapping[text.charAt(j - 1) % 'a'] % 10) {
                    pw.print(mapping[text.charAt(j) % 'a']);
                } else {
                    pw.print(" " + mapping[text.charAt(j) % 'a']);
                }
            }
            pw.println();
        }

        pw.close();
        sc.close();
    }
}
