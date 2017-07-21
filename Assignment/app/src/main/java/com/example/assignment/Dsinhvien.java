package com.example.assignment;

import java.util.ArrayList;

import Adapter.AdapterSinhVien;
import Adapter.ListViewAdapter;
import Database.Database;
import Model.LopHoc;
import Model.SinhVien;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class Dsinhvien extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dsinhvien);

		ListView listView = (ListView)findViewById(R.id.lv_showstudent);
		ArrayList<SinhVien> lopHocs = new ArrayList<SinhVien>();
		AdapterSinhVien adapter = new AdapterSinhVien(this, R.layout.item_show_student,lopHocs);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dsinhvien, menu);
		return true;
	}

}
