package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

import com.mysql.jdbc.Statement;
import database.Trie;

public class Main {
 	 static int count;
	 static ArrayList<Float> scoreArr = new ArrayList<Float>();
	 static float sizePattern ;
	 
	public static void main(String[] args)throws Exception	 {
		// TODO Auto-generated method stub
		getConnection();
		

	}
	
	public static Connection getConnection()throws Exception{
		Connection con;
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/Student";
			String userName = "root";
			String password = "deeppradhan";
			Class.forName(driver);
			
			 con = DriverManager.getConnection(url,userName,password);
			
			java.sql.Statement st=con.createStatement();
			HashMap<Integer, String> patternData = new HashMap<Integer, String>();
			
			Scanner noOfPatterns = new Scanner(System.in); 
			System.out.println("Enter  number of patterns to be searched: ");
			int countOfPattern = noOfPatterns.nextInt(); 
			
			Scanner enternPatter = new Scanner(System.in); 
			for(int i = 1; i<= countOfPattern; i++){
				System.out.println("Enter a pattern to be searched: ");
				String pattern = enternPatter.nextLine(); 
				patternData.put(i, pattern);
					
			}
			
			sizePattern = patternData.size();
		
			String document = null;
	        ResultSet rs=st.executeQuery("select summary,text from food");
	        
	        while(rs.next())
	       {
	       
	        	String summary = (rs.getString("summary"));
	        	String text = (rs.getString("text"));
	        	document = summary +" "+ text;
	        	
	        	insertDataInTries(document);
	        	count = 0;
	        	
				for (Map.Entry<Integer, String> entry : patternData.entrySet()) {
					String pattern = entry.getValue();
					searchPatternInTrie(pattern);
	        
		          }
	        
	    	  
	        }
	       return con;
	    
		}
		catch (Exception e) {

			System.err.println(e);
		}
		
		return null;
	}
	
	public static void insertDataInTries(String document){
		String lowerCaseStr = document.toLowerCase();
		String resultString = lowerCaseStr.replaceAll(".[^a-zA-Z0-9\\s+]", "");
		System.out.println(resultString);
		String[] words = resultString.split(" ");
		for ( String word : words) {
		
			buildTrie(word);		
	     }
}
	public static  void buildTrie(String key){
		Trie trie = new Trie();
		trie.insert(key);
		
	}
	
	public static void searchPatternInTrie(String pattern){
	
		float scoreValue;
		 Trie trie = new Trie();
		boolean found = trie.search(pattern);
		
		if(found){
			System.out.println("found");
			
			count++;
		}
		else{
			System.out.println("found");
		}

		scoreValue = count / sizePattern ;
		if(scoreValue != 0.0)                          
		  scoreArr.add(scoreValue);// Dont add in database, infact add in Array to avoid further database query
	}
}
