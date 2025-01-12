package main.interfaces;

import main.helpers.Constants;

/** 
 * A Page object represents a page on the marzipops.com website.
 * This is a page of any type: a category, product, contact us, etc.
 * <br>
 *  
 * <br><b>Type:</b> Abstract
 * <br><b>Abstract Methods:</b> String buildContent()
 * <br><b>Constructors:</b> Page(String, String, Type)
 * <br><b>Enums:</b> Type
 */
public abstract class Page {
	
	
	/////////////
	////ENUMS////
	/////////////
	
	/**
	 * An enum that represents the type of a webpage.
	 * <br>
	 * <br><b>Values:</b> PRODUCT, CATEGORY, SPECIAL
	 */
	public enum PageType{
		/**
		 * The webpage is a page detailing a product.
		 */
		PRODUCT,
		
		/**
		 * The webpage is a page detailing a category of products.
		 */
		CATEGORY, 
		
		/**
		 * The webpage is a page that serves some special purpose, such as the About page or the Contact page
		 */
		SPECIAL
	};
	
	
	//////////////////////////
	////INSTANCE VARIABLES////
	//////////////////////////
	
	/**
	 * The raw name of the page, as it would appear on the name of the file.
	 */
	private String rawName;

	/**
	 * The location of the file of the page. This does not include the name of the file.
	 */
	private String location;
	
	/**
	 * The type of the page.
	 */
	private PageType pageType;
	
	
	////////////////////
	////CONSTRUCTORS////
	////////////////////
	
	/**
	 * The constructor for a Page object. Assumes that the adjName is a derivative of the rawName.
	 * @param rawName The name of the page as it would appear on the name of the file.
	 * @param location The location of the page, not including the fileName.
	 * @param pageType The type of the page.
	 */
	public Page(String rawName, String location, PageType pageType){
		this.rawName = rawName;
		this.location = location;
		this.pageType = pageType;
	}
	
	
	///////////////////////////////
	////buildHTML() AND HELPERS////
	///////////////////////////////
	
	/**
	 * @return The text of the HTML file of this page.
	 */
	public String buildHTML(){
		return buildHead() + buildTop() + buildContent() + buildFooter();
	}
	
	/**
	 * A helper method to buildHTML().
	 * @return The part of the HTML file that contains the information between the head and /head tags.
	 */
	private String buildHead(){
		String pageTitle;
		if(rawName.equals("index")){pageTitle = "marzipops";}else{pageTitle = "marzipops: " + getTextName();}
		
		String write = "<!doctype html><html>"
				+ "<head>"
				+ "<title>" + pageTitle + "</title>"
				+ "<meta name='p:domain_verify' content='b556a9473bc0ff0cfd08b0608c15ffa3'/>"
				+ "<link rel='stylesheet' type='text/css' href='" + getReverseLocation() + "Foundation/css/foundation.css'></link>"
				+ "<link rel='stylesheet' type='text/css' href='" + getReverseLocation() + "CSS/style.css'></link>"
				+ "<link rel='icon' type='image/png' href=" + getReverseLocation() + "'favicon.png'>"
				+ "<script src='" + getReverseLocation() + "Javascript/Javascript.js'></script>"
				+ "<script src='" + getReverseLocation() + "Javascript/Lists.js'></script>"
				+ "<script src='" + getReverseLocation() + "Javascript/Search.js'></script>"
				+ "</head>"
				+ "<body onload='" + getOnLoadCall() + "'>";
		
		//Website Wrapper
		write += "<div class='full' style='visibility: hidden;'>";	

		return write;
	}
	
	/**
	 * A helper method to buildHead()
	 * @return the method that should be called when the page loads.
	 */
	public String getOnLoadCall(){
		return "javascript(\"" + getTextName() + "\", \"" + getReverseLocation() + "\", " + (pageType == PageType.PRODUCT) + ", " + getPageIndex() + ")";
	}
	
	/**
	 * A helper method to buildHTML().
	 * @return The part of the HTML file that contains the header above the main content. It appears the same on every page.
	 */
	private String buildTop(){
		
		//Logo and search box
		String write = "<div id='header'>" + Constants.BLANK + "<div class='row'>"
				+ "<div class='show-for-medium medium-3 columns'>&nbsp</div>"
				+ "<div class='small-12 medium-6 columns'><a href=" + getReverseLocation() + "index.html><img src='" + getReverseLocation() + "Images/Logo.jpg'></imgs></a></div>"
				+ "<div class='small-12 medium-3 columns' id='searchWrapper'>&nbsp;</div>"
				+ "</div><br>";

		//Navigation bar
		write += "<div class='row'><div class='small-12 columns'><ul class='navigationBar'>";
		for(int i=0; i<Constants.LIST_OF_SPECIALS.length; i++){
			write = write + "<li class='navigationBar-link'><p><a href='" + getReverseLocation() + Constants.LIST_OF_SPECIALS[i] + ".html#'>" + Constants.LIST_OF_SPECIALS[i].toLowerCase() + "</a></p></li>";
			if(i != Constants.LIST_OF_SPECIALS.length-1){
				write = write + "<li class='navigationBar-verticalLine'><p>|</p></li>";
			}
		}

		//Social Media links
		for(int i=0; i<Constants.LIST_OF_MEDIA.length; i++){
			write = write + "<li class='show-for-medium navigationBar-social'><a href='http://www." + Constants.LIST_OF_MEDIA[i] + ".com/marzipops' target='_blank'><img src='" + getReverseLocation() + "Images/Social Media/Orange " + Constants.LIST_OF_MEDIA[i] + ".jpg'></img></a></li>";
		}
		write += "</ul></div></div>";
		
		//Content Header
		write += "</div><div id='content'>";
		
		//Top Line
		write += buildTopLine();

		return write;
	}
	
	/**
	 * <b>ABSTRACT</b><br>
	 * A helper method to buildTop().
	 * @return The part of the HTML file that contains the top line.
	 */
	public abstract String buildTopLine();
	
	/**
	 * <b>ABSTRACT</b><br>
	 * A helper method to buildHTML().
	 * @return The part of the HTML file that contains the main content of the page.
	 */
	public abstract String buildContent();
	
	/**
	 * A helper method to buildHTML().
	 * @return The part of the HTML file that contains the footer below the main content. It appears the same on every page.
	 */
	private String buildFooter(){
		String write = "";	
		
		//Bottom social media links
		write += "</div><div id='footer'><div class='row show-for-small-only'><div class='small-6 small-centered columns'><div class='row'>";
		for(int i=0; i<Constants.LIST_OF_MEDIA.length; i++){
			write += "<div class='small-3 columns'><a href='http://www." + Constants.LIST_OF_MEDIA[i] + ".com/marzipops' target='_blank'><img src='" + getReverseLocation() + "Images/Social Media/Orange " + Constants.LIST_OF_MEDIA[i] + ".jpg'></img></a></div>";
		}
		write += "</div></div></div></div></div>";

		
		//Foundation
		write += "</div><script src='" + getReverseLocation() + "Foundation/js/vendor/jquery.js'></script>"
				+ "<script src='" + getReverseLocation() + "Foundation/js/vendor/what-input.js'></script>"
				+ "<script src='" + getReverseLocation() + "Foundation/js/vendor/foundation.min.js'></script>"
				+ "<script src='" + getReverseLocation() + "Foundation/js/app.js'></script>";

		
		//Google Analytics
		write += "<script>"
				+ "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){"
				+ 	"(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),"
				+ 		"m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)"
				+ 	"})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');"
				+ 	"ga('create', 'UA-78064797-1', 'auto');"
				+ 	"ga('send', 'pageview');"
				+ "</script>";

		write += "</body></html>";

		return write;
	}
	
	
	///////////////
	////GETTERS////
	///////////////
	
	/**
	 * @return The raw name of the page, as it would appear on the name of the file.
	 */
	public String getRawName(){
		return rawName;
	}

	/**
	 * @return The name of the page as it would appear in HTML text or in the title of a webpage.
	 */
	public String getTextName(){
		return rawName.replaceAll("-", "&dash;").replaceAll("'", "&#39;").toLowerCase();
	}
	
	/**
	 * @return The location of the file of the page. This does not include the name of the file.
	 */
	public String getLocation(){
		return location;
	}
	
	/**
	 * @return The file path to get from the location of the page to the root directory.
	 */
	public String getReverseLocation(){
		int numberOfFolders = location.length() - location.replace("/", "").length();
		
		String str = "";
		for(int i=0; i<numberOfFolders; i++){
			str += "../";
		}
		
		return str;
	}
	
	/**
	 * @return the index of the page. -1 for all non-products.
	 */
	public int getPageIndex(){
		return -1;
	}
}
