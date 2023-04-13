// 퍼즐 조각 채우기
// https://school.programmers.co.kr/learn/courses/30/lessons/84021

// BFS

// 처음에는 블록을 구할 때, 블록의 이동방향을 통해서 구했다.
// 하지만, 반례에 블록 이동방향이 같아도 모양이 다른 경우가 존재한다.

// 그래서, 아래와 같이 풀었다.
// 1. 각 블록과 빈칸을 모두 구한다.
// 2. 각 블록이 각 빈칸에 들어갈 수 있는지 비교한다.

// 이 비교는 다음과 같이 진행된다.
// 2-1. 블록의 좌표모두 왼쪽 상단으로 이동시킨다.
// 2-2. 빈칸을 한 번 회전 시키고, 다시 왼쪽 상단으로 좌표를 맞춰준다.
// 2-3. 좌표를 정렬한다.
// 2-4. 블록과 빈칸의 좌표 리스트가 일치하는지 확인한다.
// 2-5. [2-2] ~ [2.4]를 4번 반복하고, 맞는게 없으면 안 맞는 자리이다.

// 2-2.
// 회전의 경우는 (y, x) -> (x, -y) 로 바꿔주면 된다.
// 왼쪽 상단 이동의 경우는 도형을 이루는 좌표에서 minY, minX 값을 빼주면 (0,0)을 기준으로 이동된다.

data class Pos(var y: Int, var x: Int)

object Solution {
    val moveX = intArrayOf(1, 0, -1, 0)
    val moveY = intArrayOf(0, 1, 0, -1)

    var N = 0
    lateinit var visitedBlock: Array<BooleanArray>
    lateinit var visitedHole: Array<BooleanArray>

    fun rotate(list: List<Pos>): List<Pos> {
        val tempList = mutableListOf<Pos>()
        for (p in list) {
            tempList.add(Pos(p.x, p.y * -1))
        }

        return tempList
    }

    fun movePos(list: List<Pos>): List<Pos> {
        val minY = list.minOf { it.y }
        val minX = list.minOf { it.x }
        val tempList = mutableListOf<Pos>()

        for (p in list) {
            tempList.add(Pos(p.y - minY, p.x - minX))
        }

        return tempList
    }

    fun match(h: List<Pos>, b: List<Pos>): Boolean {
        // 좌표를 왼쪽 모서리 끝으로 이동

        var hole = h
        var block = movePos(b).toSet()

        for (i in 0 until 4) {
            hole = rotate(hole)
            hole = movePos(hole)

            val sortedHole = hole.toSet()

            if (sortedHole == block) return true
        }

        return false
    }

    fun bfs(map: Array<IntArray>, visited: Array<BooleanArray>,
            startY: Int, startX: Int, flag: Int): List<Pos> {

        val positions = mutableListOf<Pos>()
        val queue = ArrayDeque<Pos>()

        visited[startY][startX] = true
        val start = Pos(startY, startX)
        positions.add(start)
        queue.add(start)

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()

            for (i in 0 until 4) {
                val nextY = cur.y + moveY[i]
                val nextX = cur.x + moveX[i]

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) continue
                if (visited[nextY][nextX]) continue
                if (map[nextY][nextX] == flag) continue

                visited[nextY][nextX] = true
                val next = Pos(nextY, nextX)
                positions.add(next)
                queue.addLast(next)
            }
        }

        return positions
    }

    fun getBlocks(table: Array<IntArray>): ArrayDeque<List<Pos>> {
        val blocks = ArrayDeque<List<Pos>>()

        for (y in 0 until N) {
            for (x in 0 until N) {
                if (table[y][x] == 0) continue
                if (visitedBlock[y][x]) continue

                val block = bfs(table, visitedBlock, y, x, 0)
                blocks.addLast(block)
            }
        }

        return blocks
    }

    fun getHoles(game_board: Array<IntArray>): ArrayDeque<List<Pos>> {
        val holes = ArrayDeque<List<Pos>>()

        for (y in 0 until N) {
            for (x in 0 until N) {
                if (game_board[y][x] == 1) continue
                if (visitedHole[y][x]) continue

                val hole = bfs(game_board, visitedHole, y, x, 1)
                holes.addLast(hole)
            }
        }

        return holes
    }

    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        N = game_board.size
        visitedBlock = Array(N) { BooleanArray(N) { false } }
        visitedHole = Array(N) { BooleanArray(N) { false } }

        var answer = 0

        // 1. 블럭 뽑기
        val blocks = getBlocks(table)

        // 2. 빈칸 뽑기
        val holes = getHoles(game_board)
        val checked = BooleanArray(holes.size) { false }

        while(blocks.isNotEmpty()) {
            val block = blocks.removeFirst()

            for (i in holes.indices) {
                if (checked[i]) continue

                if (match(holes[i], block)) {
                    answer += block.size
                    checked[i] = true
                    break
                }
            }
        }

        return answer
    }
}

fun main() {
    val gb = arrayOf(
        intArrayOf(1, 1, 0, 0, 1, 0),
        intArrayOf(0, 0, 1, 0, 1, 0),
        intArrayOf(0, 1, 1, 0, 0, 1),
        intArrayOf(1, 1, 0, 1, 1, 1),
        intArrayOf(1, 0, 0, 0, 1, 0),
        intArrayOf(0, 1, 1, 1, 0, 0)
    )

    val tb =
        arrayOf(
            intArrayOf(1, 0, 0, 1, 1, 0),
            intArrayOf(1, 0, 1, 0, 1, 0),
            intArrayOf(0, 1, 1, 0, 1, 1),
            intArrayOf(0, 0, 1, 0, 0, 0),
            intArrayOf(1, 1, 0, 1, 1, 0),
            intArrayOf(0, 1, 0, 0, 0, 0)
        )

    println(Solution.solution(gb, tb))
}