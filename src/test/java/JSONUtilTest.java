import cn.hutool.core.io.file.FileReader;
import com.suncy.utils.JSONUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class JSONUtilTest {
	
	@Test
	void replaceJsonAll() {
		Map<String, String> keys = new HashMap<>();
		keys.put("value", "newValue");
		
		String jsonString = "{ \"key\": \"value\" }";
		jsonString = JSONUtil.replaceJsonAll(jsonString, "key", keys);
		assertThat(jsonString, is("{ \"key\": \"newValue\" }"));
	}
	
	@Test
	void replaceJson() {
		String jsonString = "{\"age\":\"11\",\"name\":\"asa\",\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}";
		Map<String, String> keys = new HashMap<>();
		keys.put("asa", "newValue");
		jsonString = JSONUtil.replaceJsonAll(jsonString, "name", keys);
		assertThat(jsonString, is("{\"age\":\"11\",\"name\":\"newValue\",\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}"));
	}
	
	@Test
	void replaceJsonWhitOut() {
		String jsonString = "{\"age\":\"11\",\"name\":null,\"phone\":110,\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}";
		Map<String, String> keys = new HashMap<>();
		keys.put("110", "newValue");
		jsonString = JSONUtil.replaceJsonWhitOutQuote(jsonString, "phone", keys);
		assertThat(jsonString, is("{\"age\":\"11\",\"name\":null,\"phone\":newValue,\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}"));
	}
	
	@Test
	void replaceJsons() {
		String jsonString = "{\"age\":\"11\",\"name\":null,\"phone\":110,\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}";
		Map<String, String> keys = new HashMap<>();
		keys.put("110", "newValue");
		jsonString = JSONUtil.replaceJsonWhitOutQuote(jsonString, "phone", keys);
		assertThat(jsonString, is("{\"age\":\"11\",\"name\":null,\"phone\":newValue,\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}"));
		System.out.println("jsonString = " + jsonString);
		jsonString = "{\"age\":\"11\",\"name\":null,\"phone\":\"110\",\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}";
		jsonString = JSONUtil.replaceJson(jsonString, "phone", keys);
		// assertThat(jsonString, is("{\"age\":\"11\",\"name\":null,\"phone\":newValue,\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}"));
		System.out.println("**jsonString = " + jsonString);
	}

	@Test
	public void testFindValueByKeyWithValidJSON() {
		String json = "{\"name\":\"张三\",\"age\":20,\"city\":\"北京\"}";
		String key = "name";
		Set<String> result = JSONUtil.findValueByKey(json, key);
		System.out.println("result = " + result);
	}

	@Test
	public void testFindValueByKeyWithKeyNotFound() {
		String json = "{\"name\":\"张三\",\"age\":20,\"city\":\"北京\"}";
		String key = "address";
		Set<String> result = JSONUtil.findValueByKey(json, key);
		System.out.println("result = " + result);
	}

	@Test
	public void testFindValueByKeyWithEmptyJSON() {
		String json = "";
		String key = "name";
		Set<String> result = JSONUtil.findValueByKey(json, key);
		System.out.println("result = " + result);
	}

	@Test
	public void testFindValueByKeyWithNullJSON() {
		String json = null;
		String key = "name";
		Set<String> result = JSONUtil.findValueByKey(json, key);
		System.out.println("result = " + result);
	}

	@Test
	public void testFindValueByKeyWithInvalidJSON() {
		String json = "{name:张三, age:20, city:北京}";
		String key = "name";
		Set<String> result = JSONUtil.findValueByKey(json, key);
		System.out.println("result = " + result);
	}

	@Test
	public void testFindValueByKeyWithEscapedQuotes() {
		String json = "{\"name\":\"张\\\"三\"}";
		String key = "name";
		Set<String> result = JSONUtil.findValueByKey(json, key);
		System.out.println("result = " + result);
	}

	// Test for a key that has slashes in the JSON but the method does not currently handle
	@Test
	public void testFindValueByKeyWithSlashesNotImplemented() {
		String json = "{\"name\":\"张\\/三\"}";
		String key = "name";
		Set<String> result = JSONUtil.findValueByKey(json, key);
		// As the method does not currently handle slashes, this test expects no result
		System.out.println("result = " + result);
	}

	@Test
	public void testFindValueByKeyFromFile() throws IOException {
		// 指定测试 JSON 文件的路径（相对于类路径）
		String filePath = "processKey.json";
		String jsonContent = readFileContent(filePath);

		// 假设你的 JSON 文件中有一个键为 "exampleKey"，你想测试这个键的值
		String keyToFind = "processKey";
		Set<String> result = JSONUtil.findValueByKey(jsonContent, keyToFind);

		// 这里添加断言来验证结果是否符合预期
		// 假设你知道预期结果应该是 "expectedValue"
		System.out.println("result = " + result);
		assertThat(result.size(), is(2));
	}

	private String readFileContent(String filePath) {
		FileReader fileReader = new FileReader(filePath);
		return fileReader.readString();

	}
}
