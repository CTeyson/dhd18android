package com.unikoeln.mazey.dhdexamplesecond.activities.utils.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import java.util.List;

public class XMLCreater {

    private Document document = null;

    public Document createXmlDocumentFromString(String xmlAsString) {
        try {
            document = DocumentHelper.parseText(xmlAsString);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public List<Node> getNodesOfXml(Document document, String xPathExpression) {
        return (List<Node>) document.selectNodes(xPathExpression);
    }

}
