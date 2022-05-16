// 백트래킹 문제
// 그냥 중복 조합이다.

var result = ArrayList<Int>()
val sb = StringBuilder()

fun combi(list: List<Int>, M: Int, start: Int) {
    if (M == 0) {
        for (a in result) sb.append(a).append(" ")
        sb.append("\n")
        return
    }

    for (i in start until list.size) {
        result.add(list[i])
        combi(list, M - 1, i)
        result.remove(list[i])
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    var list = readln().split(" ").map { it.toInt() }

    list = list.sorted()

    combi(list, M, 0)
    print(sb.toString())
}