import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Fox {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            String[] sounds = br.readLine().split(" ");
            ArrayList<String> notfox = new ArrayList<>();

            while (true) {
                String input = br.readLine();
                if (input.equals("what does the fox say?")){
			        break;
                }
                notfox.add(input.split(" ")[2]);
            }

            for (int j = 0; j < sounds.length; j++) {
	        	if (!notfox.contains(sounds[j])) {
                    pw.print(sounds[j] + " ");
                    pw.flush();
                }
            }
        }
        pw.close();
    }
}
