package Search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Product implements Tools{
	// DEFINE
	public static String DATAPRODUCTCSV = "./product_data.csv";

	// Define distributors order
	static final int LAZADA = 0;
	static final int TGDD = 1;
	static final int FPT = 2;

	// Define Spec order
	static final int SCREEN = 0;
	static final int COLOR = 1;
	static final int OS = 2;
	static final int CPU = 3;
	static final int RAM = 4;
	static final int ROM = 5;
	static final int CAMERA = 6;
	static final int PIN = 7;
	static final int SIM = 8;
	static final int TYPE = 9;
	
	// Define CVS Column No
	static final int CSV_ID = 0;
	static final int CSV_NAME = 1;
	static final int CSV_SCREEN = 2;
	static final int CSV_COLOR = 3;
	static final int CSV_OS = 4;
	static final int CSV_CPU = 5;
	static final int CSV_RAM = 6;
	static final int CSV_ROM = 7;
	static final int CSV_Camera = 8;
	static final int CSV_PIN = 9;
	static final int CSV_SIM = 10;
	static final int CSV_TYPE = 11;
	static final int CSV_LazadaUrl = 12;
	static final int CSV_TGDDUrl = 13;
	static final int CSV_FPTUrl = 14;
	static final int CSV_LazadaPrice = 15;
	static final int CSV_TGDDPrice = 16;
	static final int CSV_FPTPrice = 17;
	static final int CSV_IMG = 18;

	// Product variable
	public int ID;
	public String Category = "Smartphone";
	public String Name;
	public String[] Url = new String[3];
	public int[] Price = new int[3];
	public String Image;
	public String[] Spec = new String[10];


	// Constructor

	public Product(int id) {
		importFromCSV(id);
	}

	// Method
	public void importFromCSV(int id) {

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		int lineCount = 0;
		try {

			br = new BufferedReader(new FileReader(DATAPRODUCTCSV));
			while ((line = br.readLine()) != null) {

				lineCount++;
				if (lineCount > 1) {
					if (lineCount - 1 == id) {
						// use comma as separator
						String[] product = line.split(cvsSplitBy);

						this.ID = Integer.parseInt(product[CSV_ID]);
						this.Name = product[CSV_NAME];
						this.Url[LAZADA] = product[CSV_LazadaUrl];
						this.Url[TGDD] = product[CSV_TGDDUrl];
						this.Url[FPT] = product[CSV_FPTUrl];

						if (isNumeric(product[CSV_LazadaPrice])) {
							this.Price[LAZADA] = Integer.parseInt(product[CSV_LazadaPrice]);

						}
						if (isNumeric(product[CSV_TGDDPrice])) {
							this.Price[TGDD] = Integer.parseInt(product[CSV_TGDDPrice]);
						}
						if (isNumeric(product[CSV_FPTPrice])) {
							this.Price[FPT] = Integer.parseInt(product[CSV_FPTPrice]);
						}
						this.Image = product[CSV_IMG];
						for(int i=SCREEN;i<=TYPE;i++) {
							this.Spec[i] = product[i+CSV_SCREEN];
						}

					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean isNumeric(String s) {
		return s != null && s.matches("[-+]?\\d*\\.?\\d+");
	}





}
