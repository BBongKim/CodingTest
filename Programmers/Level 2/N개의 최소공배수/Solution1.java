class Solution {
    public static int solution(int[] arr) {
        int answer = 0;

        int[] mul = new int[arr.length];

        boolean isLCM = true;

        do {
            for (int k = 0; k < mul.length; k++) mul[k] += arr[k];

            for (int i = 0; i < mul.length; i++) {
                isLCM = true;
                for (int a : arr) {
                    if (mul[i] % a != 0) {
                        isLCM = false;
                        break;
                    }
                }
                answer = mul[i];
            }
        } while (!isLCM);

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {2, 6, 8, 14};
        System.out.println(solution(arr));
    }
}