// 줄세우기
// https://www.acmicpc.net/problem/2631

// DP

// Longest Increasing Subsequence 구하는 문제이다.

// O(N^2) 풀이

package Study09

object Main5 {
    fun solution(N: Int, list: IntArray): Int {
        val dp = IntArray(N) { 1 }

        for (i in list.indices) {
            for (j in 0 until i) {
                if (list[j] < list[i]) dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }

        return N - dp.max()
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val list = IntArray(N) { readLine().toInt() }

    println(Main5.solution(N, list))
}