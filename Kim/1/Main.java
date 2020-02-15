import java.util.Scanner;

public class Main {

    static int[][] problems;
    static int[] array;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        array = new int[size];
        problems = new int[size][size];

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        // 1 true
        // 2 false
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    problems[i][i] = 1;
                } else if (i < size - 1 && j == i + 1) {
                    problems[i][j] = array[i] == array[j] ? 1 : 2;
                } else if (i < size - 2 && j == i + 2) {
                    problems[i][j] = array[i] == array[j] ? 1 : 2;
                }

                problems[i][j] = dp(i, j);
            }
        }



        int problemCount = scanner.nextInt();

        for (int i = 0; i < problemCount; i++) {
            if (problems[scanner.nextInt() - 1][scanner.nextInt() - 1] == 1) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }

        }

    }

    private static int dp(int start, int end) {

        if (end - 1 == start || end - 2 == start) {
            return problems[start][end];
        }

        if (problems[start][end] != 0) {
            return problems[start][end];
        }

        return array[start] == array[end] && dp(start + 1, end - 1) == 1 ? 1 : 2;

    }

}
