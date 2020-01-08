package com.example.orientacionescolar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.orientacionescolar.Interface.OrientacionEscolarApi;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String CREATE_TABLE_UNIVERSITY_DEGREE_BRANCHES = "CREATE TABLE university_degree_branches (branch_id int primary key, branch_name varchar(50) not null);";
    private final String CREATE_TABLE_UNIVERSITY_DEGREE_CAMPUS = "CREATE TABLE university_degree_campus (campus_id int primary key, campus_name varchar(50) not null);";
    private final String CREATE_TABLE_UNIVERSITY_DEGREES = "CREATE TABLE university_degrees (degree_id int primary key, degree_name varchar(50) not null, degree_branch int not null, constraint university_degrees_university_degree_branches_branch_id_fk foreign key (degree_branch) references university_degree_branches (branch_id));";
    private final String CREATE_TABLE_UNIVERSITY_DEGREE_CENTERS = "CREATE TABLE university_degree_centers (center_id int primary key, center_name varchar(100) not null, center_campus int not null, constraint university_degree_centers_university_degree_campus_campus_id_fk foreign key (center_campus) references university_degree_campus (campus_id));";
    private final String CREATE_TABLE_UNIVERSITY_CENTER_DEGREES = "CREATE TABLE university_center_degrees (center_id int not null, degree_id int not null, primary key (center_id, degree_id), constraint university_center_degrees_university_degree_centers_center_id_fk foreign key (center_id) references university_degree_centers (center_id), constraint university_center_degrees_university_degrees_degree_id_fk foreign key (degree_id) references university_degrees (degree_id));";

    public final String COUNT_UNIVERSITY_DEGREES = "SELECT COUNT(*) FROM university_degrees";

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

    public ArrayList<UniversityDegreeCenter> getUniversityDegreeCenters(){

        ArrayList<UniversityDegreeCenter> universityDegreeCenters = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery(GET_UNIVERSITY_DEGREES_CENTERS,null);
        c.moveToFirst();
        Cursor b = getReadableDatabase().rawQuery("SELECT * FROM university_degree_campus WHERE campusId=?", new String[] {String.valueOf(c.getInt(2))});
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
        for (int i = 0; i < c.getCount(); i++) {
            universityDegrees.add(new UniversityDegree(c.getInt(0),c.getString(1),new UniversityDegreeBranch(c.getInt(5),c.getString(6)),new UniversityDegreeCampus(c.getInt(9),c.getString(10)),new UniversityDegreeCenter(c.getInt(3),c.getString(8),new UniversityDegreeCampus(c.getInt(9),c.getString(10)))));
        }

        return universityDegrees;
    }

    public void loadDataFromApi(){
        Log.d("DATA","loadDataFromApi");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.101:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrientacionEscolarApi orientacionEscolarApi = retrofit.create(OrientacionEscolarApi.class);

        orientacionEscolarApi.getUniversityDegreeCampusFromApi().enqueue(new Callback<List<UniversityDegreeCampus>>() {
            @Override
            public void onResponse(Call<List<UniversityDegreeCampus>> call, Response<List<UniversityDegreeCampus>> response) {
                Log.d("DATA","called UniversityDegreeCampus");
                response.body().forEach(campus->insertCampus(getWritableDatabase(),campus.getCampusId(),campus.getCampusName()));
            }

            @Override
            public void onFailure(Call<List<UniversityDegreeCampus>> call, Throwable t) {
                t.getStackTrace();
            }
        });

        orientacionEscolarApi.getUniversityDegreeBranchFromApi().enqueue(new Callback<List<UniversityDegreeBranch>>() {
            @Override
            public void onResponse(Call<List<UniversityDegreeBranch>> call, Response<List<UniversityDegreeBranch>> response) {
                Log.d("DATA","called UniversityDegreeBranch");
                response.body().forEach(branch->insertBranch(getWritableDatabase(),branch.getBranchId(),branch.getBranchName()));
            }

            @Override
            public void onFailure(Call<List<UniversityDegreeBranch>> call, Throwable t) {
                t.getStackTrace();
            }
        });

        orientacionEscolarApi.getUniversityDegreesFromApi().enqueue(new Callback<List<UniversityDegree>>() {
            @Override
            public void onResponse(Call<List<UniversityDegree>> call, Response<List<UniversityDegree>> response) {
                Log.d("DATA","called UniversityDegrees");
                response.body().forEach(degree->insertDegree(getWritableDatabase(),degree.getDegreeId(),degree.getDegreeName(),degree.getBranch().getBranchId()));
            }

            @Override
            public void onFailure(Call<List<UniversityDegree>> call, Throwable t) {
                t.getStackTrace();
            }
        });

        orientacionEscolarApi.getUniversityDegreeCenterFromApi().enqueue(new Callback<List<UniversityDegreeCenter>>() {
            @Override
            public void onResponse(Call<List<UniversityDegreeCenter>> call, Response<List<UniversityDegreeCenter>> response) {
                Log.d("DATA","called UniversityDegreeCenters");
                response.body().forEach(center->insertCenters(getWritableDatabase(),center.getCenterId(),center.getCenterName(),center.getCampus().getCampusId()));

            }

            @Override
            public void onFailure(Call<List<UniversityDegreeCenter>> call, Throwable t) {
                t.getStackTrace();
            }
        });
    }

    //TODO: LINKEAR DATOS Y ARREGLAR LAS DOS FUNTZIONES

}
