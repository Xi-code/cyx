package work1;

/**
 * @Title: Consumer
 * @Author 曦
 * @Date 2025/4/17 20:13
 * @description:
 */
class Consumer implements Runnable {
    private final BufferPool bufferPool;

    public Consumer(BufferPool bufferPool) {
        this.bufferPool = bufferPool;
    }

    @Override
    public void run() {
        try {
            while (bufferPool.running.get()) {
                int item = bufferPool.consume();
                if (item == -1) {
                    break;
                }
                Thread.sleep((long) (Math.random() * 2000)); // 模拟消费耗时
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Consumer[" + Thread.currentThread().getId() + "] exiting...");
    }
}

