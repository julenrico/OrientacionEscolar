package com.example.orientacionescolar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String CREATE_TABLE_UNIVERSITY_DEGREE_BRANCHES = "CREATE TABLE university_degree_branches (branch_id int primary key, branch_name varchar(50) not null);";
    private final String CREATE_TABLE_UNIVERSITY_DEGREE_CAMPUS = "CREATE TABLE university_degree_campus (campus_id int primary key, campus_name varchar(50) not null);";
    private final String CREATE_TABLE_UNIVERSITY_DEGREES = "CREATE TABLE university_degrees (degree_id int primary key, degree_name varchar(50) not null, degree_branch int not null, constraint university_degrees_university_degree_branches_branch_id_fk foreign key (degree_branch) references university_degree_branches (branch_id));";
    private final String CREATE_TABLE_UNIVERSITY_DEGREE_CENTERS = "CREATE TABLE university_degree_centers (center_id int primary key, center_name varchar(100) not null, center_campus int not null, constraint university_degree_centers_university_degree_campus_campus_id_fk foreign key (center_campus) references university_degree_campus (campus_id));";
    private final String CREATE_TABLE_UNIVERSITY_CENTER_DEGREES = "CREATE TABLE university_center_degrees (center_id int not null, degree_id int not null, primary key (center_id, degree_id), constraint university_center_degrees_university_degree_centers_center_id_fk foreign key (center_id) references university_degree_centers (center_id), constraint university_center_degrees_university_degrees_degree_id_fk foreign key (degree_id) references university_degrees (degree_id));";

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

    public void insertBranch(int branchId, String branchName){

    }

    public void insertCampus(int campusId, String campusName){

    }

    public void insertDegree(int degreeId, String degreeName, int degreeBranchId){

    }

    public void insertCenters(int centerId, String centerName, int centerCampusId){

    }

    public void insertCenterDegrees(int centerId, int degreeId){

    }
}
