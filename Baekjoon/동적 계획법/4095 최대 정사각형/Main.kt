// 최대 정사각형
// https://www.acmicpc.net/problem/4095

// DP

// 핵심은 큰 정사각형을 만들기 위해서는 결국 2X2의 정사각형이 필요하다는 것이다.
// dp[y][x] = minOf(dp[y][x - 1], dp[y - 1][x], dp[y - 1][x - 1]) + 1
// 현재 dp 값은 각 2x2 크기의 정사각형의 오른쪽 모서리를 기준으로, 3개의 dp 값 중 가장 작은 값에 1을 더한 값이 된다.
// 이때, 자기 자신이 0인 경우는 0으로 설정한다.

// 처음엔 브루트포스를 시도했다.
// 아이디어가 잘 안떠올라서 결국 풀이봄

package Study07

object Main4 {

    fun solution(N: Int, M: Int, map: Array<IntArray>): Int {
        val dp = map.copyOf()

        for (y in 1 until N) {
            for (x in 1 until M) {
                if (dp[y][x] == 0) dp[y][x] = 0
                else dp[y][x] = minOf(dp[y][x - 1], dp[y - 1][x], dp[y - 1][x - 1]) + 1
            }
        }

        return dp.maxOf { it.max() }
    }
}

fun main() {
    while (true) {
        val (N, M) = readln().split(" ").map { it.toInt() }
        if (N == 0 && M == 0) break

        val map = Array(N) { IntArray(M) { 0 } }

        repeat(N) { row ->
            val col = readln().split(" ").map { it.toInt() }.toIntArray()
            map[row] = col
        }

        println(Main4.solution(N, M, map))
    }
}