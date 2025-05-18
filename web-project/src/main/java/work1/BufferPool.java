package work1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Title: BufferPool
 * @Author 曦
 * @Date 2025/4/17 20:13
 * @description:
 */
class BufferPool {
    private final List<List<Integer>> buffers; // 缓冲区列表
    private final int bufferSize; // 每个缓冲区的大小
    final AtomicBoolean running = new AtomicBoolean(true); // 控制运行状态的原子布尔值

    public BufferPool(int numBuffers, int bufferSize) {
        this.buffers = new ArrayList<>(numBuffers);
        this.bufferSize = bufferSize;
        for (int i = 0; i < numBuffers; i++) {
            buffers.add(new ArrayList<>(bufferSize));
        }
    }

    public void produce(int item) throws InterruptedException {
        synchronized (this) {
            while (allBuffersFull()) {
                wait(); // 等待直到有缓冲区不为满
            }
            for (List<Integer> buffer : buffers) {
                if (buffer.size() < bufferSize) {
                    buffer.add(item);
                    System.out.println("Producer[" + Thread.currentThread().getId() + "] produced " + item + " into buffer " + buffers.indexOf(buffer) + " (Size: " + buffer.size() + "/" + bufferSize + ")");
                    notifyAll(); // 通知消费者
                    return;
                }
            }
        }
    }

    public int consume() throws InterruptedException {
        synchronized (this) {
            while (allBuffersEmpty()) {
                wait(); // 等待直到有缓冲区不为空
            }
            for (List<Integer> buffer : buffers) {
                if (!buffer.isEmpty()) {
                    int item = buffer.remove(0);
                    System.out.println("Consumer[" + Thread.currentThread().getId() + "] consumed " + item + " from buffer " + buffers.indexOf(buffer) + " (Remaining: " + (bufferSize - buffer.size()) + "/" + bufferSize + ")");
                    notifyAll(); // 通知生产者
                    return item;
                }
            }
            return -1; // 终止信号
        }
    }

    private boolean allBuffersFull() {
        for (List<Integer> buffer : buffers) {
            if (buffer.size() < bufferSize) {
                return false;
            }
        }
        return true;
    }

    private boolean allBuffersEmpty() {
        for (List<Integer> buffer : buffers) {
            if (!buffer.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void shutdown() {
        running.set(false);
        synchronized (this) {
            notifyAll(); // 唤醒所有等待线程
        }
    }
}


