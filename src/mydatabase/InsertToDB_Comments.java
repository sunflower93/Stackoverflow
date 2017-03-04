package mydatabase;
import tools.Tools;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Created by ShaoBin on 2017/2/16.
 */
public class InsertToDB_Comments extends DefaultHandler{
	private static Connection conn = null;
	private Comment comment = new Comment();
	private int i = 0;
	private static PreparedStatement pstmt = null;
	private int batchSize = 10000;
	
	public static void main(String[] args) throws Exception{
		// 建立数据库连接
		conn = new MyDatabaseConnection().getConnection();
		conn.setAutoCommit(false);
		
		String sql = "insert into comments values (?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		
		InputSource source = new InputSource("L:\\Stackoverflow\\stackoverflow.com-Comments\\Comments.xml");
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
        SAXParser parser = factory.newSAXParser(); 
        InsertToDB_Comments handler = new InsertToDB_Comments();
        parser.parse(source, handler);  
	}
 
 
    @Override 
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException { 
        if("row".equals(qName)){ 
			comment.setId(Tools.ToInt(attributes.getValue("Id")));
			comment.setPostId(Tools.ToInt(attributes.getValue("PostId")));
			comment.setScore(Tools.ToInt(attributes.getValue("Score")));
			comment.setText(Tools.ToString(attributes.getValue("Text")));
			comment.setCreationDate(Tools.ToString(attributes.getValue("CreationDate")));
			comment.setUserId(Tools.ToInt(attributes.getValue("UserId")));
        } 
    } 
 
    @Override 
    public void endElement(String uri, String localName, String qName) 
            throws SAXException { 
        if("row".equals(qName)){
        	i++;
        	try {
        		pstmt.setInt(1, comment.getId());
				pstmt.setInt(2, comment.getPostId());
				pstmt.setInt(3, comment.getScore());
				pstmt.setString(4, comment.getText());
				pstmt.setString(5, comment.getCreationDate());
				pstmt.setInt(6, comment.getUserId());
				pstmt.addBatch();
				if (i % batchSize == 0) {
					pstmt.executeBatch();
					conn.commit();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
    } 
    
    public void endDocument() throws SAXException {
		try {
			if (i % batchSize != 0) {
				pstmt.executeBatch();
				conn.commit();
			}
			System.out.println("***************" + i + "***************"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
    } 
}