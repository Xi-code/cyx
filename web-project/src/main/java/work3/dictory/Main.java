package work3.dictory;

/**
 * @Title: Main
 * @Author 曦
 * @Date 2025/4/24 19:26
 * @description:
 */

import java.util.List;
import java.util.Scanner;

public class Main {
    private static FileSystem fs = new FileSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== 文件系统菜单 =====");
            System.out.println("1. login [用户名]");
            System.out.println("2. dir");
            System.out.println("3. create [文件名] [保护码] [地址] [长度]");
            System.out.println("4. delete [文件名]");
            System.out.println("5. open [文件名]");
            System.out.println("6. close");
            System.out.println("7. read");
            System.out.println("8. write");
            System.out.println("9. exit");
            System.out.print("请输入命令：");

            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String cmd = parts[0];

            try {
                switch (cmd) {
                    case "login":
                        if (fs.login(parts[1]))
                            System.out.println("登录成功：" + parts[1]);
                        else
                            System.out.println("登录失败");
                        break;
                    case "dir":
                        List<FileEntry> files = fs.listFiles();
                        System.out.println("文件名\t物理地址\t保护码\t长度");
                        for (FileEntry f : files) {
                            System.out.printf("%s\t%s\t%s\t%d\n",
                                    f.getFileName(),
                                    f.getPhysicalAddress(),
                                    f.getProtectionCode(),
                                    f.getFileLength()
                            );
                        }
                        break;
                    case "create":
                        boolean created = fs.createFile(
                                parts[1], parts[2], parts[3], Integer.parseInt(parts[4])
                        );
                        System.out.println(created ? "创建成功" : "创建失败");
                        break;
                    case "delete":
                        boolean deleted = fs.deleteFile(parts[1]);
                        System.out.println(deleted ? "删除成功" : "删除失败");
                        break;
                    case "open":
                        boolean opened = fs.openFile(parts[1]);
                        System.out.println(opened ? "文件已打开" : "打开失败");
                        break;
                    case "close":
                        boolean closed = fs.closeFile();
                        System.out.println(closed ? "文件已关闭" : "关闭失败");
                        break;
                    case "read":
                        System.out.println(fs.readFile());
                        break;
                    case "write":
                        System.out.println(fs.writeFile());
                        break;
                    case "exit":
                        System.exit(0);
                    default:
                        System.out.println("无效命令");
                }
            } catch (Exception e) {
                System.out.println("参数错误！");
            }
        }
    }
}
