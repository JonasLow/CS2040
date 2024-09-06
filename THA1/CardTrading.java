import java.util.*;

public class CardTrading {

    static int method1(int[] cards, int[] buy, int[] sell) {
        int profit = 0;
        int[] firstHand = Arrays.copyOf(cards, cards.length);
        for (int i = 0; i < cards.length; i++) {
            int nett = 0;
            int[] newCards = Arrays.copyOf(cards, cards.length);
            newCards[i] = 0;
            for (int j = 0; j < newCards.length; j++) {
                if (i != j) {
                    newCards[j] = 2;
                }
                int delta = cards[j] - newCards[j];
                if (delta > 0) {
                    nett += sell[j] * delta;
                } else if (delta < 0) {
                    nett += buy[j] * delta;
                }
            }
            if (i == 0 || profit < nett) {
                firstHand = newCards;
                profit = nett;
            }
        }
        return profit;
    }

    static int method2(int[] cards, int[] buy, int[] sell) {
        int profit = 0;
        int[] nextHand = Arrays.copyOf(cards, cards.length);
        for (int i = 0; i < cards.length; i++) {
            int nett = 0;
            if (cards[i] != 0) {
                int[] newCards = Arrays.copyOf(cards, cards.length);
                for (int j = 0; j < newCards.length; j++) {
                    if (i != j) {
                        newCards[j] = 2;
                    }
                    int delta = cards[j] - newCards[j];
                    if (delta > 0) {
                        nett += sell[j] * delta;
                    } else if (delta < 0) {
                        nett += buy[j] * delta;
                    }
                }
                if (i == 0 || profit < nett) {
                    nextHand = newCards;
                    profit = nett;
                }
            }
        }
        return profit;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        int K = sc.nextInt();

        int[] cards = new int[T];
        int[] buy = new int[T];
        int[] sell = new int[T];

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            cards[num - 1] += 1;
        }

        for (int k = 0; k < T; k++) {
            buy[k] = sc.nextInt();
            sell[k] = sc.nextInt();
        }

        int profit = 0;
        for (int j = 0; j < T - K; j++) {
            if (j == 0) {
                profit = method1(cards, buy, sell);
            } else {
                profit += method2(cards, buy, sell);
            }
        }

        System.out.println(profit);
    }
}
