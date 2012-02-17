package com.arne5.guitartool;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenu extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	Button milkb, eyeb, stopb;

	MediaPlayer pmilk, peye;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		pmilk = MediaPlayer.create(this, R.raw.milk);

		peye = MediaPlayer.create(this, R.raw.eye);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// hide statusbar of Android

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		initialize();

	}

	private void initialize() {
		// TODO Auto-generated method stub

		milkb = (Button) findViewById(R.id.bMilk);

		eyeb = (Button) findViewById(R.id.bEye);
		eyeb.setOnClickListener(this);
		stopb = (Button) findViewById(R.id.bStop);
		stopb.setOnClickListener(this);
		milkb.setOnClickListener(this);

	}

	public void onClick(View v) {
		// on button clicks
		switch (v.getId()) {

		case R.id.bMilk:
			Intent beginit = new Intent("com.arne5.wally.PLAYVIDEO");
			startActivity(beginit);

			break;

		case R.id.bEye:
			try {
				peye.start();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case R.id.bStop:

			if (peye.isPlaying()) {
				peye.pause();
				peye.stop();
			}

			else if (pmilk.isPlaying()) {
				pmilk.pause();
				pmilk.stop();

			} else
				finish();

			break;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.AboutUs:
			Intent i = new Intent("com.arne5.wally.ABOUT");
			startActivity(i);
			break;
		case R.id.preferences:
			Intent p = new Intent("com.arne5.wally.PREFS");
			startActivity(p);

			break;
		case R.id.exit:
			finish();
			break;

		}
		return false;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		peye.release();

		pmilk.release();

	}

}