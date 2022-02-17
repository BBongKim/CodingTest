// 중앙에서 왼쪽에 가까우면 왼쪽으로, 오른쪽에 가까우면 오른쪽으로 돌리면 된다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int solution(int N, int[] arr) {
        int answer = 0;

        Deque<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= N; i++) deque.offer(i);

        for (int a : arr) {
            int center = deque.size() / 2;
            int idx = 0;
            for (int d : deque) {
                if (d == a) break;
                idx++;
            }

            while (deque.peek() != a) {
                if (deque.size() % 2 == 0) {
                    if (idx >= center) {
                        int tmp = deque.pollLast();
                        deque.offerFirst(tmp);
                        answer++;
                    } else {
                        int tmp = deque.pollFirst();
                        deque.offerLast(tmp);
                        answer++;
                    }
                } else {
                    if (idx > center) {
                        int tmp = deque.pollLast();
                        deque.offerFirst(tmp);
                        answer++;
                    } else {
                        int tmp = deque.pollFirst();
                        deque.offerLast(tmp);
                        answer++;
                    }
                }
            }
            deque.poll();
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) arr[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, arr));

    }
}
