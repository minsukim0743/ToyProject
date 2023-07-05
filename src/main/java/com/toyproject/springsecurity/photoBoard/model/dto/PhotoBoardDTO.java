package com.toyproject.springsecurity.photoBoard.model.dto;

public class PhotoBoardDTO {

    private int no;
    private String title;
    private String contents;
    private int count;
    private String registTime;
    private String writer;
    private String updateTime;
    private String deleteYn;
    private PhotoBoardFileDTO photoBoardFile;

    public PhotoBoardDTO(){}

    public PhotoBoardDTO(int no, String title, String contents, int count, String registTime, String writer,
                        String updateTime, String deleteYn, PhotoBoardFileDTO photoBoardFile) {
        this.no = no;
        this.title = title;
        this.contents = contents;
        this.count = count;
        this.registTime = registTime;
        this.writer = writer;
        this.updateTime = updateTime;
        this.deleteYn = deleteYn;
        this.photoBoardFile = photoBoardFile;
    }

    public PhotoBoardFileDTO getPhotoBoardFile() {
        return photoBoardFile;
    }

    public void setPhotoBoardFile(PhotoBoardFileDTO photoBoardFile) {
        this.photoBoardFile = photoBoardFile;
    }

    public int getNo(){
        return no;
    }

    public void setNo(int no){

        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteYn() {
        return deleteYn;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

    @Override
    public String toString() {
        return "photoBoardDTO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", count=" + count +
                ", registTime=" + registTime +
                ", writer='" + writer + '\'' +
                ", updateTime=" + updateTime + '\'' +
                ", deleteYn='" + deleteYn + '\'' +
                ", photoBoardFileDTO='" + photoBoardFile + '\'' +
                '}';
    }
}
