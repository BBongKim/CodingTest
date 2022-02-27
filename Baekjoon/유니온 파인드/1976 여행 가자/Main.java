// 유니온 파인드 문제
// 여행 경로의 부모가 모두 같은지를 확인하면 된다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] parent;
    static int[] plan;

    public static int find(int a) {
        if (parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa > pb) {
            parent[pa] = pb;
        } else parent[pb] = pa;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        parent = new int[N + 1];
        plan = new int[M];

        for (int i = 0; i < N + 1; i++) parent[i] = i;

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int k = 1; k < N + 1; k++) {
                if(Integer.parseInt(st.nextToken()) == 1) union(i, k);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) plan[i] = Integer.parseInt(st.nextToken());

        int tmp = find(plan[0]);
        boolean possible = true;
        for (int p : plan) {
            if (tmp != find(p)) possible = false;
        }

        if (possible) System.out.println("YES");
        else System.out.println("NO");
    }
}
