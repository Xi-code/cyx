package work3.fifo;

/**
 * @Title: FIFOThreadSimulation
 * @Author 曦
 * @Date 2025/4/20 12:07
 * @description:
 */

public class FIFOThreadSimulation {
    public static void main(String[] args) {
        PageManager manager = new PageManager(8);

        int[] seq1 = {0, 1, 2, 3, 0, 4};
        int[] seq2 = {2, 5, 6, 2, 0};

        new JobThread("作业1", seq1, manager).start();
        new JobThread("作业2", seq2, manager).start();
    }
}

