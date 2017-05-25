package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.helpers.Constants;
import main.helpers.Utility;
import main.interfaces.Item;
import main.pages.Category;
import main.pages.HomePage;

public class Main {
	
	public static void main(String[] args){
		Utility.clearDirectory(new File(Constants.WEB_LOCATION + "Categories"), false);	
		Utility.clearDirectory(new File(Constants.WEB_LOCATION + "Products"), false);
		List<Category> categories = makeListOfCategories();		
	}

	/*
	 * I can fix the casting by having getListOfCategories be a Item method
	 * and then having makeListOfCategories() return a list of Items instead of Categories.
	 * Although I might need a fix for makeListOfProducts then.
	 */
	
	
	/**
	 * Creates the list of Categories that are needed for this website.
	 * This method has four steps:<br>
	 * 1) Create the index page and create and fill it with all of it's children categories.
	 * Children Categories that have children themselves will follow the same process,
	 * With their children being created and then filled and so on and so forth.<br>
	 * 2) Add the current first holiday to the index Page's list of Items.<br>
	 * 3) Create the three other HomePages (indexSignedUp, Shop, and ShopSignedUp).<br>
	 * 4) Get a list of all of the Categories that were created and add them to a list which then gets returned.
	 * @return a list of all of the Categories.
	 */
	public static List<Category> makeListOfCategories(){		
		//The list of all of the Categories. This will get returned.
		List<Category> listOfCategories = new ArrayList<Category>();
		
		//PART 1
		//Create the Index Category
		Category index = new HomePage("index");
		//Fill with Categories. As this is a recursive function, all of the non-home-page categories will be created here.
		fillCategory(index, Constants.LIST_OF_CATEGORIES);
		//Add the index category to the listOfCategories
		listOfCategories.add(index);

		//Part 2
		//Look through each child and find that one that has the name of the first Holiday.
		//Then add that one to the index Page's list of items
		for(Item item : index.getListOfAllChildCategories()){
			if(item.getRawName().equals(Utility.sortHolidays()[0])){
				index.addToList((Category) item);
			}
		}
		
		//Part 3
		//Create and fill the other three HomePages. Also add these Categories to the listOfCategories.
		String[] otherHomePageNames = {"indexSignedUp", "Shop", "ShopSignedUp"};
		for(String pageName : otherHomePageNames){
			Category otherHomePage = new HomePage(pageName);
			listOfCategories.add(otherHomePage);
			for(Item item : index.getListOfItems()){
				otherHomePage.addToList(item);
			}
		}
		
		//Part 4
		//Get a list of all of the children and children of children, etc, of the index Category. Add these to the listOfCategories.
		//This works because all of the items in the listOfItems of Categories are Categories at the moment.
		listOfCategories.addAll(index.getListOfAllChildCategories());
		return listOfCategories;
	}
	
	/**
	 * A recursive function that fills a given Category by creating and filling it's children Categories,
	 * before adding those Categories to the original Category.
	 * @param target The Category that should be filled.
	 * @param listOfCategoryNames The list of names for the target's children Category.
	 */
	public static void fillCategory(Category target, String[] listOfCategoryNames){
		for(int i=0; i<listOfCategoryNames.length; i++){
			Category newCategory = new Category(listOfCategoryNames[i], target);
			for(String[][] infoList : Constants.LIST_OF_SPECIAL_CATEGORIES){
				if(infoList[0][0].equals(newCategory.getRawName())){
					fillCategory(newCategory, infoList[0]);
				}
			}
			target.addToList(newCategory);
		}
	}
	
}
