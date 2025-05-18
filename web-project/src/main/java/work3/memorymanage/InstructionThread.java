package work3.memorymanage;

/**
 * @Title: InstructionThread
 * @Author 曦
 * @Date 2025/4/20 10:35
 * @description:
 */

import java.util.List;

public class InstructionThread extends Thread {
    private final List<Instruction> instructions;
    private final MemoryManager manager;

    public InstructionThread(List<Instruction> instructions, MemoryManager manager) {
        this.instructions = instructions;
        this.manager = manager;
    }

    @Override
    public void run() {
        for (Instruction instr : instructions) {
            String result = manager.translate(instr.pageNumber, instr.offset);
            System.out.println("操作: " + instr.operation + " 页号: " + instr.pageNumber +
                    " 偏移: " + instr.offset + " => " + result);
            try {
                Thread.sleep(200); // 模拟延迟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

