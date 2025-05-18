package work3.memorymanage;

/**
 * @Title: PageTableEntry
 * @Author 曦
 * @Date 2025/4/20 10:34
 * @description:
 */

public class PageTableEntry {
    int pageNumber;
    int flag; // 0-不在主存，1-在主存
    int blockNumber; // 主存块号

    public PageTableEntry(int pageNumber, int flag, int blockNumber) {
        this.pageNumber = pageNumber;
        this.flag = flag;
        this.blockNumber = blockNumber;
    }
}
