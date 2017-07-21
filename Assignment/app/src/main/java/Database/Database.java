package Database;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Model.LopHoc;
import Model.SinhVien;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper{
	public static final String dbname="Quanlysinhvien";
	public static final int db_version= 1;
	
	public static final String TB_Class="lophoc";
	public static final String TB_Student="sinhvien";
	
	public static final String CL_Class_Id="id";
	public static final String CL_Class_Code="code_class";
	public static final String CL_Class_Name ="name_class";
	
	public static final String CL_Student_Id="id";
	public static final String CL_Student_Code="code_student";
	public static final String CL_Student_Name ="name_student";
	
	
	public static final String Create_TBClass="create table " + TB_Class
			+ "(" + CL_Class_Id + " integer primary key, " +CL_Class_Code+ " text, " 
			+ CL_Class_Name +" text" + ")";
	private static final String Create_TBStudent = "create table "
            + TB_Student + "(" + CL_Student_Id+ " long primary key ,"
            + CL_Class_Code + " text, " + CL_Student_Code + " text, " + CL_Student_Name + " text " + ")";


	public Database(Context context) {
		super(context, dbname, null, db_version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Create_TBClass);
		db.execSQL(Create_TBStudent);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		db.execSQL("drop table if exists " + TB_Class);
        db.execSQL("drop table if exists " + TB_Student);
        onCreate(db);
		
	}
	public boolean checkClass(String ma) {
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "select * from " + TB_Class + " where " + CL_Class_Code + " = '" + ma + "'";
        Cursor cursor = database.rawQuery(query, null);

        Log.e("query", cursor.getCount() + "        " + query);

        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }
	
	public boolean AddClass(LopHoc lopHoc){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(CL_Class_Code, lopHoc.getMaLop());
		values.put(CL_Class_Name, lopHoc.getTenLop());
		return db.insert(TB_Class,null,values)!=-1;
		
	}
	public ArrayList<String> GetMaLop(){
		ArrayList<String> malopList = new ArrayList<String>();
		String Query = "SELECT " +  CL_Class_Code + " FROM " + TB_Class;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(Query, null);
		if (cursor.moveToFirst()) {
			do {
				malopList.add(cursor.getString(0));
			} while (cursor.moveToNext());
			
		}
		return malopList;
		
	}
	public ArrayList<LopHoc> ShowClass(){
		ArrayList<LopHoc> lopHocs = new ArrayList<LopHoc>();
		SQLiteDatabase db = this.getReadableDatabase();
		String Query = "SELECT * FROM " + TB_Class;
		Cursor cursor = db.rawQuery(Query, null);
		if (cursor.moveToFirst()) {
			do {
				LopHoc lopHoc = new LopHoc();
				lopHoc.setMaLop(cursor.getString(1));
				lopHoc.setTenLop(cursor.getString(2));
				lopHocs.add(lopHoc);
			} while (cursor.moveToNext());
			
		}
		
		return lopHocs;
	}
	
	public boolean checkmasv(String masv){
		String query = "select * from " + TB_Student + " where " + CL_Student_Code+ " = '" + masv + "'";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.getCount()> 0) {
			return false;
			
		}else {
			return true;
		}
	}
	public boolean AddStudent(SinhVien sinhvien){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(CL_Student_Id, System.currentTimeMillis());
		values.put(CL_Class_Code, sinhvien.getMaLop());
		values.put(CL_Student_Code, sinhvien.getMaSinhVien());
		values.put(CL_Student_Name, sinhvien.getTenSinhVien());
		return db.insert(TB_Student,null,values)!=-1;
		
	}
	public ArrayList<SinhVien> ShowSinhVien(){
		ArrayList<SinhVien> sinhViens = new ArrayList<SinhVien>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		String query= "SELECT * FROM " + TB_Student;
		Cursor cursor = db.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				SinhVien sinhVien = new SinhVien();

				sinhVien.setId(cursor.getLong(cursor.getColumnIndex(CL_Student_Id)));
				sinhVien.setMaLop(cursor.getString(cursor.getColumnIndex(CL_Class_Code)));
				sinhVien.setMaSinhVien(cursor.getString(cursor.getColumnIndex(CL_Student_Code)));
				sinhVien.setTenSinhVien(cursor.getString(cursor.getColumnIndex(CL_Student_Name)));

				sinhViens.add(sinhVien);
			} while (cursor.moveToNext());
			
		}
		
		return sinhViens;
	}
	
	public boolean deleteStudent(String id) {
		SQLiteDatabase database = this.getWritableDatabase();

		return (database.delete(TB_Student, CL_Student_Id + "=?", new String[] {id}) != -1);
	}
	
	public boolean updateStudent(SinhVien sinhVien) {
		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CL_Student_Code, sinhVien.getMaSinhVien());
		values.put(CL_Student_Name, sinhVien.getTenSinhVien());

		return (database.update(TB_Student, values, CL_Student_Id + "=?", new String[] {String.valueOf(sinhVien.getId())}) != -1);
	}
}