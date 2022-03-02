// 사전순 조합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] list;
    static boolean[] visited;

    public static void combination(int N, int start, int M) {
        if (M == 0) {
            for (int i = 0; i < N; i++) if (visited[i]) System.out.print(list[i] + " ");
            System.out.println();
        } else {
            for (int i = start; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    combination(N, i + 1, M - 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) list[i] = i + 1;

        combination(N, 0, M);
    }
}
