// DP 문제
// X=1일 때 바로 위, 오른쪽 위
// X=2일 때 위에 3개 다
// X=3일 때 바로 위, 오른쪽 위 와 현재 map값을 합친 후, 최소 최댓값만 dp 배열에 삽입하여 계산하면 된다.
// 시간 복잡도는 O(3N) 일 듯 하다.

lateinit var map: Array<IntArray>
lateinit var dpMax: Array<IntArray>
lateinit var dpMin: Array<IntArray>
var N = 0
var min = Int.MAX_VALUE
var max = -1

fun solution() {
    for (y in 1..N) {
        for (x in 1..3) {
            when (x) {
                1 -> {
                    dpMin[y][1] = minOf(dpMin[y - 1][1] + map[y - 1][0], dpMin[y - 1][2] + map[y - 1][0])
                    dpMax[y][1] = maxOf(dpMax[y - 1][1] + map[y - 1][0], dpMax[y - 1][2] + map[y - 1][0])
                }
                2 -> {
                    val tmpMin = minOf(dpMin[y - 1][2] + map[y - 1][1], dpMin[y - 1][3] + map[y - 1][1])
                    val tmpMax = maxOf(dpMax[y - 1][2] + map[y - 1][1], dpMax[y - 1][3] + map[y - 1][1])
                    dpMin[y][2] = minOf(tmpMin, dpMin[y - 1][1] + map[y - 1][1])
                    dpMax[y][2] = maxOf(tmpMax, dpMax[y - 1][1] + map[y - 1][1])
                }
                3 -> {
                    dpMin[y][3] = minOf(dpMin[y - 1][3] + map[y - 1][2], dpMin[y - 1][2] + map[y - 1][2])
                    dpMax[y][3] = maxOf(dpMax[y - 1][3] + map[y - 1][2], dpMax[y - 1][2] + map[y - 1][2])
                }
            }
        }
    }

    for (x in 1..3) {
        if (min > dpMin[N][x]) {
            min = dpMin[N][x]
        }

        if (max < dpMax[N][x]) {
            max = dpMax[N][x]
        }
    }

    print("$max $min")
}

fun main() {
    N = readln().toInt()
    map = Array(N) { IntArray(3) { 0 } }
    dpMax = Array(N + 1) { IntArray(4) { 0 } }
    dpMin = Array(N + 1) { IntArray(4) { 0 } }

    repeat(N) { idx ->
        map[idx] = readln().split(" ").map { it.toInt() }.toIntArray()
    }

    solution()
}
