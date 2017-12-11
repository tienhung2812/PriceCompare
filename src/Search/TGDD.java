package Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TGDD extends Search.Store implements Search.Online{
	private static final String searchLink = "https://www.thegioididong.com/tim-kiem?key=";
	private static final String noresult = "<strong class=\"noresult\">";
	private static final String result = "<div class=\"countresult\">";

	public static String price, name;
	public static String para[] = new String[8];
	public static String url;
	public static String img;

	@SuppressWarnings("resource")
	private static boolean Analyze(String question) {

		String ProductUrl = searchLink + question;
		if (ProductUrl.toLowerCase().indexOf("thegioididong") != -1) {
			System.out.println("Starting...");
			if (getUrl(ProductUrl)) {
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

					if (line.indexOf(result) > -1) {
						boolean found = false;
						while (!found) {

							line = bf.readLine();
							// Search url
							if (line.indexOf("<a href=") > -1) {
								url = "https://www.thegioididong.com"
										+ line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
							}
							// Search img
							if (line.indexOf("<img") > -1) {
								img = line.substring(line.indexOf("https:"),
										line.indexOf("\"", line.indexOf("https:")));
							}
							// Search name
							if (line.indexOf("<h3>") > -1) {
								name = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
								line = bf.readLine();
								// Search Price
								if (line.indexOf("<strong") > -1) {
									price = line.substring(line.indexOf(">") + 1, line.indexOf("</strong>"));
									// Eliminate stuff
									char[] chars = price.toCharArray();
									System.out.println("Length: " + chars.length);
									price = "";
									for (int i = 0; i < chars.length; i++) {
										if (Character.isDigit(chars[i])) {
											price = price + Character.toString(chars[i]);
										}
									}
									System.out.println("TGDD Price Found!");
									// Search Parameter
									while (line.indexOf("<div class=\"para\">") < 0) {

										line = bf.readLine();
									}
									line = bf.readLine();
									while (line.indexOf("span") > -1) {
										line = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
										String s;
										// Man Hinh
										s = "M&#224;n h&#236;nh:";
										if (line.indexOf(s) > -1) {
											line = line.substring(line.indexOf(s) + s.length() + 1, line.length());
											line = line.replace("&quot;", "\"");
											para[0] = line;
										}
										// HDH
										s = "HĐH:";
										if (line.indexOf(s) > -1) {
											line = line.substring(line.indexOf(s) + s.length() + 1, line.length());
											para[1] = line;
										}
										// CPU
										s = "CPU:";
										if (line.indexOf(s) > -1) {
											line = line.substring(line.indexOf(s) + s.length() + 1, line.length());
											line = line.replaceAll("&#226;", "â");
											para[2] = line;

										}
										// RAM
										s = "RAM:";
										if (line.indexOf(s) > -1) {
											line = line.substring(line.indexOf(s) + s.length() + 1, line.length());
											if(line.indexOf(",")>-1)
											para[3] = line.substring(0, line.indexOf(",", 1));

										}
										// ROM
										s = "ROM:";
										if (line.indexOf(s) > -1) {
											line = line.substring(line.indexOf(s) + s.length() + 1, line.length());

											para[4] = line;
										}
										// CAMERA
										s = "Camera:";
										if (line.indexOf(s) > -1) {
											line = line.substring(line.indexOf(s) + s.length() + 1, line.length());
											line = line.replaceAll("v&#224;", "và");
											para[5] = line;
										}
										// PIN
										s = "PIN:";
										if (line.indexOf(s) > -1) {
											line = line.substring(line.indexOf(s) + s.length() + 1, line.length());
											para[6] = line;

										}
										// SIM
										s = "SIM:";
										if (line.indexOf(s) > -1) {
											line = line.substring(line.indexOf(s) + s.length() + 1, line.length());
											para[7] = line;

										}
										line = bf.readLine();
									}
									found = true;
									return true;
								}
							}
						}
					} else if (line.indexOf(noresult) > -1)
						return false;
				}

				// Close the file after done searching
				bf.close();
			} catch (IOException e) {
				System.out.println("IO Error Occurred: " + e.toString());
			}
			return true;
		} else {
			System.out.println("This url doesn't not belong to TheGioiDiDong");
			return false;
		}
	}

	public static boolean Search(String question) {
		System.out.println("TGDD search: " + question);

		String a[] = question.split(" ");
		question = "";
		for (int i = 0; i < a.length; i++) {
			if (a[i].length() > 0)
				question += a[i] + "+";
		}

		name = "";
		price = "";
		for (int i = 0; i < para.length; i++) {
			para[i] = "";
		}
		// question = question.substring(11,question.length());
		System.out.println("------------------\n" + question + "\n----------------");
		if (Analyze(question)) {
			System.out.println(name + "\n" + price + "\n" + url + "\n" + img);
			for (int i = 0; i < para.length; i++) {
				if (para[i].indexOf(",") > -1)
					para[i] = para[i].replaceAll(",", "");
				System.out.println(para[i]);
			}
			return true;
		} else
			return false;
	}

	public static void main(String[] args) {
		Search("Asus Zenfone 4 Max Pro ZC554KL");
	}

}
