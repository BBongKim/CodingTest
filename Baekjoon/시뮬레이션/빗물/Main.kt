// 빗물
// https://www.acmicpc.net/problem/14719

// 각 열마다 빗물이 얼마나 고이는지 계산한다.
// 빗물량 = 좌우 벽중 낮은 벽의 높이 - 현재 블럭의 높이

fun solution(H: Int, W: Int, heights: List<Int>): Int {
    var ans = 0

    for (idx in heights.indices) {
        var left = 0
        var right = 0

        // 왼쪽 벽
        for (leftIdx in idx - 1 downTo 0) {
            left = maxOf(left, heights[leftIdx])
        }

        // 오른쪽 벽
        for (rightIdx in idx + 1 until W) {
            right = maxOf(right, heights[rightIdx])
        }

        val water = minOf(left, right) - heights[idx]

        if (water > 0) ans += water
    }

    return ans
}

fun main() {
    val (H, W) = readln().split(" ").map { it.toInt() }
    val heights = readln().split(" ").map { it.toInt() }

    val ans = solution(H, W, heights)
    print(ans)
}

// 3 1 2 1 2