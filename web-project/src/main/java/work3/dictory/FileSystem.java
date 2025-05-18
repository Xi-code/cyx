package work3.dictory;

/**
 * @Title: FileSystem
 * @Author 曦
 * @Date 2025/4/24 19:26
 * @description:
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystem {
    private Map<String, UserDirectory> mfd = new HashMap<>(); // 主目录
    private String currentUser = null;
    private FileEntry openedFile = null; // 当前打开的文件（一次只能打开一个）

    public boolean login(String username) {
        if (!mfd.containsKey(username)) {
            mfd.put(username, new UserDirectory());
        }
        currentUser = username;
        return true;
    }

    public void logout() {
        currentUser = null;
        openedFile = null;
    }

    public boolean createFile(String name, String code, String address, int length) {
        if (currentUser == null) return false;
        UserDirectory ud = mfd.get(currentUser);
        FileEntry file = new FileEntry(name, address, code, length);
        return ud.addFile(file);
    }

    public boolean deleteFile(String name) {
        if (currentUser == null) return false;
        return mfd.get(currentUser).deleteFile(name);
    }

    public boolean openFile(String name) {
        if (currentUser == null || openedFile != null) return false;
        FileEntry file = mfd.get(currentUser).findFile(name);
        if (file != null) {
            openedFile = file;
            file.setOpen(true);
            return true;
        }
        return false;
    }

    public boolean closeFile() {
        if (openedFile == null) return false;
        openedFile.setOpen(false);
        openedFile = null;
        return true;
    }

    public String readFile() {
        if (openedFile == null) return "未打开文件";
        if (openedFile.getProtectionCode().charAt(0) == '0')
            return "无读取权限";
        return "读取文件内容（模拟）";
    }

    public String writeFile() {
        if (openedFile == null) return "未打开文件";
        if (openedFile.getProtectionCode().charAt(1) == '0')
            return "无写入权限";
        return "写入文件内容（模拟）";
    }

    public List<FileEntry> listFiles() {
        if (currentUser == null) return new ArrayList<>();
        return mfd.get(currentUser).listFiles();
    }
}
