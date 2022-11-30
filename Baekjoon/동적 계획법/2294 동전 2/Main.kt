// 동전 2
// https://www.acmicpc.net/problem/2294
// DP

// dp[K] = K원을 만들기 위해 필요한 최소 동전 갯수
// Bottom Up 방식으로 1원부터 K원까지 구한다.
// 현재 동전을 넣었을 때의 갯수와 안넣었을 때의 갯수 중 최솟값을 dp[price] 값으로 설정한다.


fun solution(N: Int, K: Int, coins: List<Int>): Int {
    val dp = Array(K + 1) { 10e8.toInt() }
    dp[0] = 0

    for (price in 1..K) {
        for (coin in coins) {
            if (price - coin >= 0) dp[price] = minOf(dp[price], dp[price - coin] + 1)
        }
    }

    return if (dp[K] == 10e8.toInt()) -1 else dp[K]
}

fun main() {
    val (N, K) = readln().split(" ").map { it.toInt() }
    val coins = mutableListOf<Int>()
    repeat(N) {
        coins.add(readln().toInt())
    }

    println(solution(N, K, coins))
}