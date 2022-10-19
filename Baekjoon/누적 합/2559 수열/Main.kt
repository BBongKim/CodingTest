// 수열
// https://www.acmicpc.net/problem/2559

// X부터 Y까지의 부분합
// SUM[Y] - SUM[X-1]
// 음수가 답인 경우 조심

fun solution(N: Int, K: Int, list: List<Int>): Int {
    var max = Int.MIN_VALUE
    val sumList = MutableList(N) { 0 }
    sumList[0] = list[0]

    for (idx in 1 until N) {
        sumList[idx] = sumList[idx - 1] + list[idx]
    }

    max = maxOf(max, sumList[K - 1])

    for (idx in 1 .. N - K) {
        max = maxOf(max, sumList[idx + K - 1] - sumList[idx - 1])
    }

    return max
}

fun main() {
    val(N, K) = readln().split(" ").map { it.toInt() }
    val list = readln().split(" ").map { it.toInt() }

    val ans = solution(N, K, list)

    println(ans)
}