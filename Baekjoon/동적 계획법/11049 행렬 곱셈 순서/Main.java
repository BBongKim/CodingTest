// DP 문제
// 못풀어서 풀이봄
// 항상 DP 문제는 DP 배열의 값을 무엇으로 정의할지가 중요한듯

// 여기서는 DP[i][k]가 i부터 k번째 행렬까지 곱셈 연산 최솟값 의미

// 그리고 항상 2개로 나눌 수 있는 경우에서는 dp[s][s + gap]을 해서 (s, e)/ (e, s + gap)의 2개의 범위로 나누는 기법을 많이 사용하는 것 같다.

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] dp;

    static class Matrix {
        int r, c;

        Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void solution(ArrayList<Matrix> list) {
        for (int gap = 1; gap < N; gap++) {
            for (int s = 1; s + gap <= N; s++) {
                dp[s][s + gap] = (int) 1e9;
                for (int e = s; e < s + gap; e++) {
                    int sum = list.get(s).r * list.get(e).c * list.get(s + gap).c;
                    dp[s][s + gap] = Math.min(dp[s][s + gap], dp[s][e] + dp[e + 1][s + gap] + sum);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ArrayList<Matrix> list = new ArrayList<>();
        dp = new int[N + 1][N + 1];

        list.add(new Matrix(0, 0));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new Matrix(r, c));
        }

        solution(list);

        System.out.println(dp[1][N]);
    }
}
