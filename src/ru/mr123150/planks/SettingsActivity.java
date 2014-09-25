package ru.mr123150.planks;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	EditText breakTime;
	EditText plankTime[] = new EditText[6];
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		// Show the Up button in the action bar.
		setupActionBar();
		
		breakTime=(EditText)findViewById(R.id.breakTime);
	}
	
	public void resetClick(View v){
		
	}
	
	public void breakMinusClick(View v){
		
	}
	
	public void breakPlusClick(View v){
		
	}
	
	public void plankMinusClick(View v){
		
	}
	
	public void plankPlusClick(View v){
		switch(v.getId()){
		case R.id.plankPlus0:
			Toast.makeText(this, "0", Toast.LENGTH_SHORT).show();
			break;
		case R.id.plankPlus1:
			Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
			break;
		case R.id.plankPlus2:
			Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
			break;
		case R.id.plankPlus3:
			Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
			break;
		case R.id.plankPlus4:
			Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
			break;
		case R.id.plankPlus5:
			Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
			break;
		default:
			Toast.makeText(this, "unknown", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
