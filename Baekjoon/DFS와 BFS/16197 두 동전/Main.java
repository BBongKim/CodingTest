// 두 동전
// https://www.acmicpc.net/problem/16197

// BFS

// 일반적인 BFS 풀이 접근법과 동일하지만, 움직이는 주체가 2개라는 점이 다르다.
// 그래서, 2개의 사물을 합친 하나의 상태 클래스를 이용해서 BFS 탐색을 진행했다.

// BFS 탐색 중, 아래 조건에 해당하는 경우는 큐에 삽입하지 않는다.
// 1. 2개의 코인이 모두 땅에 떨어진 경우
// 2. 이미 방문한 상태인 경우

// 그리고, 1개의 코인만 벽에 막히는 경우를 고려하여, 조건을 분리하여 큐에 삽입한다.
// 1. 코인1만 움직일 수 있을 때
// 2. 코인2만 움직일 수 있을 때
// 3. 두 코인 모두 움직일 수 있을 때

// 방문의 처리의 경우, HashMap을 사용하여 구현하였다.
// 자바로 하니까, 코틀린 data class의 편의성을 다시 깨닫는다 ㅠㅠ

package Study08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class State {
        int cnt = 0;
        Coin coin1;
        Coin coin2;

        State(Coin coin1, Coin coin2, int cnt) {
            this.coin1 = coin1;
            this.coin2 = coin2;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return Objects.equals(coin1, state.coin1) && Objects.equals(coin2, state.coin2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(coin1, coin2);
        }
    }

    public static class Coin {
        int y;
        int x;

        Coin(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coin coin = (Coin) o;
            return y == coin.y && x == coin.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    static int N;
    static int M;
    static int[] moveX = {1, 0, -1, 0};
    static int[] moveY = {0, -1, 0, 1};

    public static int solution(int n, int m, char[][] map) {
        N = n;
        M = m;

        ArrayDeque<State> queue = new ArrayDeque<>();
        ArrayList<Coin> startPoint = new ArrayList<>();
        HashMap<State, Boolean> visited = new HashMap<>();

        for (int col = 0; col < N; col++) {
            for (int row = 0; row < M; row++) {
                if (map[col][row] == 'o') startPoint.add(new Coin(col, row));
            }
        }

        State start = new State(startPoint.get(0), startPoint.get(1), 0);
        visited.put(start, true);
        queue.addLast(start);

        while (!queue.isEmpty()) {
            State cur = queue.removeFirst();

            boolean isCoin1Out = isOut(cur.coin1);
            boolean isCoin2Out = isOut(cur.coin2);

            if (cur.cnt > 10) return -1;
            if ((isCoin1Out && !isCoin2Out) || (!isCoin1Out && isCoin2Out)) return cur.cnt;

            for (int i = 0; i < 4; i++) {
                int coin1NextY = cur.coin1.y + moveY[i];
                int coin1NextX = cur.coin1.x + moveX[i];
                int coin2NextY = cur.coin2.y + moveY[i];
                int coin2NextX = cur.coin2.x + moveX[i];

                Coin nextCoin1 = new Coin(coin1NextY, coin1NextX);
                Coin nextCoin2 = new Coin(coin2NextY, coin2NextX);

                if (isOut(nextCoin1) && isOut(nextCoin2)) continue;

                boolean coin1Wall = isWall(map, nextCoin1);
                boolean coin2Wall = isWall(map, nextCoin2);
                State nextState;

                if (coin1Wall && coin2Wall) continue;
                else if (coin1Wall) {
                    nextState = new State(cur.coin1, nextCoin2, cur.cnt + 1);
                    if (visited.getOrDefault(nextState, false)) continue;

                    visited.put(nextState, true);
                    queue.addLast(nextState);
                    continue;
                } else if (coin2Wall) {
                    nextState = new State(nextCoin1, cur.coin2, cur.cnt + 1);
                    if (visited.getOrDefault(nextState, false)) continue;

                    visited.put(nextState, true);
                    queue.addLast(nextState);
                    continue;
                }

                nextState = new State(nextCoin1, nextCoin2, cur.cnt + 1);
                if (visited.getOrDefault(nextState, false)) continue;

                visited.put(nextState, true);
                queue.addLast(nextState);
            }

        }

        return -1;
    }

    public static boolean isOut(Coin coin) {
        return coin.y < 0 || coin.y >= N || coin.x < 0 || coin.x >= M;
    }

    public static boolean isWall(char[][] map, Coin coin) {
        if (isOut(coin)) return false;
        return map[coin.y][coin.x] == '#';
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];

        for (int col = 0; col < N; col++) map[col] = br.readLine().toCharArray();

        System.out.println(solution(N, M, map));
    }
}
