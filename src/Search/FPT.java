//FPT
package Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FPT extends Search.Store implements Search.Online{
	private static final String searchLink = "https://fptshop.com.vn/tim-kiem/";
	private static final String ListSearch = "<!--ListSearch-->";
	@SuppressWarnings("unused")
	private static final String result = "<div class=\"countresult\">";
	
	public static String price = "", name = "" ,url = "";



	@SuppressWarnings("resource")
	private static boolean Analyze(String question) {
		String ProductUrl = searchLink + question;
		if (ProductUrl.toLowerCase().indexOf("fpt") != -1) {
			System.out.println("Starting...");
			if (Store.getUrl(ProductUrl)) {
				System.out.println("Connected");

			} else {
				System.out.println("Error");
			}
			System.out.println("Scanning...");

			try {
				// Open the file c:\test.txt as a buffered reader
				BufferedReader bf = new BufferedReader(new FileReader(Store.DATAFILE));

				// Start a line count and declare a string to hold our current line.
				String line;

				// Let the user know what we are searching for
				System.out.println("Searching file...");

				// Loop through each line, stashing the line into our line variable.
				while ((line = bf.readLine()) != null) {
					// Increment the count and find the index of the word

					if (line.indexOf(ListSearch) > -1) {
						boolean found = false;
						while (!found) {
							name = "";
							price = "";
							if ((line = bf.readLine()) != null) {
								//Search url
								if(line.indexOf(" <a class=\"clearfix\" href=\"")>-1) {
									url = "https://fptshop.com.vn" + line.substring(line.indexOf("/"), line.indexOf("\"", line.indexOf("/")));
									
									
								}
								
								//Search name and price
								if (line.indexOf("<h3 class=\"mf-plti-name\">") > -1) {
									name = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
									if (Online.checkName(name, question)) {
										System.out.println(name);
										found = true;
										line = bf.readLine();
										price = line;
										// Eliminate stuff
										char[] chars = price.toCharArray();
										price = "";
										for (int i = 0; i < chars.length; i++) {
											if (Character.isDigit(chars[i])) {
												price = price + Character.toString(chars[i]);
											}
										}
										System.out.println("FPT Price Found!");
										return true;
									} else {
										name = "";
									}
								}
							} else {
								break;
							}
						}
					}
				}
				//
				// Close the file after done searching
				bf.close();
			} catch (IOException e) {
				System.out.println("IO Error Occurred: " + e.toString());
			}
			return false;
		} else {
			System.out.println("This url doesn't not belong to FPT");
			return false;
		}
	}

	public static boolean Search(String question) {
		System.out.println("FPT search: " + question);
		String a[] = question.split(" ");
		question = "";
		for (int i = 0; i < a.length; i++) {
			if (a[i].length() > 0)
				question += a[i] + "-";
		}

		name = "";
		price = "";

		System.out.println("------------------\n" + question + "\n----------------");
		if (Analyze(question)) {
			System.out.println(name + "\n" + price +"\n" + url);
			return true;
		} else
			return false;
	}

	public static void main(String[] args) {
		Search("Samsung J7");

	}
}

//package Search;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
//public class Didongthongminh implements Search.Store {
//	private static final String searchLink = "http://didongthongminh.vn/tim-kiem/";
//	private static final String ListSearch = "<div class=\"productlist\">";
//	@SuppressWarnings("unused")
//	private static final String result = "<div class=\"countresult\">";
//	private static String finalQuestion = "";
//	public static String price = "", name = "", url = "";
//
//	private static boolean checkName(String search) {
//		String a[] = finalQuestion.split(" ");
//		String b[] = search.split(" ");
//		if (a.length * b.length <= 0)
//			return false;
//		if (a.length <= b.length)
//			if (finalQuestion.toLowerCase().indexOf("iphone") > -1)
//				for (int i = 0; i < b.length - a.length; i++) {
//					String com = "";
//					for (int j = 0; j < a.length; j++) {
//						com += b[i + j] + " ";
//					}
//					com = com.substring(0, com.length() - 1);
//					System.out.println(search + "---" + com + "----" + finalQuestion);
//					if (finalQuestion.toLowerCase().equals((com.toLowerCase()))) {
//						return true;
//					} else
//						return false;
//				}
//			else {
//				int c = 0;
//				for (int i = 0; i < a.length; i++) {
//					if (search.indexOf(a[i]) > -1)
//						c++;
//				}
//				if (finalQuestion.toLowerCase().indexOf("galaxy s") > -1 && finalQuestion.toLowerCase().indexOf("edge")<0) {
//					if (search.toLowerCase().indexOf("edge")>-1)
//						return false;
//					else
//						return true;
//				} else if (c == a.length)
//					return true;
//				else
//					return false;
//			}
//		else {
//			return false;
//		}
//		return false;
//
//	}
//
//	@SuppressWarnings("resource")
//	private static boolean Analyze(String question) {
//		String ProductUrl = searchLink + question + ".html";
//		if (ProductUrl.toLowerCase().indexOf("didongthongminh") != -1) {
//			System.out.println("Starting...");
//			System.out.println(ProductUrl);
//			if (Store.getUrl(ProductUrl)) {
//				System.out.println("Connected");
//
//			} else {
//				System.out.println("Connection Error");
//			}
//			System.out.println("Scanning...");
//
//			try {
//				// Open the file c:\test.txt as a buffered reader
//				BufferedReader bf = new BufferedReader(new FileReader(Store.DATAFILE));
//
//				// Start a line count and declare a string to hold our current line.
//				String line;
//
//				// Let the user know what we are searching for
//				System.out.println("Searching file...");
//
//				// Loop through each line, stashing the line into our line variable.
//				while ((line = bf.readLine()) != null) {
//					// Increment the count and find the index of the word
//
//					if (line.indexOf(ListSearch) > -1) {
//
//						boolean found = false;
//						while (!found) {
//							name = "";
//							price = "";
//
//							if ((line = bf.readLine()) != null) {
//								if (line.indexOf("<div class='pagination'>") > -1)
//									return false;
//								if (line.indexOf("<a href=") > -1 && line.indexOf("<li")>-1) {
//									
//									if (line.indexOf(".html") > -1 && line.indexOf("title") > -1) {			
//										url = line.substring(line.indexOf("\"", line.indexOf("<a href=")) + 1,
//												line.indexOf("\"", line.indexOf(".html")));
//										name = line.substring(line.indexOf("title =") + 9,
//												line.indexOf(">", line.indexOf("title")) - 2);
//										price = line.substring(line.indexOf("<div class=\"price\">"),
//												line.indexOf("</span>", line.indexOf("<div class=\"price\">")));
//										// Eliminate stuff
//										char[] chars = price.toCharArray();
//										price = "";
//										for (int i = 0; i < chars.length; i++) {
//											if (Character.isDigit(chars[i])) {
//												price = price + Character.toString(chars[i]);
//											}
//										}
//										if (checkName(name)) {
//											System.out.println(" Didongthongminh Price Found!");
//											found = true;
//											return true;
//										}
//									}
//								}
//							} else {
//								break;
//							}
//						}
//					}
//				}
//				//
//				// Close the file after done searching
//				bf.close();
//			} catch (IOException e) {
//				System.out.println("IO Error Occurred: " + e.toString());
//			}
//			return false;
//		} else {
//			System.out.println("This url doesn't not belong to Didongthongminh");
//			return false;
//		}
//	}
//
//	public static boolean Search(String question) {
//		// http://didongthongminh.vn/tim-kiem/iphone%206.html
//		System.out.println("Didongthongminh search: " + question);
//		finalQuestion = question;
//		String a[] = question.split(" ");
//		question = "";
//		for (int i = 0; i < a.length; i++) {
//			if (a[i].length() > 0)
//				question += a[i] + "%20";
//		}
//		//
//		// try {
//		// @SuppressWarnings("deprecation")
//		// String a1 = URLEncoder.encode(question, "UTF-8");
//		// System.out.println(a1);
//		// question = a1;
//		// } catch (UnsupportedEncodingException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//		name = "";
//		price = "";
//
//		System.out.println("------------------\n" + question + "\n----------------");
//		if (Analyze(question)) {
//			System.out.println("<b>Result: " + name + "\n" + price + "\n" + url + "</b>");
//			return true;
//		} else {
//			System.out.println("Can't find "+finalQuestion+" product on DDTM");
//			return false;
//		}
//	}
//
//	public static void main(String[] args) {
//		long startTime = System.nanoTime();
//		Store.getUrl("https://fptshop.com.vn/tim-kiem/samsung");
//		long endTime = System.nanoTime();
//
//		System.out.println((endTime - startTime) / 1000000);
//	}
//}



