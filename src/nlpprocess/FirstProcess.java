package nlpprocess;

import mydatabase.MyDatabaseConnection;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by ShaoBin on 2017/3/3.
 */
public class FirstProcess {
    private static int LengthOfDatabase = 0;//1128764;              //java:1128764

    private static void create_test_text() {
        try {
            Connection connection = new MyDatabaseConnection().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstmt0 = null;
            PreparedStatement pstmt1 = null;
            PreparedStatement pstmt2 = null;
            String sql0 = "SELECT COUNT(Id) FROM javadata;";
            pstmt0 = connection.prepareStatement(sql0);
            ResultSet resultSet0 = pstmt0.executeQuery();
            resultSet0.next();
            LengthOfDatabase = resultSet0.getInt(1);

            System.out.println(LengthOfDatabase);

            FileWriter[] writers = new FileWriter[10];
            int i = 0;
            for (i = 0; i < 10; i++) {
                writers[i] = new FileWriter(new File("L:\\mydata\\java\\javaq&a" + i + ".txt"),true);
            }
            FileWriter writer = new FileWriter(new File("L:\\mydata\\java\\indexnumber.txt"),true);

            int start = 1;
            i = 0;
            int id = 0;
            while (start < LengthOfDatabase) {
                if (start % 10000 == 0) {
                    System.out.println(start / (LengthOfDatabase / 100.0) + "%");
                }
                String sql1 = "SELECT Id, Body FROM javadata WHERE Id > ? LIMIT 1;";//"SELECT Id, Body FROM javadata WHERE Id >= (SELECT Id FROM javadata LIMIT " + (start - 1) + ",1) LIMIT 1;";  //"SELECT Id,Body FROM javadata LIMIT " + (start - 1) + ",1;";
                String sql2 = "SELECT Body FROM javaansdata WHERE ParentId = ?;";
                pstmt1 = connection.prepareStatement(sql1, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                pstmt1.setFetchSize(Integer.MIN_VALUE);
                pstmt1.setFetchDirection(ResultSet.FETCH_REVERSE);
                pstmt2 = connection.prepareStatement(sql2, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                pstmt2.setFetchSize(Integer.MIN_VALUE);
                pstmt2.setFetchDirection(ResultSet.FETCH_REVERSE);

                pstmt1.setInt(1, id);
                ResultSet resultSet1 = pstmt1.executeQuery();

                resultSet1.next();
                id = resultSet1.getInt(1);
                if (start == 1) {
                    writer.write(resultSet1.getInt(1) + "\r\n");
                }
                if (start % ((LengthOfDatabase / 10) + 1) == 0) {
                    writers[i].flush();
                    writers[i++].close();
                    writer.write(resultSet1.getInt(1) + "\r\n");
                }
                writers[i].write(resultSet1.getString(2).replace("&lt;", "<").replace("&gt;", ">").replace("\r", "").replace("\n", "") + " ");
                pstmt2.setInt(1, resultSet1.getInt(1));

//                System.out.println(resultSet1.getInt(1) + " " + resultSet1.getString(2));
//                Scanner in = new Scanner(System.in);
//                in.nextInt();

                resultSet1.close();
                ResultSet resultSet2 = pstmt2.executeQuery();
                while (resultSet2.next()) {
                    writers[i].write(resultSet2.getString(1).replace("&lt;", "<").replace("&gt;", ">").replace("\r", "").replace("\n", "") + " ");
                }
                writers[i].write("\r\n");
                writers[i].flush();
                start++;
                resultSet2.close();
            }
            writers[i].close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void create_test_text_repair() {
        try {
            Connection connection = new MyDatabaseConnection().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstmt1 = null;
            PreparedStatement pstmt2 = null;

            FileWriter[] writers = new FileWriter[10];
            int i = 0;
            for (i = 0; i < 10; i++) {
                writers[i] = new FileWriter(new File("L:\\mydata\\java\\javaq&a" + i + ".txt"),true);
            }
            FileWriter writer = new FileWriter(new File("L:\\mydata\\java\\indexnumber.txt"),true);
//
            int start = 1097560;                                 //start index
            i = 8;
            int id = 38363952;                            //start id
            while (start < LengthOfDatabase) {
                if (start % 10000 == 0) {
                    System.out.println(start / (LengthOfDatabase / 100.0) + "%");
                }
                    String sql1 = "SELECT Id, Body FROM javadata WHERE Id > ? LIMIT 1;";//"SELECT Id, Body FROM javadata WHERE Id >= (SELECT Id FROM javadata LIMIT " + (start - 1) + ",1) LIMIT 1;";  //"SELECT Id,Body FROM javadata LIMIT " + (start - 1) + ",1;";
                    String sql2 = "SELECT Body FROM javaansdata WHERE ParentId = ?;";
                    pstmt1 = connection.prepareStatement(sql1, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    pstmt1.setFetchSize(Integer.MIN_VALUE);
                    pstmt1.setFetchDirection(ResultSet.FETCH_REVERSE);
                    pstmt2 = connection.prepareStatement(sql2, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                    pstmt2.setFetchSize(Integer.MIN_VALUE);
                    pstmt2.setFetchDirection(ResultSet.FETCH_REVERSE);

                    pstmt1.setInt(1, id);
                    ResultSet resultSet1 = pstmt1.executeQuery();

                    resultSet1.next();
                    id = resultSet1.getInt(1);
                    if (start % ((LengthOfDatabase / 10) + 1) == 0) {
                        writers[i].flush();
                        writers[i++].close();
                    }
                    writers[i].write(resultSet1.getString(2).replace("&lt;", "<").replace("&gt;", ">").replace("\r", "").replace("\n", "") + " ");
                    pstmt2.setInt(1, resultSet1.getInt(1));

                    resultSet1.close();
                    ResultSet resultSet2 = pstmt2.executeQuery();
                    while (resultSet2.next()) {
                        writers[i].write(resultSet2.getString(1).replace("&lt;", "<").replace("&gt;", ">").replace("\r", "").replace("\n", "") + " ");
                    }
                    writers[i].write("\r\n");
                    writers[i].flush();
                    start++;
                    resultSet2.close();
            }
            writers[i].close();

            start = 0;
            while (start <= LengthOfDatabase) {
                String sql0 = "SELECT Id FROM javadata LIMIT " + start + ",1;";
                PreparedStatement pstmt0 = connection.prepareStatement(sql0, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                pstmt0.setFetchSize(Integer.MIN_VALUE);
                pstmt0.setFetchDirection(ResultSet.FETCH_REVERSE);

                ResultSet resultSet0 = pstmt0.executeQuery();
                resultSet0.next();
                writer.write(resultSet0.getInt(1) + "\r\n");
                start += 122479;
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        create_test_text();
    }
}
