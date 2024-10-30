import java.io.*;
import java.util.*;

public class Millionaire {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        int m = io.nextInt();
        int n = io.nextInt();

        long[][] grid = new long[m][n];
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = io.nextLong();
            }
        }

        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        PriorityQueue<Coordinate> pq = new PriorityQueue<Coordinate>();

        pq.add(new Coordinate(0, 0, grid[0][0], 0));
        visited[0][0] = true;
        long result = 0;

        while (!pq.isEmpty() && !visited[m - 1][n - 1]) {
            Coordinate curr = pq.poll();
            visited[curr.x][curr.y] = true;
            result = Math.max(curr.diff, result);

            for (int k = 0; k < 4; k++) {
                int a = curr.x + directions[k][0];
                int b = curr.y + directions[k][1];

                if (a > -1 && b > -1 && a < m && b < n && !visited[a][b]) {
                    long h = grid[a][b];
                    long newDiff = h - curr.height;
                    Coordinate next = new Coordinate(a, b, h, newDiff);
                    pq.add(next);
                }
            }
        }
        io.print(result);
        io.close();
    }
}

class Coordinate implements Comparable<Coordinate> {
    int x;
    int y;
    long height;
    long diff;

    Coordinate(int x, int y, long height, long diff) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.diff = diff;
    }

    @Override
    public int compareTo(Coordinate other) {
        return Long.compare(this.diff, other.diff);
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
