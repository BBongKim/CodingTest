// 좋다
// https://www.acmicpc.net/problem/1253

// 투 포인터

// 각 원소를 선택하고, 투 포인터를 이용하여 해당 원소를 덧셈으로 만들 수 있는지 확인하면 된다.
// 이 때, 선택된 원소를 지운 새로운 리스트를 사용하거나, 조건문을 사용하는 2가지 방법이 있다.

// 해시맵으로 풀때는, 자기 자신이 포함되지 않은 것을 따지는 것이 중요해보인다.

package Study05

object Main2 {

    var answer = 0

    fun solution(N: Int, list: List<Int>): Int {
        for (i in list.indices) {
            var start = 0
            var end = N - 1
            val target = list[i]

            while (start < end) {
                val sum = list[start] + list[end]

                if (sum < target) start++
                else if (sum > target) end--
                else {
                    if (start != i && end != i) {
                        answer++
                        break
                    }
                    else if (start == i) start++
                    else if (end == i) end--
                }
            }
        }

        return answer
    }
}

fun main() {
    val N = readln().toInt()
    val list = readln().split(" ").map { it.toInt() }

    println(Main2.solution(N, list.sorted()))
}