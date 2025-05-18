package work2.priority;

/**
 * @Title: PCB
 * @Author 曦
 * @Date 2025/4/17 23:29
 * @description:
 */

public class PCB {
    public int name; // 进程的名字
    public PCB next; // 指向的是下一个PCB对象，最后一个进程中的指针为 null
    public int needTime; // 要求运行的时间
    public int priority; // 表示当前进程的优先级，调度时总是选取优先数大的进程先执行。
    public String state; // 表示的是当前进程的状态

    public PCB() {
    }

    public PCB(int name, int needTime, int priority) {
        this.name = name;
        this.needTime = needTime;
        this.priority = priority;
        state = "ready";
        this.next = null;
    }

    public void exec() { // 调度 被CPU执行的操作
        if (needTime > 0) {
            needTime--; // 减少needTime
        }

        if (needTime == 0) {
            state = "finished"; // 当needTime为0时，将状态设为finished
        }
        priority--;
    }

    // Getters and Setters
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

