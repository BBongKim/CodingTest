// Stack 문제

// 다음 규칙을 만족시키면된다.
// 1. 피연산자는 바로 출력
// 2. 연산자일 경우 스택 top 보다 우선순위가 높을 때만 push, 만약 낮거나 같으면 높아질 때까지 pop
// 3. '('는 스택에 바로 push
// 4. ')'는 '('가 나올 때까지 pop
// 5. 문자열이 끝났으면, 스택에 남아있는 값 모두 pop


import java.io.*;
import java.util.*;

public class Main {
    public static int getIndex(char c) {
        switch (c) {
            case '*':
            case '/':
                return 1;
            case '+':
            case '-':
                return 0;
        }
        //Error
        return -1;
    }

    public static void main(String[] args) throws IOException {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        for (char c : s.toCharArray()) {
            if ('A' <= c && c <= 'Z') {
                sb.append(c);
            } else if (c == '*' || c == '/' || c == '+' || c == '-') {
                while (!stack.isEmpty() && getIndex(c) <= getIndex(stack.peek())) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
