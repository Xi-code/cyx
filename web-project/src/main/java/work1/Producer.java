package work1;

/**
 * @Title: Producer
 * @Author 曦
 * @Date 2025/4/17 20:13
 * @description:
 */
class Producer implements Runnable {
    private final BufferPool bufferPool;
    private int counter = 0;

    public Producer(BufferPool bufferPool) {
        this.bufferPool = bufferPool;
    }

    @Override
    public void run() {
        try {
            while (bufferPool.running.get()) {
                bufferPool.produce(counter++);
                Thread.sleep((long) (Math.random() * 1500)); // 模拟生产耗时
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Producer[" + Thread.currentThread().getId() + "] exiting...");
    }
}
