package mydatabase;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * Created by ShaoBin on 2017/2/15.
 */
public class InsertToDB {
	private static Connection conn = null;

    public static void main(String[] args) {
        try {
            badgesDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//	public static void main(String[] args) throws Exception {
//        long num = 0;
//        int batchSize = 10000;
//        //建立数据库连接
//        conn = new mydatabase.MyDatabaseConnection().getConnection();
//        conn.setAutoCommit(false);
//
//        //用来统计文件存入数据库的情况
//        File file1 = new File("L:\\Stackoverflow\\stackoverflow.com-Posts\\DBNote.txt");
//        BufferedWriter output1 = new BufferedWriter(new FileWriter(file1,true));
//
//        FileInputStream inputStream = null;
//        Scanner sc = null;
//        int i = 1,j = 39;//0；
//        PreparedStatement pstmt = null;
//        String sql = "insert into answers values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        pstmt = conn.prepareStatement(sql);
//        while(j < 100){
//            System.out.println("answers" + j +".txt:");
//            //用来统计和保存错误数据
//            int BadDateCount = 0;
//            File file = new File("L:\\Stackoverflow\\stackoverflow.com-Posts\\BadDate"+j+".txt");
//            BufferedWriter output0 = new BufferedWriter(new FileWriter(file,true));
//
//            //读入文件
//            inputStream = new FileInputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\answers-segment\\answers"+j+".xml");
//            sc = new Scanner(inputStream, "UTF-8");
//
//            i = 1;
//            while (sc.hasNextLine()) {
//                //将一行数据存入swap.txt
//                String line = sc.nextLine();
//                try {
//                    PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\swap\\swap" + num + ".txt"),"UTF-8")));
//                    output.write(line);
//                    output.flush();
//                    output.close();
//                } catch (FileNotFoundException e) {
//                    num++;
//                    PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\swap\\swap" + num + ".txt"),"UTF-8")));
//                    output.write(line);
//                    output.flush();
//                    output.close();
//                }
////				OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream("swap.txt") , "UTF-8");
////				output.write(line);
////				output.flush();
////				output.close();
//
//                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//
//                try {
//                    // 解析文件，生成document对象
//                    Document document = builder.parse("L:\\Stackoverflow\\stackoverflow.com-Posts\\swap\\swap" + num + ".txt");
//                    // 生成XPath对象
//                    XPath xpath = XPathFactory.newInstance().newXPath();
//                    mydatabase.Post post = mydatabase.GetPost.Get(xpath, document);
//                    try {
//                        pstmt.setInt(1, post.getId());
////						pstmt.setInt(2, post.getPostTypeId());
//                        pstmt.setInt(2, post.getParentId());
////						pstmt.setInt(4, post.getAcceptedAnswerId());
//                        pstmt.setString(3, post.getCreationDate());
//                        pstmt.setInt(4, post.getScore());
//                        pstmt.setInt(5, post.getViewCount());
//                        pstmt.setString(6, post.getBody());
//                        pstmt.setInt(7, post.getOwnerUserId());
//                        pstmt.setString(8, post.getOwnerDisplayName());
//                        pstmt.setInt(9, post.getLastEditorUserId());
//                        pstmt.setString(10, post.getLastEditorDisplayName());
//                        pstmt.setString(11, post.getLastEditDate());
//                        pstmt.setString(12, post.getLastActivityDate());
//                        pstmt.setString(13, post.getClosedDate());
//                        pstmt.setString(14, post.getTitle());
////						pstmt.setString(16, post.getTags());
////						pstmt.setInt(17, post.getAnswerCount());
//                        pstmt.setInt(15, post.getCommentCount());
//                        pstmt.setInt(16, post.getFavoriteCount());
//                        pstmt.setString(17, post.getCommunityOwnedDate());
//                        pstmt.addBatch();
//                        if( i% batchSize == 0){
//                            pstmt.executeBatch();
//                            conn.commit();
//                        }
//                    } catch (Exception e) {
//                        System.out.println(line);
//                        e.printStackTrace();
//                    }
//                } catch (Exception e) {
//                    String line0 = ("answers" + j +".txt:" + i);
//                    output0.write(line0+"\r\n");
//                    output0.write(line+"\r\n");
//                    output0.flush();
//                    BadDateCount++;
//                }
//                i++;
//            }
//            if((i-1) % batchSize != 0){
//                pstmt.executeBatch();
//                conn.commit();
//            }
//            String line0 = ("共"+BadDateCount+"个坏数据\r\n");
//            output0.write(line0+"\r\n");
//            output0.flush();
//            output0.close();
//            String line1 = ("answers" + j +".txt:" + (i-1));
//            output1.write(line1+"\r\n");
//            output1.flush();
//            j++;
//        }
//        output1.close();
//        System.out.println("done!!!!!!!!!!!!!!!!");
//    }

    public static void answersDB() throws Exception {
        long num = 0;
        int batchSize = 10000;
        //建立数据库连接
        conn = new MyDatabaseConnection().getConnection();
        conn.setAutoCommit(false);

        //用来统计文件存入数据库的情况
        File file1 = new File("L:\\Stackoverflow\\stackoverflow.com-Posts\\DBNote.txt");
        BufferedWriter output1 = new BufferedWriter(new FileWriter(file1,true));

        FileInputStream inputStream = null;
        Scanner sc = null;
        int i = 1,j = 39;//0；
        PreparedStatement pstmt = null;
        String sql = "insert into answers values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        while(j < 100){
            System.out.println("answers" + j +".txt:");
            //用来统计和保存错误数据
            int BadDateCount = 0;
            File file = new File("L:\\Stackoverflow\\stackoverflow.com-Posts\\BadDate"+j+".txt");
            BufferedWriter output0 = new BufferedWriter(new FileWriter(file,true));

            //读入文件
            inputStream = new FileInputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\answers-segment\\answers"+j+".xml");
            sc = new Scanner(inputStream, "UTF-8");

            i = 1;
            while (sc.hasNextLine()) {
                //将一行数据存入swap.txt
                String line = sc.nextLine();
                try {
                    PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\swap\\swap" + num + ".txt"),"UTF-8")));
                    output.write(line);
                    output.flush();
                    output.close();
                } catch (FileNotFoundException e) {
                    num++;
                    PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\swap\\swap" + num + ".txt"),"UTF-8")));
                    output.write(line);
                    output.flush();
                    output.close();
                }
//				OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream("swap.txt") , "UTF-8");
//				output.write(line);
//				output.flush();
//				output.close();

                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                try {
                    // 解析文件，生成document对象
                    Document document = builder.parse("L:\\Stackoverflow\\stackoverflow.com-Posts\\swap\\swap" + num + ".txt");
                    // 生成XPath对象
                    XPath xpath = XPathFactory.newInstance().newXPath();
                    Post post = GetPost.Get(xpath, document);
                    try {
                        pstmt.setInt(1, post.getId());
//						pstmt.setInt(2, post.getPostTypeId());
                        pstmt.setInt(2, post.getParentId());
//						pstmt.setInt(4, post.getAcceptedAnswerId());
                        pstmt.setString(3, post.getCreationDate());
                        pstmt.setInt(4, post.getScore());
                        pstmt.setInt(5, post.getViewCount());
                        pstmt.setString(6, post.getBody());
                        pstmt.setInt(7, post.getOwnerUserId());
                        pstmt.setString(8, post.getOwnerDisplayName());
                        pstmt.setInt(9, post.getLastEditorUserId());
                        pstmt.setString(10, post.getLastEditorDisplayName());
                        pstmt.setString(11, post.getLastEditDate());
                        pstmt.setString(12, post.getLastActivityDate());
                        pstmt.setString(13, post.getClosedDate());
                        pstmt.setString(14, post.getTitle());
//						pstmt.setString(16, post.getTags());
//						pstmt.setInt(17, post.getAnswerCount());
                        pstmt.setInt(15, post.getCommentCount());
                        pstmt.setInt(16, post.getFavoriteCount());
                        pstmt.setString(17, post.getCommunityOwnedDate());
                        pstmt.addBatch();
                        if( i% batchSize == 0){
                            pstmt.executeBatch();
                            conn.commit();
                        }
                    } catch (Exception e) {
                        System.out.println(line);
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    String line0 = ("answers" + j +".txt:" + i);
                    output0.write(line0+"\r\n");
                    output0.write(line+"\r\n");
                    output0.flush();
                    BadDateCount++;
                }
                i++;
            }
            if((i-1) % batchSize != 0){
                pstmt.executeBatch();
                conn.commit();
            }
            String line0 = ("共"+BadDateCount+"个坏数据\r\n");
            output0.write(line0+"\r\n");
            output0.flush();
            output0.close();
            String line1 = ("answers" + j +".txt:" + (i-1));
            output1.write(line1+"\r\n");
            output1.flush();
            j++;
        }
        output1.close();
        System.out.println("done!!!!!!!!!!!!!!!!");
    }

    public static void questionsDB() throws Exception {
        long num = 0;
        int batchSize = 10000;
        //建立数据库连接
        conn = new MyDatabaseConnection().getConnection();
        conn.setAutoCommit(false);

        //用来统计文件存入数据库的情况
        File file1 = new File("L:\\Stackoverflow\\stackoverflow.com-Posts\\DBNote.txt");
        BufferedWriter output1 = new BufferedWriter(new FileWriter(file1,true));

        FileInputStream inputStream = null;
        Scanner sc = null;
        int i = 1,j = 0;
        PreparedStatement pstmt = null;
        String sql = "insert into questions values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        while(j < 100){
            System.out.println("questions" + j +".txt:");
            //用来统计和保存错误数据
            int BadDateCount = 0;
            File file = new File("L:\\Stackoverflow\\stackoverflow.com-Posts\\BadDate"+j+".txt");
            BufferedWriter output0 = new BufferedWriter(new FileWriter(file,true));

            //读入文件
            inputStream = new FileInputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\questions-segment\\questions"+j+".xml");
            sc = new Scanner(inputStream, "UTF-8");

            i = 1;
            while (sc.hasNextLine()) {
                //将一行数据存入swap.txt
                String line = sc.nextLine();
                try {
                    PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\swap\\swap" + num + ".txt"),"UTF-8")));
                    output.write(line);
                    output.flush();
                    output.close();
                } catch (FileNotFoundException e) {
                    num++;
                    PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\swap\\swap" + num + ".txt"),"UTF-8")));
                    output.write(line);
                    output.flush();
                    output.close();
                }
//				OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream("swap.txt") , "UTF-8");
//				output.write(line);
//				output.flush();
//				output.close();

                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                try {
                    // 解析文件，生成document对象
                    Document document = builder.parse("L:\\Stackoverflow\\stackoverflow.com-Posts\\swap\\swap" + num + ".txt");
                    // 生成XPath对象
                    XPath xpath = XPathFactory.newInstance().newXPath();
                    Post post = GetPost.Get(xpath, document);
                    try {
                        pstmt.setInt(1, post.getId());
                        pstmt.setInt(2, post.getAcceptedAnswerId());
                        pstmt.setString(3, post.getCreationDate());
                        pstmt.setInt(4, post.getScore());
                        pstmt.setInt(5, post.getViewCount());
                        pstmt.setString(6, post.getBody());
                        pstmt.setInt(7, post.getOwnerUserId());
                        pstmt.setString(8, post.getOwnerDisplayName());
                        pstmt.setInt(9, post.getLastEditorUserId());
                        pstmt.setString(10, post.getLastEditorDisplayName());
                        pstmt.setString(11, post.getLastEditDate());
                        pstmt.setString(12, post.getLastActivityDate());
                        pstmt.setString(13, post.getClosedDate());
                        pstmt.setString(14, post.getTitle());
                        pstmt.setString(15, post.getTags());
                        pstmt.setInt(16, post.getAnswerCount());
                        pstmt.setInt(17, post.getCommentCount());
                        pstmt.setInt(18, post.getFavoriteCount());
                        pstmt.setString(19, post.getCommunityOwnedDate());
                        pstmt.addBatch();
                        if( i% batchSize == 0){
                            pstmt.executeBatch();
                            conn.commit();
                        }
                    } catch (Exception e) {
                        System.out.println(line);
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    String line0 = ("questions" + j +".txt:" + i);
                    output0.write(line0+"\r\n");
                    output0.write(line+"\r\n");
                    output0.flush();
                    BadDateCount++;
                }
                i++;
            }
            if((i-1) % batchSize != 0){
                pstmt.executeBatch();
                conn.commit();
            }
            String line0 = ("共"+BadDateCount+"个坏数据\r\n");
            output0.write(line0+"\r\n");
            output0.flush();
            output0.close();
            String line1 = ("questions" + j +".txt:" + (i-1));
            output1.write(line1+"\r\n");
            output1.flush();
            j++;
        }
        output1.close();
        System.out.println("done!!!!!!!!!!!!!!!!");
    }

    public static void tagsDB() throws Exception {
        long num = 0;
        int batchSize = 10000;
        //建立数据库连接
        conn = new MyDatabaseConnection().getConnection();
        conn.setAutoCommit(false);

        FileInputStream inputStream = null;
        Scanner sc = null;
        int i = 1,j = 0;
        PreparedStatement pstmt = null;
        String sql = "insert into tags values (?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        //用来统计和保存错误数据
        int BadDateCount = 0;

        //读入文件
        inputStream = new FileInputStream("L:\\Stackoverflow\\stackoverflow.com-Tags\\Tags.xml");
        sc = new Scanner(inputStream, "UTF-8");

        i = 1;
        while (sc.hasNextLine()) {
            //将一行数据存入swap.txt
            String line = sc.nextLine();
            try {
                PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("L:\\Stackoverflow\\stackoverflow.com-Tags\\swap" + num + ".txt"),"UTF-8")));
                output.write(line);
                output.flush();
                output.close();
            } catch (FileNotFoundException e) {
                num++;
                PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("L:\\Stackoverflow\\stackoverflow.com-Tags\\swap" + num + ".txt"),"UTF-8")));
                output.write(line);
                output.flush();
                output.close();
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            try {
                // 解析文件，生成document对象
                Document document = builder.parse("L:\\Stackoverflow\\stackoverflow.com-Tags\\swap" + num + ".txt");
                // 生成XPath对象
                XPath xpath = XPathFactory.newInstance().newXPath();
                Tag tag = GetTag.Get(xpath, document);
                try {
                    i++;
                    pstmt.setInt(1, tag.getId());
                    pstmt.setString(2, tag.getTagName());
                    pstmt.setInt(3, tag.getCount());
                    pstmt.setInt(4, tag.getExcerptPostId());
                    pstmt.setInt(5, tag.getWikiPostId());
                    pstmt.addBatch();
                    if( i% batchSize == 0){
                        pstmt.executeBatch();
                        conn.commit();
                    }
                } catch (Exception e) {
                    System.out.println(line);
                    e.printStackTrace();
                }
            } catch (Exception e) {
                String line0 = (i + ":");
                BadDateCount++;
                System.out.println(line0);
                System.out.println(line);
            }
        }
        if(i % batchSize != 0){
            pstmt.executeBatch();
            conn.commit();
        }
        String line0 = ("共"+BadDateCount+"个坏数据\r\n");
        String line1 = ("共" + i + "条数据");
        System.out.println(line0+"                 "+line1);
        System.out.println("done!!!!!!!!!!!!!!!!");
    }

    public static void taginformationDB() throws Exception {
        long num = 0;
        int batchSize = 10000;
        //建立数据库连接
        conn = new MyDatabaseConnection().getConnection();
        conn.setAutoCommit(false);

        FileInputStream inputStream = null;
        Scanner sc = null;
        int i = 1,j = 0;
        PreparedStatement pstmt = null;
        String sql = "insert into taginformation values (?,?,?,?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        //用来统计和保存错误数据
        int BadDateCount = 0;

        //读入文件
        inputStream = new FileInputStream("L:\\Stackoverflow\\stackoverflow.com-Posts\\type4.xml");
        sc = new Scanner(inputStream, "UTF-8");

        i = 1;
        while (sc.hasNextLine()) {
            //将一行数据存入swap.txt
            String line = sc.nextLine();
            if (line == null) {
                continue;
            }
            try {
                PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("swap" + num + ".txt"),"UTF-8")));
                output.write(line);
                output.flush();
                output.close();
            } catch (FileNotFoundException e) {
                num++;
                PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("swap" + num + ".txt"),"UTF-8")));
                output.write(line);
                output.flush();
                output.close();
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            try {
                // 解析文件，生成document对象
                Document document = builder.parse("swap" + num + ".txt");
                // 生成XPath对象
                XPath xpath = XPathFactory.newInstance().newXPath();
                TagInformation tagInformation = GetTagInformation.Get(xpath, document);
                try {
                    i++;
                    pstmt.setInt(1, tagInformation.getId());
                    pstmt.setString(2, tagInformation.getCreationDate());
                    pstmt.setString(3, tagInformation.getBody());
                    pstmt.setInt(4, tagInformation.getOwnerUserId());
                    pstmt.setString(5, tagInformation.getOwnerDisplayName());
                    pstmt.setInt(6, tagInformation.getLastEditorUserId());
                    pstmt.setString(7, tagInformation.getLastEditorDisplayName());
                    pstmt.setString(8, tagInformation.getLastEditDate());
                    pstmt.setString(9, tagInformation.getLastActivityDate());
                    pstmt.setString(10, tagInformation.getClosedDate());
                    pstmt.setString(11, tagInformation.getTitle());
                    pstmt.setString(12, tagInformation.getCommunityOwnedDate());
                    pstmt.addBatch();
                    if( i% batchSize == 0){
                        pstmt.executeBatch();
                        conn.commit();
                    }
                } catch (Exception e) {
                    System.out.println(line);
                    e.printStackTrace();
                }
            } catch (Exception e) {
                String line0 = (i + ":");
                e.printStackTrace();
                BadDateCount++;
                System.out.println(line0);
                System.out.println(line);
            }
        }
        if(i % batchSize != 0){
            pstmt.executeBatch();
            conn.commit();
        }
        String line0 = ("共"+BadDateCount+"个坏数据\r\n");
        String line1 = ("共" + i + "条数据");
        System.out.println(line0+"                 "+line1);
        System.out.println("done!!!!!!!!!!!!!!!!");
    }

    public static void badgesDB() throws Exception {
        long num = 0;
        int batchSize = 10000;
        //建立数据库连接
        conn = new MyDatabaseConnection().getConnection();
        conn.setAutoCommit(false);

        FileInputStream inputStream = null;
        Scanner sc = null;
        int i = 1,j = 0;
        PreparedStatement pstmt = null;
        String sql = "insert into badges values (?,?,?,?,?,?);";
        pstmt = conn.prepareStatement(sql);
        //用来统计和保存错误数据
        int BadDateCount = 0;

        //读入文件
        inputStream = new FileInputStream("L:\\Stackoverflow\\stackoverflow.com-Badges\\Badges.xml");
        sc = new Scanner(inputStream, "UTF-8");

        i = 1;
        while (sc.hasNextLine()) {
            //将一行数据存入swap.txt
            String line = sc.nextLine();
            try {
                PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("L:\\Stackoverflow\\stackoverflow.com-Badges\\swap" + num + ".txt"),"UTF-8")));
                output.write(line);
                output.flush();
                output.close();
            } catch (FileNotFoundException e) {
                num++;
                PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("L:\\Stackoverflow\\stackoverflow.com-Badges\\swap" + num + ".txt"),"UTF-8")));
                output.write(line);
                output.flush();
                output.close();
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            try {
                // 解析文件，生成document对象
                Document document = builder.parse("L:\\Stackoverflow\\stackoverflow.com-Badges\\swap" + num + ".txt");
                // 生成XPath对象
                XPath xpath = XPathFactory.newInstance().newXPath();
                Badge badge = GetBadge.Get(xpath, document);
                try {
                    i++;
                    pstmt.setInt(1, badge.getId());
                    pstmt.setInt(2, badge.getUserId());
                    pstmt.setString(3, badge.getName());
                    pstmt.setString(4, badge.getDate());
                    pstmt.setInt(5, badge.getClasstype());
                    pstmt.setBoolean(6, badge.getTagBased());
                    pstmt.addBatch();
                    if( i% batchSize == 0){
                        pstmt.executeBatch();
                        conn.commit();
                    }
                } catch (Exception e) {
                    System.out.println(line);
                    e.printStackTrace();
                }
            } catch (Exception e) {
                String line0 = (i + ":");
                BadDateCount++;
                System.out.println(line0);
                System.out.println(line);
            }
        }
        if(i % batchSize != 0){
            pstmt.executeBatch();
            conn.commit();
        }
        String line0 = ("共"+BadDateCount+"个坏数据\r\n");
        String line1 = ("共" + i + "条数据");
        System.out.println(line0+"                 "+line1);
        System.out.println("done!!!!!!!!!!!!!!!!");
    }
}
