import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Workstations {
    public static void main(String arg[]) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));

        int count = 0;

        PriorityQueue<Researcher> researchers =
            new PriorityQueue<>(new ResearcherComparator());
        PriorityQueue<Integer> times = new PriorityQueue<>();

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        for (int i = 0; i < n; i++) {
            String[] nextLine = br.readLine().split(" ");
            int a = Integer.parseInt(nextLine[0]);
            int s = Integer.parseInt(nextLine[1]);
            researchers.add(new Researcher(a, s));
        }

        while (!researchers.isEmpty()) {
            Researcher next = researchers.poll();
            times.add(next.getA() + next.getD());

            if (next.getA() < times.peek()) {
                continue;    // unlock a new station because the first person is still using
            }

            while (!times.isEmpty()) {
                int unlockTime = times.poll();
                int unlockRange = next.getA() - unlockTime;

                if (unlockRange < 0) {
                    times.add(unlockTime);    // he will use a new machine
                    break;    // check next researcher
                } else if (unlockRange > m) {
                    continue;    // the computer will time out and be locked
                } else {    // the range is valid and the computer does not need to be unlocked
                    count++;
                    break;    // check next researcher
                }
            }
        }
        pw.println(count);
        pw.flush();
        pw.close();
    }
}

class Researcher {
    int arrival;
    int duration;
    int done;

    Researcher(int arrival, int duration) {
        this.arrival = arrival;
        this.duration = duration;
        this.done = arrival + duration;
    }

    public int getA() {
        return this.arrival;
    }

    public int getD() {
        return this.duration;
    }
}

class ResearcherComparator implements Comparator<Researcher> {
    @Override
    public int compare(Researcher r1, Researcher r2) {
        if (r1.getA() == r2.getA()) {
            return r1.getD() - r2.getD();
        }
        return r1.getA() - r2.getA();
    }
}
