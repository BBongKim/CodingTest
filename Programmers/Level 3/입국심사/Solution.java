import java.util.Arrays;

/* 혼자 못풀었다.*/

class Solution {
    public static long solution(int n, int[] times) {
        long answer = 0;

        long max_time = (long) Arrays.stream(times).max().getAsInt() * n;

        long left = 0;
        long right = max_time;
        long mid = 0;
        long people_num = 0;

        while (left <= right) {
            people_num = 0;
            mid = (left + right) / 2;

            for(int t : times) {
                people_num += mid / t;
                if(people_num >= n) break;
            }

            if(people_num >= n) {
                answer = mid;
                right = mid - 1;
            }
            else if (people_num < n) left = mid + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 7;
        int[] times = {7, 9, 12};

        System.out.println(solution(n, times));
    }
}