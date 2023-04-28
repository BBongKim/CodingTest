// 빛의 경로 사이클
// https://school.programmers.co.kr/learn/courses/30/lessons/86052

// DFS

// 처음에 BFS로 하니까, 시간초과가 났음.
// 이런 사이클 찾을때는 DFS가 더 나은 것 같다.

// [풀이]
// DFS를 돌며, visited[y][x][dir]을 통해 방문한 곳을 또 방문하면 사이클인지 판단할 수 있다.

// 방향 전환의 경우, 아래와 같이 해주면 된다.
// 'L' -> (dir + 3) % 4
// 'R' -> (dir + 1) % 4

// 그리고, 재귀 DFS를 사용하니 테케 7번이 런타임 에러가 뜬다.
// 반복문으로 바꾸니 통과되었다.

class Solution {
    val moveX = intArrayOf(1, 0, -1, 0)
    val moveY = intArrayOf(0, 1, 0, -1)

    var N = 0
    var M = 0

    val answer = mutableListOf<Int>()
    lateinit var visited: Array<Array<BooleanArray>>
    lateinit var map: Array<CharArray>

    fun convert(dir: Int, y: Int, x: Int): Int {
        return when(map[y][x]) {
            'L' -> (dir + 3) % 4
            'R' -> (dir + 1) % 4
            else -> dir
        }
    }

    fun simulate(dir: Int, y: Int, x: Int) {
        var curDir = dir
        var curY = y
        var curX = x
        var cnt = 0

        while(!visited[curY][curX][curDir]) {
            visited[curY][curX][curDir] = true

            val nextDir = convert(curDir, curY, curX)
            var nextY = curY + moveY[nextDir]
            var nextX = curX + moveX[nextDir]

            if (nextY < 0) nextY = N - 1
            if (nextY >= N) nextY = 0
            if (nextX < 0) nextX = M - 1
            if (nextX >= M) nextX = 0

            curY = nextY
            curX = nextX
            curDir = nextDir
            cnt++
        }

        answer.add(cnt)
    }

    fun solution(grid: Array<String>): List<Int> {
        map = Array(grid.size) { grid[it].toCharArray() }

        N = grid.size
        M = grid[0].length
        visited = Array(N) { Array(M) { BooleanArray(4) { false } } }

        for (y in 0 until N) {
            for (x in 0 until M) {
                for (i in 0 until 4) {
                    if (visited[y][x][i]) continue
                    simulate(i, y, x)
                }
            }
        }

        return answer.sortedBy { it }
    }
}