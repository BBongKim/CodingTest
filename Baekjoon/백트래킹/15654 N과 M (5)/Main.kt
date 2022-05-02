// 백트래킹
// 먼저 정렬하고, 순열을 뽑으면 된다.

lateinit var list: List<Int>
lateinit var visited: Array<Boolean>
lateinit var tmp: Array<Int>

fun permutation(N: Int, M: Int, depth: Int) {
    if (depth == M) {
        for (i in 0 until M) {
            print(tmp[i].toString() + " ")
        }
        println()
        return
    }

    for (i in 0 until N) {
        if (!visited[i]) {
            visited[i] = true
            tmp[depth] = list[i]
            permutation(N, M, depth + 1)
            visited[i] = false
        }
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    list = readln().split(" ").map { it.toInt() }.sorted()
    visited = Array(N) { false }
    tmp = Array(N) { 0 }

    permutation(N, M, 0)
}