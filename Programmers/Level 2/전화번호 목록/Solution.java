/* startsWith()라는 함수를 알게해줬다. */

import java.util.Arrays;

class Solution {
    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book);

        for(int i = 0; i < phone_book.length - 1; i++) {
            if(phone_book[i+1].startsWith(phone_book[i])) {
                answer = false;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] phone_book = {"14", "24", "599", "207", "5895"};
        System.out.println(solution(phone_book));
    }
}