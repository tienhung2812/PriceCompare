package Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Search.Product;

public class Manager implements Tools {
	public static int product_num = 0;
	public static double SearchTime;
	public static String question, Status = "";
	public static ArrayList<Object> product = new ArrayList<Object>();

	Manager() {
		Tools.PrintTask("INITIALIZE MANAGER");
		Search("");
		Initialize();
		if(product.size()==0) {
			Status = "Loading Error";
		}
		System.out.println(Status);
		System.out.println("Search Time: "+SearchTime + " Second");
	}
	
	Manager(int start, int end){
		Tools.PrintTask("INITIALIZE MANAGER");
		Search("",1,6);
		Initialize();
		if(product.size()==0) {
			Status = "Loading Error";
		}
		System.out.println(Status);
		System.out.println("Search Time: "+SearchTime + " Second");
	}
	

	private static int lineCount() {
		// Counting Line
		int linecount = 0;
		try {

			BufferedReader bf = new BufferedReader(new FileReader(Product.DATAPRODUCTCSV));
			System.out.println("Catching CSV file...");
			while ((bf.readLine()) != null) {
				// Increment the count and find the index of the word
				linecount++;
			}

			// Close the file after done searching
			bf.close();
		} catch (IOException e) {
			System.out.println("IO Error Occurred: " + e.toString());
		}
		return linecount;
	}

	private static void Initialize() {
		product.clear();
		if ((product_num = lineCount() - 1) > 0)
			for (int i = 0; i < product_num; i++) {
				product.add(new Product(i + 1));
			}
	}

	public static boolean Search(String str) {
		Status = "";
		question = str;
		long startTime = System.nanoTime();

		Tools.PrintTask("SEARCH " + str);
		Tools.resetCSV("./product_data.csv");
		SearchManager.Search(str);
		Initialize();
		long endTime = System.nanoTime();

		SearchTime = (endTime - startTime)/ 1000000000;

		if (product_num > 0) {
			Initialize();
			boolean found = false;
			for (int i = 1; i <= product.size(); i++) {
				if (Tools.checkRelevantProduct(printProduct(i).Name, str))
					found = true;
				if (found) {
					if (product_num > 1)
						Status = ("Found " + product_num + " products in "+SearchTime+" seconds");
					else
						Status = ("Found " + product_num + " product in "+SearchTime+" seconds");
					break;
				}
				else
					Status = ("Can't find but i suggest you somethings else");

			}
			return true;
		} else {
			Status = "Can't find";
			return false;
		}
	}

	public static boolean Search(String str, int start, int end) {
		Status = "";
		question = str;
		long startTime = System.nanoTime();

		Tools.PrintTask("SEARCH " + str);
		Tools.resetCSV("./product_data.csv");
		SearchManager.Search(str,start, end+1);
		Initialize();
		long endTime = System.nanoTime();

		SearchTime = (endTime - startTime)/ 1000000000;

		
		if (product_num > 0) {
			Initialize();
			boolean found = false;
			for (int i = 1; i <= product.size(); i++) {
				if (Tools.checkRelevantProduct(printProduct(i).Name, str))
					found = true;
				if (found) {
					if (product_num > 1)
						Status = ("Found " + product_num + " products in "+SearchTime+" seconds");
					else
						Status = ("Found " + product_num + " product in "+SearchTime+" seconds");
					break;
				}
				else
					Status = ("Can't find but i suggest you somethings else");

			}
			return true;
		} else {
			Status = "Can't find";
			return false;
		}
	}
	
	public static Product printProduct(int id) {
		Product c = (Product) product.get(id - 1);
		return c;
	}

	public static void main(String[] args) {
		// Initialize Manager
//		@SuppressWarnings("unused")
//		Manager a = new Manager(1,6);
//
//		// In ra san pham ban dau
//		for (int i = 1; i <= product.size(); i++) {
//			System.out.println(printProduct(i).Name);
//		}
//		
		//Search
		if (Search("",1,5)) {
			for (int i = 1; i <= product_num; i++)
				System.out.println(printProduct(i).Name);
			

		}
		System.out.println(Status);
		System.out.println("Search Time: "+SearchTime + " Second");

	}
}
