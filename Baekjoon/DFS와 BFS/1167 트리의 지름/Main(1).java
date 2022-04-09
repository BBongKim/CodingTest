// DFS 문제
// 근데, 내 맘대로 하다보니 다익스트라 응용해서 하니 시간초과

// 그래서, 최적화 방법을 찾아보니
// 1. 임의 x 노드에서 가장 멀리 있는 노드 a 구함
// 2. 노드 a에서 가장 멀리 있는 노드 b 까지의 길이가 답.

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

    public static int dijkstra(int n, boolean isX) {
        int[] distance = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        Arrays.fill(distance, -1);

        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.offer(new Node(n, 0));
        distance[n] = 0;
        visited[n] = true;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            visited[cur.index] = true;

            for (Node node : map.get(cur.index)) {
                if (visited[node.index]) continue;

                if (distance[node.index] >= node.dist) continue;

                int tmp = distance[cur.index] + node.dist;

                if (tmp > distance[node.index]) {
                    distance[node.index] = tmp;
                    queue.offer(new Node(node.index, tmp));
                }
            }
        }

        if (isX) {
            int idx = -1;
            int max = -1;
            for (int i = 1; i < V + 1; i++) {
                if (distance[i] > max) {
                    idx = i;
                    max = distance[i];
                }
            }
            return idx;
        } else {
            return Arrays.stream(distance).max().getAsInt();
        }
    }

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());

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

        int a = dijkstra(1, true);
        answer = dijkstra(a, false);

        System.out.println(answer);
    }
}
