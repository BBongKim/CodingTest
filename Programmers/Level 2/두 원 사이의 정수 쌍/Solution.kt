// 두 원 사이의 정수 쌍

// 수학, Ad hoc(?)

// 간단하게 원 그래프 공식과 (x, y) 를 2중 반복문을 사용하면 무조건 시간초과다.
// 너무 수학 스멜이...ㅠㅠ 그래서 풀이봄...

// 접근은 1 사분면 점 개수 X 4를 하는 것임.

// 1 사분면 점 개수 구하기

// 1. x -> (0 .. r2)일때, 각 r2 그래프의 정수 점 y 값 구함
// 2-1. x가 r1보타 크면, y까지 세로 형태의 점은 다 두 원 사이임
// 2-2. 아니면, r1 원 그래프의 정수 점 y2를 구해서, y - y2 개가 됨.
// 2-3. 근데, 만약 y2가 r1 딱 위에 있는 점이다? 그럼 1개 빼줘야해서, y - y2 + 1 개임.

import kotlin.math.*

class Solution {
    fun solution(r1: Int, r2: Int): Long {
        var answer: Long = 0
        
        for (x in 0 .. r2) {
            val y = sqrt(r2.toDouble().pow(2) - x.toDouble().pow(2)).toInt()
            
            if (r1 <= x) answer += y * 4
            else {
                val y2 = sqrt(r1.toDouble().pow(2) - x.toDouble().pow(2)).toInt()
                
                if (y2.toDouble().pow(2) + x.toDouble().pow(2) == r1.toDouble().pow(2)) {
                    answer += (y - y2 + 1) * 4
                } else {
                     answer += (y - y2) * 4
                }
            }
        }
        return answer
    }
}