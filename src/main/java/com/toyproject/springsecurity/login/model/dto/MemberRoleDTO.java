package com.toyproject.springsecurity.login.model.dto;

public class MemberRoleDTO {

    private String memberId;
    private String memberRole;

    public MemberRoleDTO() {}

    public MemberRoleDTO(String memberId, String memberRole) {
        this.memberId = memberId;
        this.memberRole = memberRole;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
    }

    @Override
    public String toString() {
        return "MemberRoleDTO{" +
                "memberId='" + memberId + '\'' +
                ", memberRole='" + memberRole + '\'' +
                '}';
    }
}
