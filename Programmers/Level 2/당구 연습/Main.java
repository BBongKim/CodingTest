// 당구 연습
// 수학(?)

// 두 공이 어딨던간에 원쿠션으로 맞힐 수 있다.

// 그리고, 입사각 반사각이 같다면 목표 공을 상하좌우로 대칭 이동 시킬 수 있다.

// 그래서, 대칭 이동한 좌표들과의 거리를 구하면 원쿠션으로 이동한 거리를 구할 수 있다.

// 최종적으로, 그 거리들 중 최소 거리가 답이 된다.

 
import java.util.*;

class Pos {
    int x; 
    int y;
    
    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    static int N;
    static int M;
    
    public List<Pos> getSymetrics(Pos start, int[] ball) {
        ArrayList<Pos> positions = new ArrayList();
        
        // 위
        if (!(start.x == ball[0] && start.y <= ball[1])) {
             positions.add(new Pos(ball[0], (N - ball[1]) + N));
        }
            
        // 아래
        if (!(start.x == ball[0] && start.y >= ball[1])) {
             positions.add(new Pos(ball[0], -1 * ball[1]));
        }       
        
        // 왼쪽
        if (!(start.y == ball[1] && start.x >= ball[0])) {
             positions.add(new Pos(-1 * ball[0], ball[1]));
        }  
        
        // 오른쪽
        if (!(start.y == ball[1] && start.x <= ball[0])) {
             positions.add(new Pos((M - ball[0]) + M, ball[1]));
        }               
        
        return positions;
    }
    
    public int getDistance(Pos start, Pos ball) {
        return (int)Math.pow(Math.abs(start.x - ball.x), 2) + (int)Math.pow(Math.abs(start.y - ball.y), 2);
    }
    
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        M = m;
        N = n;
        int[] answer = new int[balls.length];
        
        Pos start = new Pos(startX, startY);
        
        for (int i = 0; i < balls.length; i++) {
            int min = Integer.MAX_VALUE;
            List<Pos> positions = getSymetrics(start, balls[i]);
            
            for (Pos pos : positions) {
                int temp = getDistance(start, pos);
                min = Math.min(min, temp);
            }
            
            answer[i] = min;
        }
        
        return answer;
    }
}