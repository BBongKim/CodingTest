import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    static class Node implements Comparable<Node> {
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

    public static int[] solution(int n, int m, int[][] graph) {
        int[] answer = new int[n];
        int[] distance = new int[n + 1];
        ArrayList<ArrayList<Node>> list = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(distance, Integer.MAX_VALUE);

        for(int[] gra : graph) {
            list.get(gra[0]).add(new Node(gra[1], gra[2]));
        }

        pq.offer(new Node(1, 0));
        distance[1] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distance[cur.index] < cur.dist) continue;

            for(Node c : list.get(cur.index)) {
                int tmp_dist = distance[cur.index] + c.dist;
                if(tmp_dist < distance[c.index]) {
                    distance[c.index] = tmp_dist;
                    pq.offer(new Node(c.index, tmp_dist));
                }
            }
        }

        int idx = 0;
        for (int i = 1; i < distance.length; i++) {
            answer[idx++] = distance[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 6;
        int m = 11;
        int[][] graph = {{1, 2, 2},{1, 3 ,5},{1, 4, 1},{2, 3, 3},{2, 4, 2},{3, 2, 3},{3, 6, 5},{4, 3, 3},{4, 5, 1},{5, 3, 1},{5, 6, 2}};
        for(int a : solution(n, m , graph)) {
            System.out.println(a);
        }
    }
}
