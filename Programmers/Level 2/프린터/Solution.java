import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static class Document {
        int loc, prior;

        Document(int prior, int loc) {
            this.prior = prior;
            this.loc = loc;
        }
    }

    public static int solution(int[] priorities, int location) {
        int answer = 1;

        Queue<Document> queue = new LinkedList<>();

        for (int i = 0; i < priorities.length; i++) queue.add(new Document(priorities[i], i));

        while (!queue.isEmpty()) {
            Document d = queue.poll();
            if (d.prior < getMaxPrior(queue)) {
                queue.add(d);
                continue;
            }

            if (d.loc == location) break;

            else answer++;
        }

        return answer;
    }

    public static int getMaxPrior(Queue<Document> queue) {
        int max = -1;
        for (Document d : queue) {
            max = Math.max(max, d.prior);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] priorities = {1, 1, 9, 1, 1, 1};
        int location = 0;
        System.out.println(solution(priorities, location));
    }
}