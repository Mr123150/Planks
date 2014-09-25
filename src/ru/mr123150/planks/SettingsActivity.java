package ru.mr123150.planks;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends Activity {

	EditText breakTimeText;
	EditText plankTimeText[] = new EditText[6];
	
	int breakTime;
	int plankTime[] = new int[6];
	
	public static final String APP_PREFERENCES = "settings";
	public static final String APP_PREFERENCES_TIMER_0 = "Timer";
	public static final String APP_PREFERENCES_TIMER_1 = "Timer";
	public static final String APP_PREFERENCES_TIMER_2 = "Timer";
	public static final String APP_PREFERENCES_TIMER_3 = "Timer";
	public static final String APP_PREFERENCES_TIMER_4 = "Timer";
	public static final String APP_PREFERENCES_TIMER_5 = "Timer";
	public static final String APP_PREFERENCES_TIMER_BREAK = "TimerBreak";
	
	SharedPreferences settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		// Show the Up button in the action bar.
		setupActionBar();
		
		breakTimeText=(EditText)findViewById(R.id.breakTime);
		plankTimeText[0]=(EditText)findViewById(R.id.plankTime0);
		plankTimeText[1]=(EditText)findViewById(R.id.plankTime1);
		plankTimeText[2]=(EditText)findViewById(R.id.plankTime2);
		plankTimeText[3]=(EditText)findViewById(R.id.plankTime3);
		plankTimeText[4]=(EditText)findViewById(R.id.plankTime4);
		plankTimeText[5]=(EditText)findViewById(R.id.plankTime5);
		
		settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

		plankTime[0]=settings.getInt(APP_PREFERENCES_TIMER_0, 30);
		plankTime[1]=settings.getInt(APP_PREFERENCES_TIMER_1, 30);
		plankTime[2]=settings.getInt(APP_PREFERENCES_TIMER_2, 30);
		plankTime[3]=settings.getInt(APP_PREFERENCES_TIMER_3, 30);
		plankTime[4]=settings.getInt(APP_PREFERENCES_TIMER_4, 30);
		plankTime[5]=settings.getInt(APP_PREFERENCES_TIMER_5, 30);
		breakTime=settings.getInt(APP_PREFERENCES_TIMER_BREAK, 5);
		
		breakTimeText.setText(Integer.toString(breakTime));
		for(int i=0;i<6;++i){
			plankTimeText[i].setText(Integer.toString(plankTime[i]));
		}
		
	}
	
	public void resetClick(View v){
		breakTimeText.setText("5");
		for(int i=0;i<6;++i){
			plankTimeText[i].setText("30");
		}
	}
	
	public void breakMinusClick(View v){
		int time=Integer.parseInt(breakTimeText.getText().toString());
		if(time<1)time=1;
		breakTimeText.setText(Integer.toString(time-1));
	}
	
	public void breakPlusClick(View v){
		int time=Integer.parseInt(breakTimeText.getText().toString());
		breakTimeText.setText(Integer.toString(time+1));
	}
	
	public void plankMinusClick(View v){
		int i=0;
		switch(v.getId()){
		case R.id.plankPlus0:
			i=0;
			break;
		case R.id.plankPlus1:
			i=1;
			break;
		case R.id.plankPlus2:
			i=2;
			break;
		case R.id.plankPlus3:
			i=3;
			break;
		case R.id.plankPlus4:
			i=4;
			break;
		case R.id.plankPlus5:
			i=5;
			break;
		}
		int time=Integer.parseInt(plankTimeText[i].getText().toString());
		if(time<5)time=5;
		plankTimeText[i].setText(Integer.toString(time-5));
	}
	
	public void plankPlusClick(View v){
		int i=0;
		switch(v.getId()){
		case R.id.plankPlus0:
			i=0;
			break;
		case R.id.plankPlus1:
			i=1;
			break;
		case R.id.plankPlus2:
			i=2;
			break;
		case R.id.plankPlus3:
			i=3;
			break;
		case R.id.plankPlus4:
			i=4;
			break;
		case R.id.plankPlus5:
			i=5;
			break;
		}
		int time=Integer.parseInt(plankTimeText[i].getText().toString());
		plankTimeText[i].setText(Integer.toString(time+5));
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}*/

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
