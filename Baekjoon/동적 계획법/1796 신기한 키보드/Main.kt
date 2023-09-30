// 신기한 키보드
// https://www.acmicpc.net/problem/1796

// DP

package Study20

import kotlin.math.abs

object Main2 {

    // dp[i][0] -> 왼쪽 끝에 있는 알파벳(a = 0, b = 1, c = 2, ..., z)에 도착한 경우 (오른쪽 끝에 있는 알파벳 도달 후, 왼쪽 끝으로 이동)
    // dp[i][1] -> 오른쪽 끝에 있는 알파벳(a = 0, b = 1, c = 2, ..., z)에 도착한 경우 (왼쪽 끝에 있는 알파벳 도달 후, 오른쪽 끝으로 이동)


    // dp[i][0] = minOf(dp[i - 1][0] + (오른쪽 알파벳까지 거리), dp[i - 1][1] + (오른쪽 알파벳까지 거리)) + dist
    // dp[i][0] = minOf(dp[i - 1][0] + (왼쪽 알파벳까지 거리), dp[i - 1][1] + (왼쪽 알파벳까지 거리)) + dist

    val leftMap = HashMap<Char, Int>()
    val rightMap = HashMap<Char, Int>()

    fun solution(S: String) {

        for (i in S.indices) {
            leftMap[S[i]] = minOf(i, leftMap.getOrDefault(S[i], Int.MAX_VALUE))
            rightMap[S[i]] = maxOf(i, rightMap.getOrDefault(S[i], 0))
        }

        val alphabet = leftMap.keys.sorted()

        val dp = Array(alphabet.size) { IntArray(2) { 0 } }

        val dist0 = rightMap[alphabet[0]]!! - leftMap[alphabet[0]]!!
        dp[0][0] = rightMap[alphabet[0]]!! + dist0
        dp[0][1] = leftMap[alphabet[0]]!! + dist0

        for (i in 1 until alphabet.size) {
            val prevLeft = leftMap[alphabet[i - 1]]!!
            val prevRight = rightMap[alphabet[i - 1]]!!
            val left = leftMap[alphabet[i]]!!
            val right = rightMap[alphabet[i]]!!
            val dist = right - left

            dp[i][0] = minOf(dp[i - 1][0] + abs(right - prevLeft), dp[i - 1][1] + abs(right - prevRight)) + dist
            dp[i][1] = minOf(dp[i - 1][0] + abs(left - prevLeft), dp[i - 1][1] + abs(left - prevRight)) + dist
        }

        println(dp.last().minOf { it } + S.length)
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val S = readLine()

    Main2.solution(S)
}