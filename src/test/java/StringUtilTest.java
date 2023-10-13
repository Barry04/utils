import com.suncy.utils.StringUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class StringUtilTest {
	
	@Test
	public void testParseUnderLineToCamelCase_PositiveCase() {
		String input = "hello_world";
		String expectedOutput = "helloWorld";
		String actualOutput = StringUtil.parseUnderLineToCamelCase(input);
		assertEquals(expectedOutput, actualOutput);
	}
	
	
	@Test
	public void testParseUnderLineToCamelCase_EmptyString() {
		String input = "";
		String expectedOutput = "";
		String actualOutput = StringUtil.parseUnderLineToCamelCase(input);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void testParseUnderLineToCamelCase_SingleCharacter() {
		String input = "a";
		String expectedOutput = "a";
		String actualOutput = StringUtil.parseUnderLineToCamelCase(input);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void testParseUnderLineToCamelCase_MultipleUnderscores() {
		String input = "hello__world";
		String expectedOutput = "helloWorld";
		String actualOutput = StringUtil.parseUnderLineToCamelCase(input);
		assertEquals(expectedOutput, actualOutput);
	}

}
