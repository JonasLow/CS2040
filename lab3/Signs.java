import java.io.*;
import java.util.*;

public class Signs {
    static class Word {
        public final String word;
        public final String middle;

        Word(String word, String middle) {
            this.word = word;
            this.middle = middle;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(br.readLine());
        Word[] stringArr = new Word[n];

        for (int i = 0; i < n; i++) {
            String yappedWords = br.readLine();
            if (yappedWords.length() % 2 == 1) {
                stringArr[i] =
                    new Word(yappedWords,
                             "" + yappedWords.charAt(yappedWords.length() / 2));
            } else {
                stringArr[i] = new Word(
                    yappedWords,
                    yappedWords.charAt(yappedWords.length() / 2 - 1) + "" +
                        yappedWords.charAt(yappedWords.length() / 2));
            }
        }

        Arrays.sort(stringArr,
                    Comparator.comparing(yappedWords -> yappedWords.middle));
        for (Word yappedWords : stringArr) {
            System.out.println(yappedWords.word);
        }
    }
}
