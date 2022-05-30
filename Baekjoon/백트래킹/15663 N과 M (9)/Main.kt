// 백트래킹 문제
// 똑같은 수열을 중복 체크하는 법은
// 이전에 넣었던 수였는지만 체크하면된다. (첫번째 수는 제외)

lateinit var map: List<Int>
lateinit var visited: BooleanArray
lateinit var result: IntArray
val sb = StringBuilder()

fun permutation(N: Int, M: Int, depth: Int) {
    if (depth == M) {
        for (i in 0 until M) {
            sb.append(result[i]).append(" ")
        }
        sb.append("\n")
        return
    }

    // 첫번째 수는 상관 없음
    var prev = 0

    for (i in 0 until N) {
        // 이전에 넣었던 수만 중복 체크를 하면 된다.
        if (prev == map[i] || visited[i]) continue

        visited[i] = true
        prev = map[i]
        result[depth] = map[i]
        permutation(N, M, depth + 1)
        visited[i] = false
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    map = readln().split(" ").map { it.toInt() }
    result = IntArray(N) { 0 }
    visited = BooleanArray(N) { false }
    map = map.sorted()

    permutation(N, M, 0)

    print(sb.toString())
}