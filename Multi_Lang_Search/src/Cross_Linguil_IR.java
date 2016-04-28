import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.hi.HindiAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.FSDirectory;


public class Cross_Linguil_IR 
{
  public static void main(String[] args) throws Exception
  {
	 //Indexing for English Word
	   System.out.println("............Indexing for English Language..........");
	   Analyzer eng_analyzer = new StandardAnalyzer();
	   String eng_indexPath = "D:\\English_Index";
	   String docsPath = "D:\\IR_DataSet\\eng-hindi-dict-utf8";
	   //String docsPath="D:\\IR_DataSet\\Megha_Eng_wiki-small\\en\\articles";
       IndexFiles.indexing(eng_analyzer,eng_indexPath,docsPath);
       
     //Indexing for Hindi Word  
       System.out.println("............Indexing for Hindi Language..........");
       Analyzer hindi_analyzer = new HindiAnalyzer();
	   String hindi_indexPath = "D:\\Hindi_Index";
	   String hindi_docsPath = "D:\\IR_DataSet\\eng-hindi-dict-utf8";
	   //String hindi_docsPath = "D:\\IR_DataSet\\Yogesh_hindi_wikipedia-hi-html";
       IndexFiles.indexing(hindi_analyzer,hindi_indexPath,hindi_docsPath);
       
       
       
        //Search for phrase...
	    System.out.println(".......Searching for phrase.........");
	    System.out.println("Enter the Query :");
	    BufferedReader in = null;
	    String index = "D:\\English_Index";
	    String field = "contents";
	    int hitsPerPage = 10;
	    String queries = null;
	    String queryString = null;
	    boolean raw = false;
	    if (queries != null) 
	    {
	      in = Files.newBufferedReader(Paths.get(queries), StandardCharsets.UTF_8);
	    } 
	    else 
	    {
	      in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
	    }
	    
	    String line =  in.readLine();
	    
	    if (line == null || line.length() == -1) 
		{
			System.err.println("Provide an valid string");
		}
		if (line.length() == 0)
		{
			System.err.println("Empty String .Plz provive an non empty string");
		} 
	    //Scanner sc=new Scanner(System.in);
	   // String input_query=sc.next();
	    //Set of related synonyms
	    HashMap<String,HashSet<String>> hm=SearchFiles.getSynonymMap(line);
	    
	    String query_format=SearchFiles.makeQuery(line, hm, true);
	  
	    IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(index)));
	    IndexSearcher searcher = new IndexSearcher(reader);
	    //Analyzer analyzer = new StandardAnalyzer();
	
//	    /*//IndexSearcher searcher = new IndexSearcher(reader);


    searcher.setSimilarity(new ClassicSimilarity () {
	   	   	public float computeNorm(FieldInvertState state,String field){
	   	   		return 1.0f;
	   	   	}
	   	   }
   	      );


	   
	      
	    
	  QueryParser queryParser = new QueryParser(field, new StandardAnalyzer());
	  Query query = queryParser.parse(query_format);
	  System.out.println(" query is "+query);
	  SearchFiles.doPagingSearch(in, searcher, query, hitsPerPage, raw, queries == null && queryString == null);
//	  
//	  QueryParser queryParser = new QueryParser(field, new StandardAnalyzer());
//	  Query query = queryParser.parse("\"daily newspaper\"");
//	  System.out.println(" query is "+query);
//	  SearchFiles.doPagingSearch(in, searcher, query, hitsPerPage, raw, queries == null && queryString == null);
	   
	      // search with query parser
	 
	  //Search the phrase in Hindi 
	 
	 String hindi_Search=SearchFiles.makeQuery(line, hm,false);
	 IndexReader reader_hindi = DirectoryReader.open(FSDirectory.open(Paths.get(hindi_indexPath)));
	 IndexSearcher searcher_hindi = new IndexSearcher(reader_hindi);
	 //IndexSearcher searcher = new IndexSearcher(reader);
	 //IndexSearcher searcher = new IndexSearcher(reader);


	 searcher_hindi.setSimilarity(new ClassicSimilarity () {
		   	public float computeNorm(FieldInvertState state,String field){
		   		return 1.0f;
		   	}
		   }
		      );

	 QueryParser queryParser_hindi = new QueryParser(field, new HindiAnalyzer());
	  Query query_hindi = queryParser_hindi.parse(hindi_Search);
	  System.out.println(" query is "+query_hindi);
	      SearchFiles.doPagingSearch(in, searcher_hindi, query_hindi, hitsPerPage, raw, queries == null && queryString == null);
	   
	 System.out.println("Ho gya search.......");
	
	 reader.close();
	    
        
  }
}
