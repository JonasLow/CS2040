import java.io.*;
import java.util.*;

public class IntegerList {
    public static void main(String[] args) throws IOException{
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));
        
        int numTest = Integer.parseInt(br.readLine());
        for (int i = 0; i < numTest; i++) {
            String[] p = br.readLine().split("");
            int n = Integer.parseInt(br.readLine());
            String input = br.readLine();
            String[] elements;

            if (n == 0) {
                elements = new String[0];
            } else {
                elements = input.replaceAll("\\[|\\]", "").split(",");
            }
            
            LinkedList<Integer> intList = new LinkedList<>();
            for (String element : elements) {
                if (!element.trim().isEmpty()){
                    intList.add(Integer.parseInt(element.trim()));
                }
            }

            boolean reverseOrder = false;
            boolean error = false;

            for (String command : p) {
                if (command.equals("R")) {
                    reverseOrder = !reverseOrder;
                } else if (command.equals("D")) {
                    if (intList.isEmpty()) {
                        pw.println("error");
                        error = true;
                        break;
                    }
                    if (!reverseOrder) {
                        intList.removeFirst();
                    } else {
                        intList.removeLast();
                    }
                }
            }

            if (!error) {
                if (reverseOrder){
                    Collections.reverse(intList);                    
                }
                pw.println(intList.toString().replace(" ", ""));
            }
        }
        pw.flush();
    }
}
