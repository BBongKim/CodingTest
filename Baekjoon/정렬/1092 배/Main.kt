// 배
// https://www.acmicpc.net/problem/1092

// 정렬
// 크레인과 박스를 내림 차순으로 정렬하여, 힘이 쎈 크레인이 무게가 무거운 박스부터 처리하면 된다.

fun solution(N: Int, cranes: List<Int>, M: Int, boxes: List<Int>): Int {
    val sortedCranes = cranes.sortedDescending()
    val sortedBoxes = boxes.sortedDescending().toMutableList()

    var time = 0

    if (sortedCranes.first() < sortedBoxes.first()) return -1

    while (sortedBoxes.isNotEmpty()) {
        for (crane in sortedCranes) {
            for (box in sortedBoxes) {
                if (crane >= box) {
                    sortedBoxes.remove(box)
                    break
                }
            }
        }
        time++
    }

    return time
}

fun main() {
    val N = readln().toInt()
    val cranes = readln().split(" ").map { it.toInt() }
    val M = readln().toInt()
    val boxes = readln().split(" ").map { it.toInt() }

    println(solution(N, cranes, M, boxes))
}