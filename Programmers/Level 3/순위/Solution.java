// 다른 분 접근법 참고했음
// 플로이드 워셜과 비슷하지만 최단 경로 아님
// 순위가 정해진다는 것이 본인 제외한 모든 선수들과의 기록이 있다는 것임을 파악하는 것이 중요한 문제다.

class Solution {
    public static int solution(int n, int[][] results) {
        int answer = 0;

        int[][] maps = new int[n + 1][n + 1];

        for (int[] result : results) {
            maps[result[0]][result[1]] = 1;
            maps[result[1]][result[0]] = -1;
        }

        // A를 이기면 A가 이긴 사람도 이긴다.
        for (int c = 0; c < n; c++) {
            for (int k = 1; k < maps.length; k++) {
                for (int q = 1; q < maps[k].length; q++) {
                    if(maps[k][q] == 1) {
                        for(int i = 1; i < maps[q].length; i++) {
                            if (maps[q][i] == 1) {
                                maps[k][i] = 1;
                                maps[i][k] = -1;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 1; i < maps.length; i++) {
            int cnt = 0;
            for (int k = 1; k < maps[i].length; k++) {
                if (maps[i][k] == 0) cnt++;
            }
            if (cnt == 1) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {{1, 4}, {4, 2}, {2, 5}, {5, 3}};

        System.out.println(solution(n, results));
    }
}