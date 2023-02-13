// 유니온-파인드를 사용해봤다.

package Study07

object Main2 {
    lateinit var parent: Array<Int>

    private fun find(x: Int): Int {
        if (parent[x] != x) parent[x] = find(parent[x])
        return parent[x]
    }

    // a <- b 로 연결
    private fun union(a: Int, b: Int) {
        val parentA = find(a)
        val parentB = find(b)

        parent[parentB] = parentA
    }

    fun solution(N: Int, M: Int, truePeople: List<Int>, parties: List<List<Int>>): Int {
        parent = Array(N + 1) { it }

        val partyDone = Array(M) { false }

        val queue = ArrayDeque(truePeople)
        var cnt = 0

        while (queue.isNotEmpty()) {
            val truePerson = queue.removeFirst()

            for (idx in parties.indices) {
                if (partyDone[idx]) continue

                if (parties[idx].contains(truePerson)) {
                    parties[idx].forEach {
                        union(truePerson, it)
                        partyDone[idx] = true
                        if (it != truePerson) queue.addLast(it)
                    }
                }
            }
        }

        for (party in parties) {
            for (person in party) {
                if (truePeople.contains(parent[person])) {
                    cnt++
                    break
                }
            }
        }

        return M - cnt
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    val truePeople = readln().split(" ").drop(1).map { it.toInt() }
    val parties = List(M) { mutableListOf<Int>() }

    repeat(M) { idx ->
        val people = readln().split(" ").drop(1).map { it.toInt() }
        parties[idx].addAll(people)
    }

    println(Main2.solution(N, M, truePeople, parties))
}