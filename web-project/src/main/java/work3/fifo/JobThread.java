package work3.fifo;

/**
 * @Title: JobThread
 * @Author 曦
 * @Date 2025/4/20 12:07
 * @description:
 */

class JobThread extends Thread {
    private final int[] accessSeq;
    private final PageManager manager;
    private final String name;

    public JobThread(String name, int[] seq, PageManager manager) {
        this.name = name;
        this.accessSeq = seq;
        this.manager = manager;
    }

    @Override
    public void run() {
        for (int page : accessSeq) {
            manager.accessPage(page, name);
            try {
                Thread.sleep(100); // 模拟指令间隔
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

