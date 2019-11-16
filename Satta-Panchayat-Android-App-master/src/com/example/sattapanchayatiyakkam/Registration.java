package com.example.sattapanchayatiyakkam;

import com.example.unicodetoTamil.TamilUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends Activity
{
	TextView text0;
	TextView text1;
	TextView text2;
	TextView text3;
	TextView text4;
	TextView text5;
	TextView text6;
	TextView text7;
	EditText editText1;
	EditText editText2;
	EditText editText3;
	EditText editText4;
	EditText editText5;
	EditText editText6;
	
	
	Typeface tfBamini;
	Typeface tfTam;
	private RadioGroup radioDonateGroup;
	private Button btnDisplay;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.registration);
		ImageButton imagebutton=(ImageButton) findViewById(R.id.imagebutton);
		imagebutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 finish();	
				 Intent intent = new Intent(Registration.this, MainMenu.class);
				 startActivity(intent);
			}
		});
		initUI();
	}

	private void initUI() 
	{
		editText1 =(EditText) findViewById(R.id.editText1);
		editText2 =(EditText) findViewById(R.id.editText2);
		editText3 =(EditText) findViewById(R.id.editText3);
		editText4 =(EditText) findViewById(R.id.editText4);
		editText5 =(EditText) findViewById(R.id.editText5);
		editText6 =(EditText) findViewById(R.id.editText6);
		
		text0 = (TextView) findViewById(R.id.textView0);
		text1 = (TextView) findViewById(R.id.textView1);
		text2 = (TextView) findViewById(R.id.textView2);
		text3 = (TextView) findViewById(R.id.textView3);
		text4 = (TextView) findViewById(R.id.textView4);
		text5 = (TextView) findViewById(R.id.textView5);
		text6 = (TextView) findViewById(R.id.textView6);
		text7 = (TextView) findViewById(R.id.textView7);
		
		tfTam = Typeface.createFromAsset(getAssets(),"fonts/TAMGobi.ttf");
		tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
		btnDisplay = (Button) findViewById(R.id.button1);
		String str1 = "பெயர்";
		String str2 = "வயது";
		String str3 = "முழு முகவரி";
		String str4 = "இரத்தவகை";
		String str5 = "கல்வித்தகுதி	";
		String str6 = "அலைபேசி";
		String str7 = "உறுப்பினர் ";
		String str8 = "cWg;gpdu; Nru;f;if";

		assignTypeface1(text1, str1);
		assignTypeface1(text2, str2);
		assignTypeface1(text3, str3);
		assignTypeface1(text4, str4);
		assignTypeface1(text5, str5);
		assignTypeface1(text6, str6);
		assignTypeface1(text7, str7);
		assignTypeface(text0, str8);
		assignTypefacebutton(btnDisplay, str8);
		
		radioDonateGroup = (RadioGroup) findViewById(R.id.radioDonate);
		
		btnDisplay.setOnClickListener(new OnClickListener() {
	 
			@Override
			public void onClick(View v) {
				 RadioButton selectRadio = (RadioButton) findViewById(radioDonateGroup.getCheckedRadioButtonId());
					if(editText1.getText().length()==0){
						Toast.makeText(Registration.this,"Enter Name", Toast.LENGTH_SHORT).show();
					}
					if(editText2.getText().length()==0){
						Toast.makeText(Registration.this,"Enter Age", Toast.LENGTH_SHORT).show(); 
					}
					if(editText6.getText().length()==0){
						Toast.makeText(Registration.this,"Enter Mobile Number", Toast.LENGTH_SHORT).show();
					}else if(editText6.getText().length()>10){
						Toast.makeText(Registration.this,"Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
					}
				 String input = editText1.getText().toString()+";"+editText2.getText().toString()+";"+
				 editText3.getText().toString()+";"+editText4.getText().toString()+";"+
				 editText5.getText().toString()+";"+editText6.getText().toString()
				 +";"+selectRadio.getText().toString();
				 if(input.length()>130){
					 input = editText1.getText().toString()+";"+editText2.getText().toString()+";"+
					 ";"+editText4.getText().toString()+";"+
					 editText5.getText().toString()+";"+editText6.getText().toString()
					 +";"+selectRadio.getText().toString();
				 }
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage("9994572701", null, input, null, null);
			}
		});
	}
	
	private void assignTypefacebutton(Button wid, String sivaName) {
		wid.setTypeface(tfBamini);
		String stringCon = TamilUtil.convertToTamil(TamilUtil.BAMINI, sivaName);
		wid.setText(stringCon);
	}
	
	
	private void assignTypeface1(TextView wid, String sivaName) {
		wid.setTypeface(tfTam);
		String stringCon = TamilUtil.convertToTamil(TamilUtil.TSCII, sivaName);
		wid.setText(stringCon);
	}
	
	private void assignTypeface(TextView wid, String sivaName) {
		wid.setTypeface(tfBamini);
		String stringCon = TamilUtil.convertToTamil(TamilUtil.BAMINI, sivaName);
		wid.setText(stringCon);
	}
}
