import java.util.*;

public class BestRelayTeam {
    static class Runner {
        String name;
        double time;

        Runner(String name, double time) {
            this.name = name;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        String[] names = new String[n];
        double[] firsts = new double[n];
        double[] others = new double[n];
        String[] finalTeam = new String[4];
        double bestTime = -1;

        for (int i = 0; i < n; i++) {
            names[i] = sc.next();
            firsts[i] = sc.nextDouble();
            others[i] = sc.nextDouble();
        }

        for (int j = 0; j < n; j++) {
            String[] team = new String[4];
            team[0] = names[j];
            double total = firsts[j];

            List<Runner> otherRunners = new ArrayList<>();

            for (int k = 0; k < n; k++) {
                if (k != j) {
                    otherRunners.add(new Runner(names[k], others[k]));
                }
            }

            otherRunners.sort(Comparator.comparingDouble(x -> x.time));
            total += otherRunners.get(0).time + otherRunners.get(1).time +
                     otherRunners.get(2).time;
            team[1] = otherRunners.get(0).name;
            team[2] = otherRunners.get(1).name;
            team[3] = otherRunners.get(2).name;
            if (bestTime == -1 || total < bestTime) {
                bestTime = total;
                finalTeam = team;
            }
        }

        System.out.println(bestTime);
        for (int i = 0; i < 4; i++) {
            System.out.println(finalTeam[i]);
        }
    }
}
