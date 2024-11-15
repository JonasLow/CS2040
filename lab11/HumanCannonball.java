import java.util.*;
import java.io.*;

class HumanCannonball {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        ArrayList<Point> points = new ArrayList<>();
        double startX = io.nextDouble();
        double startY = io.nextDouble();

        points.add(new Point(startX, startY));

        double endX = io.nextDouble();
        double endY = io.nextDouble();
        points.add(new Point(endX, endY));

        int n = io.nextInt();
        for (int i = 0; i < n; i++) {
            double nextX = io.nextDouble();
            double nextY = io.nextDouble();
            points.add(new Point(nextX, nextY));
        }

        double[][] adjMatrix = new double[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(adjMatrix[i], Double.POSITIVE_INFINITY);
        }

        adjMatrix[0][1] = points.get(0).getDistance(points.get(1)) / 5;
        adjMatrix[1][0] = adjMatrix[0][1];

        for (int i = 2; i < n + 2; i++) {
            double distance = points.get(0).getDistance(points.get(i));
            adjMatrix[0][i] = distance / 5;
            adjMatrix[i][0] = (Math.abs(distance - 50) / 5) + 2;
        }
        for (int i = 2; i < n + 2; i++) {
            double distance = points.get(1).getDistance(points.get(i));
            adjMatrix[1][i] = distance / 5;
            adjMatrix[i][1] = (Math.abs(distance - 50) / 5) + 2;
        }
        for (int i = 2; i < n + 2; i++) {
            for (int j = 2; j < n + 2; j++) {
                if (i != j) {
                    double distance = points.get(i).getDistance(points.get(j));
                    adjMatrix[i][j] = (Math.abs(distance - 50) / 5) + 2;
                    adjMatrix[j][i] = adjMatrix[i][j];
                }
            }
        }

        double[] result = dijkstra(adjMatrix, 0);
        io.println(Double.toString(result[1]));
        io.close();
    }

    static double[] dijkstra(double[][] adjMatrix, int src) {
        int V = adjMatrix.length;
        double[] dist = new double[V];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> node.distance));
        pq.offer(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.index;
            double currentDist = current.distance;

            if (currentDist > dist[u]) {
                continue;
            }

            for (int v = 0; v < V; v++) {
                if (adjMatrix[u][v] != Double.POSITIVE_INFINITY) {
                    double newDist = dist[u] + adjMatrix[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.offer(new Node(v, dist[v]));
                    }
                }
            }
        }
        return dist;
    }
}

class Point {
    double x; 
    double y; 

    Point(double x, double y) {
        this.x = x; 
        this.y = y;
    }

    public double getDistance(Point other) {
        double diffX = Math.abs(this.x - other.x);
        double diffY = Math.abs(this.y - other.y);
        return Math.hypot(diffX, diffY);
    }
}

class Node {
    int index;
    double distance;

    Node(int index, double distance) {
        this.index = index;
        this.distance = distance;
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
        while (st == null || !st.hasMoreElements()) { 
            try { 
                String line = br.readLine();
                if(line == null) return null; 
                st = new StringTokenizer(line); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
        return st.nextToken(); 
    }

    int nextInt() { return Integer.parseInt(next()); } 
    long nextLong() { return Long.parseLong(next()); } 
    double nextDouble() { return Double.parseDouble(next()); } 
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
