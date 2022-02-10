import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;
    static int M;
    static int[] list;

    public static void permutation(int N, int depth, int k) {
        if (depth == k) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += list[i];
            }
            if (sum > answer && sum <= M) answer = sum;
        } else {
            for (int i = depth; i < N; i++) {
                swap(depth, i);
                permutation(N, depth + 1, k);
                swap(depth, i);
            }
        }
    }

    public static void swap(int a, int b) {
        int tmp = list[a];
        list[a] = list[b];
        list[b] = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new int[N];

        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < N; i++) list[i] = Integer.parseInt(st2.nextToken());

        permutation(N, 0, 3);

        System.out.println(answer);
    }

}
