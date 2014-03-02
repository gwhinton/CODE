package com.example.code;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.code.database.DS;
import com.example.code.model.Charity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.app.ListActivity;

public class MainActivity extends ListActivity {
	
	private DS ds;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ds = new DS(this);
        ds.open();
        
        ds.addIdent("123123", "123123", "123123213");
        ds.addIdent("222", "nameofPlace", "website");
      
        List<Charity> val = ds.getAllCharities();
        
        ArrayAdapter<Charity> adapter = new ArrayAdapter<Charity>(this,
        		android.R.layout.simple_list_item_1, val);
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void search(View view) {
    	//Intent intent = new Intent(this, CharityListActivity.class);
        //startActivity(intent);
    }
    @Override
    protected void onResume(){
    	ds.open();
    	super.onResume();
    }
    
    @Override
    protected void onPause(){
    	ds.close();
    	super.onPause();
    }
}
