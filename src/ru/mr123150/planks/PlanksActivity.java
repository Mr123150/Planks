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
	TextView timerRevText;
	
	int timerTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_planks);
		// Show the Up button in the action bar.
		setupActionBar();
		
		timerText=(TextView)findViewById(R.id.timerText);
		timerRevText=(TextView)findViewById(R.id.timerRevText);
		
		new CountDownTimer(30000, 10) {

		     public void onTick(long timeLeft) {
		         timerText.setText((30000 - timeLeft)/1000 + ":" + ((30000 - timeLeft)%1000)/100);
		         timerRevText.setText(timeLeft/1000 + ":" + (timeLeft%1000)/100);
		     }

		     public void onFinish() {
		         
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
