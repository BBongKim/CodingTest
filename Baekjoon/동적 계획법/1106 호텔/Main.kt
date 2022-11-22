// 호텔
// https://www.acmicpc.net/problem/1106
// DP

// dp[N] = 최소 N명 이상을 늘리기 위해 필요한 금액
// dp[n-1] > dp[n] 인 케이스를 고려해야해서, 최대로 가능한 사람수인 C * 100 값까지 계산 (배열 인덱스 고려해서 101 곱함)
// 근데 왜, C + 100도 되는거지....

data class Promotion(val cost: Int, val person: Int)

fun solution(C: Int, promotions: List<Promotion>): Int {
    val dp = Array(C * 101) { 10e8.toInt() }
    dp[0] = 0

    for (i in 1 until C * 101) {
        for (promo in promotions) {
            if (i - promo.person >= 0) dp[i] = minOf(dp[i - promo.person] + promo.cost, dp[i]) // 홍보 했을 때 vs 안했을 때
        }
    }

    return dp.toList().subList(C, C * 101).minOf { it }
}

fun main() {
    val (C, N) = readln().split(" ").map { it.toInt() }
    val promotions = mutableListOf<Promotion>()

    repeat(N) {
        val (cost, person) = readln().split(" ").map { it.toInt() }
        promotions.add(Promotion(cost, person))
    }

    println(solution(C, promotions))
}