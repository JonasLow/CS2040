import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CoconutSplat {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);

        int s = io.getInt();
        int n = io.getInt();

        List<Coconut> coconuts = new LinkedList<Coconut>();
        for (int i = 0; i < n; i++) {
            coconuts.add(new Coconut(i, 1));
        }
        int nextIndex = 0;
        while (coconuts.size() > 1) {
            nextIndex = (nextIndex + s - 1) % coconuts.size();
            Coconut nextPlayer = coconuts.get(nextIndex);
            if (nextPlayer.isFolded()) {
                nextPlayer.changeState();
                Coconut clonedFist = new Coconut(nextPlayer.getPlayer(), 2);
                coconuts.add(coconuts.indexOf(nextPlayer) + 1, clonedFist);
            } else if (nextPlayer.isFist()) {
                nextPlayer.changeState();
                nextIndex++;
            } else {
                nextPlayer.changeState();
                coconuts.remove(nextPlayer);
            }
        }
        io.println(coconuts.get(0).getPlayer() + 1);
        io.close();
    }
}

class Coconut {
    private int player;
    private int state;

    Coconut(int player, int state) {
        this.player = player;
        this.state = state;
    }

    int getPlayer() {
        return this.player;
    }

    void changeState() {
        this.state += 1;
    }

    boolean isFolded() {
        return this.state == 1;
    }

    boolean isFist() {
        return this.state == 2;
    }

    boolean isPalmDown() {
        return this.state == 3;
    }
}
