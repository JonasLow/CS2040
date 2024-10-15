import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class AlmostUF {
    static int n;
    static int m;
    
    int[] root;
    int[] next;
    int[] count;
    long[] sum;

    public AlmostUF(int n) {
        this.root = new int[n + 1];
        this.next = new int[n + 1];
        this.count = new int[n + 1];
        this.sum = new long[n + 1];

        for (int i = 1; i < n + 1; i++) {
            root[i] = i;
            next[i] = i;
            count[i] = 1;
            sum[i] = i;
        }
    }

    public int findRoot(int p) {
        int parent = next[p];
        while (parent != root[parent]) {
            parent = root[parent];
        }
        next[p] = parent;
        return parent;
    }

    boolean isSameSet(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) {
        if (!isSameSet(p, q)) {
            int x = findRoot(p);
            int y = findRoot(q);
            root[x] = y;
            next[p] = y;
            count[y] += count[x];
            sum[y] += sum[x];
        }
    }

    public void move(int p, int q) {
        if (!isSameSet(p, q)) {
            int x = findRoot(p);
            int y = findRoot(q);

            next[p] = y;

            count[x] -= 1;
            count[y] += 1;

            sum[x] -= p;
            sum[y] += p;
        }
    }

    public long sum(int p) {
        return sum[findRoot(p)];
    }

    public int count(int p) {
        return count[findRoot(p)];
    }

    public static void main(String[] args) {
        FastIO io = new FastIO();
        while (io.hasMoreTokens()) {
            n = io.nextInt();
            m = io.nextInt();
            
            AlmostUF wtf = new AlmostUF(n);
    
            for (int i = 0; i < m; i++) {
                int command = io.nextInt();
                int p = io.nextInt();
                switch(command) {
                    case 1:
                        int q1 = io.nextInt();
                        wtf.union(p, q1);
                        break;
                    case 2:
                        int q2 = io.nextInt();
                        wtf.move(p, q2);
                        break;
                    case 3:
                        int count = wtf.count(p);
                        long sum = wtf.sum(p);
                        io.println(count + " " + sum);
                        io.flush();
                        break;
                }
            }
        }
        io.close();
    }
}

class FastIO extends PrintWriter { 
    BufferedReader br; 
    StringTokenizer st;
    public FastIO() { 
        super(new BufferedOutputStream(System.out)); 
        br = new BufferedReader(new InputStreamReader(System.in));
    } 
    String next() { 
        while (st == null || ! st.hasMoreElements()) { 
            try { st = new StringTokenizer(br.readLine()); } 
            catch (IOException  e) { e.printStackTrace(); } 
        } 
        return st.nextToken(); 
    } 
    int nextInt() { return Integer.parseInt(next()); } 
    long nextLong() { return Long.parseLong(next()); } 
    double nextDouble() { return Double.parseDouble(next()); } 
    String nextLine() { 
        String str = ""; 
        try { str = br.readLine(); } 
        catch (IOException e) { e.printStackTrace(); } 
        return str; 
    }
    boolean hasMoreTokens() {
        try {
            // This will check if there is more data in the stream
            if (st != null && st.hasMoreTokens()) {
                return true;
            }
            // Check if there are more lines to read
            br.mark(1);  // Mark the current position
            if (br.read() < 0) {  // If nothing is left to read, return false
                return false;
            }
            br.reset();  // Reset back to the marked position
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
