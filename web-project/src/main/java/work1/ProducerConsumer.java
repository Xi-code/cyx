package work1;/**
 * @Title: ImprovedProducerConsumer
 * @Author  曦
 * @Date  2025/4/17 20:14
 * @description: 
*/

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        final int NUM_BUFFERS = 3;
        final int BUFFER_SIZE = 5;
        final int PRODUCERS = 2;
        final int CONSUMERS = 3;

        BufferPool bufferPool = new BufferPool(NUM_BUFFERS, BUFFER_SIZE);

        // 创建线程池
        Thread[] producers = new Thread[PRODUCERS];
        Thread[] consumers = new Thread[CONSUMERS];

        // 启动生产者
        for (int i = 0; i < PRODUCERS; i++) {
            producers[i] = new Thread(new Producer(bufferPool));
            producers[i].start();
        }

        // 启动消费者
        for (int i = 0; i < CONSUMERS; i++) {
            consumers[i] = new Thread(new Consumer(bufferPool));
            consumers[i].start();
        }

        // 添加关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nInitiating shutdown...");
            bufferPool.shutdown();

            // 等待线程终止
            try {
                for (Thread p : producers) p.join(1000);
                for (Thread c : consumers) c.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }));

        // 保持主线程运行
        while (true) {
            Thread.sleep(60000);
            System.out.println("\nSystem status: Running...");
        }
    }
}
