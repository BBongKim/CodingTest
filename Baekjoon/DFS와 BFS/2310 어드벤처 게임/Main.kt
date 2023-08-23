// 어드벤처 게임
// https://www.acmicpc.net/problem/2310

// DFS

// L의 경우, 딱히 돈을 게속 늘려주는 것이 아니기 때문에
// 일반적인 DFS로 풀 수 있었다.

package Study19

data class Room(val type: String, val num: Int, val rooms: List<Int>)

object Main {
    lateinit var visited: BooleanArray
    var money = 0
    var hasAnswer = false

    fun solution(n: Int, rooms: List<Room>) {
        hasAnswer = false
        money = 0
        visited = BooleanArray(n) { false }

        visited[0] = true
        dfs(n, 0, rooms)

        if (hasAnswer) println("Yes")
        else println("No")
    }

    fun dfs(n: Int, idx: Int, rooms: List<Room>) {
        if (idx == n - 1) {
            hasAnswer = true
            return
        }

        for (nextRoomNumber in rooms[idx].rooms) {
            if (hasAnswer) break
            if (visited[nextRoomNumber]) continue

            val next = rooms[nextRoomNumber]

            if (next.type == "L") money = maxOf(next.num, money)
            if (next.type == "T" && money < next.num) continue
            if (next.type == "T") money -= next.num

            visited[nextRoomNumber] = true
            dfs(n, nextRoomNumber, rooms)
            visited[nextRoomNumber] = false
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {

    while (true) {
        var rooms = mutableListOf<Room>()

        val n = readLine().toInt()

        if (n == 0) break

        repeat(n) {
            val input = readLine().split(" ")
            val type = input[0]
            val num = input[1].toInt()
            val roomNums = input.subList(2, input.size).map { it.toInt() - 1 }.dropLast(1)

            rooms.add(Room(type, num, roomNums))
        }

        Main.solution(n, rooms)
    }
}