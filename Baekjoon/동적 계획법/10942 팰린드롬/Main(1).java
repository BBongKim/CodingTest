// 일단 그냥 풀었는데 DP 문제라고 한다.

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] list;

    public static int solution(int S, int E) {
        int gap = E - S;

        if (gap == 0) return 1;

        gap++;

        // 짝수개
        if (gap % 2 == 0) {
            int k = E;
            for (int i = S; i <= S + (E - S) / 2; i++) {
                int front = list[i];
                int back = list[k--];

                if (front != back) return 0;
            }
        } else {
            int k = E;
            for (int i = S; i < S + (E - S) / 2; i++) {
                int front = list[i];
                int back = list[k--];

                if (front != back) return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        list = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 1; i < N + 1; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            sb.append(solution(S, E)).append("\n");
        }

        System.out.print(sb);
    }
}
