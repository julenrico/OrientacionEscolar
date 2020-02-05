package com.example.orientacionescolar.main;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "orientacionescolar.sqlite";
    private static final int DATABASE_VERSION = 1;

    /*public final String COUNT_UNIVERSITY_DEGREES = "SELECT COUNT(*) FROM university_degrees";

    public final String GET_UNIVERSITY_DEGREES = "SELECT * FROM university_degrees";
    public final String GET_UNIVERSITY_DEGREES_BRANCHES = "SELECT * FROM university_degree_branches";
    public final String GET_UNIVERSITY_DEGREES_CAMPUS = "SELECT * FROM university_degree_campus";
    public final String GET_UNIVERSITY_DEGREES_CENTERS = "SELECT * FROM university_degree_centers";*/

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*Todos los insert y delete*/

    /*public void insertBranch(SQLiteDatabase db, int branchId, String branchName){

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

        db.execSQL("INSERT INTO university_center_degrees (center_Id, degree_Id) VALUES (?,?)",new String [] {String.valueOf(centerId),String.valueOf(degreeId)});
    }

    public void insertToSuggested(UniversityDegree universityDegree){
        getWritableDatabase().execSQL("INSERT INTO suggested_university_degrees (degree_Id, center_Id) VALUES (?,?)", new String [] {String.valueOf(universityDegree.getDegreeId()), String.valueOf(universityDegree.getCenter().getCenterId())});
    }
    public void deleteFromSuggested(UniversityDegree universityDegree){
        getWritableDatabase().execSQL("DELETE FROM fav_university_degrees WHERE degree_Id = ? and center_Id=?", new String [] {String.valueOf(universityDegree.getDegreeId()), String.valueOf(universityDegree.getCenter().getCenterId())});
    }*/

    public void insertToFav(UniversityDegree universityDegree) {
        getWritableDatabase().execSQL("INSERT INTO fav_university_degrees (degree_Id, center_Id) VALUES (?,?)", new String[]{String.valueOf(universityDegree.getDegreeId()), String.valueOf(universityDegree.getCenter().getCenterId())});
    }

    public void deleteFromFav(UniversityDegree universityDegree) {
        getWritableDatabase().execSQL("DELETE FROM fav_university_degrees WHERE degree_Id = ? and center_Id=?", new String[]{String.valueOf(universityDegree.getDegreeId()), String.valueOf(universityDegree.getCenter().getCenterId())});
    }


    /*Select de los grados favoritos*/
    public ArrayList<UniversityDegree> getFavUniversityDegrees() {

        ArrayList<UniversityDegree> favUniversityDegrees = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("select * from university_degrees" +
                "    join university_center_degrees ucd on university_degrees.degree_id = ucd.degree_id" +
                "    join university_degree_branches udb on university_degrees.degree_branch = udb.branch_id" +
                "    join university_degree_centers udc on ucd.center_id = udc.center_id" +
                "    join university_degree_campus u on udc.center_campus = u.campus_id" +
                "    join fav_university_degrees fad on university_degrees.degree_id = fad.degree_id" +
                "    where university_degrees.degree_id = fad.degree_id and ucd.center_id = fad.center_id" +
                " order by degree_name asc", null);
        Log.d("CHORIPAN", String.valueOf(c.getCount()));
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            UniversityDegreeBranch branch = new UniversityDegreeBranch(c.getInt(5), c.getString(6));
            UniversityDegreeCampus campus = new UniversityDegreeCampus(c.getInt(10), c.getString(11));
            UniversityDegreeCenter center = new UniversityDegreeCenter(c.getInt(3), c.getString(8), campus);
            favUniversityDegrees.add(new UniversityDegree(c.getInt(0), c.getString(1), branch, campus, center));
            c.moveToNext();
        }

        return favUniversityDegrees;
    }

    /*public int getCountDegrees(){
        Cursor c = getReadableDatabase().rawQuery(COUNT_UNIVERSITY_DEGREES,null);
        if(!c.moveToFirst()){
            return 0;
        }
        return c.getCount();
    }*/


    /*Select de todos los grados universitarios*/
    public ArrayList<UniversityDegree> getUniversityDegrees() {

        ArrayList<UniversityDegree> universityDegrees = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("select * from university_degrees" +
                "    join university_center_degrees ucd on university_degrees.degree_id = ucd.degree_id" +
                "    join university_degree_branches udb on university_degrees.degree_branch = udb.branch_id" +
                "    join university_degree_centers udc on ucd.center_id = udc.center_id" +
                "    join university_degree_campus u on udc.center_campus = u.campus_id order by degree_name asc", null);
        Log.d("CHORIPAN", String.valueOf(c.getCount()));
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            UniversityDegreeBranch branch = new UniversityDegreeBranch(c.getInt(5), c.getString(6));
            UniversityDegreeCampus campus = new UniversityDegreeCampus(c.getInt(10), c.getString(11));
            UniversityDegreeCenter center = new UniversityDegreeCenter(c.getInt(3), c.getString(8), campus);
            universityDegrees.add(new UniversityDegree(c.getInt(0), c.getString(1), branch, campus, center));
            c.moveToNext();
        }

        return universityDegrees;
    }

    /*Select de los grados sugeridos en el test*/
    public ArrayList<UniversityDegree> getSuggestedUniversityDegrees(int suggestedBranchId, int provincia) {

        ArrayList<UniversityDegree> suggestedUniversityDegrees = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("select * from university_degrees" +
                "    join university_center_degrees ucd on university_degrees.degree_id = ucd.degree_id" +
                "    join university_degree_branches udb on university_degrees.degree_branch = udb.branch_id" +
                "    join university_degree_centers udc on ucd.center_id = udc.center_id" +
                "    join university_degree_campus u on udc.center_campus = u.campus_id" +
                "    where udb.branch_id = ? and u.campus_id = ?" +
                " order by degree_name asc", new String[]{String.valueOf(suggestedBranchId), String.valueOf(provincia)}, null);
        Log.d("CHORIPAN", String.valueOf(c.getCount()));
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            UniversityDegreeBranch branch = new UniversityDegreeBranch(c.getInt(5), c.getString(6));
            UniversityDegreeCampus campus = new UniversityDegreeCampus(c.getInt(10), c.getString(11));
            UniversityDegreeCenter center = new UniversityDegreeCenter(c.getInt(3), c.getString(8), campus);
            suggestedUniversityDegrees.add(new UniversityDegree(c.getInt(0), c.getString(1), branch, campus, center));
            c.moveToNext();
        }

        return suggestedUniversityDegrees;
    }

}
