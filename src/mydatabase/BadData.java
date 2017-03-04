package mydatabase;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Created by ShaoBin on 2017/2/15.
 */
public class BadData {

	public static void main(String[] args) throws Exception {
		FileInputStream inputStream = null;
		Scanner sc = null;
		inputStream = new FileInputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\answers-segment\\answers3.txt");
		sc = new Scanner(inputStream, "UTF-8");
		int i = 1,j = 1;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if(i>25964){
				OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream("swap.txt") , "UTF-8");
				output.write(line);
				output.flush();
				output.close();
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				try {	
					Document document = builder.parse("swap.txt");
				} catch (Exception e) {
					System.out.println("第"+j+"个："+i);
					System.out.println(line);
				}
			}
			if(i<200000)
			{
				i++;
//				if(j==3 && i==25965){
//					System.out.println("第"+j+"个："+i);
//					System.out.println(line);
//				}
			}
			else{
				System.out.println("第"+j+"个："+i); 
				i = 1;
				j++;
			}
		}
		if(i!=1)
			System.out.println(i);
		System.out.println("done!!!!!!!!!!!!!!!!");
	}

}
