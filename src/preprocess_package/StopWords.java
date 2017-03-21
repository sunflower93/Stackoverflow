package preprocess_package;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by ShaoBin on 2017/2/25.
 * Create a file for the list of stop words.
 */
public class StopWords {
    private static void stopwords_merge() {                   //merge five files of stop words into only one file.
        HashSet<String> stopwords = new HashSet<String>();
        File[] files = new File[5];
        int count = 0;
        try {
            for (int i = 0; i < 5; i++) {
                files[i] = new File("D:\\WorkSpace\\Stackoverflow\\source\\stop words" + i + ".txt");
                BufferedReader reader = new BufferedReader(new FileReader(files[i]));
                String line = reader.readLine();
                while (line != null) {
                    stopwords.add(line);
                    count++;
                    line = reader.readLine();
                }
            }
            System.out.println(count + "      " + stopwords.size());
            FileWriter writer = new FileWriter(new File("D:\\WorkSpace\\Stackoverflow\\source\\stop words.txt"));
            Iterator<String> iterator = stopwords.iterator();
            count = 1;
            while (iterator.hasNext()) {
                String line = iterator.next();
                writer.write(line + "\r\n");
            }
            writer.close();
            System.out.println("Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void stopwords_sort() {                 //sort the list of stop words.
        String[] stopwords = new String[1000];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\WorkSpace\\Stackoverflow\\source\\stop words.txt"));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                stopwords[i++] = line;
                line = reader.readLine();
            }
            System.out.println(i);
            for (int j = 0; j < i - 1; j++) {
                for (int k = 0; k < i - 1; k++) {
                    if (stopwords[k].compareTo(stopwords[k + 1]) > 0) {
                        line = stopwords[k];
                        stopwords[k] = stopwords[k + 1];
                        stopwords[k + 1] = line;
                    }
                }
            }
            FileWriter writer = new FileWriter(new File("D:\\WorkSpace\\Stackoverflow\\source\\stop words sort.txt"));
            for (int j = 0; j < i; j++) {
                writer.write(stopwords[j] + "\r\n");
            }
            reader.close();
            writer.close();
            System.out.println("Sort Success!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        stopwords_merge();
        stopwords_sort();
    }
}
