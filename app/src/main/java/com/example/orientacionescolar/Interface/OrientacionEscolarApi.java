package com.example.orientacionescolar.Interface;

import com.example.orientacionescolar.UniversityDegree;
import com.example.orientacionescolar.UniversityDegreeBranch;
import com.example.orientacionescolar.UniversityDegreeCampus;
import com.example.orientacionescolar.UniversityDegreeCenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OrientacionEscolarApi {

    @GET("/api/UniversityDegrees")
    Call<List<UniversityDegree>> getUniversityDegreesFromApi();
    @GET("/api/UniversityDegreeBranches")
    Call<List<UniversityDegreeBranch>> getUniversityDegreeBranchFromApi();
    @GET("/api/UniversityDegreeCampus")
    Call<List<UniversityDegreeCampus>> getUniversityDegreeCampusFromApi();
    @GET("/api/UniversityDegreeCenters")
    Call<List<UniversityDegreeCenter>> getUniversityDegreeCenterFromApi();
}
