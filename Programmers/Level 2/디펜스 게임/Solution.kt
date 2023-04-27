// 디펜스 게임
// https://school.programmers.co.kr/learn/courses/30/lessons/142085

// 힙

// 단순하게 생각해보면, 적이 많은 순으로 무적권을 사용하면 된다.
// 하지만, 뒤쪽 라운드에 몰려 있으면 무적권을 사용하지 못하고 라운드가 끝난다.

// 그렇다면 어떻게 해야할까?

// 라운드를 진행하면서, 라운드가 끝날 위기에 처할때마다 무적권을 사용하면된다.
// 그리고, 무적권을 사용하는 라운드는 이전 라운드에서 가장 적이 많았던 라운드로 선정한다. 

import java.util.*

class Solution {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        var answer: Int = 0
        
        if (k > enemy.size) return enemy.size
        
        var sum = 0
        var card = k
        
        val heap = PriorityQueue<Int>(Collections.reverseOrder())
        
        for (i in enemy.indices) { 
            sum += enemy[i]
            heap.offer(enemy[i])
            
            if (sum > n) {
                if (card > 0) {
                    card--
                    sum -= heap.poll()
                } else {
                    break
                }
            }
            
            answer = i + 1
        }
        
        return answer
    }
}