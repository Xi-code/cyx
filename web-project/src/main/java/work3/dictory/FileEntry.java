package work3.dictory;

/**
 * @Title: FileEntry
 * @Author 曦
 * @Date 2025/4/24 19:25
 * @description:
 */

public class FileEntry {
    private String fileName;
    private String physicalAddress;
    private String protectionCode; // 格式为 "rwx"（例如 "110" 表示可读写不可执行）
    private int fileLength;
    private boolean isOpen;

    public FileEntry(String name, String address, String code, int length) {
        fileName = name;
        physicalAddress = address;
        protectionCode = code;
        fileLength = length;
        isOpen = false;
    }

    // Getters and Setters
    public String getFileName() { return fileName; }
    public String getProtectionCode() { return protectionCode; }
    public String getPhysicalAddress() { return physicalAddress; }
    public int getFileLength() { return fileLength; }
    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean open) { isOpen = open; }
}
