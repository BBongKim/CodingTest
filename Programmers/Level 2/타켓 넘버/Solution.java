import java.util.ArrayList;

class Solution {
    static int ans = 0;
    static int sum = 0;
    static int tar = 0;

    public static int solution(int[] numbers, int target) {
        int answer = 0;
        tar = target;
        boolean[] visited = new boolean[numbers.length];
        int n = numbers.length;

        for (int r = 1; r <= n; r++) {
            combination(numbers, visited, 0, n, r);
        }

        answer = ans;
        return answer;
    }

    public static void combination(int[] numbers, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    list.add(i);
                }
            }

            for (int i = 0; i < n; i++) {
                if(list.contains(i)) {
                    sum -= numbers[i];
                }
                else sum += numbers[i];
            }
            if (sum == tar) ans++;
            sum = 0;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(numbers, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        System.out.println(solution(numbers, 3));
    }
}