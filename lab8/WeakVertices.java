import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class WeakVertices {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        while (true) {
            int n = io.nextInt();
            if (n == -1) {
                break;
            }

            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = io.nextInt();
                }
            }

            ArrayList<Integer> weak = new ArrayList<>();
            for (int row = 0; row < n; row++) {
                ArrayList<Integer> neighbours = new ArrayList<>();
                for (int col = 0; col < n; col++) {
                    if (matrix[row][col] == 1) {
                        neighbours.add(col);
                    }
                }

                if (neighbours.size() < 2) {
                    weak.add(row);
                } else {
                    boolean notWeak = false;
                    for (int neighbour : neighbours) {
                        for (int k = 0; k < n; k++) {
                            if (matrix[neighbour][k] == 1 && k != neighbour && neighbours.contains(k)) {
                                notWeak = true;
                            }
                        }
                    }
                    if (!notWeak) {
                        weak.add(row);
                    }
                }
            }
            if (n > 0) {
                StringBuilder result = new StringBuilder();
                for (int vertex : weak) {
                    result.append(vertex).append(" ");
                }
                io.println(result.toString().trim());
            } else {
                io.println("");
            }
        }
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

