package mydatabase;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tools.Tools;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class InsertToDB_Users extends DefaultHandler{
	private static Connection conn = null;
	private User user = new User();
	private int i = 0;
	private static PreparedStatement pstmt = null;
	private int batchSize = 10000;
	
	public static void main(String[] args) throws Exception{
		// 建立数据库连接
		conn = new MyDatabaseConnection().getConnection();
		conn.setAutoCommit(false);
		
		String sql = "insert into users values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//		String sql = "insert into users values (?,?)";
		pstmt = conn.prepareStatement(sql);
		
		InputSource source = new InputSource("L:\\Stackoverflow\\stackoverflow.com-Users\\Users.xml");

		source.setEncoding("UTF-16");
//		InputStream in = new FileInputStream("D:\\项目\\data\\stackoverflow.com-Users\\Users.xml");
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
        SAXParser parser = factory.newSAXParser(); 
        InsertToDB_Users handler = new InsertToDB_Users();
        parser.parse(source, handler);  
	}
 
 
    @Override 
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException { 
        if("row".equals(qName)){ 
            user.setId(Tools.ToInt(attributes.getValue("Id"))); 
            user.setReputation(Tools.ToInt(attributes.getValue("Reputation"))); 
            user.setCreationDate(Tools.ToString(attributes.getValue("CreationDate"))); 
            user.setDisplayName(Tools.ToString(attributes.getValue("DisplayName")));
            user.setEmailHash(Tools.ToString(attributes.getValue("EmailHash")));
            user.setLastAccessDate(Tools.ToString(attributes.getValue("LastAccessDate"))); 
            user.setWebsiteUrl(Tools.ToString(attributes.getValue("WebsiteUrl")));
            user.setLocation(Tools.ToString(attributes.getValue("Location")));
            user.setAboutMe(Tools.ToString(attributes.getValue("AboutMe")));
            user.setViews(Tools.ToInt(attributes.getValue("Views")));
            user.setUpVotes(Tools.ToInt(attributes.getValue("UpVotes")));
            user.setDownVotes(Tools.ToInt(attributes.getValue("DownVotes")));
            user.setProfileImageUrl(Tools.ToString(attributes.getValue("ProfileImageUrl")));
            user.setAge(Tools.ToInt(attributes.getValue("Age")));
            user.setAccountId(Tools.ToInt(attributes.getValue("AccountId")));
        } 
    } 
 
    @Override 
    public void endElement(String uri, String localName, String qName) 
            throws SAXException { 
        if("row".equals(qName)){
        	i++;
        	try {
				pstmt.setInt(1, user.getId());
//				System.out.println(user.getId());
//				pstmt.setString(2,user.getDisplayName());
				pstmt.setInt(2,user.getReputation());
				pstmt.setString(3,user.getCreationDate());
				pstmt.setString(4,user.getDisplayName());
				pstmt.setString(5,user.getEmailHash());
				pstmt.setString(6,user.getLastAccessDate());
				pstmt.setString(7,user.getWebsiteUrl());
				pstmt.setString(8,user.getLocation());
				pstmt.setString(9,user.getAboutMe());
				pstmt.setInt(10,user.getViews());
				pstmt.setInt(11,user.getUpVotes());
				pstmt.setInt(12,user.getDownVotes());
				pstmt.setString(13,user.getProfileImageUrl());
				pstmt.setInt(14,user.getAge());
				pstmt.setInt(15,user.getAccountId());
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