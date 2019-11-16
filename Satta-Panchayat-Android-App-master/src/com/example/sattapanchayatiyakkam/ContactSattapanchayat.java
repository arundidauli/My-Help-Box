package com.example.sattapanchayatiyakkam;

import com.example.unicodetoTamil.TamilUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class ContactSattapanchayat extends Activity 
{
	
	TextView text1;
	TextView text2;
	TextView text3;
	TextView text4;
	TextView text5;
	TextView text6;
	TextView text7;
	TextView text8;
	TextView text9;
	TextView text10;
	TextView text11;
	Typeface tfBamini;
	
	
	
	String addresstxt = "Kftup";
	String addresstextCont="rl;l gQ;rhaj;J ,af;fk;>"

+"41> g[hu; Nyd;> irjhg;Ngl;il> nrd;id- 600015"

+"( mz;zh rhiy> irjhg;Ngl;il fhty; epiyak; mUfpy; )"

+"jkpo;ehL> ";
	
	String mobiletxt = "miyNgrp";
	String mobiletextCont = "7667100100";
	String Emailtxt = "kpd;mQ;ry;";
	String EmailtextCont = "sattapanchayat@gmail.com";
	String facebooktxt = "KfE}y;";
	String facebooktextCont = "www.facebook.com/sattapanchayath";
	String websitetxt = "tiyg;G+ ";
	String websitetextCont = " www.sattapanchayat.org ";
	
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contactsattapanchayat);
		initUI();
	}

	private void initUI()
	{
		text1 = (TextView) findViewById(R.id.addresstext);
		text2 = (TextView) findViewById(R.id.addresstextContent);
		text3 = (TextView) findViewById(R.id.mobiletext);
		text4 = (TextView) findViewById(R.id.mobiletextContent);
		text5 = (TextView) findViewById(R.id.Emailtext);
		text6 = (TextView) findViewById(R.id.EmailtextContent);
		text7 = (TextView) findViewById(R.id.facebooktext);
		text8 = (TextView) findViewById(R.id.facebooktextContent);
		text9 = (TextView) findViewById(R.id.websitetext);
		text10 = (TextView) findViewById(R.id.websitetextContent);
		
		tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
		
		assignTypeface(text1,addresstxt);
		assignTypeface(text2,addresstextCont);
		assignTypeface(text3,mobiletxt);
		assignTypeface(text4,mobiletextCont);
		assignTypeface(text5,Emailtxt);
		text6.setText(EmailtextCont);
		assignTypeface(text7,facebooktxt);
		text8.setText(facebooktextCont);
		assignTypeface(text9,websitetxt);
		text10.setText(websitetextCont);
		
		/*assignTypeface(text3,mobiletxt);
		assignTypeface(text4,mobiletextCont);
		assignTypeface(text5,Emailtxt);
		assignTypeface(text6,EmailtextCont);
		assignTypeface(text7,facebooktxt);
		assignTypeface(text8,facebooktextCont);
		assignTypeface(text9,websitetxt);
		assignTypeface(text10,websitetextCont);*/
		
	}
	
	void assignTypeface(TextView wid,String str){
		wid.setTypeface(tfBamini);
		String stringCon = TamilUtil.convertToTamil(TamilUtil.BAMINI, str);
		wid.setText(stringCon);
	}
	public void onClickFeature(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.webButton:
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+websitetextCont));
			startActivity(browserIntent);
			break;
		case R.id.facebookButton:
			Intent facebookLink = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+facebooktextCont));
			startActivity(facebookLink);
			break;
		case R.id.emailButton:
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
				alertDialogBuilder.setTitle("Email");
				alertDialogBuilder.setMessage("Click yes to exit!").setCancelable(false).setPositiveButton("Submit",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							Intent i = new Intent(Intent.ACTION_SEND);
							i.setType("message/rfc822");
							i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"ravichandran.r@shriramvlaue.in"});
							i.putExtra(Intent.EXTRA_SUBJECT, "Doubt");
							i.putExtra(Intent.EXTRA_TEXT   , "Content");
							try {
							    startActivity(Intent.createChooser(i, "Send mail..."));
							} catch (android.content.ActivityNotFoundException ex) {
							    Toast.makeText(ContactSattapanchayat.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
							}
						}
					  })
					.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						}
					});
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
				
			
			
			break;
		case R.id.mobileButton:
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			callIntent.setData(Uri.parse("tel:"+mobiletextCont));
			startActivity(callIntent);
			break;
		case R.id.addressButton:
			String uri = "geo:"+ 13.0235 + "," + 80.2237;
			startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
			break;

		default:
			finish();
			break;
		}
	}
	
	//webButton facebookButton  emailButton  mobileButton addressButton
}






