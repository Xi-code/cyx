package work3.memorymanage;

/**
 * @Title: Main
 * @Author 曦
 * @Date 2025/4/20 10:35
 * @description:
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 初始化页表
        List<PageTableEntry> pageTable = Arrays.asList(
                new PageTableEntry(0, 1, 3),
                new PageTableEntry(1, 0, 0),
                new PageTableEntry(2, 1, 4),
                new PageTableEntry(3, 1, 5),
                new PageTableEntry(4, 1, 2),
                new PageTableEntry(5, 0, 0),
                new PageTableEntry(6, 1, 6)
        );

        // 加载操作指令（来自题目）
        List<Instruction> instructions = Arrays.asList(
                new Instruction("+", 0, 70),
                new Instruction("+", 1, 50),
                new Instruction("*", 2, 15),
                new Instruction("存", 3, 21),
                new Instruction("取", 0, 56),
                new Instruction("+", 4, 53),
                new Instruction("移位", 4, 53),
                new Instruction("存", 5, 23),
                new Instruction("取", 2, 78),
                new Instruction("+", 6, 84)
        );

        MemoryManager manager = new MemoryManager();
        manager.loadPageTable(pageTable);

        InstructionThread thread = new InstructionThread(instructions, manager);
        thread.start();
    }
}

