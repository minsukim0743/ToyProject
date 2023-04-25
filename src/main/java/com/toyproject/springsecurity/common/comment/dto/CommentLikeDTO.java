package com.toyproject.springsecurity.common.comment.dto;

public class CommentLikeDTO {

    private int commentNo;
    private String memberNickName;
    private int categoryNo;
    private int writeNo;
    private String stateYn;

    public CommentLikeDTO(){}

    public CommentLikeDTO(int commentNo, String memberNickName, int categoryNo, int writeNo, String stateYn) {
        this.commentNo = commentNo;
        this.memberNickName = memberNickName;
        this.categoryNo = categoryNo;
        this.writeNo = writeNo;
        this.stateYn = stateYn;
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

    public int getWriteNo() {
        return writeNo;
    }

    public void setWriteNo(int writeNo) {
        this.writeNo = writeNo;
    }

    public String getStateYn() {
        return stateYn;
    }

    public void setStateYn(String stateYn) {
        this.stateYn = stateYn;
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
