package preprocess_package;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.io.*;
/**
 * Created by ShaoBin on 2017/2/15.
 */
public class Analysis {
	static int front = 0;
	static int rear = 0;
	static int num = 10;
	final static char  array[] = new char[10];

	public static String transfer(String sentence){
		if(sentence!=null){
			Document simply = Jsoup.parse(sentence);
			sentence = simply.body().toString().replace("<body>" , "").replace("</body>" , "");
			if(sentence!=null){
				simply = Jsoup.parse(sentence);
				sentence = simply.body().toString().replace("<body>" , "").replace("</body>" , "");
				if(sentence!=null){
					simply = Jsoup.parse(sentence);
					sentence = simply.body().toString().replace("<body>" , "").replace("</body>" , "");
				}
			}
		}
		return sentence;
	}

	public static void writefile(FileWriter fw, FileWriter fwother, String tempString) throws IOException {
		int begin = 0;
		int bodymark = 0;
		int endmark = 0;
		fw.write("\r\n********************************************************************************************************************************************************************************************************");
		fwother.write("\r\n********************************************************************************************************************************************************************************************************");
		begin = 1;
		int i  = 0;
		bodymark = tempString.indexOf("Body");
		if((endmark=tempString.lastIndexOf("OwnerUserId"))==-1)
			if((endmark=tempString.lastIndexOf("Owner"))==-1)
				if((endmark=tempString.lastIndexOf("Last"))==-1)
					if((endmark=tempString.lastIndexOf("mydatabase.Comment"))==-1)
						if((endmark=tempString.lastIndexOf("Communit"))==-1)
							endmark = tempString.length()-2;


				/*
				 * 处理body之前的东西，没什么问题
				 */
		for(i = 0; i<bodymark; i++){
			char temp = tempString.charAt(i);
			if(temp=='<'&&begin==1){
				begin = 0;
				fw.write(System.getProperty("line.separator"));
				fw.write(System.getProperty("line.separator"));
				fw.write(System.getProperty("line.separator"));

				fwother.write(System.getProperty("line.separator"));
				fwother.write(System.getProperty("line.separator"));
				fwother.write(System.getProperty("line.separator"));

				//fw.write(temp);
				int markquota = 0;
				//parentID之前写到废话里面
				while(i<bodymark&&markquota<4) {
					temp = tempString.charAt(++i);
					fwother.write(temp);
					if(temp=='\"'){
						markquota++;
					}
				}
				fwother.write(System.getProperty("line.separator"));
				//parentID写到主干
				while(i<bodymark&&markquota<6) {
					temp = tempString.charAt(++i);
					fw.write(temp);
					fwother.write(temp);
					if(temp=='\"'){
						markquota++;
					}
				}
				fw.write(System.getProperty("line.separator"));
				//creationDATE
				while(i<bodymark&&markquota<8) {
					temp = tempString.charAt(++i);
					fwother.write(temp);
					if(temp=='\"'){
						markquota++;
					}
				}
				while(i<bodymark&&markquota<10) {
					temp = tempString.charAt(++i);
					fw.write(temp);
					fwother.write(temp);
					if(temp=='\"'){
						markquota++;
					}
				}
				fw.write(System.getProperty("line.separator"));
				fwother.write(System.getProperty("line.separator"));
			}
		}

				/*
				 * 处理body之后的东西，也没什么问题
				 */
		int lastquota =tempString.lastIndexOf("\"");
		String end = tempString.substring(endmark, lastquota+1);
		fwother.write(end);
		fwother.write(System.getProperty("line.separator"));
		tempString = tempString.substring(bodymark-1, endmark);

				/*
				 * 处理body中间的东西
				 */

		String BodyAndCode = null;
		int bodyfirstquota = tempString.indexOf("\"");
		int bodylastquota = tempString.lastIndexOf("\"");

		if(bodyfirstquota>=bodylastquota){
			BodyAndCode = "error";
		}else{
			BodyAndCode = tempString.substring(bodyfirstquota+1,bodylastquota);
		}
		//System.out.println(BodyAndCode);
				/*
				 * 分成一段一段
				 */
		String []bodies = null;
		if((BodyAndCode.indexOf("&#xD;")!=-1)||(BodyAndCode.indexOf("&#xA;")!=-1)){
			if(BodyAndCode.indexOf("&#xA;")!=-1){
				BodyAndCode = BodyAndCode.replace("&#xA;", "zpx");
			}
			if(BodyAndCode.indexOf("&#xD;")!=-1){
				BodyAndCode = BodyAndCode.replace("&#xD;", "zpx");
			}

			Document simply = Jsoup.parse(BodyAndCode);
			BodyAndCode = simply.body().text();

			//假如白过滤器，把code标签留下来
			Whitelist wl = new Whitelist();
			wl.addTags("code");
			BodyAndCode = Jsoup.clean(BodyAndCode,wl);

			//System.out.println(BodyAndCode);
			BodyAndCode = BodyAndCode.replace("<code>", "zpx<code>");
			BodyAndCode = BodyAndCode.replace("</code>", "zpx</code>");
			BodyAndCode = BodyAndCode.replace("<br>", "zpx<br>");
			BodyAndCode = BodyAndCode.replace("</br>", "zpx</br>");
			bodies = BodyAndCode.split("zpx");
		}

		boolean iscode = false;
		boolean codeshow = false;
		if(bodies!=null){
			for(int k = 0; k<bodies.length; k++){
				String sentence = bodies[k];
				//改动了
				sentence = sentence.trim();
				if(sentence.length()>0){
					if(sentence.contains("<code>")){
						iscode = true;
						codeshow = true;
						sentence = sentence.replace("<code>","");
					}
					if(sentence.contains("</code>")){
						sentence = sentence.replace("</code>","");
						iscode = false;
					}
					if(iscode){//如果是代码的话
						//***************************************************************************
						fw.write(System.getProperty("line.separator"));
						sentence = transfer(sentence);
						if(sentence!=null){
							fw.write(sentence);
						}
					}else if(!iscode&&codeshow){//这部分是注释...太多了
						fwother.write(System.getProperty("line.separator"));
						sentence = transfer(sentence);
						if(sentence!=null){
							fwother.write(sentence);
						}
					}else if(!codeshow){//代码前边的部分，连注释都不是
						fwother.write(System.getProperty("line.separator"));
						sentence = transfer(sentence);
						if(sentence!=null){
							fwother.write(sentence);
						}
					}
				}

			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(new File("L:/Stackoverflow/stackoverflow.com-Posts/answers.xml")));
		String tempString = null;
		int count = 0;

		File []code = new File[16];
		FileWriter []fw = new FileWriter[16];
		for (int i = 0; i < 16; i++) {
			code[i] = new File("L:/Stackoverflow/stackoverflow.com-Posts/answer_code/code" + i + ".xml");
			if (!code[i].exists()) {
				code[i].createNewFile();
			}
			fw[i] = new FileWriter(code[i], true);
		}
		File []describe = new File[16];
		FileWriter []fwother = new FileWriter[16];
		for (int i = 0; i < 16; i++) {
			describe[i] = new File("L:/Stackoverflow/stackoverflow.com-Posts/answer_describe/describe" + i + ".xml");
			if (!describe[i].exists()) {
				describe[i].createNewFile();
			}
			fwother[i] = new FileWriter(describe[i], true);
		}

		while(((tempString = reader.readLine()) != null)) {
			//判断里面是不是有code
			if(tempString.contains("&lt;code&gt;")&&tempString.contains("&lt;/code&gt;")){
				count++;
				if (count % 1000 == 0) System.out.println(count);
				switch (count / 1000000) {
					case 0:writefile(fw[0],fwother[0],tempString);break;
					case 1:writefile(fw[1],fwother[1],tempString);break;
					case 2:writefile(fw[2],fwother[2],tempString);break;
					case 3:writefile(fw[3],fwother[3],tempString);break;
					case 4:writefile(fw[4],fwother[4],tempString);break;
					case 5:writefile(fw[5],fwother[5],tempString);break;
					case 6:writefile(fw[6],fwother[6],tempString);break;
					case 7:writefile(fw[7],fwother[7],tempString);break;
					case 8:writefile(fw[8],fwother[8],tempString);break;
					case 9:writefile(fw[9],fwother[9],tempString);break;
					case 10:writefile(fw[10],fwother[10],tempString);break;
					case 11:writefile(fw[11],fwother[11],tempString);break;
					case 12:writefile(fw[12],fwother[12],tempString);break;
					case 13:writefile(fw[13],fwother[13],tempString);break;
					case 14:writefile(fw[14],fwother[14],tempString);break;
					default:writefile(fw[15],fwother[15],tempString);break;
				}
			}
		}
		for (FileWriter x:fw)x.close();
		for (FileWriter x:fwother)x.close();
		System.out.println("count:" + count);
	}
}

