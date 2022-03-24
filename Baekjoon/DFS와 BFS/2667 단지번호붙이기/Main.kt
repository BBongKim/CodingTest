// 그냥 DFS, BFS 문제
// 코틀린으로 BFS 해보고 싶어서 풀어봄

import java.io.*;
import java.util.*;

data class Pos(var y: Int, var x: Int)

val x_move = arrayOf(1, 0, -1, 0)
val y_move = arrayOf(0, -1, 0, 1)
val list = ArrayList<Int>()
var total_cnt = 0

fun solution(map: Array<Array<Int>>, N: Int) {
    val queue: Queue<Pos> = LinkedList()
    val visited = Array(N) { Array(N) { false } }

    for (y in 0 until N) {
        for (x in 0 until N) {
            if (map[y][x] == 0 || visited[y][x]) continue;

            var cnt = 0
            queue.offer(Pos(y, x))
            visited[y][x] = true
            total_cnt++

            while (!queue.isEmpty()) {
                var cur = queue.poll()
                cnt++

                for (i in 0 until 4) {
                    val next = Pos(cur.y + y_move[i], cur.x + x_move[i])

                    if (0 > next.x || next.x >= N || 0 > next.y || next.y >= N || visited[next.y][next.x]) continue

                    if (map[next.y][next.x] == 1) {
                        visited[next.y][next.x] = true
                        queue.offer(next)
                    }
                }
            }
            list.add(cnt)
        }
    }
}

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val map = Array(N) { Array(N) { 0 } }

    for (i in 0 until N) {
        val s = br.readLine()

        for (k in 0 until N) {
            map[i][k] = Character.getNumericValue(s[k])
        }
    }

    solution(map, N)

    println(total_cnt)
    list.sort()
    for (a in list) {
        println(a)
    }
}