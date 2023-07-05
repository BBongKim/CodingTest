// 뮤탈리스크
// https://www.acmicpc.net/problem/12869

// DP

// top-down 형식의 DP

// dp[x][y][z] -> 체력이 x, y, z인 scv를 없애는 데 필요한 최소 뮤탈 공격

// 뮤탈 공격 경우의 수는 6가지이므로, 초기 체력부터 6가지의 경우로 탐색한다.
// x,y,z가 0이 되면 탐색 종료.
// 6가지 경우 중, 최소 횟수가 dp[x][y][z]의 값으로 결정된다.

object Main {
    private val hp = Array(3) { 0 }
    private val dp = Array(61) { Array(61) { IntArray(61) { -1 } } }

    fun dfs(x: Int, y: Int, z: Int): Int {
        if (x < 0) return dfs(0, y, z)
        if (y < 0) return dfs(x, 0, z)
        if (z < 0) return dfs(x, y, 0)

        if (x == 0 && y == 0 && z == 0) return 0

        if (dp[x][y][z] != -1) return dp[x][y][z]

        var min = Int.MAX_VALUE
        min = minOf(min, dfs(x - 9, y - 3, z - 1) + 1)
        min = minOf(min, dfs(x - 9, y - 1, z - 3) + 1)
        min = minOf(min, dfs(x - 3, y - 9, z - 1) + 1)
        min = minOf(min, dfs(x - 3, y - 1, z - 9) + 1)
        min = minOf(min, dfs(x - 1, y - 9, z - 3) + 1)
        min = minOf(min, dfs(x - 1, y - 3, z - 9) + 1)

        dp[x][y][z] = min

        return min
    }

    fun solution(N: Int, list: List<Int>): Int {
        for (i in list.indices) hp[i] = list[i]
        return dfs(hp[0], hp[1], hp[2])
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val list = readLine().split(" ").map { it.toInt() }.toList()

    println(Main.solution(N, list.sortedDescending()))
}

