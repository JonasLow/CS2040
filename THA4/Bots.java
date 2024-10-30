import java.io.*;
import java.util.*;

public class Bots {
    static int n, m;
    static List<Integer>[] adj;
    static List<Integer>[] adjRev;
    static boolean[] visited;
    static Stack<Integer> stack = new Stack<>();
    static int[] sccId;
    static int sccCount = 0;
    static int[] sccSize;
    static List<Integer>[] sccAdj;
    static int[] inDegree;

    public static void main(String[] args) {
        FastIO io = new FastIO();
        n = io.nextInt();
        m = io.nextInt();
        adj = new ArrayList[n];
        adjRev = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            adjRev[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int p = io.nextInt();
            int q = io.nextInt();
            adj[p].add(q);
            adjRev[q].add(p);
        }

        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs1(i);
            }
        }

        sccId = new int[n];
        Arrays.fill(sccId, -1);
        visited = new boolean[n];

        while (!stack.isEmpty()) {
            int u = stack.pop();

            if (!visited[u]) {
                dfs2(u, sccCount);
                sccCount++;
            }
        }

        sccSize = new int[sccCount];
        for (int i = 0; i < n; i++) {
            sccSize[sccId[i]]++;
        }

        sccAdj = new ArrayList[sccCount];
        for (int i = 0; i < sccCount; i++) {
            sccAdj[i] = new ArrayList<>();
        }

        Set<Long> edgeSet = new HashSet<>();

        for (int u = 0; u < n; u++) {
            int sccU = sccId[u];
            for (int v : adj[u]) {
                int sccV = sccId[v];

                if (sccU != sccV) {
                    long code = ((long) sccU) << 32 | sccV;

                    if (!edgeSet.contains(code)) {
                        sccAdj[sccU].add(sccV);
                        edgeSet.add(code);
                    }
                }
            }
        }

        inDegree = new int[sccCount];
        
        for (int u = 0; u < sccCount; u++) {
            for (int v : sccAdj[u]) {
                inDegree[v]++;
            }
        }

        int solobots = 0;
        int botnets = 0;

        for (int i = 0; i < sccCount; i++) {
            if (inDegree[i] == 0) {
                if (sccSize[i] == 1) {
                    solobots++;
                } else {
                    botnets++;
                }
            }
        }
        io.println(solobots + " " + botnets);
        io.close();
    }

    static void dfs1(int u) {
        visited[u] = true;
        for (int v : adj[u]) {
            if (!visited[v]) {
                dfs1(v);
            }
        }
        stack.push(u);
    }

    static void dfs2(int u, int id) {
        visited[u] = true;
        sccId[u] = id;
        for (int v : adjRev[u]) {
            if (!visited[v]) {
                dfs2(v, id);
            }
        }
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
