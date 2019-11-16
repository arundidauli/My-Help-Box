package com.techtutz.lovecalcuator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.CompoundButtonCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gelitenight.waveview.library.WaveView;
import com.techtutz.lovecalcuator.helper.WaveHelper;
import com.yangp.ypwaveview.YPWaveView;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private WaveHelper mWaveHelper;
    private TextView name1,name2,txt_percentage;
    private Intent intent;
    private YPWaveView herat1,heart2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intent=getIntent();
        name1=findViewById(R.id.name1);
        name2=findViewById(R.id.name2);
        herat1=findViewById(R.id.heart1);
        heart2=findViewById(R.id.heart2);
        txt_percentage=findViewById(R.id.txt_percentage);

        final int random = new Random().nextInt(26) + 65;
        final int random1 = new Random().nextInt(26) + 65;
        herat1.setProgress(random*10);
        heart2.setProgress(random1*10);
        final int tot=(random+random1)/2;
        final float total=tot/100.0f;
        System.out.println(tot+"\n"+total);

        txt_percentage.setText(String.format("%s %%", String.valueOf(tot)));
        //final float v=Float.parseFloat(random);


        name1.setText(intent.getStringExtra("name1"));
        name2.setText(intent.getStringExtra("name2"));
        final WaveView waveView = (WaveView) findViewById(R.id.wave);
        waveView.setShapeType(WaveView.ShapeType.CIRCLE);
        waveView.setWaveColor(
                Color.parseColor("#2198F3"),
                Color.parseColor("#2a65ed"));
        waveView.setBorder(20, R.color.red);
        mWaveHelper = new WaveHelper(waveView,total);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWaveHelper.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWaveHelper.start();
    }
}
