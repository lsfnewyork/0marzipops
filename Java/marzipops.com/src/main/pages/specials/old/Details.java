package main.pages.specials.old;

import main.pages.Special;

/**
 * This page no longer exists. This is here in case someone still has the link
 */
public class Details extends Special {

	public Details() {
		super("Details");
	}

	public String buildHTML() {
		return "<html>"
					+ "<head>"
						+ "<meta http-equiv='refresh' content='0; url=http://www.marzipops.com/FAQ.html' />"
						+ "<title>Page Moved</title>"
					+ "</head>"
					+ "<body>"
						+ "This page has moved. If you are not redirected to the FAQ, click <a href='http://www.marzipops.com/FAQ.html'>here</a>."
					+ "</body>"
				+ "</html>";
	}

	public String buildContent() {
		return null;
	}

}
