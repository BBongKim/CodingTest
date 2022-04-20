// 그래서, 다익스트라로 풀어봤다.
// 실행속도가 반으로 줄어듦
// 재채점 실패 떠서 수정했다.
// 경로가 없는 경우 -1 출력이 잘 되도록 수정했다.

import java.io.*;
import java.util.*;

public class Main {
    static int N, E;
    static int node1, node2;
    static int answer;
    static int[] distance;
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();
    static int node1_N = -1;

    static class Node implements Comparable<Node> {
        int index, cost;

        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o1) {
            return this.cost - o1.cost;
        }
    }

    public static int dijkstra(int s, int e) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.offer(new Node(s, 0));
        distance[s] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (distance[cur.index] < cur.cost) continue;

            for (Node n : map.get(cur.index)) {
                int tmp = distance[cur.index] + n.cost;

                if (tmp < distance[n.index]) {
                    distance[n.index] = tmp;
                    queue.offer(new Node(n.index, tmp));
                }
            }
        }

        int r = distance[e];
        if (s == node1 && distance[N] != (int)1e9 ) node1_N = distance[N];

        Arrays.fill(distance, (int) 1e9);
        return r == (int) 1e9 ? -1 : r;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        distance = new int[N + 1];

        Arrays.fill(distance, (int) 1e9);

        for (int i = 0; i < N + 1; i++) map.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map.get(a).add(new Node(b, c));
            map.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine(), " ");
        node1 = Integer.parseInt(st.nextToken());
        node2 = Integer.parseInt(st.nextToken());

        int tmp1 = (int)1e9 , tmp2 = (int)1e9;

        int a = dijkstra(1, node1);
        int b = dijkstra(node1, node2);
        int c = dijkstra(node2, N);

        if (a >= 0 && b >= 0 && c >= 0) tmp1 = a + b + c;

        a = dijkstra(1, node2);
        b = dijkstra(node2, node1);
        c = node1_N;

        if (a >= 0 && b >= 0 && c >= 0) tmp2 = a + b + c;

        answer = Math.min(tmp1, tmp2);

        System.out.println(answer == (int)1e9 ? -1 : answer);
    }
}
