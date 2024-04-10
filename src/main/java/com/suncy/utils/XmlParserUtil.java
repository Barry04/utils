package com.suncy.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class XmlParserUtil {


    public static Map<String, Set<String>> parseFlowXml(String xmlString, SAXReader reader) throws DocumentException {
        Document document = reader.read(new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8)));
        Element rootElement = document.getRootElement();
        Map<String, Set<String>> map = new HashMap<>();
        extractFlowXml(rootElement, map);
        return map;
    }

    public static void extractFlowXml(Element element, Map<String, Set<String>> map) {
        String modelCode = Objects.nonNull(element.attribute("modelCode")) ? element.attribute("modelCode").getValue() : null;
        String appCode = Objects.nonNull(element.attribute("appCode")) ? element.attribute("appCode").getValue() : null;

        if (StringUtils.isNoneBlank(modelCode, appCode)) {
           if (!map.containsKey("entityMap")) {
               Map<String, Set<String>> modelCodeMap = new HashMap<>();
               Set<String> entitySet = new HashSet<>();
               entitySet.add(modelCode);
               modelCodeMap.put(modelCode, entitySet);
           } else {

           }
        }
        for (Element e : element.elements()) {
            extractFlowXml(e, map);
        }
    }


}