// 까먹을까봐 다시 연습

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static int find(int a) {
        if (parent[a] != a) parent[a] = find(parent[a]);
        return parent[a];
    }

    public static void union(int a, int b) {
        int parent_a = find(a);
        int parent_b = find(b);

        if (parent_a > parent_b) {
            parent[parent_a] = parent_b;
        } else {
            parent[parent_b] = parent_a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 0; i < n + 1; i++) parent[i] = i;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) union(a, b);
            else if(cmd == 1) {
                if (find(a) == find(b)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
}
