import java.io.*;
import java.util.*;

public class GuessTheDS {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio(System.in, System.out);
        while (io.hasMoreTokens()) {
            Queue<Integer> queue = new LinkedList<>();
            Stack<Integer> stack = new Stack<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            
            boolean isQueue = true;
            boolean isStack = true;
            boolean isPQ = true;

            int n = io.getInt();
            for (int i = 0; i < n; i++) {
                int operation = io.getInt();
                int value = io.getInt();

                switch(operation) {
                    case 1:
                        if (isQueue) {
                            queue.offer(value);
                        }
                        if (isStack) {
                            stack.push(value);
                        }
                        if (isPQ) {
                            pq.add(value);
                        }
                        break;
                    
                    case 2:
                        if (isQueue) {
                            if (queue.isEmpty() || queue.peek() != value) {
                                isQueue = false;
                            } else {
                                queue.poll();
                            }
                        }
                        if (isStack) {
                            if (stack.isEmpty() || stack.peek() != value) {
                                isStack = false;
                            } else {
                                stack.pop();
                            }
                        }
                        if (isPQ) {
                            if (pq.isEmpty() || pq.peek() != value) {
                                isPQ = false;
                            } else {
                                pq.remove(value);
                            }
                        }
                        break;
                }
            }

            if ((isQueue && isStack) || (isStack && isPQ) || (isPQ && isQueue)) {
                io.println("not sure");
            } else if (isQueue) {
                io.println("queue");
            } else if (isStack) {
                io.println("stack");
            } else if (isPQ) {
                io.println("priority queue");
            } else {
                io.println("impossible");
            }
        }
        io.close();
    }
}