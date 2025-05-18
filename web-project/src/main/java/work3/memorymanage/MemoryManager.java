package work3.memorymanage;

/**
 * @Title: MemoryManager
 * @Author 曦
 * @Date 2025/4/20 10:35
 * @description:
 */

import java.util.*;

public class MemoryManager {
    private final Map<Integer, PageTableEntry> pageTable = new HashMap<>();
    private final int pageSize = 128;

    public void loadPageTable(List<PageTableEntry> entries) {
        for (PageTableEntry entry : entries) {
            pageTable.put(entry.pageNumber, entry);
        }
    }

    public synchronized String translate(int pageNumber, int offset) {
        PageTableEntry entry = pageTable.get(pageNumber);
        if (entry == null || entry.flag == 0) {
            return "缺页中断：页号 " + pageNumber + " *";
        }
        int absoluteAddress = entry.blockNumber * pageSize + offset;
        return "访问地址：" + absoluteAddress;
    }
}

