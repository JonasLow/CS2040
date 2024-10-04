import java.io.*;
import java.util.*;

public class Delimiter {
    public static void main(String[] args) throws IOException{
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));
        int L = Integer.parseInt(br.readLine());
        String[] brackets = br.readLine().split("");

        Stack<String> BracketStack = new Stack<>();
        boolean isFinished = true;

        for (int i = 0; i < L; i++) {
            if (brackets[i].equals("(") || brackets[i].equals("[") || brackets[i].equals("{"))
                BracketStack.push(brackets[i]);
            else if (!brackets[i].equals(" ")) {
                if (BracketStack.isEmpty()) {
                    pw.println(brackets[i] + " " + i);
                    isFinished = false;
                    break;
                }
                String prev = BracketStack.peek();
                if (prev.equals("(") && brackets[i].equals(")")) {
                    BracketStack.pop();                    
                } else if (prev.equals("[") && brackets[i].equals("]")) {
                    BracketStack.pop();                    
                } else if (prev.equals("{") && brackets[i].equals("}")) {
                    BracketStack.pop();                    
                } else {
                    pw.println(brackets[i] + " " + i);
                    isFinished = false;
                    break;
                }
            }
        }
        if (isFinished) {
            pw.println("ok so far");
        }
        pw.flush();
    }
}
