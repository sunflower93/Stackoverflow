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
 * Created by ShaoBin on 2017/2/16.
 */
public class GetTagInformation {
	public static TagInformation Get(XPath xpath , Document document)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
			TagInformation taginformation = new TagInformation();
			// 获取节点属性值
			int Id = Tools.ToInt((String) xpath.evaluate("/row/@Id", document, XPathConstants.STRING));
			String CreationDate = (String) xpath.evaluate("/row/@CreationDate", document, XPathConstants.STRING);
			String Body = (String) xpath.evaluate("/row/@Body", document, XPathConstants.STRING);
//			Body = Body.replace("\r", "&#xD;");
//			Body = Body.replace("\n", "&#xA;");
			int OwnerUserId = Tools.ToInt((String) xpath.evaluate("/row/@OwnerUserId", document, XPathConstants.STRING));
			String OwnerDisplayName = (String) xpath.evaluate("/row/@OwnerDisplayName", document, XPathConstants.STRING);
			int LastEditorUserId = Tools.ToInt((String) xpath.evaluate("/row/@LastEditorUserId", document, XPathConstants.STRING));
			String LastEditorDisplayName = (String) xpath.evaluate("/row/@LastEditorDisplayName", document, XPathConstants.STRING);
			String LastEditDate = (String) xpath.evaluate("/row/@LastEditDate", document, XPathConstants.STRING);
			String LastActivityDate = (String) xpath.evaluate("/row/@LastActivityDate", document, XPathConstants.STRING);
			String ClosedDate = (String) xpath.evaluate("/row/@ClosedDate", document, XPathConstants.STRING);
			String Title = (String) xpath.evaluate("/row/@Title", document, XPathConstants.STRING);
			String CommunityOwnedDate = (String) xpath.evaluate("/row/@CommunityOwnedDate", document, XPathConstants.STRING);
			
			taginformation.setId(Id);
			taginformation.setCreationDate(CreationDate);
			taginformation.setBody(Body);
			taginformation.setOwnerUserId(OwnerUserId);
			taginformation.setOwnerDisplayName(OwnerDisplayName);
			taginformation.setLastEditorUserId(LastEditorUserId);
			taginformation.setLastEditorDisplayName(LastEditorDisplayName);
			taginformation.setLastEditDate(LastEditDate);
			taginformation.setLastActivityDate(LastActivityDate);
			taginformation.setClosedDate(ClosedDate);
			taginformation.setTitle(Title);
			taginformation.setCommunityOwnedDate(CommunityOwnedDate);

			return taginformation;
		}
}