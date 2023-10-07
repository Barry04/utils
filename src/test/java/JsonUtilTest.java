import com.suncy.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class JsonUtilTest {
	
	@Test
	void replaceJsonAll() {
		Map<String, String> keys = new HashMap<>();
		keys.put("value", "newValue");
		
		String jsonString = "{ \"key\": \"value\" }";
		jsonString = JsonUtil.replaceJsonAll(jsonString, "key", keys);
		assertThat(jsonString, is("{ \"key\": \"newValue\" }"));
	}
	
	@Test
	void replaceJson() {
		String jsonString = "{\"age\":\"11\",\"name\":\"asa\",\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}";
		Map<String, String> keys = new HashMap<>();
		keys.put("asa", "newValue");
		jsonString = JsonUtil.replaceJsonAll(jsonString, "name", keys);
		assertThat(jsonString, is("{\"age\":\"11\",\"name\":\"newValue\",\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}"));
	}
	
	@Test
	void replaceJsonWhitOut() {
		String jsonString = "{\"age\":\"11\",\"name\":null,\"phone\":110,\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}";
		Map<String, String> keys = new HashMap<>();
		keys.put("110", "newValue");
		jsonString = JsonUtil.replaceJsonWhitOutQuot(jsonString, "phone", keys);
		assertThat(jsonString, is("{\"age\":\"11\",\"name\":null,\"phone\":newValue,\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}"));
	}
	
	@Test
	void replaceJsons() {
		String jsonString = "{\"age\":\"11\",\"name\":null,\"phone\":110,\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}";
		Map<String, String> keys = new HashMap<>();
		keys.put("110", "newValue");
		jsonString = JsonUtil.replaceJsonWhitOutQuot(jsonString, "phone", keys);
		assertThat(jsonString, is("{\"age\":\"11\",\"name\":null,\"phone\":newValue,\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}"));
		System.out.println("jsonString = " + jsonString);
		jsonString = "{\"age\":\"11\",\"name\":null,\"phone\":\"110\",\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}";
		jsonString = JsonUtil.replaceJson(jsonString, "phone", keys);
		// assertThat(jsonString, is("{\"age\":\"11\",\"name\":null,\"phone\":newValue,\"sons\":[{\"sex\":\"1\"},{\"sex\":\"2\"}]}"));
		System.out.println("**jsonString = " + jsonString);
	}
	
	
}
