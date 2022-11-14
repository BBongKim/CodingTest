// 선수 과목
// https://www.acmicpc.net/problem/14567

// 위상정렬
// 선수 과목 순서대로 접근하며, 다음 과목의 선수 과목 수의 최댓값을 계산하면 된다.

fun solution(N: Int, map: Array<MutableList<Int>>, degree: MutableList<Int>): List<Int> {
    val ans = MutableList(N + 1) { 1 }
    val queue = ArrayDeque<Int>()

    // queue 초기화
    for (i in 1..N) {
        if (degree[i] == 0) queue.addLast(i)
    }

    while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()

        for (next in map[cur]) {
            ans[next] = maxOf(ans[next], ans[cur] + 1)  // 선수 과목 최댓값
            degree[next]--
            if (degree[next] == 0) queue.addLast(next)
        }
    }

    return ans.drop(1)
}


fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }

    val map = Array(N + 1) { mutableListOf<Int>() }
    val degree = MutableList(N + 1) { 0 }

    repeat(M) {
        val (start, end) = readln().split(" ").map { it.toInt() }
        map[start].add(end)
        degree[end]++
    }

    for (i in solution(N, map, degree)) print("$i ")
}