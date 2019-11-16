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


public class whatisSPI extends Activity
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
	
	Typeface tfBamini;
	Typeface tfTam;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.whatisspi);
		ImageButton imagebutton=(ImageButton) findViewById(R.id.imagebutton);
		imagebutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();	
				 Intent intent = new Intent(whatisSPI.this, MainMenu.class);
				 startActivity(intent);
			}
		});
		initUI();
	}

	private void initUI() 
	{
		text1 = (TextView) findViewById(R.id.what);
		text2 = (TextView) findViewById(R.id.textView2);
		text5 = (TextView) findViewById(R.id.textView3);
		text6 = (TextView) findViewById(R.id.textView4);
		text7 = (TextView) findViewById(R.id.textView5);
		text8 = (TextView) findViewById(R.id.textView6);
		text9 = (TextView) findViewById(R.id.textView7);
		text10 = (TextView) findViewById(R.id.textView8);
		text3 = (TextView) findViewById(R.id.whatwedo);
		text4 = (TextView) findViewById(R.id.textView9);
		
		text11 = (TextView) findViewById(R.id.textView10);
		text12 = (TextView) findViewById(R.id.textView11);
		text13 = (TextView) findViewById(R.id.textView12);
		text14 = (TextView) findViewById(R.id.textView13);
		text15 = (TextView) findViewById(R.id.textView14);
		text16 = (TextView) findViewById(R.id.textView15);
		text17 = (TextView) findViewById(R.id.textView16);
		text18 = (TextView) findViewById(R.id.textView17);
		text19 = (TextView) findViewById(R.id.textView18);
		text20 = (TextView) findViewById(R.id.textView19);
		text21 = (TextView) findViewById(R.id.textView20);
		text22 = (TextView) findViewById(R.id.textView21);
		text23 = (TextView) findViewById(R.id.textView22);
		text24 = (TextView) findViewById(R.id.textView23);
	
		 tfTam = Typeface.createFromAsset(getAssets(),"fonts/TAMGobi.ttf");
		 tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
		 String what = "Nehf;fq;fs;";
		 String whatContent = "நாட்டிலுள்ள அனைத்து குடிமக்களுக்கும்…";
		 String whatContent1 ="1. இலஞ்ச,ஊழல்,முறைகேடுகளற்ற சட்டத்தின் ஆட்சியை வழங்கி";
		 String whatContent2 ="2. பொருளாதார வளர்ச்சிக்கான வாய்ப்புகளை(கல்வி,தொழில்,விவசாயம்) அளித்து";
		 String whatContent3 ="3. தரமான சமூகக் கட்டமைப்பை(குடிநீர்,மின்சாரம்,மருத்துவம் போன்ற..) ஏற்படுத்தி";
		 String whatContent4 ="4. குடும்பங்களை,சமூகத்தைச் சீரழிக்கும் மதுவை ஒழித்து";
		 String whatContent5 ="5. இயற்கை வளங்கள், சுற்றுச்சூழல் பாதுகாப்பு போன்ற எதிர்கால தலைமுறையின் நலனில் அக்கறை செலுத்தி";
		 String whatContent6 =" அனைவருக்கும் வளர்ச்சி தரும் நல்லாட்சி அமைவதற்கான விழிப்புணர்வு, கருத்தாக்கம், களச்செயல்பாட்டுப் பணிகளுக்காக உருவாக்கப்பட்டதே சட்ட பஞ்சாயத்து இயக்கமாகும். ஜனநாயக நாட்டில், தங்களது உரிமைகளையும், கடமைகளையும் உணர்ந்த குடிமக்கள் உள்ள போது, நீதிக்குத் துணைநிற்கும்-அநீதிக்கு எதிர்நிற்கும் பொதுமக்கள் பெருகும்போது, ஆட்சியில் இருப்பவர்கள் யாராக இருந்தாலும் நல்லாட்சி தந்தாக வேண்டிய கட்டாயம் ஏற்படும். இதைத்தான் “குடி உயர, கோன் உயரும்” என்கிறது நம் முதுமொழி. இந்த அடிப்படையில், ஜனநாயகம், நல்லாட்சி குறித்த விழிப்புணர்வை ஏற்படுத்தி தங்கள் கடமைகளை உணர்ந்து செயலாற்றி, உரிமைகளை உரத்த குரலில் கேட்பதற்கு குடிமக்களைத் தயார்படுத்தும் இயக்கமே சட்ட பஞ்சாயத்து இயக்கம்.";
		 
		 String whatwedo = "nray;jpl;lq;fs;";
		 	String whatwedocontent8 = "இலஞ்ச-ஊழலுக்கு எதிராய்";
			String whatwedocontent = "1. நாட்டிலுள்ள அனைத்து குடிமக்களுக்கும்…";
			String whatwedocontent1 = "2. இலஞ்சம் தராமல் அரசு சேவைகளைப் பெற, வழிகாட்ட தொலைபேசி உதவி மையம்";
			String whatwedocontent2 = "3. அரசு திட்டங்கள், மக்கள் உரிமைகள், மக்கள் சாசனம் குறித்து விளக்களித்தல்";
			String whatwedocontent3 = "4. பொதுமக்களுக்குத் தேவைப்படும் அரசு அலுவலக தொலைபேசி எண்கள், முகவரி தந்து உதவுதல்";
			String whatwedocontent4 = "5. ரேசன் கார்டு,ஓட்டுனர் உரிமம்,கல்விக்கடன் போன்ற அரசு சேவைகள் குறித்த கையேடுகள் வெளியிடுதல்";
			String whatwedocontent5 = "6. தகவல் உரிமைச் சட்ட விழிப்புணர்வு, பயிற்சி";
			String whatwedocontent6 = "7. சமூக அக்கறையுள்ள இளைஞர்களுக்கு தங்கள் ஊரில் இலஞ்ச-ஊழலுக்கு எதிராய் அறிவார்ந்த முறையில் செயல்பட பயிற்சி";
			String whatwedocontent7 = "8. அரசு நிர்வாக, அரசியல் நடைமுறை சீர்கேடுகள், அதைத் தடுக்கத் தேவைப்படும் சீர்திருத்தங்களை முன்வைத்தல்";
			
			String whatwedocontent9 = "மது ஒழிப்பு";
			String whatwedocontent10 = "1. மதுக்கடைகளை மூடக்கோரும் போராட்டங்கள்";
			String whatwedocontent11 = "2. உயிர் குடிக்கும் மதுவின் தீமைகளை விளக்கி பள்ளி,கல்லூரிகளில் விழிப்புணர்வுப் பணிகள்";
			String whatwedocontent12 = "3. மது ஒழிப்பு ஆர்வலர்கள், இயக்கங்களை ஒருங்கிணைத்தல்";
			String whatwedocontent13 = "4. மது ஒழிப்பு குறித்த கருத்தாக்கத்தை வலுப்படுத்த நூல்கள், ஒளிப்படங்கள் வெளியீடு";
		 
		    assignTypeface(text1,what);
		    assignTypeface1(text2,whatContent);
		    assignTypeface1(text5,whatContent1);
		    assignTypeface1(text6,whatContent2);
		    assignTypeface1(text7,whatContent3);
		    assignTypeface1(text8,whatContent4);
		    assignTypeface1(text9,whatContent5);
		    assignTypeface1(text10,whatContent6);
			assignTypeface(text3,whatwedo);
			
			
			assignTypeface1(text11, whatwedocontent);
			assignTypeface1(text12, whatwedocontent1);
			assignTypeface1(text13, whatwedocontent2);
			assignTypeface1(text14, whatwedocontent3);
			assignTypeface1(text15, whatwedocontent4);
			assignTypeface1(text16, whatwedocontent5);
			assignTypeface1(text17, whatwedocontent6);
			assignTypeface1(text18, whatwedocontent7);
			assignTypeface1(text19, whatwedocontent8);
			assignTypeface1(text20, whatwedocontent9);
			assignTypeface1(text21, whatwedocontent10);
			assignTypeface1(text22, whatwedocontent11);
			assignTypeface1(text23, whatwedocontent12);
			assignTypeface1(text24, whatwedocontent13);
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
