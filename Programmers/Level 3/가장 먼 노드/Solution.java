import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    static class Node implements Comparable<Node>{
        int index, dist;

        Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist < o.dist ? -1 : 1;
        }
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int[] e : edge) {
            list.get(e[0]).add(new Node(e[1], 1));
            list.get(e[1]).add(new Node(e[0], 1));
        }

        distance[0] = -1;
        distance[1] = 0;
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distance[cur.index] < cur.dist) continue;

            for(Node next : list.get(cur.index)) {
                int tmp_dist = cur.dist + next.dist;
                if(tmp_dist < distance[next.index]) {
                    distance[next.index] = tmp_dist;
                    pq.offer(new Node(next.index, tmp_dist));
                }
            }
        }

        int max = Arrays.stream(distance).max().getAsInt();

        for(int i = 2; i < n + 1; i++) {
            if(distance[i] == max) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n, edge));
    }
}