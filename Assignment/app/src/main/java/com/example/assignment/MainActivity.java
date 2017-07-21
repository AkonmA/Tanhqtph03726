package com.example.assignment;

import java.util.ArrayList;

import Database.Database;
import Model.LopHoc;
import Model.SinhVien;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	Button btnaddclass,btnaddstudent,btnshowclass,btnshowstudent;
	Database db;
	ArrayAdapter<String> spadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnaddclass = (Button)findViewById(R.id.btnAddClass);
        btnaddstudent = (Button)findViewById(R.id.btnAddsinhvien);
        btnshowclass = (Button)findViewById(R.id.btnshowClass);
        btnshowstudent = (Button)findViewById(R.id.btnShowSinhVien);

        db = new Database(this);

        btnaddclass.setOnClickListener(this);
        btnaddstudent.setOnClickListener(this);
        btnshowclass.setOnClickListener(this);
        btnshowstudent.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.btnAddClass:
			
			Button btnclear, btnadd;
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.layout_dialog_themlop);
            final EditText malop = (EditText) dialog.findViewById(R.id.ed_dialog_class_id);
            final EditText tenlop = (EditText) dialog.findViewById(R.id.ed_dialog_class_name);
            btnclear = (Button) dialog.findViewById(R.id.btn_dialog_class_clear);
            btnadd = (Button) dialog.findViewById(R.id.btn_dialog_class_add);
            btnclear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    malop.setText("");
                    tenlop.setText("");
                }
            });
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ma = malop.getText().toString();
                    String ten = tenlop.getText().toString();
                    LopHoc lop = new LopHoc(ma, ten);
                    if (db.checkClass(ma)) {
                        if (db.AddClass(lop)) {
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Da ton tai ma", Toast.LENGTH_SHORT).show();
                    }

                }


            });
            dialog.show();
			
			break;
		case R.id.btnAddsinhvien:
			Button btnsvclear;
            Button btnaddsv;

            Dialog dialogsv = new Dialog(this);
            dialogsv.setContentView(R.layout.layout_dialog_themsinhvien);
            final Spinner spinner = (Spinner) dialogsv.findViewById(R.id.sp_addst_id);
            final EditText masv = (EditText) dialogsv.findViewById(R.id.ed_dialog_addst_id);
            final EditText tensv = (EditText) dialogsv.findViewById(R.id.ed_dialog_addst_name);
            btnclear = (Button) dialogsv.findViewById(R.id.btn_dialog_addst_clear);
            btnadd = (Button) dialogsv.findViewById(R.id.btn_dialog_addst_add);
            
            ArrayList<String> listclass = db.GetMaLop();
            spadapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, listclass);
            spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(spadapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            btnclear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    masv.setText("");
                    tensv.setText("");
                }
            });
            btnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String malop = spinner.getSelectedItem().toString();
                    String masinhvien = masv.getText().toString();
                    String tensinhvien = tensv.getText().toString();

                    SinhVien student = new SinhVien(malop, masinhvien, tensinhvien);
                    if (db.checkmasv(masinhvien)) {
                    	if (db.AddStudent(student)) {
                            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                        }
						
					}
                    else {
                    	Toast.makeText(getApplicationContext(), "Da ton tai ma sinh vien", Toast.LENGTH_SHORT).show();
					}
                    


                }
            });
            dialogsv.show();
            
			
			break;
		case R.id.btnshowClass:
			Intent ishowclass = new Intent(MainActivity.this,Dslop.class);
			startActivity(ishowclass);
			
			break;
		case R.id.btnShowSinhVien:
			Intent ishowstudent = new Intent(MainActivity.this,Dsinhvien.class);
			startActivity(ishowstudent);
			
			
			break;

		default:
			break;
		}
		
	}
    
}
