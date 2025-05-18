package work2.priority;

import java.util.*;

public class PriorityScheduler {

    public static void main(String[] args) {
        int[] finishedTime = new int[6];
        int[] burstTime = new int[6];

        ArrayList<PCB> list = new ArrayList<PCB>();

        PCB head = new PCB();
        // 将进程加入队列中
        PCB p1 = new PCB(1, 2, 1);
        p1.next = head.next;
        head.next = p1;
        sort(head);
        PCB p2 = new PCB(2, 3, 5);
        p2.next = head.next;
        head.next = p2;
        sort(head);

        PCB p3 = new PCB(3, 1, 3);
        p3.next = head.next;
        head.next = p3;
        sort(head);

        PCB p4 = new PCB(4, 2, 4);
        p4.next = head.next;
        head.next = p4;
        sort(head);

        PCB p5 = new PCB(5, 4, 2);
        p5.next = head.next;
        head.next = p5;
        sort(head);

        list.add(head);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        for (PCB e : list) { // 保存当前进程的burst time
            int i = e.name;
            burstTime[i] = e.needTime;
        }

        System.out.println("NAME        NEEDTIME      PRIORITY      STATE ");

        System.out.println("CPUTIME: 0");
        print(list);
        System.out.println();

        // 开始执行调度操作
        for (int cpuTime = 1; head.next != null; cpuTime++) {
            System.out.println("CPUTIME: " + cpuTime);

            head.next.state = "working"; // 将当前进程的状态改为working
            head.next.exec(); // 选择队列首部的进程执行
            // 在控制台输出当前所有进程的状态
            print(list);
            System.out.println("当前队首为: P" + head.next.name);
            System.out.println();
            if (head.next.state.equals("finished")) { // 如果当前进程已经完成，就移出队列
                finishedTime[head.next.name] = cpuTime; // 记录下它的完成时间
                head.next = head.next.next;
            } else {
                head.next.state = "ready"; // 当前时间片结束之后，重新把该进程放入到ready queue中
                sort(head); // 对ready queue进行调整，将当前进程放到合适的位置
            }
        }

        int count = 0;
        System.out.println("NAME    RoundTime    WaitingTime");
        for (int i = 1; i < 6; i++) {
            System.out.printf("P" + i + "           %3d            %3d       \n", finishedTime[i], (finishedTime[i] - burstTime[i]));
            count += finishedTime[i] - burstTime[i];
        }
        System.out.println("平均等待时间为" + (1.0 * count / 5) + "个周期");
    }

    public static void print(ArrayList<PCB> list) {
        // 打印当前进程信息
        int[] needtime = new int[6];
        int[] priority = new int[6];
        String[] state = new String[6];
        for (PCB e : list) {
            int i = e.name;
            needtime[i] = e.needTime;
            priority[i] = e.priority;
            state[i] = e.state;
        }

        for (int j = 1; j < 6; j++) {
            System.out.printf("P" + j + "        %3d           %3d             " + state[j] + "\n", needtime[j], priority[j]);
        }
    }

    // 对ready queue进行调整
    public static void sort(PCB head) {
        PCB p = head.next;
        while (p.next != null) {
            if (p.priority <= p.next.priority) {
                swap(p, p.next);
                p = p.next;
            } else {
                return;
            }
        }
    }

    // 交换两个节点
    public static void swap(PCB a, PCB b) {
        int name = b.name;
        int time = b.needTime;
        int pri = b.priority;

        b.name = a.name;
        b.needTime = a.needTime;
        b.priority = a.priority;
        a.name = name;
        a.needTime = time;
        a.priority = pri;
    }
}

