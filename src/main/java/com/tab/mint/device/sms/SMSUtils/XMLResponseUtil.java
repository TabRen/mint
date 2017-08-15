package com.tab.mint.device.sms.SMSUtils;

import com.tab.mint.enums.MintEnum;
import com.tab.mint.exception.MintException;
import org.apache.commons.lang.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 解析返回的xml
 * <p>
 * Created by tab on 5/9/17.
 */
public class XMLResponseUtil {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Document document;
    private String responsesStatus;
    private String responsesVersion;

    public XMLResponseUtil(String xml) {
        try {
            this.document = DocumentHelper.parseText(xml);
            this.responsesStatus = responsesStatus();
            this.responsesVersion = responsesVersion();
        } catch (DocumentException e) {
            logger.error("XMLResponseUtil occur exception: ", e);
            throw new MintException(MintEnum.PARSE_XML_ERROR);
        }
    }

    private String responsesStatus() {
        return document.getRootElement().attributeValue("status").toUpperCase();
    }

    private String responsesVersion() {
        return document.getRootElement().attributeValue("version").toUpperCase();
    }

    public String getResponsesStatus() {
        return responsesStatus;
    }

    public void setResponsesStatus(String responsesStatus) {
        this.responsesStatus = responsesStatus;
    }

    public String getResponsesVersion() {
        return responsesVersion;
    }

    public void setResponsesVersion(String responsesVersion) {
        this.responsesVersion = responsesVersion;
    }


    // 获取元素的值
    public String getXMLEltStrValue() {
        Element rootElement = document.getRootElement();
        Element responseText = rootElement.element("response_text");
        return StringEscapeUtils.unescapeHtml(responseText.getStringValue());
    }

    // 获取元素的值
    public String getXMLEltStrValue(String xml, String str) {
        Element rootElement;
        try {
            rootElement = DocumentHelper.parseText(xml).getRootElement();
        } catch (DocumentException e) {
            logger.error("XMLResponseUtil getXML occur exception, xml: {}, str: {} ", xml, str, e);
            throw new MintException(MintEnum.PARSE_XML_ERROR);
        }
        Element responseText = rootElement.element(str);
        return StringEscapeUtils.unescapeHtml(responseText.getStringValue());
    }

    // 获取XPath的值
    // TODO:不能用于有命名空间的情况
    public String getXPathValue(String xml, String xPath) {
        Document document;
        try {
            document = DocumentHelper.parseText(xml);

            //            String namespace = document.getRootElement().getNamespace().getStringValue();
            //            Map<String, String> xmlMap = new HashMap<>();
            //            xmlMap.put("default", namespace);
            //            SAXReader reader = new SAXReader();
            //            reader.getDocumentFactory().setXPathNamespaceURIs(xmlMap);
            //            document = reader.read(xml);

            //            XPath xPath1 = document.createXPath("//default:"+xPath);
            //            xPath1.setNamespaceURIs(xmlMap);
            //            String str = xPath1.selectSingleNode(document).getStringValue();
            //            return StringEscapeUtils.unescapeHtml(str);
        } catch (DocumentException e) {
            logger.error("XMLResponseUtil getXMLAtbStrValue occur exception, xml: {}, xPath: {} ",
                xml, xPath, e);
            throw new MintException(MintEnum.PARSE_XML_ERROR);
        }
        String str = document.selectSingleNode(xPath).getStringValue();
        return StringEscapeUtils.unescapeHtml(str);
    }

    // 获取XPathList
    // TODO:不能用于有命名空间的情况
    public List getXPathList(String xml, String xPath) {
        Document document;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            logger.error("XMLResponseUtil getXMLAtbStrValue occur exception, xml: {}, xPath: {} ",
                xml, xPath, e);
            throw new MintException(MintEnum.PARSE_XML_ERROR);
        }
        return document.selectNodes(xPath);
    }

    public List<String> getList(String elementStr) {
        List<String> list = new ArrayList<>();
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator(elementStr);
        while (iterator.hasNext()) {
            list.add(((Element) iterator.next()).getStringValue());
        }
        return list;
    }

}
