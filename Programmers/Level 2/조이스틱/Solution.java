// 푸는데 좀 걸림
// 가장 가까운 A가 아닌 문자에 먼저 접근하는 방식
// 왼쪽으로 갔을 때와 오른쪽으로 갔을 때의 거리 중 가까운 거리 사용
// 문자열 길이가 최대 20밖에 안되서 단순하게 모든 위치에서 시작한 후 도출된 최소 결과를 답으로 제출했다.

class Solution {
    static boolean[] visited;
    static int answer;
    static int answer_tmp;

    public static int solution(String name) {
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < name.length(); i++) {
            answer_tmp = 0;
            visited = new boolean[name.length()];
            answer_tmp += Math.min(i, name.length() - (i + 1) + 1);

            int idx = moveNearest(name, i);

            while (idx != Integer.MAX_VALUE) {
                answer_tmp += Math.min(name.charAt(idx) - 'A', 'Z' - name.charAt(idx) + 1);

                idx = moveNearest(name, idx);
            }
            answer = Math.min(answer, answer_tmp);
        }
        return answer;
    }

    public static int moveNearest(String name, int n) {
        int idx = Integer.MAX_VALUE;
        int dist = Integer.MAX_VALUE;

        for (int i = 0; i < name.length(); i++) {
            if (!visited[i] && name.charAt(i) != 'A') {
                if (n < i) {
                    int tmp = Math.min(i - n, name.length() - i + n);
                    if (tmp < dist) {
                        dist = tmp;
                        idx = i;
                    }
                } else if (n > i) {
                    int tmp = Math.min(n - i, name.length() - n + i);
                    if (tmp < dist) {
                        dist = tmp;
                        idx = i;
                    }
                } else {
                    idx = i;
                    dist = 0;
                }
            }
        }

        if (idx != Integer.MAX_VALUE) {
            visited[idx] = true;
            answer_tmp += dist;
        }

        return idx;
    }

    public static void main(String[] args) {
        String name = "BAAAAABBB";
        System.out.println(solution(name));
    }
}