package com.toyproject.springsecurity.photoBoard.model.dto;

public class PhotoBoardFileDTO {

    private int fileNo;
    private int boardNo;
    private String orgFileName;
    private String changeFileName;
    private int fileSize;
    private String registTime;
    private String deleteYn;
    private String filePath;

    public PhotoBoardFileDTO() {
    }

    public PhotoBoardFileDTO(int fileNo, int boardNo, String orgFileName, String changeFileName, int fileSize,
                             String registTime, String deleteYn, String filePath) {
        this.fileNo = fileNo;
        this.boardNo = boardNo;
        this.orgFileName = orgFileName;
        this.changeFileName = changeFileName;
        this.fileSize = fileSize;
        this.registTime = registTime;
        this.deleteYn = deleteYn;
        this.filePath = filePath;
    }

    public String getOrgFileName() {
        return orgFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileNo() {
        return fileNo;
    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public void setOrgFileName(String orgFileName) {
        this.orgFileName = orgFileName;
    }

    public String getChangeFileName() {
        return changeFileName;
    }

    public void setChangeFileName(String changeFileName) {
        this.changeFileName = changeFileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getDeleteYn() {
        return deleteYn;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

    @Override
    public String toString() {
        return "PhotoBoardFileDTO{" +
                "fileNo=" + fileNo +
                ", boardNo=" + boardNo +
                ", orgFileName='" + orgFileName + '\'' +
                ", changeFileName='" + changeFileName + '\'' +
                ", fileSize=" + fileSize +
                ", registTime='" + registTime + '\'' +
                ", deleteYn='" + deleteYn + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
