package com.example.sattapanchayatiyakkam;

import java.util.ArrayList;

import com.example.unicodetoTamil.TamilUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RTIPage extends Activity {
	int currentPage = 0;
	public ArrayList<String> title = getTitleval();
	public ArrayList<String> subTitle = getSubTitleval();
	public ArrayList<String> data = getData();
	LinearLayout lay1;
	LinearLayout lay2;
	LinearLayout lay3;
	LinearLayout lay4;
	LinearLayout lay5;
	LinearLayout lay6;
	LinearLayout lay7;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.rticontent);
		ImageButton imagebutton=(ImageButton) findViewById(R.id.imagebutton);
		imagebutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();	
				 Intent intent = new Intent(RTIPage.this, MainMenu.class);
				 startActivity(intent);
			}
		});
		initUI();
	}

	
	private void initUI() {
		
		MyPagerAdapter adapter = new MyPagerAdapter();
		ViewPager myPager = (ViewPager) findViewById(R.id.myfivepager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);
		/*lay1 = (LinearLayout) findViewById(R.id.text);
		lay2 = (LinearLayout) findViewById(R.id.text2);
		lay3 = (LinearLayout) findViewById(R.id.text3);
		lay4 = (LinearLayout) findViewById(R.id.text4);
		lay5 = (LinearLayout) findViewById(R.id.text5);
		lay6 = (LinearLayout) findViewById(R.id.text6);
		lay7 = (LinearLayout) findViewById(R.id.text7);*/
		myPager.setOnPageChangeListener(new PageListener());

	}

	
	private class PageListener extends SimpleOnPageChangeListener {
		public void onPageSelected(int position) {
			currentPage = position + 1;
			((TextView) findViewById(R.id.txtpageNumber)).setText("Page "
					+ currentPage + "/" + 10);
		}
	}

	ArrayList<String> getData() {
		ArrayList<String> data = new ArrayList<String>();
		data.add("         ek; ehl;L kf;fSf;F gfypy; fpilj;j Kjy; Rje;jpuk; jhd; jfty; ngWk; cupikr;rl;lk;."
				+ " kf;fs; tupg;gzj;jpy; murhq;fk; nray;gLfpwJ, mg;gb kf;fspd; tupg;gzj;jpy; kf;fspd; eyDf;fhfr; nray;gLk;"
				+ "murhq;fj;jpy; elg;gJ vd;d, ele;jJ vd;d Nurd; fhHL Ntz;b, gl;lh Ntz;b gpwg;G ,wg;G tUkhdk; thup"
				+ "Nghd;w rhd;Wfs; Ntz;b murplk; tpz;zg;gpj;jhy; mjd; kPJ mjpfhupfs; vLj;j eltbf;ifia njupe;J nfhs;Sk; cupikia kf;fSf;F kWj;jJ nts;isf;fhu muR nfhz;L te;j muR ufrparl;lk;, ufrpa rl;lk; ek;ktH Ml;rpapYk;  Mz;Lfs; njhlHe;jd."
				+ "   CoYf;F vjpuhf jfty; ngWk; cupikf;fhf gy jd;dhHt mikg;Gfs; neba Nghuhl;lq;fis elj;jpd fpuhkg;Gw kf;fspd;  tpopg;GzHtP %yNk r%fg; Gul;rpiaVw;gLj;j Kbk; vd;gij cyf ehLfSf;F czHj;jpa jpUkjp mUzhuha; mtHfs; epWtp elj;jp tUk;  tptrha njhopyhsH  tpopg;GzHtP ,af;fj;jpd; Nghuhl;lk;jhd; jfty; ngWk; cupikr;rl;lk; nfhz;Ltu Kjd;ik fhuzkhf ,Ue;jJ"
				+ "   mjd; gb nfhz;L te;j jfty; ngWk; cupikr; rl;lj;ij ehlhSkd;w cWg;gpdH jpU vjHrd; ehr;rpag;gd; mtHfs; jiyikapy; ,r;rl;lj;jpw;fhf xU FO mikf;fg;gl;lJ  mf;FO gupe;Jiuj;j rl;l Kd;tbtPy; kf;fsitapy; mwpKfg;gLj;jg;gl;lJ 11.05.2005 kf;fsitapYk; 12.05.2005 khepyq;fsitapYk; ,r;rl;lk; epiwNtw;wg;gl;lJ 15.06.2005k; Njjp NkjF FbauR jiytuhy; xg;Gjy; mspf;fg;gl;L  ek; ehL KOtJk;  [k;K fh kPH jtpu 12.10.2005k; Njjpad;W nray;ghl;bw;F te;jJ"
				+ "   mjd; %yk; murpd; epHthfk; ntspg;gilahf;fg;gl;lJ  ve;j xU FbkfDf;Fk; gjpyspf;Fk; flik murhq;fj;jpw;Fk; mjd; mjpfhupfSf;Fk; cz;L vd;gij czHj;jps;sJ  ,yQr Coy; nfhQrk; nfhQrkhf jLf;fg;gl;L tUfpwJ"
				+ "   vk;vy;v, vk;gp f;fSf;F muR epHthfj;jplk; Nfs;tp Nfl;f cs;s mjpfhuk;Nghy ,r;rl;lk; %yk;   fl;lzj;jpy; kf;fSf;Fk; fpilj;Js;sJ  jfty; nfhLf;fhj mjpfhupf;F ehs; xd;Wf;F 250 tPjk; 25000 Mapuk; tiu mguhjk;  tpjpf;ftPk; ,r;rl;lk; top tFf;fpwJ"
				+ "  r;rl;lk; gw;wp “rl;l gQ;rhaj;J” njhlHe;J gj;jpupiffs;, njhiyNgrp MNyhrid %yk;  tpopg;GzHtP nra;J tUfpwJ  mg;NghJ thrfHfSk; nghJkf;fSk; jfty; ngWk; cupik rl;lk; gw;wp xU ifNaL ntspaplyhNk vd  tpUg;gk; njuptpj;jdH  mjd; mbg;gilapy; ,f;ifNal;bid njhFj;J"
				+ "25.01.2009 jpU. cja%Hj;jp mtHfs; jiyikapy; khepyj;jiyikj;jfty; MizaH  jpU ,uhkfpU{zd; khtl;l Ml;rpau; Xa;T mtHfshy; nrd;idapy; ntspaplg;gl;lJ"
				+ "gb q;fs;Nfs; tpf; fizfisj; njhLq;fs; jfty; ngWq;fs; ,yQr CoYf;F vjpuhd xU mikjpg; Nghiuj; njhlq;Fq;fs;  “rl;l gQ;rhaj;J” vg;NghJk; cq;fSf;Fj; Jiz epw;Fk;"
				+ "");
		data.add("1.muR mYtyfq;fs; xt;nthd;wpd; nray;ghl;bYk; ntspg;gilahd xsptPkiwtw;w muR epHthfj;ijj; nfhz;L tUjy;");
		data.add("2.muR kw;Wk; muR rhHe;j mYtyfq;fspy; gzpGupgtHfspd; nghWg;GzHit Nkk;gLj;jp tpiuthf gzp nra;a itj;jy;");
		data.add("3.ve;j xU FbkfDf;Fk; gjpyspf;Fk; flik murhq;fj;jpw;Fk; mjd;mjpfhupfSf;Fk; cz;L vd;gij czur; nra;jy;");
		data.add("4.muR kw;Wk; muR rhHe;j mYtyfq;fspd; nray;ghLfs; Fo=j;j jfty;fisg; ngw tpUk;Gk; Fbkf;fSf;F mij nfhLf;f top tif nra;tNjhL yQ r Coiy jLj;jy;");
		data.add("5.Nkw;fz;l Nehf;fq;fs; epiwNtw kf;fs; rf;jp njhlHe;J mjw;fhd Ntiyfis nra;J tUfpwJ"
				+ " tPjhtJ xU jPtputhjj;ij Kjypy; xopj;jhy; midj;Jj; jP tputhjKk; xLq;fp NghFk; vd;why; xopf;fg;gl Ntz;ba Kjy; jP tputhjk; yQ rk; Coy;");
		/////////////////////////////////////////////////////////////////////////////
		
		data.add("1.jfty; vd;gJ vt;tbtj;jpYk; ,Uf;fpw tptug;nghUs; vjidk; Fwpf;Fk; gjpNtLfs; Mtzq;fs; MNyhridfs; nra;jp ntspaPLfs; Rw;wwpf;iffs; muR Mizfs; ehs; tptuf; Fwpg;NgLfs; xg;ge;jq;fs; mwpf;iffs; jhs;fs; khjpupfs; cUg;gbfs; kw;Wk; ve;jnthU kpd;dZ tbtj;jpYk; itj;jpUf;Fk; jfty;fs; Mfpait mlq;Fk;"
		+"(v.fh)Nu{d; filiag; nghWj;jtiu filapy; vj;jid ml;ilfs; cs;sd xt;nthU ehSk; vd;ndd;d nghUl;fs; tofq;fg;gl;ld vd;gJ “jfty;” murpd; nray;ghLfs; midj;Jk; jftNy "
		+"jdpahH mikg;G njhlHghd xU jftiyf; Fwpg;gpl;l Neuj;jpy; nraypypUf;Fk; NtW ve;jr; rl;lj;jpd; gbAk; xU muR epWtdq;fshy; mZfpg; ngwKbAk; vd;why; me;jj; jftYk; ,jpy; mlq;Fk; ");
		data.add("ehk; murplk; vJ gw;wp tpz;zg;gpj;jhYk; 30 ehl;fSf;Fs; tprhuiz nra;J kDjhuUf;F cupa jftiy njuptpf;f Ntz;Lk; vd jkpof murpd; gzpahsH kw;Wk; epHthf rPHjpUj;jj; Jiwapd; Njjpapl;lmurhiz(116)  nrhy;fpwJ  mjd;gb ehk;....");
		data.add("1.nfhLj;j kDtpw;F gjpy; jutpy;iy cupa eltbf;if vLf;ftpy;iy vdpy; mJgw;wpa fhuzq;fisf; Nfl;fyhk;  mjd;kPJ vLf;fg;gl;l eltbf;iffs; gupe;Jiuj;j mjpfhupapd; mwpf;if efy; thf;F %yq;fspd; efy;fisf; Nfl;fyhk;");
		data.add("2.muRj; Jiwfs; jq;fSf;F   te;j  tpz;zg;gq;fSf;F vj;jid ehl;fSf;Fs; rhd;W nfhLf;f Ntz;Lk; mjw;fhd fl;lzk; vt;tstP nray;ghL vg;gb ,Uf;f Ntz;Lk; vd;w  tptuq;fs; mlq;fpa kf;fs; rhrd efy;fisf; Nfl;fyhk;");
		data.add("3.muR Mizfs;  mwptPiufs;  Rw;wwpf;iffs;  tiuglq;fs;  gbtq;fs;    tpjpKiwfs;  ekf;Nfh NtW ahUf;Nfh  cupkk;  mDkjp  fld;  muR rYiffs;Vw;fdNt mspf;fg;gl;ltw;wpw;F (m) kWf;fg; gl;likf;F Mtz efy;fs; kw;Wk; jfty; Nfl;fyhk;"
		+"muR rhHe;j epWtdq;fs; muR mspj;j epjpapypUe;J nra;ag;gl;l nrytPfspd; nrytPr; rPl;Lfs; kUj;Jtkidfspy; kUe;Jfs; v]PNu glq;fs; Nghd;wtw;wpd; ,Ug;G kUj;JtHfs; ,Ug;gplk; gzp Neuk; gw;wpa tptuq;fs; Nfl;fyhk;");
		data.add("4.fztd; kid tp gzpg; gjpNtLfspd; ehkpdp tptuq;fs; muR CopaHfs; MrpupaHfs; eyd; rhHe;j tptuq;fs; Fw;wgj;jpupf;iffs; jz;lidf; Nfhg;Gfs; rk;gsg;gl;bay; tptuq;fs; thf;F%y efy;fs; mir k; mirah nrhj;Jfs; thq;fpa tptuq;fs; tPl;L tup tpjpg;gpd; tpjpKiwfs; rhiy ghyk; gpw fl;blq;fs; njU tpsf;Ffs; Foha;fs; fpzWfs; Mfpait vg;NghJ vg;gb ahuhy; vt;tstP ePsk; mfyk; gUkd; jd;ikapy; mikf;fg;gl;lJ Nghd;w tptuq;fs; Nfl;fyhk;");
		data.add("5.fpuhk rpl;lh mlq;fy; ‘m’ gjpNtL epyq;fs; fpuhkj;jpd; tiuglk; rhFgbf; fzf;F efuhl;rpapYk; khefuhl;rpapYk; fpuhkj;jpYk; Gwk;Nghf;F epyq;fs; kuq;fs; Mf;fpukpg;Gfs; MW Vup Fsk; Fl;il fhy;tha;fs; ePsk; mfyk; Mok; tp p jPuzk; gl;lh khw;wk; gl;lhgpupg;G epyk;Vw;fdNt ifag;gLj;jpaJ ifafg;gLj;jg; NghtJ mspf;fg;gl;l tPl;Lkidg;gl;lh me;j kf;fSf;F cz;ikapy; Ngha; NrHe;jjw;fhd xg;Gjy; rPl;Lfs;,d;iwa e piyapy; mtHfSf;F mspj;j me;j kid cs;sjh mjpy; fl;blk; fl;baJ Mfpa tptuq;fs; Nfl;fyhk;");
		return data;
	}

	ArrayList<String> getTitleval() {
		ArrayList<String> title = new ArrayList<String>();
		title.add("Kd;Diu");
		// title.add("rl;lj;jpd; Nehf;fk;?");
		title.add("vd;ndd;d jfty; Nfl;fyhk;?");
		title.add("tpz;zg;gk; vOjahH cjtNtz;Lk;");
		title.add("jghypy; mDg;Gk; Kiw");
		title.add("tpz;zg;gf; fl;lzk; nrYj;Jk;Kiw khepy muRf;F");
		title.add("jfty; ngWtjw;fhd fhy mtfhrk;");
		title.add("mjpfhupf;F mguhjk; jz;lidfs; cz;lh");
		title.add("khjpup tpz;zg;gg; gbtq;fs;");
		title.add("Kjy; Nky; KiwaPl;L tpz;zg;gk;");
		title.add(" ,uz;lhtJ Nky; KiwaPl;L tpz;zg;gk;");
		title.add("Nfs;tp gjpy;fs;");
		title.add("yQ;r xopg;G mikg;Gfspd; Kftupfs;");
		return title;
	}

	ArrayList<String> getSubTitleval() {
		ArrayList<String> title = new ArrayList<String>();
		title.add("rl;lj;jpd; Nehf;fk;?");
		return title;
	}

	class MyPagerAdapter extends PagerAdapter {
		private Typeface tfBamini;

		public MyPagerAdapter() {
		}

		@Override
		public int getCount() {
			return title.size();
		}

		public Object instantiateItem(View collection, int position) {
			View view = null;
			LayoutInflater inflater = (LayoutInflater) collection.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.rticontentpage, null);
			
			
			tfBamini = Typeface
					.createFromAsset(getAssets(), "fonts/Bamini.ttf");
			assignTypeface(((TextView) view.findViewById(R.id.heading1)),
					title.get(position));

			if (position == 0) {
				assignTypeface(((TextView) view.findViewById(R.id.heading2)),subTitle.get(position));
				assignTypeface(((TextView) view.findViewById(R.id.textView1)),data.get(0));
				assignTypeface(((TextView) view.findViewById(R.id.textView2)),data.get(1));
				assignTypeface(((TextView) view.findViewById(R.id.textView3)),data.get(2));
				assignTypeface(((TextView) view.findViewById(R.id.textView4)),data.get(3));
				assignTypeface(((TextView) view.findViewById(R.id.textView5)),data.get(4));
				assignTypeface(((TextView) view.findViewById(R.id.textView6)),data.get(5));
			}
			if(position == 1){
				assignTypeface(((TextView) view.findViewById(R.id.textView1)),data.get(6));
				assignTypeface(((TextView) view.findViewById(R.id.textView2)),data.get(7));
				assignTypeface(((TextView) view.findViewById(R.id.textView3)),data.get(8));
				assignTypeface(((TextView) view.findViewById(R.id.textView4)),data.get(9));
				assignTypeface(((TextView) view.findViewById(R.id.textView5)),data.get(10));
				assignTypeface(((TextView) view.findViewById(R.id.textView6)),data.get(11));
				assignTypeface(((TextView) view.findViewById(R.id.textView7)),data.get(12));
			}

			((ViewPager) collection).addView(view, 0);
			return view;
		}

		void assignTypeface(TextView wid, String str) {
			wid.setTypeface(tfBamini);
			String stringCon = TamilUtil.convertToTamil(TamilUtil.BAMINI, str);
			wid.setText(stringCon);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}
	}
}
