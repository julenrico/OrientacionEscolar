package com.example.orientacionescolar.main;

import androidx.annotation.Nullable;

public class UniversityDegree {

    private int degreeId;
    private String degreeName;
    private UniversityDegreeBranch branch;
    private UniversityDegreeCampus campus;
    private UniversityDegreeCenter center;

    public int getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(int degreeId) {
        this.degreeId = degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public UniversityDegreeBranch getBranch() {
        return branch;
    }

    public void setBranch(UniversityDegreeBranch branch) {
        this.branch = branch;
    }

    public UniversityDegreeCampus getCampus() {
        return campus;
    }

    public void setCampus(UniversityDegreeCampus campus) {
        this.campus = campus;
    }

    public UniversityDegreeCenter getCenter() {
        return center;
    }

    public void setCenter(UniversityDegreeCenter center) {
        this.center = center;
    }

    public UniversityDegree(int degreeId, String degreeName, UniversityDegreeBranch branch, UniversityDegreeCampus campus, UniversityDegreeCenter center) {
        this.degreeId = degreeId;
        this.degreeName = degreeName;
        this.branch = branch;
        this.campus = campus;
        this.center = center;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        UniversityDegree comparison = ((UniversityDegree)obj);
        return this.degreeName.equalsIgnoreCase(comparison.getDegreeName()) && this.center.equals(comparison.center);
    }
}
