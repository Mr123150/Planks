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
	
	public static final String APP_PREFERENCES = "settings";
	
	SharedPreferences settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_planks);
		setupActionBar();
		
		counter=1;
		counterTotal=6;
		breakTime=5;
		
		settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		
		timerText=(TextView)findViewById(R.id.timerText);
		timerRevText=(TextView)findViewById(R.id.timerRevText);
		
		breakTimerText=(TextView)findViewById(R.id.breakTimer);
		
		counterCurrentText=(TextView)findViewById(R.id.countCurrent);
		counterTotalText=(TextView)findViewById(R.id.countTotal);
		
		counterTotalText.setText(Integer.toString(counterTotal));
		
		mainTimer();
		/*new CountDownTimer(30000, 10) {

		     public void onTick(long timeLeft) {
		         timerText.setText((30000 - timeLeft)/1000 + ":" + ((30000 - timeLeft)%1000)/100);
		         timerRevText.setText(timeLeft/1000 + ":" + (timeLeft%1000)/100);
		     }

		     public void onFinish() {
		    	 timerText.setText("30:0");
		    	 timerRevText.setText("0:0");
	    		 breakTimerText.setVisibility(View.VISIBLE);
		     }
		  }.start();*/

	}
	
	public void mainTimer(){
		counterCurrentText.setText(Integer.toString(counter));
		
		mainTimer = new CountDownTimer(30000, 10) {

		     public void onTick(long timeLeft) {
		         timerText.setText((30000 - timeLeft)/1000 + ":" + ((30000 - timeLeft)%1000)/100);
		         timerRevText.setText(timeLeft/1000 + ":" + (timeLeft%1000)/100);
		     }

		     public void onFinish() {
		    	 timerText.setText("0:0");
		    	 timerRevText.setText("0:0");
		    	 breakTimerText.setVisibility(View.VISIBLE);
		    	 if(counter!=counterTotal){
		    		 ++counter;
		    		 breakTimerText.setText(Integer.toString(breakTime));
		    		 breakTimer();
		    	 }
		    	 else{
		    		 breakTimerText.setTextSize(120);
		    		 breakTimerText.setText("Done!");
		    	 }
		     }
		  };
		  mainTimer.start();
	}
	
	public void breakTimer(){
		new CountDownTimer(breakTime*1000, 10) {

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
			mainTimer.cancel();
			timerText.setText("0:0");
	    	timerRevText.setText("0:0");
	    	breakTimerText.setVisibility(View.VISIBLE);
			if(counter!=counterTotal){
	    		 ++counter;
	    		 breakTimerText.setText(Integer.toString(breakTime));
	    		 breakTimer();
	    	 }
	    	 else{
	    		 breakTimerText.setTextSize(120);
	    		 breakTimerText.setText("Done!");
	    	 }
		}
		return super.onOptionsItemSelected(item);
	}

}
