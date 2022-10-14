// 나무 자르기
// https://www.acmicpc.net/problem/2805

// 이분 탐색
// 자르는 길이의 최댓값을 구하는것과 Long 타입 주의

fun solution(M: Long, trees: List<Long>): Long {
    var start = 1L
    var end = trees.maxOf { it }
    var max = 0L

    while (start <= end) {
        val mid = (start + end) / 2
        var sum = 0L

        for (tree in trees) {
            sum += if (tree - mid > 0) tree - mid else 0L
        }

        if (sum < M) {
            end = mid - 1
        } else {
            max = maxOf(max, mid)
            start = mid + 1
        }
    }

    return max
}

fun main() {
    val (_, M) = readln().split(" ").map { it.toLong() }
    val trees = readln().split(" ").map { it.toLong() }

    val ans = solution(M, trees)

    println(ans)
}