package com.reginald.skola.main;

import androidx.annotation.Nullable;

public class UniversityDegreeCenter {

    private int centerId;
    private String centerName;
    private UniversityDegreeCampus campus;

    public int getCenterId() {
        return centerId;
    }

    public void setCenterId(int centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public UniversityDegreeCampus getCampus() {
        return campus;
    }

    public void setCampus(UniversityDegreeCampus campus) {
        this.campus = campus;
    }

    public UniversityDegreeCenter(int centerId, String centerName, UniversityDegreeCampus campus) {
        this.centerId = centerId;
        this.centerName = centerName;
        this.campus = campus;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        UniversityDegreeCenter comparison = (UniversityDegreeCenter)obj;

        return this.getCenterId() == comparison.getCenterId();
    }
}
