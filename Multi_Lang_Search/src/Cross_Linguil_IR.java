import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.hi.HindiAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;


public class Cross_Linguil_IR 
{
  public static void main(String[] args)
  {
	 //Indexing for English Word
	   System.out.println("............Indexing for English Language..........");
	   Analyzer eng_analyzer = new StandardAnalyzer();
	   String eng_indexPath = "D:\\English_Index";
	   String docsPath = "D:\\IR_DataSet\\eng-hindi-dict-utf8";
       IndexFiles.indexing(eng_analyzer,eng_indexPath,docsPath);
       
     //Indexing for Hindi Word  
       System.out.println("............Indexing for Hindi Language..........");
       Analyzer hindi_analyzer = new HindiAnalyzer();
	   String hindi_indexPath = "D:\\Hindi_Index";
	   String hindi_docsPath = "D:\\IR_DataSet\\eng-hindi-dict-utf8";
       IndexFiles.indexing(hindi_analyzer,hindi_indexPath,hindi_docsPath);
       
       /* //Search for phrase...
       System.out.println(".......Searching for phrase.........");
       //String eng_indexPath = "D:\\English_Index";
       SearchFiles.searching(eng_analyzer, eng_indexPath,);*/
  }
}
