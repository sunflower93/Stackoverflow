package preprocess_package;

import java.io.*;

/**
 * Created by ShaoBin on 2016/10/29.
 */
public class Codeprocess {
    public static void main(String[] args) {
        try {
            BufferedReader code_reader = new BufferedReader(new InputStreamReader(new FileInputStream("L:/Stackoverflow/stackoverflow.com-Posts/answer_describe/describe0.xml"), "GB2312"));
            String str = code_reader.readLine();
            long count = 0;
            while (str != null) {
                if (str.compareTo("******************************************************************************************************************************************************************************************") == 0) {
                    count++;
                }
                str = code_reader.readLine();
            }
            code_reader.close();
            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
