// 팰린드롬?
// https://www.acmicpc.net/problem/10942

// DP

// dp[s][e] -> s부터 e까지 팰린드롬인지 저장

// 1. dp[i][i]은 모두 팰린드롬이다.
// 2. list[i] == list[i + 1] 이면, 팰린드롬이다.
// 3. dp[s][e]가 팰린드롬이려면, 그 사이인 dp[s + 1][e - 1]가 팰린드롬이고, list[s] == list[e]이면 된다.

// 출력이 많아서 그런지, StringBuilder를 사용해야 시간초과가 안뜬다.

package Study16

lateinit var dp: Array<IntArray>

object Main {
    fun solution(list: IntArray, N: Int) {
        for (i in 0 until N) {
            dp[i][i] = 1

            if (i < N - 1 && list[i] == list[i + 1]) dp[i][i + 1] = 1
        }

        for (e in 2 until N) {
            for (s in 0 until e) {
                if (dp[s + 1][e - 1] == 1 && list[s] == list[e]) dp[s][e] = 1
            }
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val list = readLine().split(" ").map { it.toInt() }.toIntArray()
    val M = readLine().toInt()

    dp = Array(N + 1) { IntArray(N + 1) { 0 } }

    Main.solution(list, N)

    val sb = StringBuilder()

    repeat(M) {
        val (S, E) = readLine().split(" ").map { it.toInt() }
        sb.append("${dp[S - 1][E - 1]}\n")
    }

    print(sb.toString())
}