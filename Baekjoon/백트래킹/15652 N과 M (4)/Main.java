// 중복 조합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] list;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void combination(int N, int start, int cnt, int M) {
        if (cnt == M) {
            for (int r : result) sb.append(r).append(" ");
            sb.append("\n");
        } else {
            for (int i = start; i < N; i++) {
                result[cnt] = list[i];
                combination(N, i, cnt + 1, M);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new int[N];
        result = new int[M];

        for (int i = 0; i < N; i++) list[i] = i + 1;

        combination(N, 0, 0, M);
        System.out.println(sb);
    }
}
