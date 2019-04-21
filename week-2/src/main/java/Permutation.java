import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        if (k == 0)
            return;

        RandomizedQueue<String> rq = new RandomizedQueue<>();

        int readCount = 0;
        while(!StdIn.isEmpty()) {
            readCount++;
            String current = StdIn.readString();
            if (readCount <= k) {
                rq.enqueue(current);
            } else if (StdRandom.uniform(readCount) + 1 <= k) {
                // this is to achieve using only k items at memory
                // it ensures that all items have equal chance to get into the rq
                rq.dequeue();
                rq.enqueue(current);
            }
        }

        for (String elem : rq)
            System.out.println(elem);
    }
}
