// 중복 순열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] list;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void permutation(int N, int M, int depth) {
        if (depth == M) {
            for (int r : result) sb.append(r).append(" ");
            sb.append("\n");
        } else {
            for (int i = 0; i < N; i++) {
                result[depth] = list[i];
                permutation(N, M, depth + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new int[N];

        for (int i = 0; i < N; i++) list[i] = i + 1;

        result = new int[M];

        permutation(N, M, 0);
        System.out.println(sb);
    }
}
