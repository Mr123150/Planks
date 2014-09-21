package ru.mr123150.planks;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class PlanksActivity extends Activity {

	MenuItem skipItem;
	
	TextView timerText;
	TextView timerRevText;
	
	int timerTime;
	int totalTime;
	
	Boolean reset=false;
	
	public static final String APP_PREFERENCES = "settings";
	public static final String APP_PREFERENCES_TIMER = "Timer";
	
	SharedPreferences settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_planks);
		// Show the Up button in the action bar.
		setupActionBar();
		
		settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
	
		/*reset = getIntent().getExtras().getBoolean("reset");
		
		if(reset){
			timerTime=0;
			
			Editor editor = settings.edit();
	        editor.putInt(APP_PREFERENCES_TIMER, 0);
	        editor.apply();
		}*/
		
		timerText=(TextView)findViewById(R.id.timerText);
		timerRevText=(TextView)findViewById(R.id.timerRevText);
		
		new CountDownTimer(30000 - timerTime, 10) {

		     public void onTick(long timeLeft) {
		    	 totalTime=(int)(timeLeft-timerTime);
		         timerText.setText((30000 - totalTime)/1000 + ":" + ((30000 - totalTime)%1000)/100);
		         timerRevText.setText(timeLeft/1000 + ":" + (timeLeft%1000)/100);
		     }

		     public void onFinish() {
		    	 timerText.setText("done!");
		         
		         Editor editor = settings.edit();
		         editor.putInt(APP_PREFERENCES_TIMER, 0);
		         editor.apply();
		     }
		  }.start();

	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		timerTime=settings.getInt(APP_PREFERENCES_TIMER, 0);
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();

        Editor editor = settings.edit();
        editor.putInt(APP_PREFERENCES_TIMER, (int)(30000-totalTime));
        editor.apply();
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
		getMenuInflater().inflate(R.menu.planks, menu);
		skipItem=menu.getItem(0);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_skip:
			skipItem.setEnabled(false);
			skipItem.setIcon(R.drawable.skip_disabled);
		}
		return super.onOptionsItemSelected(item);
	}

}
