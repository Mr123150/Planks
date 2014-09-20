package ru.mr123150.planks;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PlanksActivity extends Activity {

	MenuItem skipItem;
	TextView timerText;
	
	int timerTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_planks);
		// Show the Up button in the action bar.
		setupActionBar();
		
		timerText=(TextView)findViewById(R.id.timerText);
		
		new CountDownTimer(30000, 10) {

		     public void onTick(long timeLeft) {
		    	 int sec=(int)(30000 - timeLeft)/1000;
		    	 int msec=(int)((30000 - timeLeft)%1000)/10;
		    	 
		    	 String str_sec,str_msec;
		    	 if(sec<10) str_sec="0"+Integer.toString(sec); else str_sec=Integer.toString(sec);
		    	 if(msec<10) str_msec="0"+Integer.toString(msec); else str_msec=Integer.toString(msec);
		         timerText.setText(str_sec + ":" + str_msec);
		     }

		     public void onFinish() {
		         timerText.setText("done!");
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
		}
		return super.onOptionsItemSelected(item);
	}

}
