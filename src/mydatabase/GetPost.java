package mydatabase;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import tools.Tools;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
/**
 * Created by ShaoBin on 2017/2/15.
 */
public class GetPost {
	public static Post Get(XPath xpath , Document document)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
			Post post = new Post();
			// 获取节点属性值
			int Id = Tools.StringToInt((String) xpath.evaluate("/row/@Id", document, XPathConstants.STRING));
			int PostTypeId = Tools.StringToInt((String) xpath.evaluate("/row/@PostTypeId", document, XPathConstants.STRING));
			int ParentId = Tools.StringToInt((String) xpath.evaluate("/row/@ParentId", document, XPathConstants.STRING));
			int AcceptedAnswerId = Tools.StringToInt((String) xpath.evaluate("/row/@AcceptedAnswerId", document, XPathConstants.STRING));
			String CreationDate = (String) xpath.evaluate("/row/@CreationDate", document, XPathConstants.STRING);
			int Score = Tools.StringToInt((String) xpath.evaluate("/row/@Score", document, XPathConstants.STRING));
			int ViewCount = Tools.StringToInt((String) xpath.evaluate("/row/@ViewCount", document, XPathConstants.STRING));
			String Body = (String) xpath.evaluate("/row/@Body", document, XPathConstants.STRING);
//			Body = Body.replace("\r", "&#xD;");
//			Body = Body.replace("\n", "&#xA;");
			int OwnerUserId = Tools.StringToInt((String) xpath.evaluate("/row/@OwnerUserId", document, XPathConstants.STRING));
			String LastEditorDisplayName = (String) xpath.evaluate("/row/@LastEditorDisplayName", document, XPathConstants.STRING);
			int LastEditorUserId = Tools.StringToInt((String) xpath.evaluate("/row/@LastEditorUserId", document, XPathConstants.STRING));
			String OwnerDisplayName = (String) xpath.evaluate("/row/@OwnerDisplayName", document, XPathConstants.STRING);
			String LastEditDate = (String) xpath.evaluate("/row/@LastEditDate", document, XPathConstants.STRING);
			String LastActivityDate = (String) xpath.evaluate("/row/@LastActivityDate", document, XPathConstants.STRING);
			String ClosedDate = (String) xpath.evaluate("/row/@ClosedDate", document, XPathConstants.STRING);
			String Title = (String) xpath.evaluate("/row/@Title", document, XPathConstants.STRING);
			String Tags = (String) xpath.evaluate("/row/@Tags", document, XPathConstants.STRING);
			int AnswerCount = Tools.StringToInt((String) xpath.evaluate("/row/@AnswerCount", document, XPathConstants.STRING));
			int CommentCount = Tools.StringToInt((String) xpath.evaluate("/row/@CommentCount", document, XPathConstants.STRING));
			int FavoriteCount = Tools.StringToInt((String) xpath.evaluate("/row/@FavoriteCount", document, XPathConstants.STRING));
			String CommunityOwnedDate = (String) xpath.evaluate("/row/@CommunityOwnedDate", document, XPathConstants.STRING);
			
			post.setId(Id);
			post.setPostTypeId(PostTypeId);
			post.setParentId(ParentId);
			post.setAcceptedAnswerId(AcceptedAnswerId);
			post.setCreationDate(CreationDate);
			post.setScore(Score);
			post.setViewCount(ViewCount);
			post.setBody(Body);
			post.setOwnerUserId(OwnerUserId);
			post.setLastEditorUserId(LastEditorUserId);
			post.setLastEditorDisplayName(LastEditorDisplayName);
			post.setLastEditDate(LastEditDate);
			post.setLastActivityDate(LastActivityDate);
			post.setClosedDate(ClosedDate);
			post.setTitle(Title);
			post.setTags(Tags);
			post.setAnswerCount(AnswerCount);
			post.setCommentCount(CommentCount);
			post.setFavoriteCount(FavoriteCount);
			post.setCommunityOwnedDate(CommunityOwnedDate);
			
			return post;
		}
}