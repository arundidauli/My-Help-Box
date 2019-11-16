package com.example.sattapanchayatiyakkam;

import com.example.sattapanchayatiyakkam.MyQuickAction.ActionItem;
import com.example.sattapanchayatiyakkam.MyQuickAction.QuickAction;
import com.example.unicodetoTamil.TamilUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends Activity 
{
	Button rtiButton;
	Button docButton;
	Button corruptionButton;
	Button alcoholButton;
	Button joinusButton;
	Button contactusButton;
	
	String rtiString="jfty; mwpAk; cupikr; rl;lk;";
	String docString="muR Nritfs;";
	String corruptionString=",yQ;r-Coy; xopg;G";
	String alcoholString="kJ xopg;G";
	String joinusString="cWg;gpdu;fs; Nru;f;if";
	String contactusString="njhlu;Gf;F/ ahu;";
	Typeface tfBamini;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_menu);
		initUI();
	}
	
	
	private void initUI() {
		rtiButton = (Button) findViewById(R.id.rti_button);
		docButton = (Button) findViewById(R.id.doc_button);
		corruptionButton = (Button) findViewById(R.id.corruption_button);
		alcoholButton = (Button) findViewById(R.id.alcohol_button);
		joinusButton = (Button) findViewById(R.id.joinus_button);
		contactusButton = (Button) findViewById(R.id.contactus_button);
		
		tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
		
		assignTypeface(rtiButton,rtiString);
		assignTypeface(docButton,docString);
		assignTypeface(corruptionButton,corruptionString);
		assignTypeface(alcoholButton,alcoholString);
		assignTypeface(joinusButton,joinusString);
		assignTypeface(contactusButton,contactusString);
	}
	
	
	void assignTypeface(Button wid,String str){
		wid.setTypeface(tfBamini);
		String stringCon = TamilUtil.convertToTamil(TamilUtil.BAMINI, str);
		wid.setText(stringCon);
	}


	public void onClickFeature(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.rti_button:
			Toast.makeText(getApplicationContext(), "Tamil is clicked", Toast.LENGTH_LONG).show();
			Intent i = new Intent(MainMenu.this,RTIPage.class);
			//finish();
			startActivity(i);
			break;
		case R.id.doc_button:
			Toast.makeText(getApplicationContext(), "Document", Toast.LENGTH_LONG).show();
			break;
		case R.id.corruption_button:
			Toast.makeText(getApplicationContext(), "Corruption", Toast.LENGTH_LONG).show();
			break;
		case R.id.alcohol_button:
			Toast.makeText(getApplicationContext(), "Alcohol", Toast.LENGTH_LONG).show();
			break;
		case R.id.joinus_button:
			//Add action item
			ActionItem addAction = new ActionItem();
			addAction.setTitle("What is SPI?");
			addAction.setIcon(getResources().getDrawable(R.drawable.whatwedo));
			//Accept action item
			ActionItem accAction = new ActionItem();
			accAction.setTitle("Who?");
			accAction.setIcon(getResources().getDrawable(R.drawable.team));
			//Upload action item
			ActionItem upAction = new ActionItem();
			upAction.setTitle("Join Us");
			upAction.setIcon(getResources().getDrawable(R.drawable.joiningus));
			
			final QuickAction mQuickAction  = new QuickAction(this);
			   
			mQuickAction.addActionItem(addAction);
			mQuickAction.addActionItem(accAction);
			mQuickAction.addActionItem(upAction);
			 
			//setup the action item click listener
			mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
				
				@Override
				public void onItemClick(QuickAction source, int pos,
						int actionId) {
					// TODO Auto-generated method stub
					if (pos == 0) { //Add item selected
				           Toast.makeText(MainMenu.this, "What", Toast.LENGTH_SHORT).show();
				           Intent contactusintent = new Intent(MainMenu.this,whatisSPI.class);
						   startActivity(contactusintent);
				        } else if (pos == 1) { //Accept item selected
				           Toast.makeText(MainMenu.this, "Who", Toast.LENGTH_SHORT).show();
				           Intent contactusintent = new Intent(MainMenu.this,WhoisSPI.class);
						   startActivity(contactusintent);
				        } else if (pos == 2) { //Upload item selected
				           Toast.makeText(MainMenu.this, "Register", Toast.LENGTH_SHORT).show();
				           Intent contactusintent = new Intent(MainMenu.this,Registration.class);
						   startActivity(contactusintent);
				        }
				}
			});
			 mQuickAction.show(v);
			break;
		case R.id.contactus_button:
			Toast.makeText(getApplicationContext(), "ContactUs", Toast.LENGTH_LONG).show();
			Intent contactusintent = new Intent(MainMenu.this,ContactUs.class);
			finish(); 
			startActivity(contactusintent);
			break;
			
		default:
			finish();
			break;
		}
	}
}
