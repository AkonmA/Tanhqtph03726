package com.example.assignment;

import java.util.ArrayList;

import Adapter.ListViewAdapter;
import Database.Database;
import Model.LopHoc;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class Dslop extends Activity {
	ListViewAdapter adapter;
	LopHoc lopHoc;
	Database db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dslop);
		ListView listView = (ListView)findViewById(R.id.lv_showclass);
		db = new Database(this);
		ArrayList<LopHoc> lopHocs = new ArrayList<LopHoc>();
		lopHocs.addAll(db.ShowClass());
		adapter = new ListViewAdapter(this, R.layout.item_show_class,lopHocs);
		listView.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dslop, menu);
		return true;
	}

}
