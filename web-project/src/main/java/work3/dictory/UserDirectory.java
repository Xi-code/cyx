package work3.dictory;

/**
 * @Title: UserDirectory
 * @Author 曦
 * @Date 2025/4/24 19:25
 * @description:
 */

import java.util.ArrayList;
import java.util.List;

public class UserDirectory {
    private List<FileEntry> files = new ArrayList<>();
    private static final int MAX_FILES = 10;
    public boolean addFile(FileEntry file) {
        if (files.size() >= MAX_FILES) return false;
        files.add(file);
        return true;
    }
    public boolean deleteFile(String fileName) {
        return files.removeIf(f -> f.getFileName().equals(fileName));
    }
    public FileEntry findFile(String fileName) {
        return files.stream()
                .filter(f -> f.getFileName().equals(fileName))
                .findFirst()
                .orElse(null);
    }
    public List<FileEntry> listFiles() {
        return new ArrayList<>(files);
    }
}
