import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    static class Edge implements Comparable<Edge> {
        int n1, n2, cost;

        Edge(int n1, int n2, int cost) {
            this.n1 = n1;
            this.n2 = n2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int[] parent;

    public static int find(int n) {
        if (parent[n] != n) parent[n] = find(parent[n]);

        return parent[n];
    }

    public static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 < p2) parent[p2] = p1;
        else parent[p1] = p2;
    }

    public static int solution(int m, int n, int[][] maps) {
        int answer = 0;
        parent = new int[m + 1];

        for (int i = 0; i < parent.length; i++) parent[i] = i;

        ArrayList<Edge> edges = new ArrayList<>();

        for (int[] map : maps) {
            edges.add(new Edge(map[0], map[1], map[2]));
        }

        Collections.sort(edges);

        for(Edge e : edges) {
            if (find(e.n1) != find(e.n2)) {
                union(e.n1, e.n2);
                answer += e.cost;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int m = 7;
        int n = 9;
        int[][] map = {{1, 2, 29}, {1, 5, 75}, {2, 3, 35}, {2, 6, 34}, {3, 4, 7}, {4, 6, 23},
                {4, 7, 13}, {5, 6, 53}, {6, 7, 25}};

        System.out.println(solution(m, n, map));
    }
}
