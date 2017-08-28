package main.pages.specials.main;

import main.pages.Special;

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
						+ "This page has moved. Click <a href='http://www.marzipops.com/FAQ.html'>here</a> to go to the new page."
					+ "</body>"
				+ "</html>";
	}

	public String buildContent() {
		return null;
	}

}
