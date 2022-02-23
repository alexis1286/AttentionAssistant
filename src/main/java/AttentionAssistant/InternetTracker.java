/**
 * Tracks the internet Usage to determine if user is on topic while searching the internet 
 */

//To be implemented by Paul

package AttentionAssistant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.apache.commons.lang3.StringUtils;

public class InternetTracker {
	
	int internetScore;
	
	/**
	 * Instantiating empty InternetTracker object
	 * @author jmitchel2
	 */
	public InternetTracker(){
		this.internetScore = 100;
	}
	
	/**
	 * Create a class InternetTracker with a specified
	 * internetScore
	 * @param int 
	 */
	public InternetTracker(int internetScore) {
		this.internetScore = internetScore;
	}
	/**
	 * Start of Encapsulation
	 * 
	 * Get internetScore
	 * @return int
	 */
	public int getInternetScore() {
		return this.internetScore;
	}

	/**
	 * Set internetScore
	 * @param int
	 */
	public void setInternetScore(int internetScore) {
		this.internetScore = internetScore;
	}
	
	/**
	 * Starts tracking relevance of Internet activity 
	 * 
	 * @param ArrayList<String>
	 * @author ehols001
	 */
	public void startTracking(ArrayList<String> keywords) throws IOException {
		//Can substitute out what's being stored in uri with whatever method we decide on for getting the url
		String uri = "https://en.wikipedia.org";
		
		String text = parseFromOrigin(uri).toLowerCase();
		calculateInternetScore(keywords, text);
	}
	
	/**
	 * Iterates through a list of provided keywords checking if the 
	 * current keyword exists within the webpage, totaling up an average score.
	 * 
	 * @param ArrayList<String> keywords
	 * @param String text - text from a web page
	 * @author ehols001
	 */
	public void calculateInternetScore(ArrayList<String> keywords, String text) {
		int score = 0, total = 0;
		for (String keyword : keywords) {
			if (text.contains(keyword)) {
				//int count = StringUtils.countMatches(text, keyword);
				score = 100;
				total += score;
			}
			else {
				score = 0;
			}
		}
		if (total == 0) {
			this.setInternetScore(total);
		}
		else {
			int averageScore = total / keywords.size();
			this.setInternetScore(averageScore);
		}
	}
	
	/**
	 * Parses the contents of a web page from live or local origin
	 * and returns all text within the body.
	 * 
	 * @param String - Can be either a live url or a local file path 
	 * @return String - The parsed contents of the page
	 * @throws IOException
	 * @author ehols001
	 */
	public String parseFromOrigin(String uri) throws IOException {
		String text = "";
		if (uri.startsWith("http")) {
			Document webpage = Jsoup.connect(uri).get();
			text = webpage.body().text();
			return text;
		}
		else {
			File input = new File(uri);
			Document localpage = Jsoup.parse(input, "UTF-8");
			text = localpage.body().text();
			return text;
		}
	}
	
}
