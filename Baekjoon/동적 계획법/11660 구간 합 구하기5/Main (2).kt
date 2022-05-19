// 다른 풀이로 풀어봤다. 근데, 생각보다 시간이 별로 차이가 안난다..
// 참고, https://chanhuiseok.github.io/posts/baek-19/
// 핵심은 (1, 1)부터 (x, y)의 합을 미리 구해놓은 DP 배열을 생성하는 것이다.
// DP 배열과 마지막 답을 구할 때 중복 처리를 조심해야한다.
// dp[y + 1][x + 1] = dp[y][x + 1] + dp[y + 1][x] - dp[y][x]
// answer = dp[y2][x2] - dp[y2][x1 - 1] - dp[y1 - 1][x2] + dp[y1 - 1][x1 - 1]

lateinit var map: Array<IntArray>
lateinit var dp: Array<IntArray>

fun solution(y1: Int, x1: Int, y2: Int, x2: Int): Int {
    if (x1 == x2 && y1 == y2) return map[y1 - 1][x1 - 1]
    return dp[y2][x2] - dp[y2][x1 - 1] - dp[y1 - 1][x2] + dp[y1 - 1][x1 - 1];
}

fun main() {
    var (N, M) = readln().split(" ").map { it.toInt() }
    map = Array(N) { IntArray(N) { 0 } }
    dp = Array(N + 1) { IntArray(N + 1) { 0 } }

    repeat(N) { i ->
        map[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    for (y in 0 until N) {
        for (x in 0 until N) {
            dp[y + 1][x + 1] = dp[y][x + 1] + dp[y + 1][x] - dp[y][x] + map[y][x]
        }
    }

    val sb = StringBuilder()

    repeat(M) {
        val (y1, x1, y2, x2) = readln().split(" ").map { it.toInt() }

        sb.append(solution(y1, x1, y2, x2)).append("\n")
    }

    print(sb.toString())
}