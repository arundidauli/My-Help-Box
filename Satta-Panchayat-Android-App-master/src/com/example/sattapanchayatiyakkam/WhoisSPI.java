package com.example.sattapanchayatiyakkam;

import com.example.unicodetoTamil.TamilUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class WhoisSPI extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.whoisspi);
		ImageButton imagebutton=(ImageButton) findViewById(R.id.imagebutton);
		imagebutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();	
				 Intent intent = new Intent(WhoisSPI.this, MainMenu.class);
				 startActivity(intent);
			}
		});
		initUI();
	}
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
	TextView text12;
	TextView text13;
	TextView text14;
	TextView text15;
	TextView text16;
	TextView text17;
	TextView text18;
	TextView text19;
	TextView text20;
	TextView text21;
	TextView text22;
	TextView text23;
	TextView text24;
	TextView text25;
	TextView text26;
	
	Typeface tfBamini;
	Typeface tfTam;
	
	private void initUI() {
		 text1=(TextView) findViewById(R.id.textView1);
		 text2=(TextView) findViewById(R.id.textView2);
		 text3=(TextView) findViewById(R.id.textView3);
		 text4=(TextView) findViewById(R.id.textView4);
		 text5=(TextView) findViewById(R.id.leader);
		 text6=(TextView) findViewById(R.id.associate);
		 text7=(TextView) findViewById(R.id.guidlines);
		 
		 text8=(TextView) findViewById(R.id.textView5);
		 text9=(TextView) findViewById(R.id.textView6);
		 text10=(TextView) findViewById(R.id.textView7);
		 text11=(TextView) findViewById(R.id.textView8);
		 text12=(TextView) findViewById(R.id.textView9);
		 text13=(TextView) findViewById(R.id.textView10);
		 text14=(TextView) findViewById(R.id.textView11);
		 text15=(TextView) findViewById(R.id.textView12);
		 text16=(TextView) findViewById(R.id.textView13);
		 text17=(TextView) findViewById(R.id.textView14);
		 text18=(TextView) findViewById(R.id.textView15);
		 text19=(TextView) findViewById(R.id.textView16);
		 text20=(TextView) findViewById(R.id.textView17);
		 text21=(TextView) findViewById(R.id.textView18);
		 text21=(TextView) findViewById(R.id.textView19);
		 text22=(TextView) findViewById(R.id.textView20);
		 text23=(TextView) findViewById(R.id.textView21);
		 text24=(TextView) findViewById(R.id.textView22);
		 text25=(TextView) findViewById(R.id.textView23);
		 text26=(TextView) findViewById(R.id.textView24);
		 
		 tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
		 
			String sivaName = "சிவ.இளங்கோ(36)";
			String sivaDesignation = "(இதழியல்), (அரசியல் அறிவியல்)";
			String senthilName = "செந்தில் ஆறுமுகம்(36)";
			String senthilDesignation = "தகவல் தொழில்நுட்ப பொறியாளர்(2005 வரை)";
			String sivaRole = "epWtdu; - jiytu;";
			String senthilRole = "epWtdu; - nghJr;nrayhsu;";
			String guidlinesHeading ="epGzu;fs; & MNyhrfu;fs;";
			
			String guidlinesContent = "1. செளரிராஜிலு, பதிவுத்துறை துணைத்தலைவர்(ஓய்வு)";
			String guidlinesContent1 = "2. செ.மா.அரசு, ஊழல் எதிர்ப்பு இயக்கம் & பொதுப்பணித்துறை தலைமைப் பொறியாளர் (ஓய்வு)";
			String guidlinesContent2 = "3. சிவராஜ், தாசில்தார்(ஓய்வு)";
			String guidlinesContent3 = "4. சீனிவாசன், முன்னாள் தகவல் ஆணையாளர், மாநில தகவல் ஆணையர், தமிழ்நாடு";
			String guidlinesContent4 = "5. திரு .மோகன் ராம், குடிமைப் பொருள் துறை(ஓய்வு)";
			String guidlinesContent5 = "6. திரு.ஆறுபாதி கல்யாணம், காவிரி டெல்டா விவசாய சங்கம்";
			String guidlinesContent6 = "7. திரு. நல்லோர் வட்டம் பாலசுப்ரமணியம், சமூக ஆர்வலர்";
			String guidlinesContent7 = "8. திரு. எண்ணங்களின் சங்கமம் J.பிரபாகர், சமூக ஆர்வலர்";
			String guidlinesContent8 = "9. திரு.பார்த்தசாரதி, கல்வியாளர்";
			String guidlinesContent9 = "10.பொறியாளர்.திரு.வீரப்பன், பொதுப்பணித்துறை(ஓய்வு)";
			String guidlinesContent10 = "11.திரு.கோவிந்தசாமி, மின்சாரத்துறை(ஓய்வு)";
			String guidlinesContent11 = "12.திரு.பாஸ்கர், கிராம நிர்வாக அலுவலர்(ஓய்வு)";
			String guidlinesContent12 = "13.திரு.தேவராஜன்,மின்சாரத்துறை(ஓய்வு) ";
			String guidlinesContent13 = "14.திரு.ராஜேந்திரன், இந்திய ரிசர்வ் வங்கி ";
			String guidlinesContent14 = "15.திரு.குருசாமி, கூட்டுறவுத் துறை(ஓய்வு)";
			String guidlinesContent15 = "16.திரு. ரங்கநாதன், குடிசை மாற்று வாரியம்";
			String guidlinesContent16 = "17.திரு. வில்வம், சார்புச் செயலாளர்(ஓய்வு), தலைமைச் செயலகம்";
			String guidlinesContent17 = "18.திரு. நாராயணசாமி, ஊராட்சி உதவி இயக்குனர்(ஓய்வு)";
			String guidlinesContent18 = "19.திரு. செல்வராஜ், மின்சாரத்துறை(ஓய்வு)";
			
			tfTam = Typeface.createFromAsset(getAssets(),"fonts/TAMGobi.ttf");
			
			
			assignTypeface1(text1,sivaName);
			assignTypeface1(text2,sivaDesignation);
			assignTypeface1(text3,senthilName);
			assignTypeface1(text4,senthilDesignation);
			assignTypeface(text5,sivaRole);
			assignTypeface(text6,senthilRole);
			assignTypeface(text7,guidlinesHeading);
			
			assignTypeface1(text8,guidlinesContent);
			assignTypeface1(text9,guidlinesContent1);
			assignTypeface1(text10,guidlinesContent2);
			assignTypeface1(text11,guidlinesContent3);
			assignTypeface1(text12,guidlinesContent4);
			assignTypeface1(text13,guidlinesContent5);
			assignTypeface1(text14,guidlinesContent6);
			assignTypeface1(text15,guidlinesContent7);
			assignTypeface1(text16,guidlinesContent8);
			assignTypeface1(text17,guidlinesContent9);
			assignTypeface1(text18,guidlinesContent10);
			assignTypeface1(text19,guidlinesContent11);
			assignTypeface1(text20,guidlinesContent12);
			assignTypeface1(text21,guidlinesContent13);
			assignTypeface1(text22,guidlinesContent14);
			assignTypeface1(text23,guidlinesContent15);
			assignTypeface1(text24,guidlinesContent16);
			assignTypeface1(text25,guidlinesContent17);
			assignTypeface1(text26,guidlinesContent18);				
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
