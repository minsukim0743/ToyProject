package com.toyproject.springsecurity.freeBoard.model.dto;

import java.util.Date;

public class FreeBoardDTO {
    private int no;
    private String title;
    private String contents;
    private int count;
    private Date registTime;
    private String writer;
    private Date updateTime;
    private String deleteYn;

    public FreeBoardDTO(){}

    public FreeBoardDTO(int no, String title, String contents, int count, Date registTime, String writer, Date updateTime, String deleteYn) {
        this.no = no;
        this.title = title;
        this.contents = contents;
        this.count = count;
        this.registTime = registTime;
        this.writer = writer;
        this.updateTime = updateTime;
        this.deleteYn = deleteYn;
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

    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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
        return "FreeBoardDTO{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", count=" + count +
                ", registTime=" + registTime +
                ", writer='" + writer + '\'' +
                ", updateTime=" + updateTime +
                ", deleteYn='" + deleteYn + '\'' +
                '}';
    }
}
