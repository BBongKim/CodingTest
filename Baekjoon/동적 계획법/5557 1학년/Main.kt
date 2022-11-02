// 1학년
// https://www.acmicpc.net/problem/5557

// DP

// O(2^n) -> O(20N)으로 해결 가능
// dp[i][j]의 저장 값 = 리스트의 i번째 수까지 계산하여 나올 수 있는 j 값의 경우의 수

// 리스트의 0번째 수는 무조건 필요하니까 우선 넣고
// i = 1, j = 0부터 dp[i - 1][j]값에서 계산된 값에 리스트의 i번 째 수를 더하고, 뺀 값을 계산
// 이때, 계산된 값 x가 (0 <= x <= 20)인 경우에만 경우의 수를 증가시킨다.

fun solution(N: Int, list: List<Int>): Long {
    val dp = Array(N - 1) { Array(21) { 0L } }

    dp[0][list.first()] = 1

    for (i in 1..N - 2) {
        for (j in 0..20) {
            if (dp[i - 1][j] == 0L) continue // 이전에 나올 수 없는 값이면 넘어감

            if (j + list[i] <= 20) dp[i][j + list[i]] += dp[i - 1][j]
            if (j - list[i] >= 0) dp[i][j - list[i]] += dp[i - 1][j]
        }
    }

    return dp[N - 2][list.last()]
}

fun main() {
    val N = readln().toInt()
    val list = readln().split(" ").map { it.toInt() }

    println(solution(N, list))
}