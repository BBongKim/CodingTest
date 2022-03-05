// 다익스트라 까먹을까봐 다시 품

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
        public int compareTo(Node o) {
            return this.dist - o.dist > 0 ? 1 : -1;
        }
    }

    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static int[] distance;

    public static void solution(int V, int E, int s) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.add(new Node(s, 0));
        distance[s] = 0;

        while(!queue.isEmpty()) {
            Node cur = queue.poll();

            if (distance[cur.index] < cur.dist) continue;

            for (Node n : list.get(cur.index)) {
                int tmp_dist = distance[cur.index] + n.dist;

                if (tmp_dist < distance[n.index]) {
                    distance[n.index] = tmp_dist;
                    queue.offer(new Node(n.index, tmp_dist));
                }
            }
        }

        for (int i = 1; i < V + 1; i++) {
            if (distance[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(distance[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(br.readLine());

        distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i < V + 1; i++) list.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(end, w));
        }

        solution(V, E, s);
    }
}
