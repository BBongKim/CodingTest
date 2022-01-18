import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static int solution(String s) {
        int answer = -1;
        StringBuilder string = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();

        for(int i = 0; i < s.length(); i++) queue.add(s.charAt(i));

        while(!queue.isEmpty()) {
            string.append(queue.poll());
            if(string.length() > 1 && string.charAt(string.length() - 1) == string.charAt(string.length() - 2)) string.delete(string.length() - 2, string.length());
        }

        return string.length() == 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        System.out.println(solution("baabaa"));
    }
}