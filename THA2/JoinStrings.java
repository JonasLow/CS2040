import java.io.*;
import java.util.*;

public class JoinStrings {
    static class Node {
        StringBuilder sb;
        Node tail;
        Node next;

        Node(String s) {
            this.sb = new StringBuilder(s);
            this.next = null;
            this.tail = this;
        }

        public void add(Node other) {
            this.tail.next = other;
            this.tail = other.tail;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(br.readLine());
        }

        Node headNode = nodes[0];
        for (int j = 0; j < n - 1; j++) {
            String[] tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]) - 1;
            int b = Integer.parseInt(tokens[1]) - 1;

            nodes[a].add(nodes[b]);
            headNode = nodes[a];
        }

        while (headNode != null) {
            pw.print(headNode.sb.toString());
            headNode = headNode.next;
        }

        pw.flush();
    }
}
