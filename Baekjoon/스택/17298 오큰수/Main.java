// 스택을 2개 사용하면 된다.
// 1개는 일반 스택, 다른 하나는 아직 NGE를 찾지 못한 수를 담는 스택
// 일반 스택에서 계속 pop 하다가, 아직 NGE를 찾지 못한 수보다 큰 값이 나오면 그 때 다시 일반 스택에 push 하여 NGE를 찾아 주면 된다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static class Pos {
        int value, index;

        Pos(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N];
        int[] answer = new int[N];

        StringBuilder sb = new StringBuilder();

        Stack<Pos> stack = new Stack<>();
        Stack<Pos> stack2 = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) list[i] = Integer.parseInt(st.nextToken());

        for (int i = N - 1; i >= 0; i--) stack.push(new Pos(list[i], i));

        while (!stack.isEmpty()) {
            if (!stack2.isEmpty() && stack2.peek().value < stack.peek().value) {
                Pos tmp = stack2.pop();
                stack.push(tmp);
            }

            if (stack.size() == 1) {
                Pos last = stack.pop();
                answer[last.index] = -1;
                break;
            }

            Pos prev_top = stack.pop();

            if (prev_top.value < stack.peek().value) {
                Pos top = stack.peek();
                answer[prev_top.index] = top.value;
            } else stack2.push(prev_top);
        }

        for (Pos p : stack2) {
            answer[p.index] = -1;
        }

        for (int a : answer) sb.append(a).append(" ");

        System.out.println(sb);

    }
}
