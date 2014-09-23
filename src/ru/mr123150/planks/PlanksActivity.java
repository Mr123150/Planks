package ru.mr123150.planks;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class PlanksActivity extends Activity {

	MenuItem skipItem;
	
	TextView timerText;
	TextView timerRevText;
	TextView breakTimerText;
	TextView counterCurrentText;
	TextView counterTotalText;
	
	CountDownTimer mainTimer;
	
	int counter;
	int counterTotal;
	int breakTime;
	
	String timerTimeString;
	int timerTime[]=new int[6];
	
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
		setContentView(R.layout.activity_planks);
		setupActionBar();
		
		counter=0;
		counterTotal=6;
		
		settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

		timerTime[0]=settings.getInt(APP_PREFERENCES_TIMER_0, 30);
		timerTime[1]=settings.getInt(APP_PREFERENCES_TIMER_1, 30);
		timerTime[2]=settings.getInt(APP_PREFERENCES_TIMER_2, 30);
		timerTime[3]=settings.getInt(APP_PREFERENCES_TIMER_3, 30);
		timerTime[4]=settings.getInt(APP_PREFERENCES_TIMER_4, 30);
		timerTime[5]=settings.getInt(APP_PREFERENCES_TIMER_5, 30);
		breakTime=settings.getInt(APP_PREFERENCES_TIMER_BREAK, 5);
		
		timerText=(TextView)findViewById(R.id.timerText);
		timerRevText=(TextView)findViewById(R.id.timerRevText);
		
		breakTimerText=(TextView)findViewById(R.id.breakTimer);
		
		counterCurrentText=(TextView)findViewById(R.id.countCurrent);
		counterTotalText=(TextView)findViewById(R.id.countTotal);
		
		counterTotalText.setText(Integer.toString(counterTotal));
		
		mainTimer();
	}
	
	public void mainTimer(){
		final int timer=timerTime[counter];
		counterCurrentText.setText(Integer.toString(counter+1));
		
		mainTimer = new CountDownTimer(timer, 10) {

		     public void onTick(long timeLeft) {
		         timerText.setText((timer - timeLeft)/1000 + ":" + ((timer - timeLeft)%1000)/100);
		         timerRevText.setText(timeLeft/1000 + ":" + (timeLeft%1000)/100);
		     }

		     public void onFinish() {
		    	 timerFinish();
		     }
		  };
		  mainTimer.start();
	}
	
	public void breakTimer(){
		new CountDownTimer(breakTime*1000, 100) {

		     public void onTick(long timeLeft) {
		    	 breakTimerText.setText(Long.toString(timeLeft/1000+1));
		     }

		     public void onFinish() {
		    	 breakTimerText.setText("0");
		    	 breakTimerText.setVisibility(View.GONE);
		    	 mainTimer();
		     }
		  }.start();
	}
	
	public void timerFinish(){
		timerText.setText("0:0");
   	 	timerRevText.setText("0:0");
   	 	breakTimerText.setVisibility(View.VISIBLE);
   	 	if(++counter!=counterTotal){
   	 		breakTimerText.setText(Integer.toString(breakTime));
   	 		breakTimer();
   	 	}
   	 	else{
   	 		breakTimerText.setTextSize(120);
   	 		breakTimerText.setText("Done!");
   	 	}
	}
	
	public void donePressed(View v){
		NavUtils.navigateUpFromSameTask(this);
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
			//skipItem.setEnabled(false);
			//skipItem.setIcon(R.drawable.skip_disabled);
			mainTimer.cancel();
			timerFinish();
		}
		return super.onOptionsItemSelected(item);
	}

}
