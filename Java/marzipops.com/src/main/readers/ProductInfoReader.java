package main.readers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import main.pages.Category;

public class ProductInfoReader {

	/**
	 * An enum that represents a piece of information about a Product
	 */
	public enum Info {
		
		/**
		 * The raw name of a Product, as it would appear on the name of the file.
		 */
		RAW_NAME(0),
		
		/**
		 * The description of a Product.
		 */
		DESCRIPTION(2),
		
		/**
		 * The availability of a Product.
		 */
		AVAILABLE(3),
		
		/**
		 * The default category of the Product.
		 */
		DEFAULT_CATEGORY(7),
		
		/**
		 * All of the categories that a Product belongs to.
		 */
		CATEGORIES(13),
		
		/**
		 * The keywords that should bring up a Product during a search.
		 */
		SEARCH_TERMS(14),
		
		/**
		 * The Etsy code of a Product.
		 */
		ETSY(16),
		
		/**
		 * How many extra photos a Product has.
		 */
		EXTRA_PHOTOS(17),
		
		/**
		 * Whether a Product is sold out.
		 */
		SOLD_OUT(18);

		/**
		 * The column in the excel file that the Info can be found in.
		 */
		private int column;
		
		/**
		 * Constructor for an Info enum.
		 * @param column The column in the excel file that the Info can be found in.
		 */
		private Info(final int column){
			this.column = column;
		}
		
		/**
		 * @return The column in the excel file that the Info can be found in.
		 */
		public int getColumn(){
			return column;
		}
	}

	/**
	 * What row the excel file start listing Products.
	 */
	private static final int START = 2;

	/**
	 * The number of Products listed in the excel file.
	 */
	private int numberOfProducts;

	/**
	 * Each product and then the info about each product.
	 */
	private String[][] sheetContents;

	/**
	 * The list of Categories.
	 */
	private List<Category> listOfCategories;

	/**
	 * The main constructor for a ProductInfoReader object.
	 * @param fileLocation The location of the excel file that this ProductInfoReader should read from.
	 * @param listOfCategories The list of Categories.
	 * @throws BiffException Workbook.getWorkbook(File)
	 * @throws IOException Workbook.getWorkbook(File)
	 */
	public ProductInfoReader(String fileLocation, List<Category> listOfCategories) throws BiffException, IOException{
		File file = new File(fileLocation);
		Workbook w = Workbook.getWorkbook(file);
		Sheet sheet = w.getSheet(0);

		numberOfProducts = Integer.parseInt(sheet.getCell(0, 0).getContents());

		sheetContents = new String[numberOfProducts][Info.values().length];

		for(int i=0; i<numberOfProducts; i++){
			for(int j=0; j<Info.values().length; j++){
				sheetContents[i][j] = sheet.getCell(Info.values()[j].getColumn(), i + START).getContents();
			}
		}

		this.listOfCategories = listOfCategories;
	}
	
	/**
	 * @return The number of Products listed in the excel file.
	 */
	public int getNumberOfProducts(){
		return numberOfProducts;
	}
	
	/**
	 * @return The list of Categories.
	 */
	public List<Category> getListOfCategories(){
		return listOfCategories;
	}
	
	/**
	 * Get a piece of info about a particular Product.
	 * @param info The piece of info to return.
	 * @param id The id of the Product to check about.
	 * @return The piece of info about a particular Product.
	 */
	public String getInfo(Info info, int id){
		 return sheetContents[id][info.ordinal()];
	}
}
