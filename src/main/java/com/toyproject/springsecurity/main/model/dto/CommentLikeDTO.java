package com.toyproject.springsecurity.main.model.dto;

public class CommentLikeDTO {

    private int commentNo;
    private String memberNickName;
    private int categoryNo;
    private int writeNo;

    public CommentLikeDTO(){}

    public CommentLikeDTO(int commentNo, String memberNickName, int categoryNo, int writeNo) {
        this.commentNo = commentNo;
        this.memberNickName = memberNickName;
        this.categoryNo = categoryNo;
        this.writeNo = writeNo;
    }

    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getMemberNickName() {
        return memberNickName;
    }

    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public int getWriterNo() {
        return writeNo;
    }

    public void setWriterNo(int writeNo) {
        this.writeNo = writeNo;
    }

    @Override
    public String toString() {
        return "CommentLikeDTO{" +
                "commentNo=" + commentNo +
                ", memberNickName='" + memberNickName + '\'' +
                ", categoryNo=" + categoryNo +
                ", writeNo=" + writeNo +
                '}';
    }
}
