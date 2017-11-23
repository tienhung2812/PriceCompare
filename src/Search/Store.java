package Search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public interface Store {
	public static String DATAFILE = "./HtmlSource.data";
	@SuppressWarnings("resource")
	static boolean getUrl(String urlstring) {

		URL url;
		InputStream is = null;
		BufferedWriter bw = null;
		BufferedReader br;
		FileWriter fw = null;
		String line;

		try {
			url = new URL(urlstring);
			System.out.println("Connecting...");
			is = url.openStream(); // throws an IOException
			br = new BufferedReader(new InputStreamReader(is));

			// Create data file
			try {
				fw = new FileWriter(DATAFILE);
				bw = new BufferedWriter(fw);
				bw.write("");

				System.out.println("Loading...");

			} catch (IOException e) {

				e.printStackTrace();
				return false;

			}
			// Append file
			while ((line = br.readLine()) != null) {
				try {
					bw = new BufferedWriter(new FileWriter(DATAFILE, true));
					bw.write(line);
					bw.newLine();
					bw.flush();
				} catch (IOException ioe) {
					ioe.printStackTrace();
					return false;
				}
				// System.out.println(line);
			}
			// Close file
			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();
				return false;
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException ioe) {
				
				return false;
			}
		}
		System.out.println("Dowloaded!");
		return true;
	}

}
