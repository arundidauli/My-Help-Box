package com.example.unicodetoTamil;


public class TamilUtil {
	public final static int BAMINI = 0;
	public final static int TSCII = 1;
	public final static int ANJAL = 2;
	public final static int TAB = 3;
	public final static int TAM = 4;
	
	public static String convertToTamil(int encodCode, String unicodeStr){
		String convertedStr = "";
		TamilUtil tu = new TamilUtil();
		if(encodCode== TamilUtil.BAMINI){
			convertedStr = tu.convertToBamini(unicodeStr);
		}
		else if(encodCode== TamilUtil.TSCII){
			convertedStr = tu.convertToTSCII(unicodeStr);
		}
		else if(encodCode==TamilUtil.ANJAL){
			convertedStr = tu.convertToAnjal(unicodeStr);
		}
		else if(encodCode==TamilUtil.TAB){
			convertedStr = tu.convertToTab(unicodeStr);
		}
		else if(encodCode==TamilUtil.TAM){
			convertedStr = tu.convertToTam(unicodeStr);
		}
		return convertedStr;
	}
	
	private String convertToBamini(String unicodeStr){
		unicodeStr = unicodeStr.replace("ஸ�?ரீ", "=");
		unicodeStr = unicodeStr.replace(",", ">");
		unicodeStr = unicodeStr.replace("ஜௌ", "n[s");
		unicodeStr = unicodeStr.replace("ஜோ", "N[h");
		unicodeStr = unicodeStr.replace("ஜொ", "n[h");
		unicodeStr = unicodeStr.replace("ஜா", "[h");
		unicodeStr = unicodeStr.replace("ஜி", "[p");
		unicodeStr = unicodeStr.replace("ஜீ", "[P");
		unicodeStr = unicodeStr.replace("ஜ�?", "[{");
		unicodeStr = unicodeStr.replace("ஜூ", "[_");
		unicodeStr = unicodeStr.replace("ஜெ", "n[");
		unicodeStr = unicodeStr.replace("ஜே", "N[");
		unicodeStr = unicodeStr.replace("ஜை", "i[");
		unicodeStr = unicodeStr.replace("ஜ�?", "[;");
		unicodeStr = unicodeStr.replace("ஜ", "[");
		unicodeStr = unicodeStr.replace("கௌ", "nfs");
		unicodeStr = unicodeStr.replace("கோ", "Nfh");
		unicodeStr = unicodeStr.replace("கொ", "nfh");
		unicodeStr = unicodeStr.replace("கா", "fh");
		unicodeStr = unicodeStr.replace("கி", "fp");
		unicodeStr = unicodeStr.replace("கீ", "fP");
		unicodeStr = unicodeStr.replace("க�?", "F");
		unicodeStr = unicodeStr.replace("கூ", "$");
		unicodeStr = unicodeStr.replace("கெ", "nf");
		unicodeStr = unicodeStr.replace("கே", "Nf");
		unicodeStr = unicodeStr.replace("கை", "if");
		unicodeStr = unicodeStr.replace("க�?", "f;");
		unicodeStr = unicodeStr.replace("க", "f");
		unicodeStr = unicodeStr.replace("ஙௌ", "nqs");
		unicodeStr = unicodeStr.replace("ஙோ", "Nqh");
		unicodeStr = unicodeStr.replace("ஙொ", "nqh");
		unicodeStr = unicodeStr.replace("ஙா", "qh");
		unicodeStr = unicodeStr.replace("ஙி", "qp");
		unicodeStr = unicodeStr.replace("ஙீ", "qP");
		unicodeStr = unicodeStr.replace("ங�?", "*");
		unicodeStr = unicodeStr.replace("ஙூ", "*");
		unicodeStr = unicodeStr.replace("ஙெ", "nq");
		unicodeStr = unicodeStr.replace("ஙே", "Nq");
		unicodeStr = unicodeStr.replace("ஙை", "iq");
		unicodeStr = unicodeStr.replace("ங�?", "q;");
		unicodeStr = unicodeStr.replace("ங", "q");
		unicodeStr = unicodeStr.replace("சௌ", "nrs");
		unicodeStr = unicodeStr.replace("சோ", "Nrh");
		unicodeStr = unicodeStr.replace("சொ", "nrh");
		unicodeStr = unicodeStr.replace("சா", "rh");
		unicodeStr = unicodeStr.replace("சி", "rp");
		unicodeStr = unicodeStr.replace("சீ", "rP");
		unicodeStr = unicodeStr.replace("ச�?", "R");
		unicodeStr = unicodeStr.replace("சூ", "R+");
		unicodeStr = unicodeStr.replace("செ", "nr");
		unicodeStr = unicodeStr.replace("சே", "Nr");
		unicodeStr = unicodeStr.replace("சை", "ir");
		unicodeStr = unicodeStr.replace("ச�?", "r;");
		unicodeStr = unicodeStr.replace("ச", "r");
		unicodeStr = unicodeStr.replace("ஞௌ", "nQs");
		unicodeStr = unicodeStr.replace("ஞோ", "NQh");
		unicodeStr = unicodeStr.replace("ஞொ", "nQh");
		unicodeStr = unicodeStr.replace("ஞா", "Qh");
		unicodeStr = unicodeStr.replace("ஞி", "Qp");
		unicodeStr = unicodeStr.replace("ஞீ", "QP");
		unicodeStr = unicodeStr.replace("ஞ�?", "*");
		unicodeStr = unicodeStr.replace("ஞூ", "*");
		unicodeStr = unicodeStr.replace("ஞெ", "nQ");
		unicodeStr = unicodeStr.replace("ஞே", "NQ");
		unicodeStr = unicodeStr.replace("ஞை", "iQ");
		unicodeStr = unicodeStr.replace("ஞ�?", "Q;");
		unicodeStr = unicodeStr.replace("ஞ", "Q");
		unicodeStr = unicodeStr.replace("டௌ", "nls");
		unicodeStr = unicodeStr.replace("டோ", "Nlh");
		unicodeStr = unicodeStr.replace("டொ", "nlh");
		unicodeStr = unicodeStr.replace("டா", "lh");
		unicodeStr = unicodeStr.replace("டி", "b");
		unicodeStr = unicodeStr.replace("டீ", "B");
		unicodeStr = unicodeStr.replace("ட�?", "L");
		unicodeStr = unicodeStr.replace("டூ", "^");
		unicodeStr = unicodeStr.replace("டெ", "nl");
		unicodeStr = unicodeStr.replace("டே", "Nl");
		unicodeStr = unicodeStr.replace("டை", "il");
		unicodeStr = unicodeStr.replace("ட�?", "l;");
		unicodeStr = unicodeStr.replace("ட", "l");
		unicodeStr = unicodeStr.replace("ணௌ", "nzs");
		unicodeStr = unicodeStr.replace("ணோ", "Nzh");
		unicodeStr = unicodeStr.replace("ணொ", "nzh");
		unicodeStr = unicodeStr.replace("ணா", "zh");
		unicodeStr = unicodeStr.replace("ணி", "zp");
		unicodeStr = unicodeStr.replace("ணீ", "zP");
		unicodeStr = unicodeStr.replace("ண�?", "Z");
		unicodeStr = unicodeStr.replace("ணூ", "Z}");
		unicodeStr = unicodeStr.replace("ணெ", "nz");
		unicodeStr = unicodeStr.replace("ணே", "Nz");
		unicodeStr = unicodeStr.replace("ணை", "iz");
		unicodeStr = unicodeStr.replace("ண�?", "z;");
		unicodeStr = unicodeStr.replace("ண", "z");
		unicodeStr = unicodeStr.replace("தௌ", "njs");
		unicodeStr = unicodeStr.replace("தோ", "Njh");
		unicodeStr = unicodeStr.replace("தொ", "njh");
		unicodeStr = unicodeStr.replace("தா", "jh");
		unicodeStr = unicodeStr.replace("தி", "jp");
		unicodeStr = unicodeStr.replace("தீ", "jP");
		unicodeStr = unicodeStr.replace("த�?", "J");
		unicodeStr = unicodeStr.replace("தூ", "J}");
		unicodeStr = unicodeStr.replace("தெ", "nj");
		unicodeStr = unicodeStr.replace("தே", "Nj");
		unicodeStr = unicodeStr.replace("தை", "ij");
		unicodeStr = unicodeStr.replace("த�?", "j;");
		unicodeStr = unicodeStr.replace("த", "j");
		unicodeStr = unicodeStr.replace("நௌ", "nes");
		unicodeStr = unicodeStr.replace("நோ", "Neh");
		unicodeStr = unicodeStr.replace("நொ", "neh");
		unicodeStr = unicodeStr.replace("நா", "eh");
		unicodeStr = unicodeStr.replace("நி", "ep");
		unicodeStr = unicodeStr.replace("நீ", "eP");
		unicodeStr = unicodeStr.replace("ந�?", "E");
		unicodeStr = unicodeStr.replace("நூ", "E}");
		unicodeStr = unicodeStr.replace("நெ", "ne");
		unicodeStr = unicodeStr.replace("நே", "Ne");
		unicodeStr = unicodeStr.replace("நை", "ie");
		unicodeStr = unicodeStr.replace("ந�?", "e;");
		unicodeStr = unicodeStr.replace("ந", "e");
		unicodeStr = unicodeStr.replace("னௌ", "nds");
		unicodeStr = unicodeStr.replace("னோ", "Ndh");
		unicodeStr = unicodeStr.replace("னொ", "ndh");
		unicodeStr = unicodeStr.replace("னா", "dh");
		unicodeStr = unicodeStr.replace("னி", "dp");
		unicodeStr = unicodeStr.replace("னீ", "dP");
		unicodeStr = unicodeStr.replace("ன�?", "D");
		unicodeStr = unicodeStr.replace("னூ", "D}");
		unicodeStr = unicodeStr.replace("னெ", "nd");
		unicodeStr = unicodeStr.replace("னே", "Nd");
		unicodeStr = unicodeStr.replace("னை", "id");
		unicodeStr = unicodeStr.replace("ன�?", "d;");
		unicodeStr = unicodeStr.replace("ன", "d");
		unicodeStr = unicodeStr.replace("பௌ", "ngs");
		unicodeStr = unicodeStr.replace("போ", "Ngh");
		unicodeStr = unicodeStr.replace("பொ", "ngh");
		unicodeStr = unicodeStr.replace("பா", "gh");
		unicodeStr = unicodeStr.replace("பி", "gp");
		unicodeStr = unicodeStr.replace("பீ", "gP");
		unicodeStr = unicodeStr.replace("ப�?", "G");
		unicodeStr = unicodeStr.replace("பூ", "G+");
		unicodeStr = unicodeStr.replace("பெ", "ng");
		unicodeStr = unicodeStr.replace("பே", "Ng");
		unicodeStr = unicodeStr.replace("பை", "ig");
		unicodeStr = unicodeStr.replace("ப�?", "g;");
		unicodeStr = unicodeStr.replace("ப", "g");
		unicodeStr = unicodeStr.replace("மௌ", "nks");
		unicodeStr = unicodeStr.replace("மோ", "Nkh");
		unicodeStr = unicodeStr.replace("மொ", "nkh");
		unicodeStr = unicodeStr.replace("மா", "kh");
		unicodeStr = unicodeStr.replace("மி", "kp");
		unicodeStr = unicodeStr.replace("மீ", "kP");
		unicodeStr = unicodeStr.replace("ம�?", "K");
		unicodeStr = unicodeStr.replace("மூ", "%");
		unicodeStr = unicodeStr.replace("மெ", "nk");
		unicodeStr = unicodeStr.replace("மே", "Nk");
		unicodeStr = unicodeStr.replace("மை", "ik");
		unicodeStr = unicodeStr.replace("ம�?", "k;");
		unicodeStr = unicodeStr.replace("ம", "k");
		unicodeStr = unicodeStr.replace("யௌ", "nas");
		unicodeStr = unicodeStr.replace("யோ", "Nah");
		unicodeStr = unicodeStr.replace("யொ", "nah");
		unicodeStr = unicodeStr.replace("யா", "ah");
		unicodeStr = unicodeStr.replace("யி", "ap");
		unicodeStr = unicodeStr.replace("யீ", "aP");
		unicodeStr = unicodeStr.replace("ய�?", "A");
		unicodeStr = unicodeStr.replace("யூ", "A+");
		unicodeStr = unicodeStr.replace("யெ", "na");
		unicodeStr = unicodeStr.replace("யே", "Na");
		unicodeStr = unicodeStr.replace("யை", "ia");
		unicodeStr = unicodeStr.replace("ய�?", "a;");
		unicodeStr = unicodeStr.replace("ய", "a");
		unicodeStr = unicodeStr.replace("ரௌ", "nus");
		unicodeStr = unicodeStr.replace("ரோ", "Nuh");
		unicodeStr = unicodeStr.replace("ரொ", "nuh");
		unicodeStr = unicodeStr.replace("ரா", "uh");
		unicodeStr = unicodeStr.replace("ரி", "up");
		unicodeStr = unicodeStr.replace("ரீ", "uP");
		unicodeStr = unicodeStr.replace("ர�?", "U");
		unicodeStr = unicodeStr.replace("ரூ", "\\&");
		unicodeStr = unicodeStr.replace("ரெ", "nu");
		unicodeStr = unicodeStr.replace("ரே", "Nu");
		unicodeStr = unicodeStr.replace("ரை", "iu");
		unicodeStr = unicodeStr.replace("ர�?", "u;");
		unicodeStr = unicodeStr.replace("ர", "u");
		unicodeStr = unicodeStr.replace("லௌ", "nys");
		unicodeStr = unicodeStr.replace("லோ", "Nyh");
		unicodeStr = unicodeStr.replace("லொ", "nyh");
		unicodeStr = unicodeStr.replace("லா", "yh");
		unicodeStr = unicodeStr.replace("லி", "yp");
		unicodeStr = unicodeStr.replace("லீ", "yP");
		unicodeStr = unicodeStr.replace("ல�?", "Y");
		unicodeStr = unicodeStr.replace("லூ", "Y}");
		unicodeStr = unicodeStr.replace("லெ", "ny");
		unicodeStr = unicodeStr.replace("லே", "Ny");
		unicodeStr = unicodeStr.replace("லை", "iy");
		unicodeStr = unicodeStr.replace("ல�?", "y;");
		unicodeStr = unicodeStr.replace("ல", "y");
		unicodeStr = unicodeStr.replace("ளௌ", "nss");
		unicodeStr = unicodeStr.replace("ளோ", "Nsh");
		unicodeStr = unicodeStr.replace("ளொ", "nsh");
		unicodeStr = unicodeStr.replace("ளா", "sh");
		unicodeStr = unicodeStr.replace("ளி", "sp");
		unicodeStr = unicodeStr.replace("ளீ", "sP");
		unicodeStr = unicodeStr.replace("ள�?", "S");
		unicodeStr = unicodeStr.replace("ளூ", "Sh");
		unicodeStr = unicodeStr.replace("ளெ", "ns");
		unicodeStr = unicodeStr.replace("ளே", "Ns");
		unicodeStr = unicodeStr.replace("ளை", "is");
		unicodeStr = unicodeStr.replace("ள�?", "s;");
		unicodeStr = unicodeStr.replace("ள", "s");
		unicodeStr = unicodeStr.replace("வௌ", "nts");
		unicodeStr = unicodeStr.replace("வோ", "Nth");
		unicodeStr = unicodeStr.replace("வொ", "nth");
		unicodeStr = unicodeStr.replace("வா", "th");
		unicodeStr = unicodeStr.replace("வி", "tp");
		unicodeStr = unicodeStr.replace("வீ", "tP");
		unicodeStr = unicodeStr.replace("வ�?", "T");
		unicodeStr = unicodeStr.replace("வூ", "T+");
		unicodeStr = unicodeStr.replace("வெ", "nt");
		unicodeStr = unicodeStr.replace("வே", "Nt");
		unicodeStr = unicodeStr.replace("வை", "it");
		unicodeStr = unicodeStr.replace("வ�?", "t;");
		unicodeStr = unicodeStr.replace("வ", "t");
		unicodeStr = unicodeStr.replace("ழௌ", "nos");
		unicodeStr = unicodeStr.replace("ழோ", "Noh");
		unicodeStr = unicodeStr.replace("ழொ", "noh");
		unicodeStr = unicodeStr.replace("ழா", "oh");
		unicodeStr = unicodeStr.replace("ழி", "op");
		unicodeStr = unicodeStr.replace("ழீ", "oP");
		unicodeStr = unicodeStr.replace("ழ�?", "O");
		unicodeStr = unicodeStr.replace("ழூ", "*");
		unicodeStr = unicodeStr.replace("ழெ", "no");
		unicodeStr = unicodeStr.replace("ழே", "No");
		unicodeStr = unicodeStr.replace("ழை", "io");
		unicodeStr = unicodeStr.replace("ழ�?", "o;");
		unicodeStr = unicodeStr.replace("ழ", "o");
		unicodeStr = unicodeStr.replace("றௌ", "nws");
		unicodeStr = unicodeStr.replace("றோ", "Nwh");
		unicodeStr = unicodeStr.replace("றொ", "nwh");
		unicodeStr = unicodeStr.replace("றா", "wh");
		unicodeStr = unicodeStr.replace("றி", "wp");
		unicodeStr = unicodeStr.replace("றீ", "wP");
		unicodeStr = unicodeStr.replace("ற�?", "W");
		unicodeStr = unicodeStr.replace("றூ", "W}");
		unicodeStr = unicodeStr.replace("றெ", "nw");
		unicodeStr = unicodeStr.replace("றே", "Nw");
		unicodeStr = unicodeStr.replace("றை", "iw");
		unicodeStr = unicodeStr.replace("ற�?", "w;");
		unicodeStr = unicodeStr.replace("ற", "w");
		unicodeStr = unicodeStr.replace("ஹௌ", "n`s");
		unicodeStr = unicodeStr.replace("ஹோ", "N`h");
		unicodeStr = unicodeStr.replace("ஹொ", "n`h");
		unicodeStr = unicodeStr.replace("ஹா", "`h");
		unicodeStr = unicodeStr.replace("ஹி", "`p");
		unicodeStr = unicodeStr.replace("ஹீ", "`P");
		unicodeStr = unicodeStr.replace("ஹ�?", "{`");
		unicodeStr = unicodeStr.replace("ஹூ", "`_");
		unicodeStr = unicodeStr.replace("ஹெ", "n`");
		unicodeStr = unicodeStr.replace("ஹே", "N`");
		unicodeStr = unicodeStr.replace("ஹை", "i`");
		unicodeStr = unicodeStr.replace("ஹ�?", "`;");
		unicodeStr = unicodeStr.replace("ஹ", "`");
		unicodeStr = unicodeStr.replace("ஷௌ", "n\\s");
		unicodeStr = unicodeStr.replace("ஷோ", "N\\h");
		unicodeStr = unicodeStr.replace("ஷொ", "n\\h");
		unicodeStr = unicodeStr.replace("ஷா", "\\h");
		unicodeStr = unicodeStr.replace("ஷி", "\\p");
		unicodeStr = unicodeStr.replace("ஷீ", "\\P");
		unicodeStr = unicodeStr.replace("ஷ�?", "\\{");
		unicodeStr = unicodeStr.replace("ஷூ", "\\\\_");
		unicodeStr = unicodeStr.replace("ஷெ", "n\\");
		unicodeStr = unicodeStr.replace("ஷே", "N\\");
		unicodeStr = unicodeStr.replace("ஷை", "i\\");
		unicodeStr = unicodeStr.replace("ஷ�?", "\\;");
		unicodeStr = unicodeStr.replace("ஷ", "\\");
		unicodeStr = unicodeStr.replace("ஸௌ", "n]s");
		unicodeStr = unicodeStr.replace("ஸோ", "N]h");
		unicodeStr = unicodeStr.replace("ஸொ", "n]h");
		unicodeStr = unicodeStr.replace("ஸா", "]h");
		unicodeStr = unicodeStr.replace("ஸி", "]p");
		unicodeStr = unicodeStr.replace("ஸீ", "]P");
		unicodeStr = unicodeStr.replace("ஸ�?", "]{");
		unicodeStr = unicodeStr.replace("ஸூ", "]_");
		unicodeStr = unicodeStr.replace("ஸெ", "n]");
		unicodeStr = unicodeStr.replace("ஸே", "N]");
		unicodeStr = unicodeStr.replace("ஸை", "i]");
		unicodeStr = unicodeStr.replace("ஸ�?", "];");
		unicodeStr = unicodeStr.replace("ஸ", "]");
		unicodeStr = unicodeStr.replace("அ", "m");
		unicodeStr = unicodeStr.replace("ஆ", "M");
		unicodeStr = unicodeStr.replace("இ", ",");
		unicodeStr = unicodeStr.replace("ஈ", "<");
		unicodeStr = unicodeStr.replace("உ", "c");
		unicodeStr = unicodeStr.replace("ஊ", "C");
		unicodeStr = unicodeStr.replace("எ", "v");
		unicodeStr = unicodeStr.replace("�?", "V");
		unicodeStr = unicodeStr.replace("�?", "I");
		unicodeStr = unicodeStr.replace("ஒ", "x");
		unicodeStr = unicodeStr.replace("ஓ", "X");
		unicodeStr = unicodeStr.replace("ஔ", "xs");
		unicodeStr = unicodeStr.replace("ஃ", "\"");
		
		return unicodeStr;
	}
	
	private String convertToTSCII(String unicodeStr){
		unicodeStr = unicodeStr.replace("ஜௌ", "¦ƒª");
		unicodeStr = unicodeStr.replace("ஜோ", "§ƒ¡");
		unicodeStr = unicodeStr.replace("ஜொ", "¦ƒ¡");
		unicodeStr = unicodeStr.replace("ஜா", "ƒ¡");
		unicodeStr = unicodeStr.replace("ஜி", "ƒ¢");
		unicodeStr = unicodeStr.replace("ஜீ", "ƒ£");
		unicodeStr = unicodeStr.replace("ஜ�?", "ƒ¤");
		unicodeStr = unicodeStr.replace("ஜூ", "ƒ¥");
		unicodeStr = unicodeStr.replace("ஜெ", "¦ƒ");
		unicodeStr = unicodeStr.replace("ஜே", "§ƒ");
		unicodeStr = unicodeStr.replace("ஜை", "¨ƒ");
		unicodeStr = unicodeStr.replace("ஜ�?", "\\ˆ");
		unicodeStr = unicodeStr.replace("ஜ", "ƒ");
		unicodeStr = unicodeStr.replace("கௌ", "¦¸ª");
		unicodeStr = unicodeStr.replace("கோ", "§¸¡");
		unicodeStr = unicodeStr.replace("கொ", "¦¸¡");
		unicodeStr = unicodeStr.replace("கா", "¸¡");
		unicodeStr = unicodeStr.replace("கி", "¸¢");
		unicodeStr = unicodeStr.replace("கீ", "¸£");
		unicodeStr = unicodeStr.replace("க�?", "Ì");
		unicodeStr = unicodeStr.replace("கூ", "Ü");
		unicodeStr = unicodeStr.replace("கெ", "¦¸");
		unicodeStr = unicodeStr.replace("கே", "§¸");
		unicodeStr = unicodeStr.replace("கை", "¨¸");
		unicodeStr = unicodeStr.replace("க�?", "ì");
		unicodeStr = unicodeStr.replace("க", "¸");
		unicodeStr = unicodeStr.replace("ஙௌ", "¦¹ª");
		unicodeStr = unicodeStr.replace("ஙோ", "§¹¡");
		unicodeStr = unicodeStr.replace("ஙொ", "¦¹¡");
		unicodeStr = unicodeStr.replace("ஙா", "¹¡");
		unicodeStr = unicodeStr.replace("ஙி", "¹¢");
		unicodeStr = unicodeStr.replace("ஙீ", "¹£");
		unicodeStr = unicodeStr.replace("ங�?", "");
		unicodeStr = unicodeStr.replace("ஙூ", "");
		unicodeStr = unicodeStr.replace("ஙெ", "¦¹");
		unicodeStr = unicodeStr.replace("ஙே", "§¹");
		unicodeStr = unicodeStr.replace("ஙை", "¨¹");
		unicodeStr = unicodeStr.replace("ங�?", "í");
		unicodeStr = unicodeStr.replace("ங", "¹");
		unicodeStr = unicodeStr.replace("சௌ", "¦ºª");
		unicodeStr = unicodeStr.replace("சோ", "§º¡");
		unicodeStr = unicodeStr.replace("சொ", "¦º¡");
		unicodeStr = unicodeStr.replace("சா", "º¡");
		unicodeStr = unicodeStr.replace("சி", "º¢");
		unicodeStr = unicodeStr.replace("சீ", "º£");
		unicodeStr = unicodeStr.replace("ச�?", "�?");
		unicodeStr = unicodeStr.replace("சூ", "�?");
		unicodeStr = unicodeStr.replace("செ", "¦º");
		unicodeStr = unicodeStr.replace("சே", "§º");
		unicodeStr = unicodeStr.replace("சை", "¨º");
		unicodeStr = unicodeStr.replace("ச�?", "î");
		unicodeStr = unicodeStr.replace("ச", "º");
		unicodeStr = unicodeStr.replace("ஞௌ", "¦»ª");
		unicodeStr = unicodeStr.replace("ஞோ", "§»¡");
		unicodeStr = unicodeStr.replace("ஞொ", "¦»¡");
		unicodeStr = unicodeStr.replace("ஞா", "»¡");
		unicodeStr = unicodeStr.replace("ஞி", "»¢");
		unicodeStr = unicodeStr.replace("ஞீ", "»£");
		unicodeStr = unicodeStr.replace("ஞ�?", "");
		unicodeStr = unicodeStr.replace("ஞூ", "");
		unicodeStr = unicodeStr.replace("ஞெ", "¦»");
		unicodeStr = unicodeStr.replace("ஞே", "§»");
		unicodeStr = unicodeStr.replace("ஞை", "¨»");
		unicodeStr = unicodeStr.replace("ஞ�?", "ï");
		unicodeStr = unicodeStr.replace("ஞ", "»");
		unicodeStr = unicodeStr.replace("டௌ", "¦¼ª");
		unicodeStr = unicodeStr.replace("டோ", "§¼¡");
		unicodeStr = unicodeStr.replace("டொ", "¦¼¡");
		unicodeStr = unicodeStr.replace("டா", "¼¡");
		unicodeStr = unicodeStr.replace("டி", "Ê");
		unicodeStr = unicodeStr.replace("டீ", "Ë");
		unicodeStr = unicodeStr.replace("ட�?", "Î");
		unicodeStr = unicodeStr.replace("டூ", "Þ");
		unicodeStr = unicodeStr.replace("டெ", "¦¼");
		unicodeStr = unicodeStr.replace("டே", "§¼");
		unicodeStr = unicodeStr.replace("டை", "¨¼");
		unicodeStr = unicodeStr.replace("ட�?", "ð");
		unicodeStr = unicodeStr.replace("ட", "¼");
		unicodeStr = unicodeStr.replace("ணௌ", "¦½ª");
		unicodeStr = unicodeStr.replace("ணோ", "§½¡");
		unicodeStr = unicodeStr.replace("ணொ", "¦½¡");
		unicodeStr = unicodeStr.replace("ணா", "½¡");
		unicodeStr = unicodeStr.replace("ணி", "½¢");
		unicodeStr = unicodeStr.replace("ணீ", "½£");
		unicodeStr = unicodeStr.replace("ண�?", "�?");
		unicodeStr = unicodeStr.replace("ணூ", "ß");
		unicodeStr = unicodeStr.replace("ணெ", "¦½");
		unicodeStr = unicodeStr.replace("ணே", "§½");
		unicodeStr = unicodeStr.replace("ணை", "¨½");
		unicodeStr = unicodeStr.replace("ண�?", "ñ");
		unicodeStr = unicodeStr.replace("ண", "½");
		unicodeStr = unicodeStr.replace("தௌ", "¦¾ª");
		unicodeStr = unicodeStr.replace("தோ", "§¾¡");
		unicodeStr = unicodeStr.replace("தொ", "¦¾¡");
		unicodeStr = unicodeStr.replace("தா", "¾¡");
		unicodeStr = unicodeStr.replace("தி", "¾¢");
		unicodeStr = unicodeStr.replace("தீ", "¾£");
		unicodeStr = unicodeStr.replace("த�?", "�?");
		unicodeStr = unicodeStr.replace("தூ", "à");
		unicodeStr = unicodeStr.replace("தெ", "¦¾");
		unicodeStr = unicodeStr.replace("தே", "§¾");
		unicodeStr = unicodeStr.replace("தை", "¨¾");
		unicodeStr = unicodeStr.replace("த�?", "ò");
		unicodeStr = unicodeStr.replace("த", "¾");
		unicodeStr = unicodeStr.replace("நௌ", "¦¿ª");
		unicodeStr = unicodeStr.replace("நோ", "§¿¡");
		unicodeStr = unicodeStr.replace("நொ", "¦¿¡");
		unicodeStr = unicodeStr.replace("நா", "¿¡");
		unicodeStr = unicodeStr.replace("நி", "¿¢");
		unicodeStr = unicodeStr.replace("நீ", "¿£");
		unicodeStr = unicodeStr.replace("ந�?", "Ñ");
		unicodeStr = unicodeStr.replace("நூ", "á");
		unicodeStr = unicodeStr.replace("நெ", "¦¿");
		unicodeStr = unicodeStr.replace("நே", "§¿");
		unicodeStr = unicodeStr.replace("நை", "¨¿");
		unicodeStr = unicodeStr.replace("ந�?", "ó");
		unicodeStr = unicodeStr.replace("ந", "¿");
		unicodeStr = unicodeStr.replace("னௌ", "¦Éª");
		unicodeStr = unicodeStr.replace("னோ", "§É¡");
		unicodeStr = unicodeStr.replace("னொ", "¦É¡");
		unicodeStr = unicodeStr.replace("னா", "É¡");
		unicodeStr = unicodeStr.replace("னி", "É¢");
		unicodeStr = unicodeStr.replace("னீ", "É£");
		unicodeStr = unicodeStr.replace("ன�?", "Û");
		unicodeStr = unicodeStr.replace("னூ", "ë");
		unicodeStr = unicodeStr.replace("னெ", "¦É");
		unicodeStr = unicodeStr.replace("னே", "§É");
		unicodeStr = unicodeStr.replace("னை", "¨É");
		unicodeStr = unicodeStr.replace("ன�?", "ý");
		unicodeStr = unicodeStr.replace("ன", "É");
		unicodeStr = unicodeStr.replace("பௌ", "¦Àª");
		unicodeStr = unicodeStr.replace("போ", "§À¡");
		unicodeStr = unicodeStr.replace("பொ", "¦À¡");
		unicodeStr = unicodeStr.replace("பா", "À¡");
		unicodeStr = unicodeStr.replace("பி", "À¢");
		unicodeStr = unicodeStr.replace("பீ", "À£");
		unicodeStr = unicodeStr.replace("ப�?", "Ò");
		unicodeStr = unicodeStr.replace("பூ", "â");
		unicodeStr = unicodeStr.replace("பெ", "¦À");
		unicodeStr = unicodeStr.replace("பே", "§À");
		unicodeStr = unicodeStr.replace("பை", "¨À");
		unicodeStr = unicodeStr.replace("ப�?", "ô");
		unicodeStr = unicodeStr.replace("ப", "À");
		unicodeStr = unicodeStr.replace("மௌ", "¦�?ª");
		unicodeStr = unicodeStr.replace("மோ", "§�?¡");
		unicodeStr = unicodeStr.replace("மொ", "¦�?¡");
		unicodeStr = unicodeStr.replace("மா", "�?¡");
		unicodeStr = unicodeStr.replace("மி", "�?¢");
		unicodeStr = unicodeStr.replace("மீ", "�?£");
		unicodeStr = unicodeStr.replace("ம�?", "Ó");
		unicodeStr = unicodeStr.replace("மூ", "ã");
		unicodeStr = unicodeStr.replace("மெ", "¦�?");
		unicodeStr = unicodeStr.replace("மே", "§�?");
		unicodeStr = unicodeStr.replace("மை", "¨�?");
		unicodeStr = unicodeStr.replace("ம�?", "õ");
		unicodeStr = unicodeStr.replace("ம", "�?");
		unicodeStr = unicodeStr.replace("யௌ", "¦Âª");
		unicodeStr = unicodeStr.replace("யோ", "§Â¡");
		unicodeStr = unicodeStr.replace("யொ", "¦Â¡");
		unicodeStr = unicodeStr.replace("யா", "Â¡");
		unicodeStr = unicodeStr.replace("யி", "Â¢");
		unicodeStr = unicodeStr.replace("யீ", "Â£");
		unicodeStr = unicodeStr.replace("ய�?", "Ô");
		unicodeStr = unicodeStr.replace("யூ", "ä");
		unicodeStr = unicodeStr.replace("யெ", "¦Â");
		unicodeStr = unicodeStr.replace("யே", "§Â");
		unicodeStr = unicodeStr.replace("யை", "¨Â");
		unicodeStr = unicodeStr.replace("ய�?", "ö");
		unicodeStr = unicodeStr.replace("ய", "Â");
		unicodeStr = unicodeStr.replace("ரௌ", "¦Ãª");
		unicodeStr = unicodeStr.replace("ரோ", "§Ã¡");
		unicodeStr = unicodeStr.replace("ரொ", "¦Ã¡");
		unicodeStr = unicodeStr.replace("ரா", "Ã¡");
		unicodeStr = unicodeStr.replace("ரி", "Ã¢");
		unicodeStr = unicodeStr.replace("ரீ", "Ã£");
		unicodeStr = unicodeStr.replace("ர�?", "Õ");
		unicodeStr = unicodeStr.replace("ரூ", "å");
		unicodeStr = unicodeStr.replace("ரெ", "¦Ã");
		unicodeStr = unicodeStr.replace("ரே", "§Ã");
		unicodeStr = unicodeStr.replace("ரை", "¨Ã");
		unicodeStr = unicodeStr.replace("ர�?", "÷");
		unicodeStr = unicodeStr.replace("ர", "Ã");
		unicodeStr = unicodeStr.replace("லௌ", "¦Äª");
		unicodeStr = unicodeStr.replace("லோ", "§Ä¡");
		unicodeStr = unicodeStr.replace("லொ", "¦Ä¡");
		unicodeStr = unicodeStr.replace("லா", "Ä¡");
		unicodeStr = unicodeStr.replace("லி", "Ä¢");
		unicodeStr = unicodeStr.replace("லீ", "Ä£");
		unicodeStr = unicodeStr.replace("ல�?", "Ö");
		unicodeStr = unicodeStr.replace("லூ", "æ");
		unicodeStr = unicodeStr.replace("லெ", "¦Ä");
		unicodeStr = unicodeStr.replace("லே", "§Ä");
		unicodeStr = unicodeStr.replace("லை", "¨Ä");
		unicodeStr = unicodeStr.replace("ல�?", "ø");
		unicodeStr = unicodeStr.replace("ல", "Ä");
		unicodeStr = unicodeStr.replace("ளௌ", "¦Çª");
		unicodeStr = unicodeStr.replace("ளோ", "§Ç¡");
		unicodeStr = unicodeStr.replace("ளொ", "¦Ç¡");
		unicodeStr = unicodeStr.replace("ளா", "Ç¡");
		unicodeStr = unicodeStr.replace("ளி", "Ç¢");
		unicodeStr = unicodeStr.replace("ளீ", "Ç£");
		unicodeStr = unicodeStr.replace("ள�?", "Ù");
		unicodeStr = unicodeStr.replace("ளூ", "é");
		unicodeStr = unicodeStr.replace("ளெ", "¦Ç");
		unicodeStr = unicodeStr.replace("ளே", "§Ç");
		unicodeStr = unicodeStr.replace("ளை", "¨Ç");
		unicodeStr = unicodeStr.replace("ள�?", "û");
		unicodeStr = unicodeStr.replace("ள", "Ç");
		unicodeStr = unicodeStr.replace("வௌ", "¦Åª");
		unicodeStr = unicodeStr.replace("வோ", "§Å¡");
		unicodeStr = unicodeStr.replace("வொ", "¦Å¡");
		unicodeStr = unicodeStr.replace("வா", "Å¡");
		unicodeStr = unicodeStr.replace("வி", "Å¢");
		unicodeStr = unicodeStr.replace("வீ", "Å£");
		unicodeStr = unicodeStr.replace("வ�?", "×");
		unicodeStr = unicodeStr.replace("வூ", "ç");
		unicodeStr = unicodeStr.replace("வெ", "¦Å");
		unicodeStr = unicodeStr.replace("வே", "§Å");
		unicodeStr = unicodeStr.replace("வை", "¨Å");
		unicodeStr = unicodeStr.replace("வ�?", "ù");
		unicodeStr = unicodeStr.replace("வ", "Å");
		unicodeStr = unicodeStr.replace("ழௌ", "¦Æª");
		unicodeStr = unicodeStr.replace("ழோ", "§Æ¡");
		unicodeStr = unicodeStr.replace("ழொ", "¦Æ¡");
		unicodeStr = unicodeStr.replace("ழா", "Æ¡");
		unicodeStr = unicodeStr.replace("ழி", "Æ¢");
		unicodeStr = unicodeStr.replace("ழீ", "Æ£");
		unicodeStr = unicodeStr.replace("ழ�?", "Ø");
		unicodeStr = unicodeStr.replace("ழூ", "è");
		unicodeStr = unicodeStr.replace("ழெ", "¦Æ");
		unicodeStr = unicodeStr.replace("ழே", "§Æ");
		unicodeStr = unicodeStr.replace("ழை", "¨Æ");
		unicodeStr = unicodeStr.replace("ழ�?", "ú");
		unicodeStr = unicodeStr.replace("ழ", "Æ");
		unicodeStr = unicodeStr.replace("றௌ", "¦Èª");
		unicodeStr = unicodeStr.replace("றோ", "§È¡");
		unicodeStr = unicodeStr.replace("றொ", "¦È¡");
		unicodeStr = unicodeStr.replace("றா", "È¡");
		unicodeStr = unicodeStr.replace("றி", "È¢");
		unicodeStr = unicodeStr.replace("றீ", "È£");
		unicodeStr = unicodeStr.replace("ற�?", "Ú");
		unicodeStr = unicodeStr.replace("றூ", "ê");
		unicodeStr = unicodeStr.replace("றெ", "¦È");
		unicodeStr = unicodeStr.replace("றே", "§È");
		unicodeStr = unicodeStr.replace("றை", "¨È");
		unicodeStr = unicodeStr.replace("ற�?", "ü");
		unicodeStr = unicodeStr.replace("ற", "È");
		unicodeStr = unicodeStr.replace("ஹௌ", "¦ª");
		unicodeStr = unicodeStr.replace("ஹோ", "§¡");
		unicodeStr = unicodeStr.replace("ஹொ", "¦¡");
		unicodeStr = unicodeStr.replace("ஹா", "†¡");
		unicodeStr = unicodeStr.replace("ஹி", "¢");
		unicodeStr = unicodeStr.replace("ஹீ", "£");
		unicodeStr = unicodeStr.replace("ஹ�?", "¤");
		unicodeStr = unicodeStr.replace("ஹூ", "¥");
		unicodeStr = unicodeStr.replace("ஹெ", "¦");
		unicodeStr = unicodeStr.replace("ஹே", "§");
		unicodeStr = unicodeStr.replace("ஹை", "¨");
		unicodeStr = unicodeStr.replace("ஹ�?", "‹ ");
		unicodeStr = unicodeStr.replace("ஹ", "†");
		unicodeStr = unicodeStr.replace("ஷௌ", "¦„ª");
		unicodeStr = unicodeStr.replace("ஷோ", "§„¡");
		unicodeStr = unicodeStr.replace("ஷொ", "¦„¡");
		unicodeStr = unicodeStr.replace("ஷா", "„¡");
		unicodeStr = unicodeStr.replace("ஷி", "„¢");
		unicodeStr = unicodeStr.replace("ஷீ", "„£");
		unicodeStr = unicodeStr.replace("ஷ�?", "„¤");
		unicodeStr = unicodeStr.replace("ஷூ", "„¥");
		unicodeStr = unicodeStr.replace("ஷெ", "¦„");
		unicodeStr = unicodeStr.replace("ஷே", "§„");
		unicodeStr = unicodeStr.replace("ஷை", "¨„");
		unicodeStr = unicodeStr.replace("ஷ�?", "‰");
		unicodeStr = unicodeStr.replace("ஷ", "„");
		unicodeStr = unicodeStr.replace("ஸௌ", "¦…ª");
		unicodeStr = unicodeStr.replace("ஸோ", "§…¡");
		unicodeStr = unicodeStr.replace("ஸொ", "¦…¡");
		unicodeStr = unicodeStr.replace("ஸா", "…¡");
		unicodeStr = unicodeStr.replace("ஸி", "…¢");
		unicodeStr = unicodeStr.replace("ஸீ", "…£");
		unicodeStr = unicodeStr.replace("ஸ�?", "…¤");
		unicodeStr = unicodeStr.replace("ஸூ", "…¥");
		unicodeStr = unicodeStr.replace("ஸெ", "¦…");
		unicodeStr = unicodeStr.replace("ஸே", "§…");
		unicodeStr = unicodeStr.replace("ஸை", "¨…");
		unicodeStr = unicodeStr.replace("ஸ�?", "Š");
		unicodeStr = unicodeStr.replace("ஸ", "…");
		unicodeStr = unicodeStr.replace("அ", "«");
		unicodeStr = unicodeStr.replace("ஆ", "¬");
		unicodeStr = unicodeStr.replace("இ", "þ");
		unicodeStr = unicodeStr.replace("ஈ", "®");
		unicodeStr = unicodeStr.replace("உ", "¯");
		unicodeStr = unicodeStr.replace("ஊ", "°");
		unicodeStr = unicodeStr.replace("எ", "±");
		unicodeStr = unicodeStr.replace("�?", "²");
		unicodeStr = unicodeStr.replace("�?", "³");
		unicodeStr = unicodeStr.replace("ஒ", "´");
		unicodeStr = unicodeStr.replace("ஓ", "µ");
		unicodeStr = unicodeStr.replace("ஔ", "¶");
		unicodeStr = unicodeStr.replace("ஃ", "·");
		unicodeStr = unicodeStr.replace("ஸ�?ரீ", "");
		unicodeStr = unicodeStr.replace("‘", "‘");
		unicodeStr = unicodeStr.replace("௧", "�?");
		unicodeStr = unicodeStr.replace("௨", "�?");
		unicodeStr = unicodeStr.replace("௩", "");
		unicodeStr = unicodeStr.replace("௪", "�?");
		unicodeStr = unicodeStr.replace("௫", "�?");
		unicodeStr = unicodeStr.replace("௰", "�?");
		unicodeStr = unicodeStr.replace("௱", "");
		unicodeStr = unicodeStr.replace("௲", "Ÿ");
		unicodeStr = unicodeStr.replace("௭", "–");
		unicodeStr = unicodeStr.replace("௮", "—");
		unicodeStr = unicodeStr.replace("௯", "˜");
		unicodeStr = unicodeStr.replace("௲", "Ÿ");
		unicodeStr = unicodeStr.replace("௭", "–");

		return unicodeStr;
	}
	
	private String convertToAnjal(String unicodeStr){
		String TamilText[] = { "û", "û‘", "û’", "û“", "û�?", "û•", "—û", "þû", "—û‘", "÷ñõ",
				"—ûã", "û", "ü", "—óó", "þó‘", "—ó‘", "ó‘", "ó", "ó’", "ó",
				"ó�?", "ó•", "—ó", "þó", "€ó", "ô", "ó", "—�?ã", "þ�?‘", "—�?‘",
				"�?‘", "Ž", "�?", "�?", "˜", "—�?", "þ�?", "€�?", "™",
				"�?", "—šã", "þš‘", "—š‘", "š‘", "š", "š", "—š", "þš", "€š",
				"›", "š", "—œã", "þœ‘", "—œ‘", "œ‘", "�?", "", "Ÿ", "¡",
				"—œ", "þœ", "€œ", "¢", "œ", "œ", "—£ã", "þ£‘", "—£‘", "£‘",
				"£", "£", "—£", "þ£", "€£", "¤", "£", "—¥ã", "þ¥‘", "—¥‘",
				"¥‘", "¦", "§", "¨", "©", "—¥", "þ¥", "€¥", "ª", "¥",
				"—«ã", "þ«‘", "—«‘", "«‘", "¬", "−", "®–", "®", "—«", "þ«",
				"€«", "¯", "«", "—°ã", "þ°‘", "—°‘", "°‘", "±", "²", "³–",
				"³", "—°", "þ°", "€°", "´", "°", "—µã", "þµ‘", "—µ‘", "µ‘",
				"¶", "ÿ", "¸–", "¸", "—µ", "þµ", "€µ", "¹", "µ", "—îã",
				"þî‘", "—î‘", "î‘", "ï", "ð", "ñ–", "ñ", "—î", "þî", "€î",
				"ò", "î", "—ºã", "þº‘", "—º‘", "º‘", "»", "¼", "½", "¾",
				"—º", "þº", "€º", "¿", "º", "—Àã", "þÀ‘", "—À‘", "À‘", "�?",
				"Â", "Ã", "Ä", "—À", "þÀ", "€À", "Å", "À", "—Æã", "þÆ‘",
				"—Æ‘", "Æ‘", "Ç", "È", "É", "Ê", "—Æ", "þÆ", "€Æ", "Ë",
				"Æ", "—Ìã", "þÌ‘", "—Ì‘", "Ì‘", "�?", "Î", "�?", "�?", "—Ì",
				"þÌ", "€Ì", "Ñ", "Ì", "—Òã", "þÒ‘", "—Ò‘", "Ò‘", "Ó", "Ô",
				"Õ–", "Õ", "—Ò", "þÒ", "€Ò", "Ö", "Ò", "—ãã", "þã‘", "—ã‘",
				"ã‘", "ä", "å", "æ", "ç", "—ã", "þã", "€ã", "è", "ã",
				"€×", "—××", "þ×‘", "—×‘", "×‘", "Ø", "Ù", "Ú", "Û", "—×",
				"þ×", "Ü", "×", "—éé", "þé‘", "—é‘", "é‘", "ê", "ë", "ì–",
				"ì", "—é", "þé", "€é", "í", "é", "—ùù", "þù‘", "—ù‘", "ù‘",
				"ù", "ù", "ù�?", "ù•", "—ù", "þù", "€ù", "ú", "ù", "—�?�?",
				"þ�?‘", "—�?‘", "�?‘", "Þ", "ß", "à", "á", "—�?", "þ�?", "€�?",
				"â", "�?", "öåå", "÷åõ", "öåõ", "åõ", "æ", "ç", "åú", "åü",
				"öå", "÷å", "øå", "è", "å", "—õõ", "þõ‘", "—õ‘", "õ‘", "õ",
				"õ", "—õ", "þõ", "€õ", "ö", "õ", "—÷÷", "þ÷‘", "—÷‘", "÷‘",
				"÷", "÷", "—÷", "þ÷", "€÷", "ø", "÷", "�?", "‚", "„",
				"…", "†", "‡", "ˆ", "‰", "Š", "‹", "Šã", "Œ", "ƒ",
				"ý"};

		String TamilReplace[] = {"க�?ஷ", "க�?ஷா", "க�?ஷி", "க�?ஷீ", "க�?ஷ�?", "க�?ஷூ", "க�?ஷெ", "க�?ஷே", "க�?ஷொ", "க�?ஷோ",
				"க�?ஷௌ", "க�?ஷை", "க�?ஷ�?", "ஜௌ", "ஜோ", "ஜொ", "ஜா", "ஜி", "ஜி", "ஜீ",
				"ஜ�?", "ஜூ", "ஜெ", "ஜே", "ஜை", "ஜ�?", "ஜ", "கௌ", "கோ", "கொ",
				"கா", "கி", "கீ", "க�?", "கூ", "கெ", "கே", "கை", "க�?",
				"க", "ஙௌ", "ஙோ", "ஙொ", "ஙா", "ஙி", "ஙீ", "ஙெ", "ஙே", "ஙை",
				"ங�?", "ங", "சௌ", "சோ", "சொ", "சா", "சி", "சீ", "ச�?", "சூ",
				"செ", "சே", "சை", "ச�?", "ச", "ச", "ஞௌ", "ஞோ", "ஞொ", "ஞா",
				"ஞி", "ஞீ", "ஞெ", "ஞே", "ஞை", "ஞ�?", "ஞ", "டௌ", "டோ", "டொ",
				"டா", "டி", "டீ", "ட�?", "டூ", "டெ", "டே", "டை", "ட�?", "ட",
				"ணௌ", "ணோ", "ணொ", "ணா", "ணி", "ணீ", "ணூ", "ண�?", "ணெ", "ணே",
				"ணை", "ண�?", "ண", "தௌ", "தோ", "தொ", "தா", "தி", "தீ", "தூ",
				"த�?", "தெ", "தே", "தை", "த�?", "த", "நௌ", "நோ", "நொ", "நா",
				"நி", "நீ", "நூ", "ந�?", "நெ", "நே", "நை", "ந�?", "ந", "னௌ",
				"னோ", "னொ", "னா", "னி", "னீ", "னூ", "ன�?", "னெ", "னே", "னை",
				"ன�?", "ன", "பௌ", "போ", "பொ", "பா", "பி", "பீ", "ப�?", "பூ",
				"பெ", "பே", "பை", "ப�?", "ப", "மௌ", "மோ", "மொ", "மா", "மி",
				"மீ", "ம�?", "மூ", "மெ", "மே", "மை", "ம�?", "ம", "யௌ", "யோ",
				"யொ", "யா", "யி", "யீ", "ய�?", "யூ", "யெ", "யே", "யை", "ய�?",
				"ய", "ரௌ", "ரோ", "ரொ", "ரா", "ரி", "ரீ", "ர�?", "ரூ", "ரெ",
				"ரே", "ரை", "ர�?", "ர", "லௌ", "லோ", "லொ", "லா", "லி", "லீ",
				"லூ", "ல�?", "லெ", "லே", "லை", "ல�?", "ல", "ளௌ", "ளோ", "ளொ",
				"ளா", "ளி", "ளீ", "ள�?", "ளூ", "ளெ", "ளே", "ளை", "ள�?", "ள",
				"வை", "வௌ", "வோ", "வொ", "வா", "வி", "வீ", "வ�?", "வூ", "வெ",
				"வே", "வ�?", "வ", "றௌ", "றோ", "றொ", "றா", "றி", "றீ", "றூ",
				"ற�?", "றெ", "றே", "றை", "ற�?", "ற", "ஹௌ", "ஹோ", "ஹொ", "ஹா",
				"ஹி", "ஹீ", "ஹ�?", "ஹூ", "ஹெ", "ஹே", "ஹை", "ஹ�?", "ஹ", "ழௌ",
				"ழோ", "ழொ", "ழா", "ழி", "ழீ", "ழ�?", "ழூ", "ழெ", "ழே", "ழை",
				"ழ�?", "ழ", "ஷௌ", "ஷோ", "ஷொ", "ஷா", "ஷி", "ஷீ", "ஷ�?", "ஷூ",
				"ஷெ", "ஷே", "ஷை", "ஷ�?", "ஷ", "ஷௌ", "ஷோ", "ஷொ", "ஷா", "ஷி",
				"ஷீ", "ஷெ", "ஷே", "ஷை", "ஷ�?", "ஷ", "ஸௌ", "ஸோ", "ஸொ", "ஸா",
				"ஸி", "ஸீ", "ஸெ", "ஸே", "ஸை", "ஸ�?", "ஸ", "அ", "ஆ", "ஈ",
				"உ", "ஊ", "எ", "�?", "�?", "ஒ", "ஓ", "ஔ", "ஃ", "இ",
				"ஸ�?ரீ"};

		int count = 0;

		while (count < TamilText.length) {
			unicodeStr = unicodeStr.replaceAll(TamilReplace[count],TamilText[count]);
			count++;
		}
		return unicodeStr; 
	}
	
	
	private String convertToTab(String unicodeStr){
		String TamilText[] = {"þ", "þ£", "þ¤", "þ¦", "þ§", "þ¨", "ªþ", "«þ", "ªþ£", "«þ£",
				"ªþ÷", "¬þ", "þ¢", "ªè÷", "«è£", "ªè£", "è£", "è¤", "è¦", "°",
				"Ã", "ªè", "«è", "¬è", "è¢", "è", "ªé÷", "«é£", "ªé£", "é£",
				"é¤", "é¦", "±", "Ä", "ªé", "«é", "¬é", "é¢", "é", "ªê÷",
				"«ê£", "ªê£", "ê£", "ê¤", "ê¦", "²", "Å", "ªê", "«ê", "¬ê",
				"ê¢", "ê", "ªü÷", "«ü£", "ªü£", "ü£", "ü¤", "ü¦", "ü§", "ü¨",
				"ªü", "«ü", "¬ü", "ü¢", "ü", "ªë÷", "«ë£", "ªë£", "ë£", "ë¤",
				"ë¦", "³", "Æ", "ªë", "«ë", "¬ë", "ë¢", "ë", "ªì÷", "«ì£",
				"ªì£", "ì£", "ì¤", "®", "¯", "ì¦", "´", "Ç", "ªì", "«ì",
				"¬ì", "ì¢", "ì", "ªí÷", "«í£", "ªí£", "í£", "í¤", "í¦", "µ",
				"È", "ªí", "«í", "¬í", "í¢", "í", "ªî÷", "«î£", "ªî£", "î£",
				"î¤", "î¦", "¶", "É", "ªî", "«î", "¬î", "î¢", "î", "ªï÷",
				"«ï£", "ªï£", "ï£", "ï¤", "ï¦", "¸", "Ë", "ªï", "«ï", "¬ï",
				"ï¢", "ï", "ªù÷", "«ù£", "ªù£", "ù£", "ù¤", "ù¦", "Â", "Û",
				"ªù", "«ù", "¬ù", "ù¢", "ù", "ªð÷", "«ð£", "ªð£", "ð£", "ð¤",
				"ð¦", "¹", "Ì", "ªð", "«ð", "¬ð", "ð¢", "ð", "ªñ÷", "«ñ£",
				"ªñ£", "ñ£", "ñ¤", "ñ¦", "º", "�?", "ªñ", "«ñ", "¬ñ", "ñ¢",
				"ñ", "ªò÷", "«ò£", "ªò£", "ò£", "ò¤", "ò¦", "»", "Î", "ªò",
				"«ò", "¬ò", "ò¢", "ò", "ªó÷", "«ó£", "ªó£", "ó£", "ó¤", "ó¦",
				"¼", "�?", "ªó", "«ó", "¬ó", "ó¢", "ó", "ªô÷", "«ô£", "ªô£",
				"ô£", "ô¤", "ô¦", "½", "Ö", "ªô", "«ô", "¬ô", "ô¢", "ô",
				"ª÷÷", "«÷£", "ª÷£", "÷£", "÷¤", "÷¦", "À", "Ù", "ª÷", "«÷",
				"¬÷", "÷¢", "÷", "ªõ÷", "«õ£", "ªõ£", "õ£", "õ¤", "õ¦", "¾",
				"×", "ªõ", "«õ", "¬õ", "õ¢", "õ", "ªö÷", "«ö£", "ªö£", "ö£",
				"ö¤", "ö¦", "¿", "Ø", "ªö", "«ö", "¬ö", "ö¢", "ö", "ªø÷",
				"«ø£", "ªø£", "ø£", "ø¤", "ø¦", "�?", "Ú", "ªø", "«ø", "¬ø",
				"ø¢", "ø", "ªý÷", "«ý£", "ªý£", "ý£", "ý¤", "ý¦", "ªý", "«ý",
				"¬ý", "ý¢", "ý", "ªû÷", "«û£", "ªû£", "û£", "û¤", "û¦", "ªû",
				"«û", "¬û", "û¢", "û", "ªú÷", "«ú£", "ªú£", "ú£", "ú¤", "ú¦",
				"ªú", "«ú", "¬ú", "ú¢", "ú", "Ü", "�?", "Þ", "ß", "à",
				"á", "â", "ã", "ä", "å", "æ", "å÷", "ç", "‚", "ƒ",
				"„", "…", "†", "—", "˜", "–", "", "ˆ", "‰", "Ã",
				"ÿ"};

		String TamilReplace[] = {"க�?ஷ", "க�?ஷா", "க�?ஷி", "க�?ஷீ", "க�?ஷ�?", "க�?ஷூ", "க�?ஷெ", "க�?ஷே", "க�?ஷொ", "க�?ஷோ",
				"க�?ஷௌ", "க�?ஷை", "க�?ஷ�?", "கௌ", "கோ", "கொ", "கா", "கி", "கீ", "க�?",
				"கூ", "கெ", "கே", "கை", "க�?", "க", "ஙௌ", "ஙோ", "ஙொ", "ஙா",
				"ஙி", "ஙீ", "ங�?", "ஙூ", "ஙெ", "ஙே", "ஙை", "ங�?", "ங", "சௌ",
				"சோ", "சொ", "சா", "சி", "சீ", "ச�?", "சூ", "செ", "சே", "சை",
				"ச�?", "ச", "ஜௌ", "ஜோ", "ஜொ", "ஜா", "ஜி", "ஜீ", "ஜ�?", "ஜூ",
				"ஜெ", "ஜே", "ஜை", "ஜ�?", "ஜ", "ஞௌ", "ஞோ", "ஞொ", "ஞா", "ஞி",
				"ஞீ", "ஞ�?", "ஞூ", "ஞெ", "ஞே", "ஞை", "ஞ�?", "ஞ", "டௌ", "டோ",
				"டொ", "டா", "டி", "டி", "டீ", "டீ", "ட�?", "டூ", "டெ", "டே",
				"டை", "ட�?", "ட", "ணௌ", "ணோ", "ணொ", "ணா", "ணி", "ணீ", "ண�?",
				"ணூ", "ணெ", "ணே", "ணை", "ண�?", "ண", "தௌ", "தோ", "தொ", "தா",
				"தி", "தீ", "த�?", "தூ", "தெ", "தே", "தை", "த�?", "த", "நௌ",
				"நோ", "நொ", "நா", "நி", "நீ", "ந�?", "நூ", "நெ", "நே", "நை",
				"ந�?", "ந", "னௌ", "னோ", "னொ", "னா", "னி", "னீ", "ன�?", "னூ",
				"னெ", "னே", "னை", "ன�?", "ன", "பௌ", "போ", "பொ", "பா", "பி",
				"பீ", "ப�?", "பூ", "பெ", "பே", "பை", "ப�?", "ப", "மௌ", "மோ",
				"மொ", "மா", "மி", "மீ", "ம�?", "மூ", "மெ", "மே", "மை", "ம�?",
				"ம", "யௌ", "யோ", "யொ", "யா", "யி", "யீ", "ய�?", "யூ", "யெ",
				"யே", "யை", "ய�?", "ய", "ரௌ", "ரோ", "ரொ", "ரா", "ரி", "ரீ",
				"ர�?", "ரூ", "ரெ", "ரே", "ரை", "ர�?", "ர", "லௌ", "லோ", "லொ",
				"லா", "லி", "லீ", "ல�?", "லூ", "லெ", "லே", "லை", "ல�?", "ல",
				"ளௌ", "ளோ", "ளொ", "ளா", "ளி", "ளீ", "ள�?", "ளூ", "ளெ", "ளே",
				"ளை", "ள�?", "ள", "வௌ", "வோ", "வொ", "வா", "வி", "வீ", "வ�?",
				"வூ", "வெ", "வே", "வை", "வ�?", "வ", "ழௌ", "ழோ", "ழொ", "ழா",
				"ழி", "ழீ", "ழ�?", "ழூ", "ழெ", "ழே", "ழை", "ழ�?", "ழ", "றௌ",
				"றோ", "றொ", "றா", "றி", "றீ", "ற�?", "றூ", "றெ", "றே", "றை",
				"ற�?", "ற", "ஹௌ", "ஹோ", "ஹொ", "ஹா", "ஹி", "ஹீ", "ஹெ", "ஹே",
				"ஹை", "ஹ�?", "ஹ", "ஷௌ", "ஷோ", "ஷொ", "ஷா", "ஷி", "ஷீ", "ஷெ",
				"ஷே", "ஷை", "ஷ�?", "ஷ", "ஸௌ", "ஸோ", "ஸொ", "ஸா", "ஸி", "ஸீ",
				"ஸெ", "ஸே", "ஸை", "ஸ�?", "ஸ", "அ", "ஆ", "இ", "ஈ", "உ",
				"ஊ", "எ", "�?", "�?", "ஒ", "ஓ", "ஔ", "ஃ", "௧", "௨",
				"௩", "௪", "௫", "௰", "௱", "௲", "௯", "௭", "௮", "௬",
				"ஸ�?ரீ"};

		int count = 0;
		while (count < TamilText.length) {
			unicodeStr = unicodeStr.replaceAll(TamilReplace[count],TamilText[count]);
			count++;
		}

		return unicodeStr;
	}
	
	private String convertToTam(String unicodeStr){
		String TamilText[] = {"þ", "þ£", "¬þ", "V", "r", "þ§", "þ¨", "ªþ", "«þ", "ªþ£",
				"«þ£", "ªþ÷", "z", "ªü÷", "«ü£", "ªü£", "ü£", "T", "p", "ü§",
				"ü¨", "ªü", "«ü", "¬ü", "ªü£", "x", "ü", "ªè÷", "«è£", "ªè£",
				"è£", "A", "W", "°", "Ã", "ªè", "«è", "¬è", "‚", "è",
				"ªé÷", "«é£", "ªé£", "é£", "B", "X", "±", "Ä", "ªé", "«é",
				"¬é", "ƒ", "é", "ªê÷", "«ê£", "ªê£", "ê£", "C", "Y", "²",
				"Å", "ªê", "«ê", "¬ê", "„", "ê", "ªë÷", "«ë£", "ªë£", "ë£",
				"D", "Z", "³", "Æ", "ªë", "«ë", "¬ë", "…", "ë", "ªì÷",
				"«ì£", "ªì£", "ì£", "®", "¯", "´", "Ç", "ªì", "«ì", "¬ì",
				"†", "ì", "ªí÷", "«í£", "ªí£", "í£", "E", "a", "µ", "È",
				"ªí", "«í", "¬í", "‡", "í", "ªî÷", "«î£", "ªî£", "î£", "F",
				"b", "¶", "É", "ªî", "«î", "¬î", "ˆ", "î", "ªï÷", "«ï£",
				"ªï£", "ï£", "G", "c", "¸", "Ë", "ªï", "«ï", "¬ï", "ªï£",
				"‰", "ï", "ªù÷", "«ù£", "ªù£", "ù£", "Q", "m", "Â", "Û",
				"ªù", "«ù", "¬ù", "ªù£", "¡", "ù", "ªð÷", "«ð£", "ªð£", "ð£",
				"H", "d", "¹", "Ì", "ªð", "«ð", "¬ð", "ªð£", "Š", "ð",
				"ªñ÷", "«ñ£", "ªñ£", "ñ£", "I", "e", "º", "�?", "ªñ", "«ñ",
				"¬ñ", "ªñ£", "‹", "ñ", "ªò÷", "«ò£", "ªò£", "ò£", "J", "f",
				"»", "Î", "ªò", "«ò", "¬ò", "ªò£", "Œ", "ò", "ªó÷", "«ó£",
				"ªó£", "ó£", "K", "g", "¼", "�?", "ªó", "«ó", "¬ó", "˜",
				"ó", "ªô÷", "«ô£", "ªô£", "ô£", "L", "h", "½", "Ö", "ªô",
				"«ô", "¬ô", "™", "ô", "ª÷÷", "«÷£", "ª÷£", "÷£", "O", "k",
				"À", "Ù", "ª÷", "«÷", "¬÷", "œ", "÷", "ªõ÷", "«õ£", "ªõ£",
				"õ£", "M", "i", "¾", "×", "ªõ", "«õ", "¬õ", "ªõ£", "š",
				"õ", "ªö÷", "«ö£", "ªö£", "ö£", "N", "j", "¿", "Ø", "ªö",
				"«ö", "¬ö", "›", "ö", "ªø÷", "«ø£", "ªø£", "ø£", "P", "l",
				"�?", "Ú", "ªø", "«ø", "¬ø", "ªø£", "Ÿ", "ø", "ªý÷", "«ý£",
				"ªý£", "ý£", "U", "q", "ªý", "«ý", "¬ý", "y", "ý", "ªû÷",
				"«û£", "ªû£", "û£", "S", "o", "ªû", "«û", "¬û", "ªû£", "w",
				"û", "ªú÷", "«ú£", "ªú£", "ú£", "R", "n", "ªú", "«ú", "¬ú",
				"v", "ú", "Ü", "�?", "Þ", "ß", "à", "á", "â", "ã",
				"ä", "å", "æ", "å÷", "ç", "ÿ", "ƒ"};

		String TamilReplace[] = {"க�?ஷ", "க�?ஷா", "க�?ஷை", "க�?ஷி", "க�?ஷீ", "க�?ஷ�?", "க�?ஷூ", "க�?ஷெ", "க�?ஷே", "க�?ஷொ",
				"க�?ஷோ", "க�?ஷௌ", "க�?ஷ�?", "ஜௌ", "ஜோ", "ஜொ", "ஜா", "ஜி", "ஜீ", "ஜ�?",
				"ஜூ", "ஜெ", "ஜே", "ஜை", "ஜொ", "ஜ�?", "ஜ", "கௌ", "கோ", "கொ",
				"கா", "கி", "கீ", "க�?", "கூ", "கெ", "கே", "கை", "க�?", "க",
				"ஙௌ", "ஙோ", "ஙொ", "ஙா", "ஙி", "ஙீ", "ங�?", "ஙூ", "ஙெ", "ஙே",
				"ஙை", "ங�?", "ங", "சௌ", "சோ", "சொ", "சா", "சி", "சீ", "ச�?",
				"சூ", "செ", "சே", "சை", "ச�?", "ச", "ஞௌ", "ஞோ", "ஞொ", "ஞா",
				"ஞி", "ஞீ", "ஞ�?", "ஞூ", "ஞெ", "ஞே", "ஞை", "ஞ�?", "ஞ", "டௌ",
				"டோ", "டொ", "டா", "டி", "டீ", "ட�?", "டூ", "டெ", "டே", "டை",
				"ட�?", "ட", "ணௌ", "ணோ", "ணொ", "ணா", "ணி", "ணீ", "ண�?", "ணூ",
				"ணெ", "ணே", "ணை", "ண�?", "ண", "தௌ", "தோ", "தொ", "தா", "தி",
				"தீ", "த�?", "தூ", "தெ", "தே", "தை", "த�?", "த", "நௌ", "நோ",
				"நொ", "நா", "நி", "நீ", "ந�?", "நூ", "நெ", "நே", "நை", "நொ",
				"ந�?", "ந", "னௌ", "னோ", "னொ", "னா", "னி", "னீ", "ன�?", "னூ",
				"னெ", "னே", "னை", "னொ", "ன�?", "ன", "பௌ", "போ", "பொ", "பா",
				"பி", "பீ", "ப�?", "பூ", "பெ", "பே", "பை", "பொ", "ப�?", "ப",
				"மௌ", "மோ", "மொ", "மா", "மி", "மீ", "ம�?", "மூ", "மெ", "மே",
				"மை", "மொ", "ம�?", "ம", "யௌ", "யோ", "ய", "யா", "யி", "யீ",
				"ய�?", "யூ", "யெ", "யே", "யை", "யொ", "ய�?", "ய", "ரௌ", "ரோ",
				"ரொ", "ரா", "ரி", "ரீ", "ர�?", "ரூ", "ரெ", "ரே", "ரை", "ர�?",
				"ர", "லௌ", "லோ", "லொ", "லா", "லி", "லீ", "ல�?", "லூ", "லெ",
				"லே", "லை", "ல�?", "ல", "ளௌ", "ளோ", "ள", "ளா", "ளி", "ளீ",
				"ள�?", "ளூ", "ளெ", "ளே", "ளை", "ள�?", "ள", "வௌ", "வோ", "வொ",
				"வா", "வி", "வீ", "வ�?", "வூ", "வெ", "வே", "வை", "வொ", "வ�?",
				"வ", "ழௌ", "ழோ", "ழொ", "ழா", "ழி", "ழீ", "ழ�?", "ழூ", "ழெ",
				"ழே", "ழை", "ழ�?", "ழ", "றௌ", "றோ", "றொ", "றா", "றி", "றீ",
				"ற�?", "றூ", "றெ", "றே", "றை", "றொ", "ற�?", "ற", "ஹௌ", "ஹோ",
				"ஹொ", "ஹா", "ஹி", "ஹீ", "ஹெ", "ஹே", "ஹை", "ஹ�?", "ஹ", "ஷௌ",
				"ஷோ", "ஷொ", "ஷா", "ஷி", "ஷீ", "ஷெ", "ஷே", "ஷை", "ஷொ", "ஷ�?",
				"ஷ", "ஸௌ", "ஸோ", "ஸொ", "ஸா", "ஸி", "ஸீ", "ஸெ", "ஸே", "ஸை",
				"ஸ�?", "ஸ", "அ", "ஆ", "இ", "ஈ", "உ", "ஊ", "எ", "�?",
				"�?", "ஒ", "ஓ", "ஔ", "ஃ", "ஸ�?ரீ", "‘"};

		int count = 0;
		while (count < TamilText.length) {
			unicodeStr = unicodeStr.replaceAll(TamilReplace[count], TamilText[count]);
			count++;
		}
		return unicodeStr;
		
	}
}
