import java.io.*;
import java.util.*;

public class ZipfSong {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int m = io.getInt();

        ArrayList<Song> songs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long f = io.getLong();
            String songName = io.getWord();
            songs.add(new Song(f, i, songName));
        }

        Collections.sort(songs);

        for (int j = 0; j < m; j++) {
            io.println(songs.get(j).name);
        }
        io.flush();
        io.close();
    }
}

class Song implements Comparable<Song> {
    static long z0;
    long fi;
    int i;
    String name;
    double q;

    Song(long fi, int i, String name) {
        this.fi = fi;
        this.i = i;
        this.name = name;
        if (i == 0) {
            z0 = fi;
        }
        this.q = 1.0 * (i + 1) * fi / z0;
    }

    @Override
    public int compareTo(Song other) {
        if (this.q != other.q) {
            return Double.compare(other.q, this.q);
        }
        return Long.compare(this.i, other.i);
    }
}
