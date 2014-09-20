package ru.mr123150.planks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    switch (item.getItemId()) {
	    
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}*/
	
	public void startClick(View view){
		Intent intent=new Intent(MainActivity.this,PlanksActivity.class);
		startActivity(intent);
	}
	
	public void settingsClick(View view){
		
	}

}
