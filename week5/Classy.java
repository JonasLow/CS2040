import java.io.*;
import java.util.*;

public class Classy {
    static class Person {
        public final String name;
        public final String classInfo;

        Person(String name, String classInfo) {
            this.name = name;
            this.classInfo = classInfo;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(
            new BufferedWriter(new OutputStreamWriter(System.out)));

        int cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < cases; i++) {
            int numOfPpl = Integer.parseInt(br.readLine());
            Person[] people = new Person[numOfPpl];

            for (int j = 0; j < numOfPpl; j++) {
                String person = br.readLine();
                String name = person.substring(0, person.indexOf(":")).trim();
                String personClass =
                    person.substring(person.indexOf(":") + 1).trim();
                personClass =
                    personClass.substring(0, personClass.indexOf(" class"))
                        .trim();
                String[] classParts = personClass.split("[- ]+");

                List<String> classList =
                    new ArrayList<>(Arrays.asList(classParts));
                while (classList.size() < 10) {
                    classList.add(0, "middle");
                }
                Collections.reverse(classList);

                StringBuilder initials = new StringBuilder();
                for (String part : classList) {
                    initials.append(part.charAt(0));
                }

                people[j] = new Person(name, initials.toString());
            }

            Arrays.sort(people, (p1, p2) -> {
                int classComparison = p2.classInfo.compareTo(p1.classInfo);

                if (classComparison != 0) {
                    return classComparison;
                }

                return p1.name.compareTo(p2.name);
            });

            for (Person someone : people) {
                pw.println(someone);
            }
            pw.println("==============================");
        }
        pw.flush();
    }
}
