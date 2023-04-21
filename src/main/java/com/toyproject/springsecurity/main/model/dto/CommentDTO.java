package com.toyproject.springsecurity.main.model.dto;

public class CommentDTO {

    private int commentNo;
    private String content;
    private String writer;
    private String regDate;
    private int commentLike;
    private int categoryNo;
    private int writeNo;

    public CommentDTO() {}

    public CommentDTO(int commentNo, String content, String writer, String regDate, int commentLike, int categoryNo, int writeNo) {
        this.commentNo = commentNo;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
        this.commentLike = commentLike;
        this.categoryNo = categoryNo;
        this.writeNo = writeNo;
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

    public int getCommentLike() {
        return commentLike;
    }

    public void setCommentLike(int commentLike) {
        this.commentLike = commentLike;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public int getWriteNo() {
        return writeNo;
    }

    public void setWriteNo(int writeNo) {
        this.writeNo = writeNo;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "commentNo=" + commentNo +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate='" + regDate + '\'' +
                ", commentLike=" + commentLike +
                ", categoryNo=" + categoryNo +
                ", writeNo=" + writeNo +
                '}';
    }
}
