import java.io.*;
import java.util.*;

public class FerryLoading {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        
        int c = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < c; i++) {
            String[] lm = br.readLine().split(" ");
            int l = Integer.parseInt(lm[0]) * 100;
            int m = Integer.parseInt(lm[1]);
            Queue<Integer> left = new LinkedList<>();
            Queue<Integer> right = new LinkedList<>();

            for (int j = 0; j < m; j++) {
                String[] carSide = br.readLine().split(" ");
                int car = Integer.parseInt(carSide[0]);
                String side = carSide[1];
                if (side.equals("left")) {
                    left.add(car);
                } else {
                    right.add(car);
                }
            }

            int side = 1;
            int times = 0;
            while(!left.isEmpty() || !right.isEmpty()) {
                int len = l;
                if (side == 1) {
                    while (!left.isEmpty()) {
                        if (len - left.peek() >= 0) {
                            len -= left.poll();
                        } else {
                            break;
                        }
                    }
                    side = 0;
                } else if (side == 0) {
                    while (!right.isEmpty()) {
                        if (len - right.peek() >= 0) {
                            len -= right.poll();
                        } else {
                            break;
                        }
                    }
                    side = 1;
                }
                times++;
            }
            pw.println(times);
        }
        pw.flush();
    }
}
