package com.example.orientacionescolar;

public class UniversityDegreeCampus {

    private int campusId;
    private String campusName;

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public UniversityDegreeCampus(int campusId, String campusName) {
        this.campusId = campusId;
        this.campusName = campusName;
    }
}
