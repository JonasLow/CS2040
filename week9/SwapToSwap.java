import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SwapToSwap {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        int n = io.nextInt(); // Number of elements in the array
        int k = io.nextInt(); // Number of swap pairs

        UnionFind uf = new UnionFind(n + 1); // Initialize Union-Find (1-based indexing)

        // Process each swap pair
        for (int i = 0; i < k; i++) {
            int a = io.nextInt();
            int b = io.nextInt();
            uf.union(a, b);
        }

        boolean possible = true;

        // Check for each position if the element can reach its target position
        for (int i = 1; i <= n / 2; i++) {
            int initialValue = n - i + 1; // Initial value at position i
            int targetValue = i;           // Target value at position i

            if (!uf.connected(initialValue, targetValue)) {
                possible = false;
                break;
            }
        }

        if (possible) {
            io.println("Yes");
        } else {
            io.println("No");
        }

        io.close();
    }
}

// Union-Find (Disjoint Set Union) Implementation
class UnionFind {
    private int[] parent;
    private int[] rank;

    // Constructor initializes parent and rank arrays
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for(int i =0; i < size; i++) {
            parent[i] = i; // Each element is its own parent initially
            rank[i] = 1;   // Initial rank is 1
        }
    }

    // Find the root parent of element x with path compression
    public int findSet(int x) {
        if(parent[x] != x) {
            parent[x] = findSet(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union the sets containing elements x and y
    public void union(int x, int y) {
        int rootX = findSet(x);
        int rootY = findSet(y);

        if(rootX == rootY) {
            return; // Already in the same set
        }

        // Union by rank to keep tree shallow
        if(rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if(rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    // Check if elements x and y are in the same set
    public boolean connected(int x, int y) {
        return findSet(x) == findSet(y);
    }
}

// FastIO class for efficient input and output
class FastIO extends PrintWriter { 
    BufferedReader br; 
    StringTokenizer st;

    public FastIO() { 
        super(new BufferedOutputStream(System.out)); 
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    // Method to fetch the next token
    String next() { 
        while (st == null || !st.hasMoreElements()) { 
            try { 
                String line = br.readLine();
                if(line == null) return null; // End of input
                st = new StringTokenizer(line); 
            } catch (IOException  e) { 
                e.printStackTrace(); 
            } 
        } 
        return st.nextToken(); 
    }

    // Method to fetch the next integer
    int nextInt() { return Integer.parseInt(next()); } 

    // Method to fetch the next long
    long nextLong() { return Long.parseLong(next()); } 

    // Method to fetch the next double
    double nextDouble() { return Double.parseDouble(next()); } 

    // Method to fetch the next line
    String nextLine() { 
        String str = ""; 
        try { 
            str = br.readLine(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        return str; 
    }
}
