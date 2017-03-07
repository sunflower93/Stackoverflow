package tools;

import java.io.*;

/**
 * Created by ShaoBin on 2017/2/15.
 */
public class Tools {

	public static void file_init(File file) {
		try {
			FileWriter writer = new FileWriter(file , false);
			writer.write("");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void findmessageById(String string) {
		/**
		 * string : the id example: Id=\"1031333\"
		 */

		try {
			File output = new File("L:\\Stackoverflow\\stackoverflow.com-PostHistory\\unque.xml");//Users\\unique.xml");
			file_init(output);
			BufferedReader reader = new BufferedReader(new FileReader("L:\\Stackoverflow\\stackoverflow.com-PostHistory\\PostHistory.xml"));

			String tempString = null;

			FileWriter fw = new FileWriter(output, true);
			while(((tempString = reader.readLine()) != null)){
				if(tempString.contains(string)){
					fw.write(tempString + "\n");
				}
			}
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int StringToInt(String s){
		if(s.equals("") || s.length() == 0)
			return -1;
		else return Integer.parseInt(s);
	}

	public static int ToInt(String s){
		if(s == null || s.length() == 0)
			return -32768;
		else return Integer.parseInt(s);
	}

	public static String ToString(String s){
		if(s == null || s.length() == 0)
			return "";
		else return s;
	}

	public static boolean ToBoolean(String s) {
		if (s.charAt(0) == 'f') {
			return false;
		}
		else return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File("L:\\Stackoverflow\\stackoverflow.com-Posts\\questions.xml")));
		File []files = new File[100];
		FileWriter[]fw = new FileWriter[100];
		for (int i = 0; i < 100; i++) {
			files[i] = new File("L:/Stackoverflow/stackoverflow.com-Posts/questions-segment/questions" + i + ".xml");
			if (!files[i].exists()) {
				files[i].createNewFile();
			}
			fw[i] = new FileWriter(files[i], true);
		}
		String tempString = null;
		int i = 0;
		int count = 0;
		while ((tempString = reader.readLine()) != null){
			i++;
			if (i >= 123510) {
				System.out.println("file" + count + " finished!!!");
				fw[count].close();
				count++;
				i = 0;
			}
			fw[count].write(tempString + "\n");
		}
		fw[count].close();
	}
}
