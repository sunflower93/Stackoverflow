package preprocess_package;

import java.io.*;

/**
 * Created by ShaoBin on 2017/2/15.
 */
public class FileSeg {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("L:\\Stackoverflow\\stackoverflow.com-Posts\\answers.xml")));
        File []files = new File[100];
        FileWriter[]fw = new FileWriter[100];
        for (int i = 0; i < 100; i++) {
            files[i] = new File("L:/Stackoverflow/stackoverflow.com-Posts/answers-segment/answers" + i + ".xml");
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
            if (i >= 197770) {
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
