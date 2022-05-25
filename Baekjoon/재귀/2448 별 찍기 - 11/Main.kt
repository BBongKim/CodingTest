// 재귀 문제
// 예전에 비슷한 걸 풀었는데도 기억이 안나서 하다가 그냥 풀이 봤다...
// 위, 좌하단, 우하단 순으로 삼각형을 출력하면된다.
// 이 때, 높이가 3이 될 때까지 재귀적으로 위치를 나눈다.

var N = 0
lateinit var map: Array<CharArray>

fun star(height: Int, topX: Int, topY: Int) {
    if (height == 3) {
        map[topY][topX] = '*'
        map[topY + 1][topX - 1] = '*'
        map[topY + 1][topX + 1] = '*'
        for (x in topX - 2..topX + 2) map[topY + 2][x] = '*'
        return
    }

    // 상단
    star(height / 2, topX, topY)
    // 좌하단
    star(height / 2, topX - height / 2, topY + height / 2)
    // 우하단
    star(height / 2, topX + height / 2, topY + height / 2)
}

fun main() {
    N = readln().toInt()
    map = Array(N) { CharArray(2 * N) { ' ' } }

    star(N, N - 1, 0)

    val sb = StringBuilder()

    for (y in map.indices) {
        for (x in map[0].indices) {
            sb.append(map[y][x])
        }
        sb.append("\n")
    }

    print(sb.toString())
}