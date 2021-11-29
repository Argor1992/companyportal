package com.company.application.domain.projectdocuments.data;

import java.util.ArrayList;
import java.util.List;

public class ServerFile {
    private String fileName;
    private String path;
    private ServerFile parent;
    private List<ServerFile> children;
    private boolean isDirectory;
    private static final String FILE_DIRECTORY = "./projectfiles/";

    public ServerFile(String fileName, String path, boolean isDirectory) {
        this.fileName = fileName;
        this.path = path;
        this.children = new ArrayList<>();
        this.isDirectory = isDirectory;
    }

    public ServerFile(String fileName, String path, ServerFile parent, boolean isDirectory) {
        this.fileName = fileName;
        this.path = path;
        this.parent = parent;
        this.children = new ArrayList<>();
        this.isDirectory = isDirectory;
    }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getPath() { return path; }
    public String getAbsolutePath() { return FILE_DIRECTORY + path; }
    public void setPath(String path) { this.path = path; }
    public ServerFile getParent() { return parent; }
    public void setParent(ServerFile parent) { this.parent = parent; }
    public List<ServerFile> getChildren() { return children; }
    public void setChildren(List<ServerFile> children) { this.children = children; }
    public void addChildren(ServerFile file) {
        if (children == null) {
            children = List.of(file);
        } else {
            children.add(file);
        }
    }
    public boolean isDirectory() { return isDirectory; }
    public void setDirectory(boolean directory) { isDirectory = directory; }

    public String getFileSuffix() {
        if (isDirectory)
            return "";

        int index = fileName.indexOf(".");
        return fileName.substring(index+1);
    }
}
