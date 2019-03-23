package hw2.travelcompany.common.solutions.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public final class XmlDomUtils {

    private XmlDomUtils() {
    }

    public static String getOnlyElementTextContent(Element elementSource, String tagName) {
        NodeList elementsByTagName = elementSource.getElementsByTagName(tagName);
        return elementsByTagName.item(0).getTextContent();
    }

    public static String getOnlyElementTextContentOrNull(Element elementSource, String tagName) {
        NodeList elementsByTagName = elementSource.getElementsByTagName(tagName);
        if (elementsByTagName.getLength() > 0) {
            return elementsByTagName.item(0).getTextContent();
        } else {
            return null;
        }
    }
    public static Document getDocument(String file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        return documentBuilder.parse(new File(file));
    }
    public static Element getOnlyElement(Document document, String tagName) {
        return (Element) document.getElementsByTagName(tagName).item(0);
    }

    public static Element getOnlyElement(Element element, String tagName) {
        return (Element) element.getElementsByTagName(tagName).item(0);
    }

}
