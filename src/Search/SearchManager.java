package Search;

import java.util.ArrayList;
import java.util.List;

public class SearchManager implements Tools {
	public static List<String> SearchProducts = new ArrayList<String>();

	SearchManager(String a) {
		Search(a);
	}

	private static void reset() {
		// Remove previous search
		SearchProducts.clear();
		Lazada.SearchProducts.clear();
		Tools.resetCSV("./product_data.csv");

	}

	public static void Search(String question) {
		reset();
		Lazada.Search(question);
		boolean search = false;
		String searchString;
		for (int i = 0; i < Lazada.SearchProducts.size(); i++) {
			String[] a = Lazada.SearchProducts.get(i).split(",");
			System.out.println(Lazada.SearchProducts.get(i));

			if (a[0].toLowerCase().indexOf("iphone") > -1)
				searchString = a[0] + " " + a[1];
			else
				searchString = a[0];
			if (i == 0) {
				search = (TGDD.Search(searchString)) && FPT.Search(searchString);
				if (search) {
					System.out.println(searchString);
					if (TGDD.price.length() * FPT.price.length() > 0) {
						SearchProducts.add(a[0] + "," + TGDD.para[0] + "," + a[2] + "," + TGDD.para[1] + ","
								+ TGDD.para[2] + "," + TGDD.para[3] + "," + TGDD.para[4] + "," + TGDD.para[5] + ","
								+ TGDD.para[6] + "," + TGDD.para[7] + "," + a[3] + "," + a[4] + "," + TGDD.url + ","
								+ FPT.url + "," + a[5] + "," + TGDD.price + "," + FPT.price + "," + TGDD.img);
					}
				} else {
					System.out.println("Can't find!");
					search = false;
				}

			} else {

				String[] b = Lazada.SearchProducts.get(i - 1).split(",");

				if ((a[0].indexOf(b[0]) > -1 && a[1].indexOf(b[1]) > -1) && search) {
					if (TGDD.price.length() * FPT.price.length() > 0) {
						SearchProducts.add(b[0] + "," + TGDD.para[0] + "," + b[2] + "," + TGDD.para[1] + ","
								+ TGDD.para[2] + "," + TGDD.para[3] + "," + TGDD.para[4] + "," + TGDD.para[5] + ","
								+ TGDD.para[6] + "," + TGDD.para[7] + "," + b[3] + "," + b[4] + "," + TGDD.url + ","
								+ FPT.url + "," + b[5] + "," + TGDD.price + "," + FPT.price + "," + TGDD.img);
					}
				} else {
					search = (TGDD.Search(searchString)) && FPT.Search(searchString);
					if (search) {
						System.out.println(a[0] + a[1]);
						if (TGDD.price.length() * FPT.price.length() > 0) {
							String result = a[0] + "," + TGDD.para[0] + "," + a[2] + "," + TGDD.para[1] + ","
									+ TGDD.para[2] + "," + TGDD.para[3] + "," + TGDD.para[4] + "," + TGDD.para[5] + ","
									+ TGDD.para[6] + "," + TGDD.para[7] + "," + a[3] + "," + a[4] + "," + TGDD.url + ","
									+ FPT.url + "," + a[5] + "," + TGDD.price + "," + FPT.price + "," + TGDD.img;
							SearchProducts.add(result);
						}

					} else {
						System.out.println("Can't find!");
						search = false;
					}
				}
			}
		}
		System.out.println("\n\n RESULT:");
		if (SearchProducts.size() > 0) {
			System.out.println("Write into file:");

			for (int i = 0; i < SearchProducts.size(); i++) {
				SearchProducts.set(i, SearchProducts.get(i).replaceAll("  ", ""));
				System.out.println(SearchProducts.get(i));
				Tools.FileWriter(i + 1 + "," + SearchProducts.get(i), "./product_data.csv");

			}
		} else {
			System.out.println("Error");
		}
	}

	public static void Search(String question, int start, int end) {
		reset();
		if (start == 1||Lazada.SearchProducts.size()==0)
			Lazada.Search(question);
		boolean search = false;
		String searchString;
		if (end > Lazada.SearchProducts.size())
			end = Lazada.SearchProducts.size();
		int i = start;
		for (i = start - 1; i < end; i++) {

			String[] a = Lazada.SearchProducts.get(i).split(",");
			System.out.println(Lazada.SearchProducts.get(i));

			if (a[0].toLowerCase().indexOf("iphone") > -1)
				searchString = a[0] + " " + a[1];
			else
				searchString = a[0];
			if (i == 0) {
				search = (TGDD.Search(searchString)) && FPT.Search(searchString);
				if (search) {
					System.out.println(searchString);
					if (TGDD.price.length() * FPT.price.length() > 0) {
						SearchProducts.add(a[0] + "," + TGDD.para[0] + "," + a[2] + "," + TGDD.para[1] + ","
								+ TGDD.para[2] + "," + TGDD.para[3] + "," + TGDD.para[4] + "," + TGDD.para[5] + ","
								+ TGDD.para[6] + "," + TGDD.para[7] + "," + a[3] + "," + a[4] + "," + TGDD.url + ","
								+ FPT.url + "," + a[5] + "," + TGDD.price + "," + FPT.price + "," + TGDD.img);
					}
				} else {
					System.out.println("Can't find!");
					search = false;
				}

			} else {

				String[] b = Lazada.SearchProducts.get(i - 1).split(",");

				if ((a[0].indexOf(b[0]) > -1 && a[1].indexOf(b[1]) > -1) && search) {
					if (TGDD.price.length() * FPT.price.length() > 0) {
						SearchProducts.add(b[0] + "," + TGDD.para[0] + "," + b[2] + "," + TGDD.para[1] + ","
								+ TGDD.para[2] + "," + TGDD.para[3] + "," + TGDD.para[4] + "," + TGDD.para[5] + ","
								+ TGDD.para[6] + "," + TGDD.para[7] + "," + b[3] + "," + b[4] + "," + TGDD.url + ","
								+ FPT.url + "," + b[5] + "," + TGDD.price + "," + FPT.price + "," + TGDD.img);
					}
				} else {
					search = (TGDD.Search(searchString)) && FPT.Search(searchString);
					if (search) {
						System.out.println(a[0] + a[1]);
						if (TGDD.price.length() * FPT.price.length() > 0) {
							String result = a[0] + "," + TGDD.para[0] + "," + a[2] + "," + TGDD.para[1] + ","
									+ TGDD.para[2] + "," + TGDD.para[3] + "," + TGDD.para[4] + "," + TGDD.para[5] + ","
									+ TGDD.para[6] + "," + TGDD.para[7] + "," + a[3] + "," + a[4] + "," + TGDD.url + ","
									+ FPT.url + "," + a[5] + "," + TGDD.price + "," + FPT.price + "," + TGDD.img;
							SearchProducts.add(result);
						}

					} else {
						System.out.println("Can't find!");
						search = false;
					}
				}
			}
		}
		System.out.println("\n\n RESULT:");
		if (SearchProducts.size() > 0) {
			System.out.println("Write into file:");
			System.out.println(start+" "+end+" "+ SearchProducts.size() + " "+Lazada.SearchProducts.size());
			for (i = 0; i < SearchProducts.size(); i++) {
				SearchProducts.set(i, SearchProducts.get(i).replaceAll("  ", ""));
				System.out.println(SearchProducts.get(i));
				Tools.FileWriter(i + 1 + "," + SearchProducts.get(i), "./product_data.csv");

			}
		} else {
			System.out.println("Error");
		}
	}

	public static void main(String[] args) {

		Search("oppo");

	}

}
