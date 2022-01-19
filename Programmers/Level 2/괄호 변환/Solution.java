import java.util.Stack;

class Solution {
    public static String solution(String p) {
        String answer = "";
        boolean correct = true;

        correct = isValid(p);

        // 올바르게 수정
        if (!correct) {
            answer = transformBracket(p);
        } else {
            answer = p;
        }

        return answer;
    }

    public static String transformBracket(String p) {
        // 1
        if (p.length() == 0) return "";

        // 2
        String[] uv = new String[2];

        String last = "";

        splitBracket(p, uv);

        // 3
        if (isValid(uv[0])) {
            String tmp = transformBracket(uv[1]);
            if (tmp.equals("")) return uv[0];
            else last = tmp;
        } else { //4
            String tmp = "(";
            String v = transformBracket(uv[1]);
            tmp += (v + ")");
            String tmp2 = uv[0].substring(1, uv[0].length() - 1);

            for(char c : tmp2.toCharArray()) {
                if(c == '(') tmp += ')';
                else tmp += '(';
            }

            return tmp;
        }

        return uv[0] + last;
    }

    public static void splitBracket(String p, String[] uv) {
        char[] cs = p.toCharArray();
        int left = 0;
        int right = 0;
        int idx = 0;

        for(char c : cs) {
            if (c == '(') left++;
            else right++;
            idx++;

            if(left == right) break;
        }

        uv[0] = p.substring(0, idx);
        uv[1] = p.substring(idx);
    }

    public static boolean isValid(String p) {
        // 올바른 괄호 문자열 확인
        Stack<Character> stack = new Stack<>();
        char[] s = p.toCharArray();
        try {
            for (char c : s) {
                if (c == '(') stack.push(c);
                else if (c == ')') stack.pop();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "()))((()";
        solution(s);
    }
}