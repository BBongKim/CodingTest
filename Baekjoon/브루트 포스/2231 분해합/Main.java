// 중복 순열 쓰면됨

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer = Integer.MAX_VALUE;
    static int[] list = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    static int[] output;

    public static int permutation(int N, int depth, int k) {
        if (depth == k) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k; i++) {
                sb.append(output[i]);
            }

            int tmp = Integer.parseInt(sb.toString());
            int tmp_sum = 0;
            for (char c : sb.toString().toCharArray()) {
                tmp_sum += c - '0';
            }

            int result = tmp + tmp_sum;

            if (result == N) answer = Math.min(answer, tmp);
        } else {
            for (int i = 0; i < list.length; i++) {
                output[depth] = list[i];
                permutation(N, depth + 1, k);
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num = br.readLine();
        StringTokenizer st = new StringTokenizer(num, " ");
        int N = Integer.parseInt(st.nextToken());

        output = new int[num.length()];
        permutation(N, 0, num.length());

        if (answer == Integer.MAX_VALUE) answer = 0;
        System.out.println(answer);
    }
}
