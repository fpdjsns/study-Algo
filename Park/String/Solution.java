import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    // Complete the alternate function below.
    static int alternate(String str) {

        // 문자열 분석 distinct
        char[] cArray = str.toCharArray();
        Set cSet = new HashSet<String>();
        for (int inx = 0; inx < cArray.length; inx++) {
            cSet.add(cArray[inx]);
        }

        Iterator it = cSet.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next());
        }

        char[] distinctArray = sb.toString().toCharArray();

        // 쌍 만들기
        List<Map<String, String>> candidates = new ArrayList<>();

        for (int inx = 0; inx < distinctArray.length - 2; inx++) {
            for (int jnx = 1; jnx < distinctArray.length - 1; jnx++) {
                Map<String, String> candidate = new HashMap<>();
                candidate.put(new String(String.valueOf(distinctArray[inx])), String.valueOf(distinctArray[jnx]));
                candidates.add(candidate);
            }
        }

        Stream<Character> cStream = str.chars().mapToObj(c -> (char)c);

        int solution = -1;
        // 두개의 문자를 제외한 모든 문자 삭제
        for (Map<String, String> candidate : candidates) {

            String first = candidate.keySet().iterator().next();
            String second = candidate.get(first);

            List<Character> cList = cStream.filter(character -> String.valueOf(character).equals(first)
                                                                || String.valueOf(character).equals(second))
                                           .collect(Collectors.toList());
            String result = cList.toString();
            // check alternate

            int inx = 0;
            boolean isSuccess = true;
            if (result.startsWith(first)) {
                while (inx < result.length() && isSuccess) {
                    if (String.valueOf(result.charAt(inx)).equals(first)
                        && String.valueOf(result.charAt(inx + 1)).equals(second)) {
                        inx += inx + 2;
                    } else {
                        isSuccess = false;
                    }
                }
            } else {
                while (inx < result.length() && isSuccess) {
                    if (String.valueOf(result.charAt(inx)).equals(second)
                        && String.valueOf(result.charAt(inx + 1)).equals(first)) {
                        inx += inx + 2;
                    } else {
                        isSuccess = false;
                    }
                }
            }

            if (isSuccess && solution < result.length()) {
                solution = result.length();
            }
            isSuccess = true;
        }

        return solution;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = alternate(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
