import java.util.*;

class CardTrading2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();    // number of cards
        int T = scanner.nextInt();    // number of card types
        int K = scanner.nextInt();    // number of combos

        int[] count = new int[T + 1];

        for (int i = 0; i < N; i++) {
            int number = scanner.nextInt();
            count[number]++;
        }

        ArrayList<Type> types = new ArrayList<>();

        for (int i = 1; i <= T; i++) {
            Long cost = scanner.nextLong();
            Long earn = scanner.nextLong();
            Type type = new Type(i, cost, earn);
            type.increment(count[type.getId()]);
            type.update();
            types.add(type);
        }

        Collections.sort(types, new TypeComparator());

        long profit = 0;

        for (int i = 0; i < K; i++) {
            Type type = types.get(i);
            profit -= type.getBuy();
        }
        for (int i = K; i < T; i++) {
            Type type = types.get(i);
            profit += type.getSell();
        }

        if (N == 1) {
            System.out.println(0);
        } else {
            System.out.println(profit);
        }
    }
}

class Type {
    private final int id;
    private Long buy;
    private Long sell;
    private int count;

    Type(int id, Long buy, Long sell) {
        this.id = id;
        this.buy = buy;
        this.sell = sell;
        this.count = 0;
    }

    int getId() {
        return id;
    }

    Long getBuy() {
        return buy;
    }

    Long getSell() {
        return sell;
    }

    int getCount() {
        return count;
    }

    void increment(int number) {
        this.count = this.count + number;
    }

    void update() {
        buy = (2 - count) * buy;
        sell = count * sell;
    }

    Long getDifference() {
        return buy + sell;
    }

    public int compareTo(Type type) {
        return this.getDifference().compareTo(type.getDifference());
    }

    public String toString() {
        return "Id: " + id + ", Buy: " + buy + ", Sell: " + sell +
            ", Count: " + count +
            ",        Difference: " + this.getDifference();
    }
}

class TypeComparator implements Comparator<Type> {
    public int compare(Type type1, Type type2) {
        int difference = type1.compareTo(type2);
        if (difference == 0) {
            return type1.getBuy().compareTo(type2.getBuy());
        }
        return difference;
    }
}
