package work3.fifo;

/**
 * @Title: PageTableEntry
 * @Author 曦
 * @Date 2025/4/20 12:06
 * @description:
 */

class PageTableEntry {
    int pageNumber;
    int blockNumber;
    boolean inMemory;
    boolean modified;
    String diskAddr;

    PageTableEntry(int pageNumber, String diskAddr) {
        this.pageNumber = pageNumber;
        this.inMemory = false;
        this.modified = false;
        this.blockNumber = -1;
        this.diskAddr = diskAddr;
    }
}
