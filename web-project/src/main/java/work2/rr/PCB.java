package work2.rr;

/**
 * @Title: PCB
 * @Author 曦
 * @Date 2025/4/18 16:37
 * @description:
 */

import java.util.ArrayList;

public class PCB {
    public int name; // 进程的名字
    public PCB next; // 指向的是下一个PCB对象
    public int needTime; // 要求运行的时间
    public int executionTime; // 表示当前进程的已运行的时间，初始值为0
    public String state; // 表示的是当前进程的状态

    public PCB(int name, int needTime) {
        this.name = name;
        this.needTime = needTime;
        this.executionTime = 0;
        state = "ready";
        this.next = null;
    }

    public void exec() { // 调度 被CPU执行的操作
        if (executionTime < needTime) {
            executionTime++;
        }
        if (executionTime == needTime) {
            state = "finished"; // 当已执行时间等于需要执行时间时，就把状态改为finished
        }
    }

    // Getter和Setter方法
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public PCB getNext() {
        return next;
    }

    public void setNext(PCB next) {
        this.next = next;
    }

    public int getNeedTime() {
        return needTime;
    }

    public void setNeedTime(int needTime) {
        this.needTime = needTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public PCB() {
    }
}

