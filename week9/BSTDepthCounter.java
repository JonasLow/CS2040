import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BSTDepthCounter {
    public static void main(String[] args) throws IOException {
        FastIO io = new FastIO();
        int n = io.nextInt(); // Number of elements to insert
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        
        long counter = 0; // To store the cumulative depth
        StringBuilder sb = new StringBuilder(); // For efficient output accumulation
        
        for (int i = 0; i < n; i++) {
            int x = io.nextInt(); // Current element to insert
            
            // Find predecessor and successor
            Integer pre = treeMap.lowerKey(x);
            Integer suc = treeMap.higherKey(x);
            
            // Determine depths of predecessor and successor
            int preDepth = (pre != null) ? treeMap.get(pre) : -1;
            int sucDepth = (suc != null) ? treeMap.get(suc) : -1;
            
            // Calculate current depth
            int currentDepth = 0;
            if (pre == null && suc == null) {
                currentDepth = 0; // Root node
            } else if (pre == null) {
                currentDepth = sucDepth + 1;
            } else if (suc == null) {
                currentDepth = preDepth + 1;
            } else {
                currentDepth = Math.max(preDepth, sucDepth) + 1;
            }
            
            // Insert the current element with its depth
            treeMap.put(x, currentDepth);
            
            // Update the cumulative counter
            counter += currentDepth;
            
            // Append the current counter to the output
            sb.append(counter).append('\n');
        }
        
        // Print all results at once
        io.println(sb.toString());
        io.close();
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
