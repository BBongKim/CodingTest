// 수학 문제

// DP를 이용하면 코드가 더 짧다.
// nCm , n+1Cm, n+2Cm, ... 을 dp 값으로 저장할 때
// dp[i + 1] = dp[i] * (i + 1) / ((i+1)-m)

import java.math.BigInteger

lateinit var dp: Array<BigInteger>

fun solution(N: Int, M: Int): BigInteger {

    for (i in M until N) {
        dp[i + 1] = dp[i] * (i + 1).toBigInteger() / (i + 1 - M).toBigInteger()
    }

    return dp[N]
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    dp = Array(N + 1) { BigInteger("0") }
    dp[M] = BigInteger("1")

    solution(N, M)

    println(dp[N])
}