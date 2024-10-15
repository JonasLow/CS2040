import java.io.*;
import java.util.*;

public class DistBallotBox {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);

        while (true) {
            int n = io.getInt();
            int b = io.getInt();
            
            if (n == -1 && b == -1) {
                break;
            }

            int[] populations = new int[n];
            for (int i = 0; i < n; i++) {
                populations[i] = io.getInt();
            }
            Arrays.sort(populations);

            if (n == b) {
                io.println(populations[n - 1]);
            }

            if (n > b) {
                io.println(-1);
            }

            int small = 1;
            int large = populations[n - 1];
            while (small < large) {
                int mid = (small + large) / 2;
                int votes = 0;
                for (int population : populations) {
                    votes += (population + mid - 1) / mid;
                }
                if (votes <= b) {
                    large = mid;
                } else {
                    small = mid + 1;
                }
            }

            io.println(small);
        }

        io.close();
    }
}
