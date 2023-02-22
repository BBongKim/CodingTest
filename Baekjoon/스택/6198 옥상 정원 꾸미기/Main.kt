// 옥상 정원 꾸미기
// https://www.acmicpc.net/problem/6198

// 스택

// 처음에는, 이진 탐색인줄 알고 upper bound를 이용해서
// 건물의 높이로 정렬하고, 가장 인덱스가 낮은 건물을 찾아보려고 했음.
// 근데, 높이와 인덱스 모두를 고려해야 하다보니 이진 탐색의 좌우 구분 조건이 잘 떨어지지 않았음

// 찾아보니, Monotonic Stack을 이용하면,
// upper bound처럼 x보다 작거나 큰 값 중 가장 먼저 등장하는 값을 찾을 수 있다고 알게됨.

package Study08

import java.util.*

data class Building(val height: Int, val idx: Int)

object Main4 {
    fun solution(N: Int, buildings: List<Building>): Long {
        val stack = Stack<Building>()
        var cnt = 0L

        for (building in buildings.reversed()) {
            while (stack.isNotEmpty() && stack.peek().height < building.height) stack.pop()

            cnt += if (stack.isNotEmpty()) stack.peek().idx - building.idx - 1
            else N  - (building.idx + 1)

            stack.push(building)
        }

        return cnt
    }
}

fun main() {
    val N = readln().toInt()
    val buildings = mutableListOf<Building>()

    repeat(N) {
        buildings.add(Building(readln().toInt(), it))
    }

    println(Main4.solution(N, buildings))
}