import javafx.util.Pair;

import java.util.HashMap;

class Pos {
    int x, y;

    Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {

    public static String solution(int[] numbers, String hand) {
        String answer = "";
        Pos R_pos = new Pos(2, 3);
        Pos L_pos = new Pos(0, 3);

        HashMap<Integer, Pos> map = new HashMap<>();

        map.put(-1, new Pos(0, 3)); // *
        map.put(-2, new Pos(2, 3)); // #
        map.put(1, new Pos(0, 0));
        map.put(2, new Pos(1, 0));
        map.put(3, new Pos(2, 0));
        map.put(4, new Pos(0, 1));
        map.put(5, new Pos(1, 1));
        map.put(6, new Pos(2, 1));
        map.put(7, new Pos(0, 2));
        map.put(8, new Pos(1, 2));
        map.put(9, new Pos(2, 2));
        map.put(0, new Pos(1, 3));

        for(int n : numbers) {
            Pos num_pos = map.get(n);

            if (n == 1 || n == 4 || n == 7) {
                answer += "L";
                L_pos = num_pos;
            } else  if (n == 3 || n == 6 || n == 9) {
                answer += "R";
                R_pos = num_pos;
            } else {
                if (getDist(num_pos, R_pos) < getDist(num_pos, L_pos)) {
                    answer += "R";
                    R_pos = num_pos;
                } else if (getDist(num_pos, R_pos) > getDist(num_pos, L_pos)) {
                    answer += "L";
                    L_pos = num_pos;
                } else {
                    if(hand.equals("right")) {
                        answer += "R";
                        R_pos = num_pos;
                    } else {
                        answer += "L";
                        L_pos = num_pos;
                    }
                }
            }
        }
        return answer;
    }

    public static int getDist(Pos p1, Pos p2) {
        int xd = Math.abs(p1.x-p2.x);
        int yd = Math.abs(p1.y-p2.y);
        return xd+yd;
    }


    public static void main(String[] args) {
        int[] num = {5, 5, 5, 5, 5};
        System.out.println(solution(num, "right"));
    }
}