package Search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public interface Tools {
	public static void FileWriter(String text, String file) {

		try {

			@SuppressWarnings("resource")
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(text);
			bw.newLine();
			bw.flush();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@SuppressWarnings("resource")
	public static void FileEmpty(String file) {

		try {
			BufferedWriter bw = null;
			FileWriter fw = null;
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("");

			System.out.println("Data file is empty!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void resetCSV(String file) {
		FileEmpty(file);
		FileWriter(
				"ID,Name,Màn hình,Màu,OS,CPU,RAM,ROM,Camera,PIN,SIM,Loại,Lazada Url,TGDD url,FPT url,Lazada Price,TGDD Price,FPT Price,ImgUrl",
				file);
	}

	static void PrintTask(String taskname) {
		int lenght = taskname.length();
		int i;
		int size=0;
		if(taskname.length()<38) {
			size = 40;
		}
		else {
			for(i=40;i<taskname.length();i+=20)
				size=i+20;
		}
		
		
		System.out.println("");
		for(i=0;i<size;i++)
		System.out.print("*");
		System.out.print("\n*");
		for(i=0;i<size-2;i++)
			System.out.print(" ");
		System.out.print("*\n*");
		for (i = 0; i < size/2 - lenght / 2 - 1; i++)
			System.out.print(" ");
		System.out.print(taskname);
		if (lenght % 2 == 0)
			for (i = 0; i < size/2 - lenght / 2-1; i++)
				System.out.print(" ");
		else
			for (i = 0; i < size/2 - lenght / 2-2; i++)
				System.out.print(" ");
		System.out.println("*");
		System.out.print("*");
		for(i=0;i<size-2;i++)
			System.out.print(" ");
		System.out.println("*");
		for(i=0;i<size;i++)
			System.out.print("*");
		System.out.println("");
	}

	static boolean checkRelevantProduct(String name, String question) {
		String qarray[] = question.split(" ");
		if(qarray.length>1)
			for(int i=0;i<qarray.length;i++) {
				if(name.toLowerCase().indexOf(qarray[i].toLowerCase())>-1)
					return true;	
			}
		else
			if(name.toLowerCase().indexOf(question.toLowerCase())>-1)
				return true;	
		return false;	
	}
}
