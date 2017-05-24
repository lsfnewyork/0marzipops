package main.pages.specials;

import main.helpers.Constants;
import main.pages.Special;
import main.readers.DetailsReader;

public class Details extends Special{

	/**
	 * Constructor for an Details object.
	 */
	public Details(){
		super("Details");
	}
	
	/**
	 * Abstract method of Page.java
	 * @return The part of the HTML file that contains the main content of the page.
	 */
	public String buildContent() {
		String write = "";
		
		try {
			//Reader
			DetailsReader reader = new DetailsReader(Constants.EXCEL_FILE);

			//Questions
			write = "<div class='row'><div class='small-12 columns'>"
					+ "<div class='row'><div class='small-12 columns'>";
			
				for(int index=0; index<reader.getNumberOfQuestions(); index++){
					if(index == 0){
						write += writeHeader(false, "About our Products");
					}else if(index == reader.getBreakingPoint()){
						write += writeHeader(false, "Placing an Order");
					}
					String question = reader.getQuestion(index);
					write += "<p class='bold pointer'><a href='#" + index + "'>" + question + "</a></p>";
				}
				
			write += "</div></div>";
			
			//Answers		
			write += "<div class='row'><div class='small-12 columns'>";
				
			for(int index=0; index<reader.getNumberOfQuestions(); index++){
				if(index == 0){
					write += writeHeader(true, "About our Products");
				}else if(index == reader.getBreakingPoint()){
					write += writeHeader(true, "Placing an Order");
				}
				
				String question = reader.getQuestion(index);
				String answer = reader.getAnswer(index);
				
				write += "<a name='" + index + "'>&nbsp;</a><br>"
						+ "<div class='row'><div class='small-12 columns'><p class='bold pointer'><a id='PopBlue' href='#" + index + "' onClick='boldStuff(" + index + ");' '>" + question + "</a></p></div></div>"
						+ "<div class='row'><div class='small-12 columns'><p>" + answer + "</p></div></div>";
			}
			
			write += "</div></div>"
					+ "</div></div>";
			
			
			//Blanks at the bottom to leave room for #names
			for(int i=0; i<35; i++){
				write += Constants.BLANK;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return write;
	}
	
	/**
	 * Helper method to buildContent(). Builds the part of the HTML file that contains the section headers on the details page.
	 * @param haveName Should the "a" element have a "name" attribute. True if yes.
	 * @param text The name of the section.
	 * @return The HTML file that contains the section headers on the details page.
	 */
	private String writeHeader(boolean haveName, String text){
		String name = "";
		if(haveName==true){name = "name=" + text.charAt(0);}
		return Constants.BLANK + "<h3><a class='bold popBlue' " + name + " href='#" + text.charAt(0) + "'>" + text + "</a></h3>";
	}
}
