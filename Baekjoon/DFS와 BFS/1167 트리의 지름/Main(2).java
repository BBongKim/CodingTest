// DFS로 풀어봄
// 다익스트라처럼 했을때 보다 약간 빠르긴 하다.

import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int index, dist;

        Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o1) {
            return o1.dist - this.dist;
        }
    }

    static int V;
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();
    static boolean[] visited;
    static int distance = 0;
    static int idx;
    static int max = -1;

    public static void dfs(int n) {
        boolean isEnd = true;

        for (Node node : map.get(n)) {
            if (visited[node.index]) continue;

            isEnd = false;
            visited[node.index] = true;
            distance += node.dist;
            dfs(node.index);
            distance -= node.dist;
            visited[node.index] = false;
        }

        if (isEnd) {
            if (distance > max) {
                max = distance;
                idx = n;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        visited = new boolean[V + 1];

        for (int i = 0; i < V + 1; i++) map.add(new ArrayList<>());

        for (int i = 1; i < V + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int b = Integer.parseInt(st.nextToken());

                if (b == -1) break;

                int c = Integer.parseInt(st.nextToken());

                map.get(a).add(new Node(b, c));
            }
        }

        visited[1] = true;
        dfs(1);

        // 다시 초기화
        visited[1] = false;
        visited[idx] = true;
        distance = 0;
        max = -1;
        dfs(idx);

        System.out.println(max);
    }
}
