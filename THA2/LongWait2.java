import java.io.IOException;

public class LongWait2 {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);
        int Q = io.getInt();
        int K = io.getInt();

        int[] memberQueue = new int[2 * Q - 1];
        int[] normalQueue = new int[2 * Q - 1];
        int memberFirst = Q;
        int memberLast = Q;
        int normalFirst = Q;
        int normalLast = Q;

        for (int i = 0; i < Q; i++) {
            String operation = io.getWord();
            int id;

            switch (operation) {
                case "queue":
                    id = io.getInt();
                    if (memberLast - memberFirst < K) {
                        memberQueue[memberLast] = id;
                        memberLast++;
                    } else {
                        normalQueue[normalLast] = id;
                        normalLast++;
                    }
                    break;

                case "vip":
                    id = io.getInt();
                    memberFirst--;
                    memberQueue[memberFirst] = id;
                    if (memberLast - memberFirst > K) {
                        normalFirst--;
                        normalQueue[normalFirst] = memberQueue[memberLast - 1];
                        memberLast--;
                    }
                    break;

                case "member":
                    id = io.getInt();
                    memberQueue[memberLast] = id;
                    memberLast++;
                    if (memberLast - memberFirst > K) {
                        normalFirst--;
                        normalQueue[normalFirst] = memberQueue[memberLast - 1];
                        memberLast--;
                    }
                    break;

                case "slower":
                    K++;
                    if (memberLast - memberFirst < K &&
                        normalLast - normalFirst > 0) {
                        memberQueue[memberLast] = normalQueue[normalFirst];
                        normalFirst++;
                        memberLast++;
                    }
                    break;

                case "faster":
                    K--;
                    if (memberLast - memberFirst > K &&
                        memberLast - memberFirst > 0) {
                        normalFirst--;
                        normalQueue[normalFirst] = memberQueue[memberLast - 1];
                        memberLast--;
                    }
                    break;

                case "findID":
                    int pos = io.getInt() - 1;
                    if (pos < memberLast - memberFirst) {
                        io.println(memberQueue[pos + memberFirst]);
                    } else {
                        pos -= (memberLast - memberFirst);
                        io.println(normalQueue[pos + normalFirst]);
                    }
                    break;
            }
        }
        io.close();
    }
}
