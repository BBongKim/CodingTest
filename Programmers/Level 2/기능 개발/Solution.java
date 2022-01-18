import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            double p = progresses[i];
            double s = speeds[i];
            double remain = Math.ceil((100-p)/s);
            queue.add((int)remain);
        }

        while(!queue.isEmpty()) {
            int cnt = 0;
            int n = queue.peek();

            for(int i = 0; i < queue.size(); i++) {
                queue.set(i, queue.get(i) - n);
            }

            for(int i = 0; !queue.isEmpty() && queue.get(i) <= 0;) {
                queue.poll();
                cnt++;
            }
            ans.add(cnt);
        }

        answer = new int[ans.size()];
        int idx = 0;
        for(int a : ans) {
            answer[idx++] = a;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] pro = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 1, 1, 1, 1, 1};
        solution(pro, speeds);
    }
}