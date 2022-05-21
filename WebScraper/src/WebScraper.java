import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper {
	
	
	static String userInput;
	
	static String rawData;
	
	static String email;
	
	static int emailCount = 0;


	public static void main(String[] args) {
		
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a url to scrape: ");
		

		
		userInput = input.next();
		
		
		final String url = userInput;
		
		Document finalURL = null;
		try {
			finalURL = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Pattern p = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
		 Matcher matcher = p.matcher(finalURL.text());
		Set<String> emails = new HashSet<String>();
        while (matcher.find()) {
            emails.add(matcher.group());
        }
		
		
		
		try {
			final Document document = Jsoup.connect(userInput).get();
			
			
			
			rawData = document.outerHtml();
			
			//System.out.println(rawData);
			
			for (String word : rawData.split(" ")) { 
				if (word.contains("@")){
			        email += word;    
					
			    }
				
			}
			
			Set<String> links = new HashSet<String>();
			
			Elements elements = finalURL.select("a[href]");
	        for (Element e : elements) {
	            links.add(e.attr("href"));
	        }
	        
	        for (int i = 0; i < emails.size(); i++) {	        	
	        	emailCount++;
	        	//System.out.println(emails);
	        	
	        }
	        
	        System.out.println(emailCount + " emails were found!");
	        System.out.println(" ");
	        
	        emails.forEach((address) -> {
	            System.out.println(address);
	       });
	        
	       
	        
	        //System.out.println("The list of emails:" + emails);
	        
				
				
		}
		
		
		catch (Exception ex) { 
			ex.printStackTrace();		 }
		
		
		
	}

}

