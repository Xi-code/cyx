package work3.memorymanage;

/**
 * @Title: Instruction
 * @Author 曦
 * @Date 2025/4/20 10:34
 * @description:
 */

public class Instruction {
    String operation;
    int pageNumber;
    int offset;

    public Instruction(String operation, int pageNumber, int offset) {
        this.operation = operation;
        this.pageNumber = pageNumber;
        this.offset = offset;
    }
}

