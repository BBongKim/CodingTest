// 목장 건설하기
// https://www.acmicpc.net/problem/14925

// DP

// 몰랐는데 풀어보니까, 최대 정사각형이랑 풀이가 똑같다...

package Study09

object Main3 {
    fun solution(M: Int, N: Int, map: Array<IntArray>): Int {
        val dp = map.copyOf()

        for (y in 1 until M) {
            for (x in 1 until N) {
                if (dp[y][x] == 0) dp[y][x] = 0
                else dp[y][x] = minOf(dp[y - 1][x], dp[y - 1][x - 1], dp[y][x - 1]) + 1
            }
        }

        return dp.maxOf { it.max() }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (M, N) = readLine().split(" ").map { it.toInt() }
    val map = Array(M) { readLine().split(" ").map { it.toInt() }.toIntArray() }

    for (y in map.indices) {
        for (x in map[0].indices) {
            if (map[y][x] == 1 || map[y][x] == 2) map[y][x] = 0
            else map[y][x] = 1
        }
    }

    println(Main3.solution(M, N, map))
}