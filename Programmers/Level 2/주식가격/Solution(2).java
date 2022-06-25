// 스택을 이용한 풀이법
// 스택에는 시간 초를 담아 두고, 스택 top 초의 가격과 현재 초의 가격을 비교한다.
// 현재 초의 가격이 스택 top 초의 가격보다 작으면, 현재 시간과 스택에서 pop한 시간의 차가 주가가 유지된 시간이다.
// 마지막에 스택이 비어있지 않으면, 해당 초의 가격들은 끝까지 변동이 없었다는 의미이다. 따라서, (N-1)초와의 시간차를 구하면된다.

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
	int N = prices.length;
        int[] answer = new int[prices.length];        
        Stack<Integer> stack = new Stack<Integer>();
        
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int time = stack.pop();
                answer[time] = i - time;             
            }            
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            int time = stack.pop();
            answer[time] = (N - 1) - time;    
        }
        
        return answer;
    }
}