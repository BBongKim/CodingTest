// 최소 스패닝 트리 문제
// 그냥 구현하면 됨

import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int a, b, c;

        Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }

    static ArrayList<Edge> list = new ArrayList<>();
    static int[] parent;

    public static int find(int a) {
        if (parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    public static void union(int a, int b) {
        int parent_a = find(a);
        int parent_b = find(b);

        if (parent_a < parent_b) parent[parent_b] = parent_a;
        else if (parent_b < parent_a) parent[parent_a] = parent_b;
    }

    public static void main(String[] args) throws IOException {
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) parent[i] = i;

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new Edge(a, b, c));
        }

        Collections.sort(list);

        for (Edge e : list) {
            if (find(e.a) != find(e.b)) {
                union(e.a, e.b);
                answer += e.c;
            }
        }

        System.out.println(answer);
    }
}
