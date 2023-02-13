// 거짓말
// https://www.acmicpc.net/problem/1043

// 그래프, 분리집합

// 유니온 파이드를 사용하지 않았지만, 어떻게 보면 진실 집합을 사용했다고 볼 수 있다.

// 1. 먼저, 진실을 아는 사람 큐를 만든다.
// 2. 큐에서 한 사람씩 뽑아서, 그 사람이 포함된 파티를 찾는다.
// 3. 그 파티에 참여한 사람들은 모두 진실 큐로 넣어준다.
// 4. 진실 파티의 개수를 1 증가시킨다.
// 5. 2-4 과정을 큐가 빌때까지 반복한다.
// 6. 정답은 (전체 파티 개수 - 진실 파티 개수)

package Study07

object Main {
    fun solution(N: Int, M: Int, truePeople: List<Int>, parties: List<List<Int>>): Int {
        val partyDone = Array(M) { false }

        val queue = ArrayDeque(truePeople)

        var cnt = 0

        while (queue.isNotEmpty()) {
            val personKnowsTruth = queue.removeFirst()

            for (idx in parties.indices) {
                if (partyDone[idx]) continue

                if (parties[idx].contains(personKnowsTruth)) {
                    for (person in parties[idx]) {
                        if (person != personKnowsTruth) queue.addLast(person)
                    }
                    partyDone[idx] = true
                    cnt++
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

    println(Main.solution(N, M, truePeople, parties))
}