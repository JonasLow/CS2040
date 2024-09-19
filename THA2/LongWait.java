import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class LongWait {
    public static void main(String[] args) throws IOException {
        int K;
        int Q;

        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));

        String[] firstLine = br.readLine().split(" ");
        Q = Integer.parseInt(firstLine[0]);
        K = Integer.parseInt(firstLine[1]);

        HashMap<Integer, Integer> memberQueue = new HashMap<>(Q);
        HashMap<Integer, Integer> normalQueue = new HashMap<>(Q);
                int memberFirst = 0;
        int memberLast = 0;
        int normalFirst = 0;
        int normalLast = 0;

        for (int i = 0; i < Q; i++) {
            String[] input = br.readLine().split(" ");
            String operation = input[0];
            int id;

            switch (operation) {
                case "queue":
                    id = Integer.parseInt(input[1]);
                    if (memberLast - memberFirst < K) {
                        memberQueue.put(memberLast, id);
                        memberLast++;
                    } else {
                        normalQueue.put(normalLast, id);
                        normalLast++;
                    }
                    break;

                case "vip":
                    id = Integer.parseInt(input[1]);
                    memberFirst--;
                    memberQueue.put(memberFirst, id);
                    if (memberLast - memberFirst > K) {
                        normalFirst--;
                        normalQueue.put(normalFirst,
                                        memberQueue.remove(memberLast - 1));
                        memberLast--;
                    }
                    break;

                case "member":
                    id = Integer.parseInt(input[1]);
                    memberQueue.put(memberLast, id);
                    memberLast++;
                    if (memberLast - memberFirst > K) {
                        normalFirst--;
                        normalQueue.put(normalFirst,
                                        memberQueue.remove(memberLast - 1));
                        memberLast--;
                    }
                    break;

                case "slower":
                    K++;
                    if (memberLast - memberFirst < K &&
                        normalLast - normalFirst > 0) {
                        memberQueue.put(memberLast,
                                        normalQueue.remove(normalFirst));
                        normalFirst++;
                        memberLast++;
                    }
                    break;

                case "faster":
                    K--;
                    if (memberLast - memberFirst > K &&
                        memberLast - memberFirst > 0) {
                        normalFirst--;
                        normalQueue.put(normalFirst,
                                        memberQueue.remove(memberLast - 1));
                        memberLast--;
                    }
                    break;

                case "findID":
                    int pos = Integer.parseInt(input[1]) - 1;
                    if (pos < memberLast - memberFirst) {
                        pw.println(memberQueue.get(pos + memberFirst));
                    } else {
                        pos -= (memberLast - memberFirst);
                        pw.println(normalQueue.get(pos + normalFirst));
                    }
                    break;
            }
        }
        pw.flush();
    }
}
