package com.example.orientacionescolar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "orientacionescolar.sqlite";
    private static final int DATABASE_VERSION = 1;

    public final String COUNT_UNIVERSITY_DEGREES = "SELECT COUNT(*) FROM university_degrees";

    public final String GET_UNIVERSITY_DEGREES = "SELECT * FROM university_degrees";
    public final String GET_UNIVERSITY_DEGREES_BRANCHES = "SELECT * FROM university_degree_branches";
    public final String GET_UNIVERSITY_DEGREES_CAMPUS = "SELECT * FROM university_degree_campus";
    public final String GET_UNIVERSITY_DEGREES_CENTERS = "SELECT * FROM university_degree_centers";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*Functions to insert data */

    public void insertBranch(SQLiteDatabase db, int branchId, String branchName){

        db.execSQL("INSERT INTO university_degree_branches (branch_Id, branch_Name) VALUES (?,?)",new String [] {String.valueOf(branchId),String.valueOf(branchName)});
    }

    public void insertCampus(SQLiteDatabase db, int campusId, String campusName){

        db.execSQL("INSERT INTO university_degree_campus (campus_Id, campus_Name) VALUES (?,?)",new String [] {String.valueOf(campusId),String.valueOf(campusName)});
    }

    public void insertDegree(SQLiteDatabase db, int degreeId, String degreeName, int degreeBranchId){

        db.execSQL("INSERT INTO university_degrees (degree_Id, degree_Name, degree_Branch) VALUES (?,?,?)",new String [] {String.valueOf(degreeId),String.valueOf(degreeName),String.valueOf(degreeBranchId)});
    }

    public void insertCenters(SQLiteDatabase db, int centerId, String centerName, int centerCampusId){

        db.execSQL("INSERT INTO university_degree_centers (center_Id, center_Name, center_Campus) VALUES (?,?,?)",new String [] {String.valueOf(centerId),String.valueOf(centerName),String.valueOf(centerCampusId)});
    }

    public void insertCenterDegrees(SQLiteDatabase db, int centerId, int degreeId){

        db.execSQL("INSERT INTO university_center_degrees (center_Id, degree_Id) VALUES (?,?)",new String [] {String.valueOf(degreeId),String.valueOf(degreeId)});
    }

    public int getCountDegrees(){
        Cursor c = getReadableDatabase().rawQuery(COUNT_UNIVERSITY_DEGREES,null);
        if(!c.moveToFirst()){
            return 0;
        }
        return c.getCount();
    }

    public ArrayList<UniversityDegreeBranch> getUniversityDegreeBranches(){

        ArrayList<UniversityDegreeBranch> universityDegreeBranches = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery(GET_UNIVERSITY_DEGREES_BRANCHES,null);
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            universityDegreeBranches.add(new UniversityDegreeBranch(c.getInt(0),c.getString(1)));
        }
        return universityDegreeBranches;
    }

    public ArrayList<UniversityDegreeCampus> getUniversityDegreeCampus(){

        ArrayList<UniversityDegreeCampus> universityDegreeCampus = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery(GET_UNIVERSITY_DEGREES_CAMPUS,null);
        c.moveToFirst();

        for (int i = 0; i < c.getCount(); i++) {
            universityDegreeCampus.add(new UniversityDegreeCampus(c.getInt(0),c.getString(1)));
        }
        return universityDegreeCampus;
    }

    public ArrayList<UniversityDegreeCenter> getUniversityDegreeCenters(){

        ArrayList<UniversityDegreeCenter> universityDegreeCenters = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery(GET_UNIVERSITY_DEGREES_CENTERS,null);
        c.moveToFirst();
        Cursor b = getReadableDatabase().rawQuery("SELECT * FROM university_degree_campus WHERE campus_id=?", new String[] {String.valueOf(c.getInt(2))});
        b.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            universityDegreeCenters.add(new UniversityDegreeCenter(c.getInt(0), c.getString(1),new UniversityDegreeCampus(b.getInt(0),b.getString(1))));
        }
        return universityDegreeCenters;
    }

    public ArrayList<UniversityDegree> getUniversityDegrees(){

        ArrayList<UniversityDegree> universityDegrees = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("select * from university_degrees" +
                "    join university_center_degrees ucd on university_degrees.degree_id = ucd.degree_id" +
                "    join university_degree_branches udb on university_degrees.degree_branch = udb.branch_id" +
                "    join university_degree_centers udc on ucd.center_id = udc.center_id" +
                "    join university_degree_campus u on udc.center_campus = u.campus_id",null);
        Log.d("CHORIPAN",String.valueOf(c.getCount()));
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            universityDegrees.add(new UniversityDegree(c.getInt(0),c.getString(1),new UniversityDegreeBranch(c.getInt(5),c.getString(6)),new UniversityDegreeCampus(c.getInt(9),c.getString(10)),new UniversityDegreeCenter(c.getInt(3),c.getString(8),new UniversityDegreeCampus(c.getInt(9),c.getString(10)))));
        }

        return universityDegrees;
    }

}
