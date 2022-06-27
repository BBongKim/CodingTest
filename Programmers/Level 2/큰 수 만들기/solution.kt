// 그리디 문제
// 아이디어가 생각이 안나서, 자료구조 접근만 봤다.
// 스택을 사용하면 된다.
// 앞 자리부터 스택을 값을 넣는다.
// 근데, 스택의 top보다 현재 값이 크다면, 스택의 top이 현재 값보다 크거나 같을 때 까지 계속 pop하면 된다.
// 그리고, 결과는 (n-k)개의 자리수를 가져야 하기 때문에 (스택에 있는 숫자 개수 + 뒤에 남아 있는 숫자 개수)가 (n-k)개보다 클 때만 pop 작업을 하면 된다.
// 마지막으로, 스택의 개수가 (n-k)보다 크면 (n-k)개가 될 때까지 pop하면 된다. (number="99", k="1"과 같은 경우를 위해서이다.)

import java.util.*

class Solution {
    fun solution(number: String, k: Int): String {     
        val n = number.length
        val stack = Stack<Int>()
        var idx = 0
        
        for (char in number) {
            val num = char.digitToInt()
            
            while (!stack.isEmpty() && num > stack.peek() && stack.size + (n - idx) > (n - k)) {
                stack.pop()
            }
            
            stack.push(num)
            idx++
        }
        
        while (stack.size > (n - k)) {
            stack.pop()
        }
        
        val builder = StringBuilder()
        for (s in stack) {
            builder.append(s)
        }
                
        return builder.toString()
    }
}