// 사전순 수열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] list;
    static int[] result;
    static boolean[] visited;

    public static void permutation(int N, int M, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) System.out.print(result[i] + " ");
            System.out.println();
        }
         else {
             for (int i = 0; i < N; i++) {
                 if (!visited[i]) {
                     visited[i] = true;
                     result[depth] = list[i];
                     permutation(N, M, depth + 1);
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
        result = new int[M];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) list[i] = i + 1;

        permutation(N, M, 0);
    }
}
