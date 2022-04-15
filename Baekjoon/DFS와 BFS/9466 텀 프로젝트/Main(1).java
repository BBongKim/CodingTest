// DFS 문제라는데, 일단 DFS를 쓰긴 썼다.
// 내 풀이는 유니온 파인드를 통해 사이클이 발생하는 경우를 확실히 찾았을 때만, DFS로 탐색하여 팀원수를 세주었다.
// 유니온파인드 시간복잡도가 O(logN) ~ O(N) 이라서 가능했던거 같다.
// DFS 탐색도 O(N)이니 커봐야 O(2N) ~= O(N) 정도

import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static int find(int a) {
        if (a != parent[a]) parent[a] = find(parent[a]);
        return parent[a];
    }

    public static void union(int a, int b) {
        int p_a = find(a);
        int p_b = find(b);

        if (p_a < p_b) {
            parent[p_b] = p_a;
        } else if (p_a > p_b) {
            parent[p_a] = p_b;
        }
    }

    public static int solution(int[] list, int N) {
        int team = 0;

        for (int i = 1; i < N + 1; i++) {

            if (i == list[i]) {
                team++;
                continue;
            }

            int a = find(i);
            int b = find(list[i]);

            if (a != b) {
                union(i, list[i]);
            } else {
                int tmp = list[i];
                team++;
                while (tmp != i) {
                    team++;
                    tmp = list[tmp];
                }
            }
        }

        return N - team;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int[] list = new int[N + 1];
            parent = new int[N + 1];

            for (int i = 1; i < N + 1; i++) {
                list[i] = Integer.parseInt(st.nextToken());
                parent[i] = i;
            }

            System.out.println(solution(list, N));
        }
    }
}
