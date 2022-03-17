// Deque 문제
// 쉽게 생각했다가, 결국 접근법을 보았다.
// 처음에 ArrayList로 정직하게 구현하면 무조건 시간초과가 난다.
// 따라서, Deque를 이용하여 D를 하기 전에 R이 짝수개로 나온 경우는 앞에서 poll()을 하고,
// 홀수개로 나온 경우는 뒤에서 poll()을 한다.

// 또한, 마지막 출력시 R이 짝수개인 경우는 그대로 출력, 홀수개인 경우는 lastPoll()을 통해 거꾸로 리스트를 출력해야한다.


import java.io.*;
import java.util.*;

public class Main {

    public static void solution(Deque<Integer> list, String cmd) {
        boolean RR = true;
        StringBuilder sb = new StringBuilder();

        for (char c : cmd.toCharArray()) {
            if (c == 'R') {
                RR = !RR;
            } else if (c == 'D') {
                if (list.size() == 0) {
                    System.out.println("error");
                    return;
                } else {
                    if (RR) list.removeFirst();
                    else list.removeLast();
                }
            }
        }

        sb.append("[");
        int size = list.size();
        int idx = 0;
        if (RR) {
            while(!list.isEmpty()) {
                if (idx != size - 1) sb.append(list.pollFirst()).append(",");
                else sb.append(list.pollFirst());
                idx++;
            }
        } else {
            while(!list.isEmpty()) {
                if (idx != size - 1) sb.append(list.pollLast()).append(",");
                else sb.append(list.pollLast());
                idx++;
            }
        }
        sb.append("]");

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String cmd = br.readLine();
            int N = Integer.parseInt(br.readLine());
            Deque<Integer> list = new ArrayDeque<>();
            String arr = br.readLine();
            StringTokenizer st = new StringTokenizer(arr, "[,]");

            for (int k = 0; k < N; k++) list.add(Integer.parseInt(st.nextToken()));

            solution(list, cmd);
        }
    }
}
