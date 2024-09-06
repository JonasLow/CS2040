import java.util.*;

public class CardTrading3 {
    static class Card {
        long buy;
        long sell;

        Card(long buy, long sell, long count) {
            this.buy = (2 - count) * buy;
            this.sell = count * sell;
        }

        public int compareTo(Card other) {
            return Long.compare(this.buy + this.sell, other.buy + other.sell);
        }
    }

    static class cardComparator implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            int diff = c1.compareTo(c2);
            if (diff == 0) {
                return Long.compare(c1.buy, c2.buy);
            }
            return diff;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        int K = sc.nextInt();

        int[] distribution = new int[T + 1];

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            distribution[num - 1]++;
        }

        ArrayList<Card> cards = new ArrayList<>();
        for (int j = 1; j <= T; j++) {
            long a = sc.nextInt();
            long b = sc.nextInt();
            cards.add(new Card(a, b, distribution[j - 1]));
        }

        Collections.sort(cards, new cardComparator());

        long profit = 0;
        for (int s = 0; s < K; s++) {
            Card current = cards.get(s);
            profit -= current.buy;
        }

        for (int u = K; u < T; u++) {
            Card current = cards.get(u);
            profit += current.sell;
        }

        if (N == 1) {
            System.out.println(0);
        } else {
            System.out.println(profit);
        }
    }
}
