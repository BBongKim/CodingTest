// DP 문제
// 풀이봄 ㅠ

// N이 3일 때를 생각해보면, 나올 수 있는 경우의 수는 총 4가지
// 2 X (지그재그로 끝까지의 경우 + 한칸 뛰고 반대칸을 선택하는 경우)
// 따라서, DP 점화식은 위 둘 중 큰 값으로 설정하면된다.

import java.io.*
import java.util.*

lateinit var map: Array<Array<Int>>
lateinit var dp: Array<Array<Int>>

fun solution(N: Int): Int {
    if (N == 1) return maxOf(map[0][0],(map[1][0]))

    dp[0][0] = map[0][0]
    dp[1][0] = map[1][0]
    dp[0][1] = map[1][0] + map[0][1]
    dp[1][1] = map[0][0] + map[1][1]

    for (i in 2 until N) {
        dp[0][i] = map[0][i] + maxOf(dp[1][i - 1],dp[1][i - 2])
        dp[1][i] = map[1][i] + maxOf(dp[0][i - 1], dp[0][i - 2])
    }

    return maxOf(dp[0][N - 1], (dp[1][N - 1]))
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var T = br.readLine().toInt()

    repeat(T) {
        var N = br.readLine().toInt()

        map = Array(2) { Array(N) { 0 } }
        dp = Array(2) { Array(N) { 0 } }

        for (i in 0..1) {
            val st = StringTokenizer(br.readLine(), " ")
            for (k in 0 until N) {
                map[i][k] = st.nextToken().toInt()
            }
        }
        println(solution(N))
    }
}