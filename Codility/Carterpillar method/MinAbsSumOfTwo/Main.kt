// 투 포인터 문제
// 자꾸, 랜덤 테케에서 틀려서 걍 풀이봄
// 처음에 정렬로 해서 양쪽 값만 더하는 걸 생각하긴 했는데, 정렬을 절댓값 기준으로 했어야 했다.

// 투포인터로 풀고 싶으면
// 양쪽에서 시작해서, 절댓값이 큰쪽이 한칸 움직이는 방식으로 구하면 된다.
// 양쪽이 같아졌을 때 반복문 종료 및 마지막 값의 2배도 고려해야됨.

import kotlin.math.abs

fun solution(A: IntArray): Int {
    val list = A.sortedBy { abs(it) }

    var min = abs(list[0] + list[0])

    for (i in 0 until list.size - 1) {
        min = minOf(min, abs(list[i] + list[i + 1]))
    }

    return min
}

fun main() {
    val A = intArrayOf(-10, -8, 0)
    println(solution(A))
}
