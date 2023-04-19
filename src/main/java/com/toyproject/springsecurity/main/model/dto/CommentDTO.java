package com.toyproject.springsecurity.main.model.dto;

public class CommentDTO {

    private int commentNo;
    private String content;
    private String writer;
    private String regDate;
    private int commentLike;

    public CommentDTO() {}

    public CommentDTO(int commentNo, String content, String writer, String regDate, int commentLike) {
        this.commentNo = commentNo;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
        this.commentLike = commentLike;
    }

    public int getCommentLike(){

        return commentLike;
    }

    public void setCommentLike(int commentLike){

        this.commentLike = commentLike;
    }
    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }


    @Override
    public String toString() {
        return  "commentNo=" + commentNo +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate='" + regDate + '\'' +
                ", commentLike=" + commentLike;
    }
}
