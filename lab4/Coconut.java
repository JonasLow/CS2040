import java.io.*;

public class Coconut {
    static int remaining;
    static int idx = 0;

    static class Player {
        int hands;
        String left;
        String right;

        Player() {
            this.hands = 1;
            this.left = "";
            this.right = "";
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        int s = Integer.parseInt(input[0]);
        int p = Integer.parseInt(input[1]);
        remaining = p;
        boolean flag = false;

        Player[] players = new Player[p];

        for (int i = 0; i < p; i++) {
            players[i] = new Player();
        }

        while (remaining > 1) {
            int tracker;
            if (flag) {
                tracker = -1;
                flag = false;
            } else {
                tracker = 0;
            }

            while (tracker < s) {
                tracker += players[idx].hands;
                idx++;
                idx = idx % p;
            }
            idx = (idx + p - 1) % p;

            if (s == tracker && players[idx].hands == 2) {
                if (players[idx].right.equals("fist")) {
                    players[idx].right = "down";
                } else {
                    players[idx].hands--;
                    remaining--;
                }
                idx++;
            } else if (s == tracker && players[idx].hands == 1) {
                if (players[idx].right.equals("")) {
                    players[idx].left = "fist";
                    players[idx].right = "fist";
                    players[idx].hands++;
                    remaining++;
                } else if (players[idx].right.equals("fist")) {
                    players[idx].right = "down";
                    idx++;
                } else {
                    players[idx].hands--;
                    remaining--;
                    idx++;
                }
            } else if (tracker > s && players[idx].hands == 2) {
                if (players[idx].left == "fist") {
                    players[idx].left = "down";
                } else {
                    players[idx].hands--;
                    remaining--;
                }
            } else if (tracker > s && players[idx].hands == 1) {
                if (players[idx].left.equals("")) {
                    players[idx].left = "fist";
                    players[idx].right = "fist";
                    players[idx].hands++;
                    remaining++;
                } else if (players[idx].left.equals("fist")) {
                    players[idx].left = "down";
                    flag = true;
                    idx++;
                } else if (players[idx].right.equals("fist")) {
                    players[idx].right = "down";
                    flag = true;
                    idx++;
                } else {
                    players[idx].hands--;
                    remaining--;
                    idx++;
                }
            }

            idx = idx % p;
        }
        System.out.println(idx + 1);
    }
}
