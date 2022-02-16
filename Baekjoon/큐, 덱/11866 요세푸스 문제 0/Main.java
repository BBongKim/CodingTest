// K 자리전까지는 큐에서 뺐다가 다시 넣으면된다.
// 큐의 원소수가 K보다 작으면 (큐 크기 - 1)번째 수를 출력하면된다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void solution(int N, int K) {
        Queue<Integer> queue = new LinkedList<>();
        int[] ans = new int[N];
        int idx = 0;

        for (int i = 1; i < N + 1; i++) queue.offer(i);

        while (queue.size() >= K) {
            for (int cnt = 0; cnt < K - 1; cnt++) {
                int tmp = queue.poll();
                queue.offer(tmp);
            }
            ans[idx++] = queue.poll();
        }

        while (!queue.isEmpty()) {
            for (int cnt = 0; cnt < K - queue.size() - 1; cnt++) {
                int tmp = queue.poll();
                queue.offer(tmp);
            }
            ans[idx++] = queue.poll();
        }

        System.out.print("<");
        for(int i = 0; i < ans.length; i++) {
            if (i != ans.length - 1) System.out.print(ans[i] + ", ");
            else System.out.print(ans[i]);
        }
        System.out.println(">");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        solution(N, K);
    }
}
