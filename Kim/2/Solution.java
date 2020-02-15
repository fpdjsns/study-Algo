import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the alternate function below.
    static int alternate(String s) {

        char[] array = s.toCharArray();
        Set<String> characters = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            characters.add(Character.toString(array[i]));
        }
        List<String> distinct = new ArrayList<>(characters);

        int count = 0;

        for (int i = 0; i < distinct.size() - 1; i++) {
            for (int j = i + 1; j < distinct.size(); j++) {
                char[] test = s.replaceAll(distinct.get(i), "").replaceAll(distinct.get(j), "").toCharArray();

                count = Math.max(count, count(test));
            }

        }


        return count;
    }

    static int count(char[] test) {
        for (int k = 0; k < test.length - 1; k++) {
            if (test[k] == test[k + 1]) {
                return 0;
            }
        }

        return test.length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = alternate(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
