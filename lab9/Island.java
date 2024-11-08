import java.util.*;
import java.io.*;

public class Island {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        int r = io.nextInt();
        int c = io.nextInt();

        char[][] grid = new char[r][c];

        for (int i = 0; i < r; i++) {
            String line = io.nextLine();
            for (int j = 0; j < c; j++) {
                char next = line.charAt(j);
                grid[i][j] = next;
            }
        }

        boolean[][] visited = new boolean[r][c];
        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        int result = 0;
        for (int k = 0; k < r; k++) {
            for (int m = 0; m < c; m++) {
                if (grid[k][m] == 'L' && !visited[k][m]) {
                    visited[k][m] = true;
                    Queue<Vertex> queue = new ArrayDeque<Vertex>();
                    queue.add(new Vertex(k, m));
                    while (!queue.isEmpty()) {
                        Vertex next = queue.poll();
                        for (int n = 0; n < 4; n++) {
                            int nextRow = next.row + direction[n][0];
                            int nextCol = next.col + direction[n][1];
                            if (nextRow > -1 && nextCol > -1 && nextRow < r && nextCol < c && grid[nextRow][nextCol] != 'W' && !visited[nextRow][nextCol]) {
                                visited[nextRow][nextCol] = true;
                                queue.add(new Vertex(nextRow, nextCol));
                            }
                        }
                    }
                    result++;
                } else if (grid[k][m] == 'W') {
                    visited[k][m] = true;
                }
            }
        }
        io.println(result);
        io.close();
    }
}

class Vertex {
    int row;
    int col;

    Vertex(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

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
