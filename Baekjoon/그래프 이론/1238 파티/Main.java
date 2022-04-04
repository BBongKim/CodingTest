// 다익스트라 문제
// 가는데 비용 + 오는데 비용이 최대값이 답이 된다.
// 이 때, X에서 다시 집으로 돌아가는건 한 번만 구해도 된다.

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();
    static int[] distance_x;

    static class Node implements Comparable<Node> {
        int index, dist;

        Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o1) {
            return this.dist - o1.dist;
        }
    }

    public static int solution() {
        dijkstra(X, X);

        int answer = -1;

        for (int i = 1; i <= N; i++) {
            if (i == X) continue;

            int cost = dijkstra(i, X) + distance_x[i];

            answer = Math.max(answer, cost);
        }

        return answer;
    }

    public static int dijkstra(int n, int x) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[] distance = new int[N + 1];
        Arrays.fill(distance, (int) 1e9);

        queue.offer(new Node(n, 0));

        distance[n] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (distance[cur.index] < cur.dist) continue;

            for (Node node : map.get(cur.index)) {
                int tmp = distance[cur.index] + node.dist;

                if (tmp < distance[node.index]) {
                    distance[node.index] = tmp;
                    queue.offer(new Node(node.index, tmp));
                }
            }
        }

        if (n == X) distance_x = distance;

        return distance[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) map.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map.get(a).add(new Node(b, c));
        }

        System.out.println(solution());
    }
}
