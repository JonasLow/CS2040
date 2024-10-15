import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class Nicknames {
    public static void main(String[] args) {
        FastIO io = new FastIO();
        HashMap<String, Integer> wordbank = new HashMap<>();
        AVL tree = new AVL();

        int a = io.nextInt();
        for (int i = 0; i < a; i++) {
            String name = io.next();
            tree.insert(name);
        }

        int b = io.nextInt();
        for (int i = 0; i < b; i++) {
            String nickname = io.next();
            int count;
            if (wordbank.containsKey(nickname)) {
                count = wordbank.get(nickname);
            } else {
                count = tree.countPrefix(nickname);
                wordbank.put(nickname, count); // Cache the result
            }
            io.println(count);
        }
        io.close();
    }
}

class Node {
    Node parent;
    Node leftChild;
    Node rightChild;
    String item;
    int height;
    int size;

    Node(String item) {
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
        this.item = item;
        this.height = 1;
        this.size = 1;
    }
}

class AVL {
    Node root;

    AVL() {
        this.root = null;
    }

    // Helper method to get the height of a node
    int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // Helper method to get the size of a node
    int size(Node node) {
        return (node == null) ? 0 : node.size;
    }

    // Right rotation
    Node rightRotate(Node y) {
        Node x = y.leftChild;
        Node T2 = x.rightChild;

        // Perform rotation
        x.rightChild = y;
        y.leftChild = T2;

        // Update parents
        if (T2 != null) {
            T2.parent = y;
        }
        x.parent = y.parent;
        y.parent = x;

        // Update heights
        y.height = Math.max(height(y.leftChild), height(y.rightChild)) + 1;
        x.height = Math.max(height(x.leftChild), height(x.rightChild)) + 1;

        // Update sizes
        y.size = size(y.leftChild) + size(y.rightChild) + 1;
        x.size = size(x.leftChild) + size(x.rightChild) + 1;

        // Return new root
        return x;
    }

    // Left rotation
    Node leftRotate(Node x) {
        Node y = x.rightChild;
        Node T2 = y.leftChild;

        // Perform rotation
        y.leftChild = x;
        x.rightChild = T2;

        // Update parents
        if (T2 != null) {
            T2.parent = x;
        }
        y.parent = x.parent;
        x.parent = y;

        // Update heights
        x.height = Math.max(height(x.leftChild), height(x.rightChild)) + 1;
        y.height = Math.max(height(y.leftChild), height(y.rightChild)) + 1;

        // Update sizes
        x.size = size(x.leftChild) + size(x.rightChild) + 1;
        y.size = size(y.leftChild) + size(y.rightChild) + 1;

        // Return new root
        return y;
    }

    // Get balance factor of node N
    int getBalance(Node node) {
        return (node == null) ? 0 : height(node.leftChild) - height(node.rightChild);
    }

    // Insert method
    Node insert(Node node, String item) {
        // Perform the normal BST insertion
        if (node == null) {
            return new Node(item);
        }

        if (item.compareTo(node.item) < 0) {
            node.leftChild = insert(node.leftChild, item);
            node.leftChild.parent = node;
        } else if (item.compareTo(node.item) > 0) {
            node.rightChild = insert(node.rightChild, item);
            node.rightChild.parent = node;
        } else {
            return node; // No duplicates allowed
        }

        // Update height and size of this node
        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;
        node.size = size(node.leftChild) + size(node.rightChild) + 1;

        // Get the balance factor of this node
        int balance = getBalance(node);

        // If this node becomes unbalanced, there are 4 cases

        // Left Left Case
        if (balance > 1 && item.compareTo(node.leftChild.item) < 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && item.compareTo(node.rightChild.item) > 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && item.compareTo(node.leftChild.item) > 0) {
            node.leftChild = leftRotate(node.leftChild);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && item.compareTo(node.rightChild.item) < 0) {
            node.rightChild = rightRotate(node.rightChild);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // Public insert method
    void insert(String item) {
        this.root = insert(this.root, item);
    }

    // Implement the rank function
    int getRank(Node node, String key) {
        if (node == null) {
            return 0;
        }
        if (key.compareTo(node.item) <= 0) {
            return getRank(node.leftChild, key);
        } else {
            int leftSize = (node.leftChild != null) ? node.leftChild.size : 0;
            return leftSize + 1 + getRank(node.rightChild, key);
        }
    }

    // Public method to get rank
    int getRank(String key) {
        return getRank(this.root, key);
    }

    // Method to count the number of names starting with a given prefix
    int countPrefix(String prefix) {
        String lowerBound = prefix;
        String upperBound = prefix + Character.MAX_VALUE;
        int rankUpper = getRank(upperBound);
        int rankLower = getRank(lowerBound);
        return rankUpper - rankLower;
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
            try { st = new StringTokenizer(br.readLine()); }
            catch (IOException e) { e.printStackTrace(); }
        }
        return st.nextToken();
    }

    int nextInt() { return Integer.parseInt(next()); }

    long nextLong() { return Long.parseLong(next()); }

    double nextDouble() { return Double.parseDouble(next()); }

    String nextLine() {
        String str = "";
        try { str = br.readLine(); }
        catch (IOException e) { e.printStackTrace(); }
        return str;
    }
}
