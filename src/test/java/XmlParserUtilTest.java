import com.suncy.utils.XmlParserUtil;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

public class XmlParserUtilTest {

    private static final String XML_STRING = "<flows>\n" +
                                           "   <flow modelCode=\"model1\" appCode=\"app1\"/>\n" +
                                           "   <flow modelCode=\"model2\" appCode=\"app2\"/>\n" +
                                           "   <flow modelCode=\"model3\" appCode=\"app1\"/>\n" +
                                           "</flows>";

    private static SAXReader reader;

    @BeforeAll
    public static void setup() {
        reader = new SAXReader();
    }

    @Test
    public void testParseFlowXml() throws DocumentException {
        // 执行待测方法
        Map<String, Set<String>> result = XmlParserUtil.parseFlowXml(XML_STRING, reader);

        System.out.println("result = " + result);
    }
}
