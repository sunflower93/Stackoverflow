package mydatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by ShaoBin on 2017/2/21.
 */
public class DataSelection {
    private static Connection conn = null;
    public static void main(String[] args) {
        try {
            conn = new MyDatabaseConnection().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstmt = null;

            //生成三个指定标签小数据库
//            String select_sql = "INSERT INTO javadata SELECT Id,AcceptedAnswerId,CreationDate,Score,Body,OwnerUserId,LastEditorUserId,LastEditDate,Title,Tags,AnswerCount,CommentCount FROM questions where Tags like '%<java>%';";
//            String select_sql = "INSERT INTO pythondata SELECT Id,AcceptedAnswerId,CreationDate,Score,Body,OwnerUserId,LastEditorUserId,LastEditDate,Title,Tags,AnswerCount,CommentCount FROM questions where Tags like '%<python>%';";
//            String select_sql = "INSERT INTO htmldata SELECT Id,AcceptedAnswerId,CreationDate,Score,Body,OwnerUserId,LastEditorUserId,LastEditDate,Title,Tags,AnswerCount,CommentCount FROM questions where Tags like '%<html>%';";

            //生成三个指定标签答案数据库

//            String select_sql = "SELECT Id FROM javadata;";
//            String insert_sql = "INSERT INTO javaansdata SELECT Id,ParentId,CreationDate,Score,Body,OwnerUserId,LastEditorUserId,LastEditDate,Title,CommentCount FROM answers where ParentId = ?;";
//            String select_sql = "SELECT Id FROM pythondata;";
//            String insert_sql = "INSERT INTO pythonansdata SELECT Id,ParentId,CreationDate,Score,Body,OwnerUserId,LastEditorUserId,LastEditDate,Title,CommentCount FROM answers where ParentId = ?;";
//            String select_sql = "SELECT Id FROM htmldata;";
//            String insert_sql = "INSERT INTO htmlansdata SELECT Id,ParentId,CreationDate,Score,Body,OwnerUserId,LastEditorUserId,LastEditDate,Title,CommentCount FROM answers where ParentId = ?;";

            //生成三个指定标签评论数据库
//            String select_sql = "SELECT Id FROM javadata;";
//            String select_sql = "SELECT Id FROM javaansdata;";
//            String insert_sql = "INSERT INTO javacommentdata SELECT * FROM comments WHERE PostId = ?;";
//            String select_sql = "SELECT Id FROM pythondata;";
//            String select_sql = "SELECT Id FROM pythonansdata;";
//            String insert_sql = "INSERT INTO pythoncommentdata SELECT * FROM comments WHERE PostId = ?;";
//            String select_sql = "SELECT Id FROM htmldata;";
//            String select_sql = "SELECT Id FROM htmlansdata;";
//            String insert_sql = "INSERT INTO htmlcommentdata SELECT * FROM comments WHERE PostId = ?;";
//            pstmt = conn.prepareStatement(select_sql);
//            ResultSet resultSet = pstmt.executeQuery();
//            PreparedStatement pstmt1 = conn.prepareStatement(insert_sql);
//            int i = 0;
//            while (resultSet.next()) {
//                i++;
//                int id = resultSet.getInt(1);
//                pstmt1.setInt(1,id);
//                pstmt1.executeUpdate();
//                conn.commit();
//                if (i % 1000 == 0) {
//                    System.out.println(i);
//                }
//            }
//            pstmt.close();
//            pstmt1.close();

            //剔除无答案问题
//            String delete_sql = "DELETE FROM javadata WHERE AnswerCount = 0";
//            String delete_sql = "DELETE FROM pythondata WHERE AnswerCount = 0";
            String delete_sql = "DELETE FROM htmldata WHERE AnswerCount = 0";
            pstmt = conn.prepareStatement(delete_sql);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
