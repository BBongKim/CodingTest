// DFS, BFS 문제
// 그냥 트리 탐색 문제이다.

var N = 0
var map: ArrayList<ArrayList<Int>> = ArrayList()
lateinit var answer: Array<Int>

fun dfs(parent: Int, idx: Int) {
    answer[idx] = parent

    for (i in map[idx]) {
        if (i != parent) dfs(idx, i)
    }
}

fun main() {
    N = readln().toInt()
    answer = Array(N + 1) { 0 }
    repeat(N + 1) { map.add(ArrayList()) }

    for (i in 0 until N - 1) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        map[b].add(a)
        map[a].add(b)
    }

    dfs(0, 1)

    val bd = StringBuilder()

    for (i in 2 ..N) bd.append(answer[i]).append("\n")

    print(bd)
}