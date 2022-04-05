// 위상 정렬 문제 + 약간의 DP
// 테스트 케이스는 다 풀었는데, 자꾸 틀려서 다른 풀이 참고

// 결론은, 다음 노드의 시간을 edge 를 끊을 때마다 Max 값으로 설정하면 된다.

import java.io.*;
import java.util.*;

public class Main {

    static int[] cost;
    static int[] child;
    static int[] time;
    static ArrayList<ArrayList<Node>> map = new ArrayList<>();

    static class Node {
        int index;

        Node(int index) {
            this.index = index;
        }
    }

    public static int solution(int N, int W) {
        Queue<Node> queue = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {

            if (child[i] == 0) {
                if (i == W) return cost[i];

                queue.offer(new Node(i));
                time[i] = cost[i];
            }
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.index == W) break;

            for (Node n : map.get(cur.index)) {
                child[n.index]--;

                time[n.index] = Math.max(time[n.index], time[cur.index] + cost[n.index]);

                if (child[n.index] == 0) queue.offer(new Node(n.index));
            }
        }

        return time[W];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            cost = new int[N + 1];
            child = new int[N + 1];
            time = new int[N + 1];
            map = new ArrayList<>();

            for (int i = 0; i < N + 1; i++) map.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i < N + 1; i++) cost[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map.get(a).add(new Node(b));
                child[b]++;
            }

            int W = Integer.parseInt(br.readLine());

            System.out.println(solution(N, W));
        }
    }
}
