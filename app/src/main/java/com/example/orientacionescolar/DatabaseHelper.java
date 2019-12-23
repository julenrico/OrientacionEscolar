package com.example.orientacionescolar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String CREATE_TABLE_UNIVERSITY_DEGREE_BRANCHES = "CREATE TABLE university_degree_branches (branch_id int primary key, branch_name varchar(50) not null);";
    private final String CREATE_TABLE_UNIVERSITY_DEGREE_CAMPUS = "CREATE TABLE university_degree_campus (campus_id int primary key, campus_name varchar(50) not null);";
    private final String CREATE_TABLE_UNIVERSITY_DEGREES = "CREATE TABLE university_degrees (degree_id int primary key, degree_name varchar(50) not null, degree_branch int not null, constraint university_degrees_university_degree_branches_branch_id_fk foreign key (degree_branch) references university_degree_branches (branch_id));";
    private final String CREATE_TABLE_UNIVERSITY_DEGREE_CENTERS = "CREATE TABLE university_degree_centers (center_id int primary key, center_name varchar(100) not null, center_campus int not null, constraint university_degree_centers_university_degree_campus_campus_id_fk foreign key (center_campus) references university_degree_campus (campus_id));";
    private final String CREATE_TABLE_UNIVERSITY_CENTER_DEGREES = "CREATE TABLE university_center_degrees (center_id int not null, degree_id int not null, primary key (center_id, degree_id), constraint university_center_degrees_university_degree_centers_center_id_fk foreign key (center_id) references university_degree_centers (center_id), constraint university_center_degrees_university_degrees_degree_id_fk foreign key (degree_id) references university_degrees (degree_id));";

    public final String COUNT_UNIVERSITY_DEGREES = "SELECT COUNT(*) FROM univeristy_degrees";

    public final String GET_UNIVERSITY_DEGREES = "SELECT * FROM university_degrees";
    public final String GET_UNIVERSITY_DEGREES_BRANCHES = "SELECT * FROM university_degree_branches";
    public final String GET_UNIVERSITY_DEGREES_CAMPUS = "SELECT * FROM university_degree_campus";
    public final String GET_UNIVERSITY_DEGREES_CENTERS = "SELECT * FROM university_degree_centers";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_UNIVERSITY_DEGREE_BRANCHES);
        db.execSQL(CREATE_TABLE_UNIVERSITY_DEGREE_CAMPUS);
        db.execSQL(CREATE_TABLE_UNIVERSITY_DEGREES);
        db.execSQL(CREATE_TABLE_UNIVERSITY_DEGREE_CENTERS);
        db.execSQL(CREATE_TABLE_UNIVERSITY_CENTER_DEGREES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*Functions to insert data */

    public void insertBranch(SQLiteDatabase db, int branchId, String branchName){

        db.execSQL("INSERT INTO university_degree_branches (branch_Id, branch_Name) VALUES ("+branchId+","+ branchName+")");
    }

    public void insertCampus(SQLiteDatabase db, int campusId, String campusName){

        db.execSQL("INSERT INTO university_degree_campus (campus_Id, campus_Name) VALUES ("+campusId+","+ campusName+")");
    }

    public void insertDegree(SQLiteDatabase db, int degreeId, String degreeName, int degreeBranchId){

        db.execSQL("INSERT INTO university_degrees (degree_Id, degree_Name, degree_Branch) VALUES ("+degreeId+","+ degreeName+","+ degreeBranchId+")");
    }

    public void insertCenters(SQLiteDatabase db, int centerId, String centerName, int centerCampusId){

        db.execSQL("INSERT INTO university_degree_centers (center_Id, center_Name, center_Campus) VALUES ("+centerId+","+ centerName+","+ centerCampusId+")");
    }

    public void insertCenterDegrees(SQLiteDatabase db, int centerId, int degreeId){

        db.execSQL("INSERT INTO university_center_degrees (center_Id, degree_Id) VALUES ("+centerId+","+ degreeId+")");
    }

    public int getCountDegrees(){
        Cursor c = getReadableDatabase().rawQuery(COUNT_UNIVERSITY_DEGREES,null);
        return c.getCount();
    }

    public ArrayList<UniversityDegreeBranch> getUniversityDegreeBranches(){

        ArrayList<UniversityDegreeBranch> universityDegreeBranches = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery(GET_UNIVERSITY_DEGREES_BRANCHES,null);
        for (int i = 0; i < c.getCount(); i++) {
            universityDegreeBranches.add(new UniversityDegreeBranch(c.getInt(0),c.getString(1)));
        }
        return universityDegreeBranches;
    }

    public ArrayList<UniversityDegreeCampus> getUniversityDegreeCampus(){

        ArrayList<UniversityDegreeCampus> universityDegreeCampus = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery(GET_UNIVERSITY_DEGREES_CAMPUS,null);
        for (int i = 0; i < c.getCount(); i++) {
            universityDegreeCampus.add(new UniversityDegreeCampus(c.getInt(0),c.getString(1)));
        }
        return universityDegreeCampus;
    }

    /*public ArrayList<UniversityDegreeCenter> getUniversityDegreeCenters(){

        ArrayList<UniversityDegreeCenter> universityDegreeCenters = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery(GET_UNIVERSITY_DEGREES_CENTERS,null);
        for (int i = 0; i < c.getCount(); i++) {
            universityDegreeCenters.add(new UniversityDegreeCenter(c.getInt(0), c.getString(1), ));
        }
        return universityDegreeCenters;
    }

    public ArrayList<UniversityDegree>() getUniversityDegrees(){

        ArrayList<UniversityDegree> universityDegrees = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery(GET_UNIVERSITY_DEGREES,null);
        for (int i = 0; i < c.getCount(); i++) {
            universityDegrees.add(new UniversityDegree(c.getInt(0),c.getString(1),))
        }

        return universityDegrees;
    }*/

    //TODO: LINKEAR DATOS Y ARREGLAR LAS DOS FUNTZIONES

}
