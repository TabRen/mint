package com.tab.mint.device.sms.SMSUtils;

import com.tab.mint.device.sms.SMSUtils.CPL.CPL;
import com.tab.mint.device.sms.SMSUtils.KDM.AuthenticatedPublic;
import com.tab.mint.device.sms.SMSUtils.KDM.KDM;
import com.tab.mint.device.sms.SMSUtils.KDM.KDMRequiredExtensions;
import com.tab.mint.device.sms.SMSUtils.KDM.RequiredExtensions;
import com.tab.mint.device.sms.SMSUtils.SPL.*;
import com.tab.mint.enums.MintEnum;
import com.tab.mint.exception.MintException;
import org.apache.commons.lang.StringEscapeUtils;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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

    public CPL getCPL() {
        if (getXMLEltStrValue() != null) {
            try {
                Document document = DocumentHelper.parseText(getXMLEltStrValue());
                CPL cpl = new CPL();
                cpl.setId(document.getRootElement().element("Id").getStringValue());
                cpl.setAnnotationText(
                    document.getRootElement().element("AnnotationText").getStringValue());
                cpl.setIssueDate(document.getRootElement().element("IssueDate").getStringValue());
                cpl.setIssuer(document.getRootElement().element("Issuer").getStringValue());
                cpl.setCreator(document.getRootElement().element("Creator").getStringValue());
                cpl.setContentTitleText(
                    document.getRootElement().element("ContentTitleText").getStringValue());
                cpl.setContentKind(
                    document.getRootElement().element("ContentKind").getStringValue());
                return cpl;
            } catch (DocumentException e) {
                logger.error("XMLResponseUtil getCPL occur exception: ", e);
                throw new MintException(MintEnum.PARSE_XML_ERROR);
            }
        }
        return null;
    }

    // 解析SPLXml
    public SPL parseSPLXML(String xml) {
        if ((null == xml) || xml.isEmpty())
            return null;
        Document document;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            logger.error("BarcoSMS parseSPL occur exception: ", e);
            throw new MintException(MintEnum.PARSE_XML_ERROR);
        }

        SPL spl = new SPL();
        spl.setUuid(document.getRootElement().element("Id").getStringValue());
        spl.setContentTitleText(
            document.getRootElement().element("ContentTitleText").getStringValue());
        spl.setAnnotationText(document.getRootElement().element("AnnotationText").getStringValue());
        spl.setIssuer(document.getRootElement().element("Issuer").getStringValue());
        spl.setIssueDate(document.getRootElement().element("IssueDate").getStringValue());
        spl.setCreator(document.getRootElement().element("Creator").getStringValue());
        // 使用XPath方式解析
        // 获取根结点<ShowPlaylist>
        Node rootNode = document.selectSingleNode("ShowPlaylist");
        List clipLists = rootNode.selectNodes("ClipList");
        if (!clipLists.isEmpty()) {
            List<ClipList> list1 = new ArrayList<>();
            for (Object obj1 : clipLists) {
                ClipList clipList = new ClipList();
                Node node1 = (Node) obj1;
                List clips = node1.selectNodes("Clip");
                if (!clips.isEmpty()) {
                    List<Clip> list2 = new ArrayList<>();
                    for (Object obj2 : clips) {
                        Clip clip = new Clip();
                        Node node2 = (Node) obj2;
                        clip.setType(node2.selectSingleNode("Type").getStringValue());
                        clip.setId(node2.selectSingleNode("Id").getStringValue());
                        clip.setContentTitleText(
                            node2.selectSingleNode("ContentTitleText").getStringValue());
                        clip.setDurationInMilliseconds(
                            node2.selectSingleNode("DurationInMilliseconds").getStringValue());
                        clip.setContentKind(node2.selectSingleNode("ContentKind").getStringValue());
                        List cueLists = node2.selectNodes("CueList");
                        if (!cueLists.isEmpty()) {
                            List<CueList> list3 = new ArrayList<>();
                            for (Object obj3 : cueLists) {
                                CueList cueList = new CueList();
                                Node node3 = (Node) obj3;
                                List cues = node3.selectNodes("Cue");
                                if (!cues.isEmpty()) {
                                    List<Cue> list4 = new ArrayList<>();
                                    for (Object obj4 : cues) {
                                        Cue cue = new Cue();
                                        Node node4 = (Node) obj4;
                                        cue.setName(
                                            node4.selectSingleNode("Name").getStringValue());
                                        cue.setOffsetInMilliseconds(
                                            node4.selectSingleNode("OffsetInMilliseconds")
                                                .getStringValue());
                                        list4.add(cue);
                                    }
                                    cueList.setCue(list4);
                                }
                                list3.add(cueList);
                            }
                            clip.setCueList(list3);
                        }
                        list2.add(clip);
                    }
                    clipList.setClip(list2);
                }
                list1.add(clipList);
            }
            spl.setClipList(list1);
        }
        return spl;
    }

    public KDM getKDM() {
        if (getXMLEltStrValue() != null) {
            try {
                KDM kdm = new KDM();
                Document document = DocumentHelper.parseText(getXMLEltStrValue());
                Element authenticatedPublicElt =
                    document.getRootElement().element("AuthenticatedPublic");
                if (authenticatedPublicElt != null) {
                    AuthenticatedPublic authenticatedPublic = new AuthenticatedPublic();
                    authenticatedPublic
                        .setMessageId(authenticatedPublicElt.element("MessageId").getStringValue());
                    authenticatedPublic.setMessageType(
                        authenticatedPublicElt.element("MessageType").getStringValue());
                    authenticatedPublic.setAnnotationText(
                        authenticatedPublicElt.element("AnnotationText").getStringValue());
                    authenticatedPublic
                        .setIssueDate(authenticatedPublicElt.element("IssueDate").getStringValue());
                    Element equiredExtensionsElt =
                        authenticatedPublicElt.element("RequiredExtensions");
                    if (equiredExtensionsElt != null) {
                        RequiredExtensions requiredExtensions = new RequiredExtensions();
                        Element KDMRequiredExtensionsElt =
                            equiredExtensionsElt.element("KDMRequiredExtensions");
                        if (KDMRequiredExtensionsElt != null) {
                            KDMRequiredExtensions kdmRequiredExtensions =
                                new KDMRequiredExtensions();
                            kdmRequiredExtensions.setCompositionPlaylistId(
                                KDMRequiredExtensionsElt.element("CompositionPlaylistId")
                                    .getStringValue());
                            kdmRequiredExtensions.setContentTitleText(
                                KDMRequiredExtensionsElt.element("ContentTitleText")
                                    .getStringValue());
                            kdmRequiredExtensions.setContentKeysNotValidBefore(
                                KDMRequiredExtensionsElt.element("ContentKeysNotValidBefore")
                                    .getStringValue());
                            kdmRequiredExtensions.setContentKeysNotValidAfter(
                                KDMRequiredExtensionsElt.element("ContentKeysNotValidAfter")
                                    .getStringValue());
                            requiredExtensions.setKDMRequiredExtensions(kdmRequiredExtensions);
                        }
                        authenticatedPublic.setRequiredExtensions(requiredExtensions);
                    }
                    kdm.setAuthenticatedPublic(authenticatedPublic);
                    return kdm;
                }
            } catch (DocumentException e) {
                logger.error("XMLResponseUtil getKDM occur exception: ", e);
                throw new MintException(MintEnum.PARSE_XML_ERROR);
            }
        }
        return null;
    }

}
