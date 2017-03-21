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
 * Created by ShaoBin on 2017/3/6.
 */
public class GetBadge {
    public static Badge Get(XPath xpath , Document document) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        Badge badge = new Badge();

        // 获取节点属性值
        int id = Tools.ToInt((String) xpath.evaluate("/row/@Id", document, XPathConstants.STRING));
        int userid = Tools.ToInt((String) xpath.evaluate("/row/@UserId", document, XPathConstants.STRING));
        String name = (String) xpath.evaluate("/row/@Name", document, XPathConstants.STRING);
        String date = (String) xpath.evaluate("/row/@Date", document, XPathConstants.STRING);
        int classtype = Tools.ToInt((String) xpath.evaluate("/row/@Class", document, XPathConstants.STRING));
        boolean tagBased = Tools.ToBoolean((String) xpath.evaluate("/row/@TagBased", document, XPathConstants.STRING));

        badge.setId(id);
        badge.setUserId(userid);
        badge.setName(name);
        badge.setDate(date);
        badge.setClasstype(classtype);
        badge.setTagBased(tagBased);

        return badge;
    }
}
