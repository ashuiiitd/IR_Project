import java.net.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.*;


public class Lang_Translater 
{
	
	int i=0;
	
  public static void main(String args[]) throws Exception
  {
	  // API Key
	  // trnsl.1.1.20160420T023202Z.cddccb17bc2a7b3f.5f22390edf85a1324e01b794b3416d3afcc3b49b
	  
//	    Translate.setClientId("Language_translation");
//		Translate.setClientSecret("us2d362oaVG3oXuRGay/zKNINbTr43bmq4Jz3S9uqeg=");
//		String translatedText=Translate.execute("happy", Language.ENGLISH, Language.HINDI);
//		System.out.println(translatedText);
	  String s="deepak";
	  System.out.println(s);  
	  
	  Lang_Translater l=new Lang_Translater();
	  l.changeString(s);
	  System.out.println(l.i);
	  l.changeString(s);
	  System.out.println(l.i);
	  
	  Lang_Translater l1=new Lang_Translater();
	  l1.changeString(s);
	  System.out.println(l1.i);
  }
  public void changeString(String s)
  {
	 
	 i++;
	  
  }
}



class User{
	
}