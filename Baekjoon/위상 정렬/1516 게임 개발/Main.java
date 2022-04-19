// DP + 위상정렬 문제

// 건물 짓는 순서대로 계산하기 위해서 위상정렬을 사용하고, 시간 계산을 위해 DP를 사용했다.
// 이전에 지었던 건물 중 가장 오래 걸리는 시간을 dp(prevTime)에 저장했다.

// ACM Craft 문제랑 비슷해서 풀때는 딱히 DP라고 생각은 안했는데, 시간 저장을 구현하는데 있어 자연스럽게 DP가 들어간 것 같다.

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Node>> list = new ArrayList<>();
    static int[] parent;
    static int[] buildTime;
    static int[] prevTime;
    static int[] answer;

    static class Node {
        int index, time = 0;

        Node(int i) {
            index = i;
        }

        Node(int i, int t) {
            index = i;
            time = t;
        }
    }

    public static void solution() {
        Queue<Node> queue = new LinkedList<>();

        for (int i = 1; i < N + 1; i++) {
            if (parent[i] == 0) queue.offer(new Node(i, buildTime[i]));
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            answer[cur.index] = cur.time;

            for (Node node : list.get(cur.index)) {
                parent[node.index]--;

                prevTime[node.index] = Math.max(prevTime[node.index], cur.time);

                if (parent[node.index] == 0) {
                    queue.offer(new Node(node.index, prevTime[node.index] + buildTime[node.index]));
                }
            }
        }

        for (int i = 1; i < N + 1; i++) System.out.println(answer[i]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        buildTime = new int[N + 1];
        prevTime = new int[N + 1];
        answer = new int[N + 1];

        for (int i = 0; i < N + 1; i++) list.add(new ArrayList<>());

        for (int a = 1; a < N + 1; a++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            buildTime[a] = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int b = Integer.parseInt(st.nextToken());

                if (b != -1) {
                    list.get(b).add(new Node(a));
                    parent[a]++;
                }
            }
        }

        solution();
    }
}
