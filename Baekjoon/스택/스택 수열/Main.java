// NO가 되는 경우는 stack top이 현재 포인터보다 큰 경우이다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    public static void solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        ArrayList<String> answer = new ArrayList<>();

        int idx = 1;
        boolean no_ans = false;

        for (int a : arr) {
            if (stack.peek() > a) {
                no_ans = true;
                break;
            }
            while (stack.peek() != a) {
                stack.push(idx++);
                answer.add("+");
            }
            stack.pop();
            answer.add("-");
        }

        if(no_ans) System.out.println("NO");
        else {
            for(String s : answer) System.out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
           arr[i] = Integer.parseInt(br.readLine());
        }

        solution(arr);
    }
}
