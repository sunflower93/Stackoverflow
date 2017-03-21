package preprocess_package;

import mydatabase.MyDatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by ShaoBin on 2017/3/3.
 */
public class BodyTag {
    private static void findtags() {           //
        try {
            Connection conn = new MyDatabaseConnection().getConnection();
            conn.setAutoCommit(false);
            HashSet<String> bodytags = new HashSet<>();

            int start = 0;
            int count = 0;
            while (start < 1935944) {
                String sql = "SELECT Id,Body FROM javadata LIMIT " + start + ",20000;";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet resultSet = pstmt.executeQuery();
                System.out.println(start + "query finished!!!");
                while (resultSet.next()) {
                    count++;
                    if (count % 20000 == 0) {
                        System.out.println(count);
                    }
                    String line = resultSet.getString("body");
                    String[] temp = line.split(" ");
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i].length() > 3 && temp[i].charAt(0) == '<' && temp[i].charAt(temp[i].length() - 1) == '>') {
                            bodytags.add(temp[i]);
                        }
                    }
                    start += 20000;
                }
                pstmt.close();
                resultSet.close();
            }
            System.out.println("all query finish!!!");

            Iterator<String> iterator = bodytags.iterator();
            FileWriter writer = new FileWriter(new File("D:\\WorkSpace\\Stackoverflow\\source\\body tags.txt"));
            while (iterator.hasNext()) {
                writer.write(iterator.next() + "\r\n");
            }
            writer.close();
            System.out.println("Success!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\WorkSpace\\Stackoverflow\\source\\body tags.txt")));
            String line = reader.readLine();
            HashSet<String> set = new HashSet<>();
            while (line != null) {
                String[] temp = line.split(">");
                set.add(temp[0] + ">");
                if (temp[temp.length - 1].contains("<")) {
                    String[] temp1 = temp[temp.length - 1].split("<");
                    set.add("<" + temp1[temp1.length - 1] + ">");
                }
                line = reader.readLine();
            }
            FileWriter writer = new FileWriter("D:\\WorkSpace\\Stackoverflow\\source\\body tags stopwords.txt");
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                writer.write(iterator.next() + "\r\n");
            }
            System.out.println("finish!!!!");
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
