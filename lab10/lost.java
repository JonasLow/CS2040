import java.util.*;
import java.io.*;

public class lost {
    static FastIO io = new FastIO();

    static class Graph {
        int vertex;
        int edge;
        Edge[] E;

        Graph(int vertex, int edge) {
            this.vertex = vertex;
            this.edge = edge;
            this.E = new Edge[this.edge];
            for (int i = 0; i < edge; ++i) {
                E[i] = new Edge();
            }
        }

        class Edge implements Comparable<Edge> {
            int src;
            int weight;
            int dest;

            public int compareTo(Edge other) {
                return Integer.compare(this.weight, other.weight);
            }
        }

        class Subset {
            int parent;
            int rank;
        }

        // UFDS find
        int find(Subset subsets[], int i) {
            if (subsets[i].parent != i) {
                subsets[i].parent = find(subsets, subsets[i].parent);
            }
            return subsets[i].parent;
        }

        // UDFS union
        void Union(Subset subsets[], int x, int y) {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);
            if (subsets[xroot].rank < subsets[yroot].rank) {
                subsets[xroot].parent = yroot;
            } else if (subsets[xroot].rank > subsets[yroot].rank) {
                subsets[yroot].parent = xroot;
            } else {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }

        // Kruskal
        void KruskalMST() {
            Edge result[] = new Edge[vertex];
            int e = 0, i = 0;
            for (i = 0; i < vertex; ++i) {
                result[i] = new Edge();
            }
            Arrays.sort(E);
            Subset subsets[] = new Subset[vertex];
            for (i = 0; i < vertex; ++i) {
                subsets[i] = new Subset();
            }
            for (int v = 0; v < vertex; ++v) {
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }
            i = 0;
            while (e < vertex - 1) {
                Edge next_edge = new Edge();
                next_edge = E[i++];
                int x = find(subsets, next_edge.src);
                int y = find(subsets, next_edge.dest);
                if (x != y) {
                    result[e++] = next_edge;
                    Union(subsets, x, y);
                }
            }
            Arrays.sort(result, 0, e, new Comparator<Edge>() {
                public int compare(Edge e1, Edge e2) {
                    if (e1.src != e2.src) {
                        return Integer.compare(e1.src, e2.src);
                    } else {
                        return Integer.compare(e1.dest, e2.dest);
                    }
                }
            });
            for (i = 0; i < e; ++i) {
                io.println((result[i].src + 1) + " " + (result[i].dest + 1));
            }
        }
    }

    public static void main(String[] args) {
        int n = io.nextInt();
        int x = 0;
        Graph g = new Graph(n, (n * n - n) / 2);

        for (int i = 0; i < n; i++) {
            String strs[] = io.nextLine().split(" ");
            for (int j = i + 1; j < n; j++) {
                g.E[x].src = i;
                g.E[x].dest = j;
                g.E[x].weight = Integer.parseInt(strs[j]);
                x++;
            }
        }
        g.KruskalMST();
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
