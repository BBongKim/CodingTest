// 퇴사 2
// https://www.acmicpc.net/problem/15486

// DP

// dp[i + consult.duration] = maxOf(dp[i + consult.duration], 이전 최대값 + consult.profit) 이된다.
// 앞의 값을 먼저 갱신하는 DP도 있음을 유의해야겠다.

package Study14

data class Consult(val duration: Int, val profit: Int)

object Main {
    fun solution(N: Int, consults: List<Consult>): Int {
        val dp = Array(N + 2) { 0 }
        val list = mutableListOf<Consult>()
        var max = 0
        list.add(Consult(0, 0))
        list.addAll(consults)

        for (i in 1..N) {
            max = maxOf(max, dp[i])

            if (list[i].duration + i > N + 1) continue
            dp[i + list[i].duration] = maxOf(dp[i + list[i].duration], max + list[i].profit)
        }

        return dp.maxOf { it }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val list = List(N) {
        val (d, p) = readLine().split(" ").map { it.toInt() }
        Consult(d, p)
    }

    println(Main.solution(N, list))
}
