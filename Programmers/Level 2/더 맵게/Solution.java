import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        boolean no_ans = false;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int s : scoville) {
            pq.add(s);
        }

        while(pq.size() > 1) {
            int min, min2;
            min = pq.poll();
            min2 = pq.poll();

            int new_food = min + (min2 * 2);

            pq.add(new_food);
            answer++;

            if(pq.peek() >= K) {
                no_ans = false;
                break;
            }
            else no_ans = true;
        }

        if(no_ans) return -1;
        else return answer;
    }

    public static void main(String[] args) {
        int[] s = {1, 2, 3, 9, 10, 12};

        System.out.println(solution(s, 7));
    }
}