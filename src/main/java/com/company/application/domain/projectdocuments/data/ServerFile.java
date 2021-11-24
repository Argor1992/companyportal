package com.company.application.domain.projectdocuments.data;

import java.util.ArrayList;
import java.util.List;

public class ServerFile {
    private String fileName;
    private List<ServerFile> children;
    private boolean isDirectory;

    public ServerFile(String fileName, boolean isDirectory) {
        this.children = new ArrayList<>();
        this.fileName = fileName;
        this.isDirectory = isDirectory;
    }

    public ServerFile(String fileName, List<ServerFile> children, boolean isDirectory) {
        this.fileName = fileName;
        this.children = children;
        this.isDirectory = isDirectory;
    }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
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
}
