// 위상정렬 문제
// 서로 순위가 바뀐 것은 인접 리스트에서 순서를 바꿔준다.
// 시작 할 때 큐에 값이 1개 이상 -> 답이 여러개 -> ? 출력
// 사이클 발생 -> 답 없음 -> IMPOSSIBLE 출력

import java.io.*;
import java.util.*;

public class Main {

    public static void solution(int N, int[] degree, ArrayList<ArrayList<Integer>> map) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 1; i < N + 1; i++) if (degree[i] == 0) queue.add(i);

        for (int i = 1; i < N + 1; i++) {
            if (queue.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }

            if (queue.size() > 1) {
                System.out.println("?");
                return;
            }

            int cur = queue.poll();
            sb.append(cur).append(" ");
            answer.add(cur);

            for (int m : map.get(cur)) {
                degree[m]--;
                if (degree[m] == 0) queue.offer(m);
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] ranking = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for (int k = 1; k < N + 1; k++) ranking[k] = Integer.parseInt(st.nextToken());

            int M = Integer.parseInt(br.readLine());

            ArrayList<ArrayList<Integer>> map = new ArrayList<>();
            int[] degree = new int[N + 1];
            for (int s = 0; s < N + 1; s++) map.add(new ArrayList<>());

            // 간선 초기화
            for (int p = 1; p < N + 1; p++) {
                for (int t = p + 1; t < N + 1; t++) {
                    map.get(ranking[p]).add(ranking[t]);
                    degree[ranking[t]]++;
                }
            }

            for (int p = 0; p < M; p++) {
                StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st2.nextToken());
                int b = Integer.parseInt(st2.nextToken());

                if (map.get(b).contains(a)) {
                    degree[a]--;
                    map.get(a).add(b);
                    degree[b]++;
                    map.get(b).remove((Integer) a);
                } else if (map.get(a).contains(b)) {
                    degree[b]--;
                    map.get(b).add(a);
                    degree[a]++;
                    map.get(a).remove((Integer) b);
                }
            }

            solution(N, degree, map);
        }
    }
}
