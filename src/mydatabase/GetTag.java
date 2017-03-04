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
public class GetTag {
	public static Tag Get(XPath xpath , Document document)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
			Tag tag = new Tag();

			// 获取节点属性值
			int id = Tools.ToInt((String) xpath.evaluate("/row/@Id", document, XPathConstants.STRING));
			String tagName = (String) xpath.evaluate("/row/@TagName", document, XPathConstants.STRING);
			int count = Tools.ToInt((String) xpath.evaluate("/row/@Count", document, XPathConstants.STRING));
			int excerptPostId = Tools.ToInt((String) xpath.evaluate("/row/@ExcerptPostId", document, XPathConstants.STRING));
			int wikiPostId = Tools.ToInt((String) xpath.evaluate("/row/@WikiPostId", document, XPathConstants.STRING));

			tag.setId(id);
			tag.setTagName(tagName);
			tag.setCount(count);
			tag.setExcerptPostId(excerptPostId);;
			tag.setWikiPostId(wikiPostId);;

			return tag;
		}
}