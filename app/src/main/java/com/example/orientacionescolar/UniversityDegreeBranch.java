package com.example.orientacionescolar;

public class UniversityDegreeBranch {

    private int branchId;
    private String branchName;

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public UniversityDegreeBranch(int branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }
}
