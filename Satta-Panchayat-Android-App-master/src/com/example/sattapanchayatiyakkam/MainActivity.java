package com.example.sattapanchayatiyakkam;

import com.example.unicodetoTamil.TamilUtil;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	showSplashScreen sh;
	public boolean isrunning = false;
	protected Dialog mSplashDialog;
	
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		sh = (showSplashScreen) getLastNonConfigurationInstance();
		if (sh == null) {
			sh = savethesplashinstance();
		}
		
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			sh.showSplashScren();
		}
		setContentView(R.layout.activity_main);
		
		Typeface tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
		button = (Button) findViewById(R.id.tamil_button);
		button.setTypeface(tfBamini);
		String tamilString = "jkpo;";
		String rtiStringCon = TamilUtil.convertToTamil(TamilUtil.BAMINI, tamilString);
		button.setText(rtiStringCon);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public showSplashScreen savethesplashinstance() {
		return new showSplashScreen();
	}
	
	public Typeface getDefaultTypeFaces(){
		Typeface tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
		return tfBamini;
	}
	
	public static Typeface getDefaultTypeFace(){
		MainActivity act=new MainActivity();
		Typeface tfBamini = act.getDefaultTypeFaces();
		return tfBamini;
	}
	
	private class showSplashScreen {
		public void showSplashScren() {
			isrunning = true;
			mSplashDialog = new Dialog(MainActivity.this, R.style.SplashScreen);
			mSplashDialog.setContentView(R.layout.splashscreen);
			mSplashDialog.setCancelable(false);
			mSplashDialog.show();
			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					removeSplashScreen();
				}
			}, 6000);
		}

		
		private void removeSplashScreen() {

			if (mSplashDialog != null) {
				mSplashDialog.dismiss();
				mSplashDialog = null;
			}
		}
	}

	public void onClickFeature(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.tamil_button:
			Toast.makeText(getApplicationContext(), "Tamil is clicked", Toast.LENGTH_LONG).show();
			Intent i = new Intent();
			i.setClassName("com.example.sattapanchayatiyakkam","com.example.sattapanchayatiyakkam.MainMenu");
			//finish();
			startActivity(i);
			break;
		case R.id.english_button:
			Toast.makeText(getApplicationContext(), "Under Construction", Toast.LENGTH_LONG).show();
			break;
		default:
			finish();
			break;
		}
	}

}
