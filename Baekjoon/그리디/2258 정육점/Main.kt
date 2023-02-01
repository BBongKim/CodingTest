// 정육점
// https://www.acmicpc.net/problem/2258

// 그리디, 정렬

// 1. 가격 내림차순 -> 무게 오름차순으로 정렬한다.
// 2. 이전과 같은 가격이면 가격을 더해주고, 더 비싸다면 현재 고기 가격으로 설정한다.
// 3. 무게 조건을 만족할 때마다, 최솟값을 갱신해주어야한다.

// 아 다시 생각해보니까. 답이 MAX_VALUE일 수도 있어서 
// return if (answer == Int.MAX_VALUE) -1 일 경우, 오답이다. ㅠㅠ

package Study05

data class Meat(val weight: Int, val cost: Int)

object Main3 {
    fun solution(N: Int, M: Int, meats: List<Meat>): Int {
        var answer = Int.MAX_VALUE
        var totalCost = 0
        var totalWeight = 0
        var hasAnswer = false

        for (i in meats.indices) {
            totalWeight += meats[i].weight

            if (i > 0 && meats[i - 1].cost == meats[i].cost) {
                totalCost += meats[i].cost
            } else {
                totalCost = meats[i].cost
            }

            if (totalWeight >= M) {
                hasAnswer = true        // 왜 플래그 넣으니까 정답이지...?
                answer = minOf(answer, totalCost)
            }
        }

        return if (hasAnswer) answer else -1
    }
}

fun main() {
    val (N, M) = readln().split(" ").map { it.toInt() }
    val meats = mutableListOf<Meat>()

    repeat(N) {
        val (w, c) = readln().split(" ").map { it.toInt() }
        meats.add(Meat(w, c))
    }

    println(Main3.solution(N, M, meats.sortedWith(compareBy<Meat> { it.cost }.thenByDescending { it.weight })))
}
