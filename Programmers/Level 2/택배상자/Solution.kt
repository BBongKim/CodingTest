// 택배상자

// https://school.programmers.co.kr/learn/courses/30/lessons/131704

// 구현, 스택

// 아래 3가지를 계속 반복함.
// 1. 같으면 order와 택배 상자의 인덱스를 모두 증가 및 answer++
// 2. order와 스택 top 값 같으면, order 인덱스만 증가 및 answer++
// 3. stack에 택배 상자 넣기

// 4. 반복 종료 후, stack에 남아있는 값 order와 계속 비교해보기.

import java.util.*

class Solution {
    fun solution(order: IntArray): Int {
        var answer: Int = 0
        val N = order.size
    
        var cur = 0
        var box = 1
        
        val stack = Stack<Int>()
       
        while (box <= N && cur < N) {
            if (box == order[cur]) {
                answer++
                box++
                cur++  
                continue
            } 
            
            if (stack.isNotEmpty() && stack.peek() == order[cur]) {
                stack.pop()
                answer++
                cur++
                continue
            }      
            
            stack.add(box)
            box++
        }
        
        while (cur < N && stack.isNotEmpty() && stack.peek() == order[cur]) {
            stack.pop()
            answer++
            cur++
        }
        
        return answer
    }
}