package com.lvym.fdfs.pojo;

public class Fdfs {
    private Integer id;

    private String userName;

    private String groupName;

    private String remoteFilepath;

    private String oldFilename;

    private Long fileSize;

    private String fileType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getRemoteFilepath() {
        return remoteFilepath;
    }

    public void setRemoteFilepath(String remoteFilepath) {
        this.remoteFilepath = remoteFilepath == null ? null : remoteFilepath.trim();
    }

    public String getOldFilename() {
        return oldFilename;
    }

    public void setOldFilename(String oldFilename) {
        this.oldFilename = oldFilename == null ? null : oldFilename.trim();
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }
}