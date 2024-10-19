import java.util.StringTokenizer;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Plank implements Comparable<Plank> {
    long length;
    long weight;
    long id;
    private static long counter = 0;

    public Plank(long length, long weight) {
        this.length = length;
        this.weight = weight;
        this.id = counter++;
    }

    @Override
    public int compareTo(Plank other) {
        if (this.length != other.length) {
            return Long.compare(this.length, other.length);
        } else if (this.weight != other.weight){
            return Long.compare(other.weight, this.weight);
        } else {
            return Long.compare(this.id, other.id);
        }
    }

    public String toString() {
        return "Plank {length=" + length + ", weight=" + weight + "}";
    }
}


public class TomAndJerry {
    static TreeSet<Plank> planks; 
    
    TomAndJerry() {
        planks = new TreeSet<>();
    }

    public void addPlank(long length, long weight) {
        planks.add(new Plank(length, weight));
    }

    public long chase(long x) {
        Plank dummyFloor = new Plank(x, Long.MIN_VALUE);
        Plank dummyCeil = new Plank(x, Long.MAX_VALUE);
        Plank A = planks.floor(dummyFloor);
        planks.remove(A);
        Plank B = planks.ceiling(dummyCeil);
        planks.remove(B);

        if (A == null || B == null) {
            return - 1;
        }

        long E = (1L + A.weight + B.weight) * (1L + Math.abs(A.length - B.length));

        return E;
    }

    public static void main(String[] args) {
        FastIO io = new FastIO();
        int q = io.nextInt();
        TomAndJerry p = new TomAndJerry();

        for (int i = 0; i < q; i++) {
            String opr = io.next();
            switch (opr) {
                case "a":
                    long W = io.nextLong();
                    long L = io.nextLong();
                    p.addPlank(L, W);
                    break;
            
                case "c":
                    long X = io.nextLong();
                    long E = p.chase(X);
                    io.println(E);
                    break;
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
        while (st == null || !st.hasMoreElements()) { 
            try { 
                st = new StringTokenizer(br.readLine()); 
            } catch (IOException  e) { 
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