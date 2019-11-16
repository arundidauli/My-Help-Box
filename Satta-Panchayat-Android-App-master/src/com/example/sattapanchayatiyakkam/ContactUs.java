package com.example.sattapanchayatiyakkam;

import android.os.Bundle;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ContactUs extends TabActivity 
{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contactus);
	
		ImageButton imagebutton=(ImageButton) findViewById(R.id.imagebutton);
		imagebutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();	
				 Intent intent = new Intent(ContactUs.this, MainActivity.class);
				 startActivity(intent);
			}
		});
		
		Resources ressources = getResources();
        TabHost tabHost = getTabHost();
         
        // ContactGovtOrgs tab
        Intent intentAndroid = new Intent().setClass(this, ContactGovtOrgs.class);
        TabSpec tabGovt = tabHost.newTabSpec("Govt")
                .setIndicator("Govt Organisations", ressources.getDrawable(R.drawable.ic_launcher))
                .setContent(intentAndroid);
         
        //ContactSattapanchayat tab
        Intent intentLinux = new Intent().setClass(this, ContactSattapanchayat.class);
        TabSpec tabSattapanchayat = tabHost.newTabSpec("Sattapanchayat")
                .setIndicator("Sattapanchayat", ressources.getDrawable(R.drawable.ic_launcher))
                .setContent(intentLinux); 
        
        tabHost.addTab(tabGovt);
        tabHost.addTab(tabSattapanchayat);
        
	}

}
