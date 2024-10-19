import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Friends {
    public static void main(String[] args) throws IOException {
        FastIO io = new FastIO();
        int n = io.nextInt(); // Number of test cases

        for (int i = 0; i < n; i++) {
            int f = io.nextInt(); // Number of friendship pairs

            // Initialize Union-Find structure
            UnionFind uf = new UnionFind();

            // Mapping from person name to unique ID
            HashMap<String, Integer> nameToId = new HashMap<>();
            int idCounter = 0;

            for (int j = 0; j < f; j++) {
                String person1 = io.next();
                String person2 = io.next();

                // Assign unique IDs to each person if not already assigned
                if (!nameToId.containsKey(person1)) {
                    nameToId.put(person1, idCounter);
                    uf.addElement(idCounter);
                    idCounter++;
                }
                if (!nameToId.containsKey(person2)) {
                    nameToId.put(person2, idCounter);
                    uf.addElement(idCounter);
                    idCounter++;
                }

                int id1 = nameToId.get(person1);
                int id2 = nameToId.get(person2);

                // Union the two friends and get the size of the group
                int groupSize = uf.union(id1, id2);

                // Output the size of the friend group
                io.println(groupSize);
            }
        }
        io.close();
    }
}

// Union-Find (Disjoint Set Union) Implementation
class UnionFind {
    private int[] parent;
    private int[] size;
    private int capacity;

    public UnionFind() {
        // Initialize with a capacity, will expand as needed
        capacity = 1000000; // Adjust based on problem constraints
        parent = new int[capacity];
        size = new int[capacity];
        // Initially, no elements are added
    }

    // Add a new element with its own parent
    public void addElement(int x) {
        if (x >= parent.length) {
            expand();
        }
        parent[x] = x;
        size[x] = 1;
    }

    // Find the root parent of element x with path compression
    public int findSet(int x) {
        if (parent[x] != x) {
            parent[x] = findSet(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union the sets containing elements x and y, return the size of the merged set
    public int union(int x, int y) {
        int rootX = findSet(x);
        int rootY = findSet(y);

        if (rootX == rootY) {
            return size[rootX];
        }

        // Union by size to keep tree shallow
        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
            return size[rootY];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
            return size[rootX];
        }
    }

    // Expand the arrays if capacity is exceeded
    private void expand() {
        int newCapacity = capacity * 2;
        int[] newParent = new int[newCapacity];
        int[] newSize = new int[newCapacity];
        System.arraycopy(parent, 0, newParent, 0, capacity);
        System.arraycopy(size, 0, newSize, 0, capacity);
        parent = newParent;
        size = newSize;
        capacity = newCapacity;
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
