package Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lazada implements Search.Store {
	private static String searchLink = "http://www.lazada.vn/dien-thoai-di-dong/?scs=1&sc=EaYR&q="; // default search
	public static List<String> SearchProducts = new ArrayList<String>(); // List
	private static String criteria = "<div class=\"c-product-list  c-product-list_view_grid c-product-list_justify_space-between"; // Critria
	private static double SearchTime;
	
	private static int colormodel(String color) {
		String c[] = { "Trắng", "Vàng", "Đen" };
		for (int i = 0; i < c.length; i++) {
			if (color.indexOf(c[i]) > -1)
				return color.indexOf(c[i]);
		}
		return -1;
	}

	public static boolean Analyze(String question) {
		question = question.replaceAll(" ", "+");
		String ProductUrl = searchLink + question;

		if (ProductUrl.toLowerCase().indexOf("lazada") != -1) {
			System.out.println("Starting...");
			if (Store.getUrl(ProductUrl)) {
				System.out.println("Connected");

			} else {
				System.out.println("Error");
			}
			System.out.println("Scanning...");
			try {

				BufferedReader bf = new BufferedReader(new FileReader(Store.DATAFILE));
				String line;

				System.out.println("Searching file...");

				while ((line = bf.readLine()) != null) {
					// Increment the count and find the index of the word
					int indexfound = line.indexOf(criteria);

					// If greater than -1, means we found the word
					if (indexfound > -1) {

						String p_name = null, p_price, url = "";
						while ((line = bf.readLine()) != null) {
							if (line.indexOf("c-product-card__description") > -1) {// Find Name
								line = bf.readLine();
								url = "lazada.vn"
										+ line.substring(line.indexOf("/"), line.indexOf("\"", line.indexOf("/")));
								line = bf.readLine();

								p_name = line;

							} else if (line.indexOf("<span class=\"c-product-card__price-final\">") > -1) {// Find price
								line = bf.readLine();
								// Eliminating stuff
								char[] chars = line.toCharArray();
								p_price = "";
								for (int i = 0; i < chars.length; i++) {
									if (Character.isDigit(chars[i])) {
										p_price = p_price + Character.toString(chars[i]);
									}
								}
								SearchProducts.add(p_name + "," + url + "," + p_price);
							}
							// Product.Product.FileWriter(line, "./LAZADASEARCH.DATA");
						}
						System.out.println("Inspect complete!");
						break;
					}
				}

				// Close the file after done searching
				bf.close();
			} catch (IOException e) {
				System.out.println("IO Error Occurred: " + e.toString());
			}
			return true;
		} else {
			System.out.println("This url doesn't not belong to Lazada");
			return false;
		}

	}

	public static void Classify() {
		String name = null, rom = null, color = null, type = null, a;
		int end;

		for (int i = 0; i < SearchProducts.size(); i++) {
			end = 0;
			rom = null;
			color = null;
			type = null;
			name = null;
			// Lay mau
			a = SearchProducts.get(i);
			
			if (a.indexOf("(") > -1 && a.indexOf(")") > -1) {
				color = a.substring(a.indexOf("(") + 1, a.indexOf(")"));
				if (color.length() > 10)
					color = "";
				else {
					if (a.substring(a.indexOf("(") - 1, a.indexOf("(")) == " ") {
						end = a.indexOf("(");
					}
				}
			} else if (colormodel(a) > -1)
				end = colormodel(a);
			// Lay ROM
			for (int j = 8; j <= 256; j *= 2) {
				String array[] = a.split(" ");
				for (int k = 0; k < array.length; k++) {
					if (array[k].indexOf(Integer.toString(j) + "G") > -1) {
						rom = Integer.toString(j) + "G";
						if (a.indexOf(rom) < end || end == 0) {
							end = a.indexOf(rom);
						}
						break;
					}
				}

			}
			if (end == 0) {
				if (a.indexOf("-") > a.indexOf("("))
					end = a.indexOf("(");
				else
					end = a.indexOf("-") - 1;

			}
			// Lay type
			if (a.indexOf("-") > -1 && a.indexOf(",") > -1) {

				type = a.substring(a.indexOf("-") + 1, a.indexOf(",", a.indexOf("-")));
				if (type.length() >= 5)
					if (type.substring(0, 4).matches(".*\\d+.*"))
						type = "";
			}
			if (end > 0) {
				name = a.substring(0, end);
				// System.out.print(name + "||");
				// System.out.print(rom + "||");
				// System.out.print(color + "||");
				// System.out.print(type);
				// System.out.println("");
				// System.out.println("");
			}
			
			if (name != null)
				SearchProducts.set(i, name + "," + rom + "," + color + "," + type + ","
						+ SearchProducts.get(i).substring(a.indexOf(",") + 1, a.length()));
			else {
				SearchProducts.remove(i);
				i=i-1;
			}
//			String test[] = SearchProducts.get(i).split(",");
//			if(test.length<4) {
//				SearchProducts.remove(i);
//				i=i-1;
//			}
		}
	}

	public static void Search(String question) {
		long startTime = System.nanoTime();
		Analyze(question);
		java.util.Collections.sort(SearchProducts);
		Classify();
		long endTime = System.nanoTime();
		SearchTime = (endTime - startTime)/ 1000000000;
	}

	public static void main(String[] args) {
		Search("");
		for (int i = 0; i < SearchProducts.size(); i++) {
			System.out.println(SearchProducts.get(i));
		}
		System.out.println("Time: "+ SearchTime);
		
	}

}
