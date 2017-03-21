package tfidf;

import mydatabase.MyDatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ShaoBin on 2017/3/15.
 */
public class TfIdf {

    public static void computeIDF(String path) {
        try {
            HashMap<String, Integer> dictionary = new HashMap<>();                //记录词出现的文件数：词-文件数
            BufferedReader[] reader = new BufferedReader[10];                    //*************10

            FileWriter writer = new FileWriter(new File(path + "tf.txt"), true);

            int filecout = 0;

            for (int i = 0; i < 10; i++) {                                       //**************10
                reader[i] = new BufferedReader(new FileReader(new File(path + "javaq&a_lemma" + i + ".txt")));//("D:\\WorkSpace\\Stackoverflow\\source\\stop words" + i + ".txt")));//
                String line = reader[i].readLine();
                System.out.println("i:" + i);
                while (line != null) {
                    filecout++;
                    String[] words = line.split(" ");
                    HashMap<String, Integer> wordintext = new HashMap<>();
                    for (String word: words) {
                        if (wordintext.containsKey(word)) {
                            wordintext.put(word, wordintext.get(word) + 1);
                        }
                        else {
                            wordintext.put(word, 1);
                        }
                    }

                    String tfline = "";
                    Iterator iterator = wordintext.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)iterator.next();
                        String key = entry.getKey();
                        int value = entry.getValue();
                        if (tfline.length() != 0) tfline = tfline + " ";
                        tfline = tfline + "<" + key + "," + (value * 1.0 / words.length) + ">";
                        if (dictionary.containsKey(key)) {
                            dictionary.put(key, dictionary.get(key) + 1);
                        }
                        else {
                            dictionary.put(key, 1);
                        }
                    }
                    writer.write(tfline + "\r\n");
                    line = reader[i].readLine();
                }
            }
            Connection conn = new MyDatabaseConnection().getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO javadictionary(word,idf) VALUES (?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            int batchSize = 100;
            int i = 0;
            Iterator iterator = dictionary.entrySet().iterator();
            while (iterator.hasNext()) {
                i++;
                Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)iterator.next();
                String key = entry.getKey();
                Integer value = entry.getValue();
                double idf = Math.log((filecout * 1.0) / value);
                pstmt.setString(1, key);
                pstmt.setDouble(2, idf);
                pstmt.addBatch();
                if (i % 100 == 0) {
                    pstmt.executeBatch();
                    conn.commit();
                    System.out.println(i + ":" + key);
                }
            }
            if(i % batchSize != 0){
                pstmt.executeBatch();
                conn.commit();
            }
            writer.flush();
            writer.close();
            System.out.println(i + "words finished!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void computeTFIDF(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path + "tf.txt")));
            FileWriter writer = new FileWriter(new File(path + "idf.txt"), true);

            Connection conn = new MyDatabaseConnection().getConnection();
            conn.setAutoCommit(false);
            String sql = "SELECT idf FROM javadictionary WHERE word=?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            String readline = reader.readLine();
            String writeline = "";

            int i = 0;
            while (readline != null) {
                i++;
                if (i % 10000 == 0) {
                    System.out.println(i * 1.0 / 1224784 + "%");
                }
                String[] tfs = readline.split(" ");
                for (String tfx : tfs) {
                    String[] kvs = tfx.replace("<", "").replace(">", "").split(",");
                    pstmt.setString(1, kvs[0]);
                    ResultSet r = pstmt.executeQuery();
                    double tfidf = Double.valueOf(kvs[1]) * r.getDouble(1);
                    if (writeline.length() != 0) writeline = writeline + " ";
                    writeline = writeline + "<" + kvs[0] + "," + tfidf + ">";
                    r.close();
                }
                writer.write(writeline + "\r\n");
                readline = reader.readLine();
            }
            writer.flush();
            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        computeIDF("L:\\mydata\\java\\");
        System.out.println("TF and IDF compute finished!!!");
        computeTFIDF("L:\\mydata\\java\\");
        System.out.println("Finished!!!");
    }
}
