package hw2.travelcompany.common.solutions.xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public final class XmlDomUtils {

    private XmlDomUtils() {
    }

    public static String getOnlyElementTextContent(Element elementSource, String tagName) {
        NodeList elementsByTagName = elementSource.getElementsByTagName(tagName);
        return elementsByTagName.item(0).getTextContent();
    }
}
