// 이분 그래프는 인접한 모든 정점이 자신과 다른 색이어야 한다.

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> map;
    static int[] color;         // RED: 1 , BLUE: -1, not visited: 0

    public static boolean bfs(int n) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(n);
        color[n] = 1;

        while(!queue.isEmpty()) {
            int cur_node = queue.poll();

            for (int node : map.get(cur_node)) {
                if (color[node] == 0) {
                    queue.offer(node);
                    color[node] = color[cur_node] * -1;
                } else if (color[cur_node] == color[node]) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            map = new ArrayList<>();
            color = new int[V + 1];
            boolean isBi = true;

            for (int i = 0; i < V + 1; i++) map.add(new ArrayList<>());

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                map.get(a).add(b);
                map.get(b).add(a);
            }

            for (int i = 1; i <= V; i++) {
                if (color[i] != 0) continue;

                isBi = bfs(i);

                if (!isBi) break;
            }

            if (isBi) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
