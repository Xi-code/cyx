package work3.fifo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Title: PageManager
 * @Author 曦
 * @Date 2025/4/20 12:06
 * @description:
 */

class PageManager {
    private final int MAX_BLOCKS = 3;
    private final LinkedList<Integer> fifoQueue = new LinkedList<>();
    private final Map<Integer, PageTableEntry> pageTable = new HashMap<>();
    private int nextBlock = 0;

    public PageManager(int totalPages) {
        for (int i = 0; i < totalPages; i++) {
            pageTable.put(i, new PageTableEntry(i, "01" + i));
        }
    }

    public synchronized void accessPage(int pageNum, String threadName) {
        PageTableEntry entry = pageTable.get(pageNum);
        if (entry.inMemory) {
            System.out.println(threadName + " 访问页 " + pageNum + " —— 命中");
            entry.modified = true; // 模拟修改操作
        } else {
            System.out.println(threadName + " 缺页中断：页 " + pageNum);
            if (fifoQueue.size() < MAX_BLOCKS) {
                loadPage(pageNum);
            } else {
                int oldPage = fifoQueue.poll();
                PageTableEntry oldEntry = pageTable.get(oldPage);
                if (oldEntry.modified) {
                    System.out.println("写回磁盘：" + oldEntry.diskAddr + " ← 页 " + oldPage);
                }
                oldEntry.inMemory = false;
                oldEntry.blockNumber = -1;
                loadPage(pageNum);
            }
        }
    }

    private void loadPage(int pageNum) {
        PageTableEntry entry = pageTable.get(pageNum);
        entry.inMemory = true;
        entry.blockNumber = nextBlock++;
        entry.modified = false;
        fifoQueue.offer(pageNum);
        System.out.println("→ 装入页 " + pageNum + " 到主存块 " + entry.blockNumber);
    }
}

