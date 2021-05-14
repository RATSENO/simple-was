package nhn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SettingUtil {
	
	private static final Logger log = LoggerFactory.getLogger(SettingUtil.class);
	private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();
	
	private static JSONObject readServerSetting() {
		JSONObject jsonObject = null;
		URL url = CLASS_LOADER.getResource("setting.json");
		InputStreamReader inputStreamReader;
		try {
			inputStreamReader = new InputStreamReader(url.openStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
			JSONParser jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(bufferedReader);
		} catch (IOException e) {
			log.error("setting.json �б� ����");
		} catch (ParseException e) {
			log.error("setting.json �б� ����");
		}
		return jsonObject;
	}
	
	public static Long getServerPort() {
		JSONObject settingJsonObject = readServerSetting();
		return (Long) settingJsonObject.get("serverPort");
	}
	
	public static Long getClientThreadPoolCount() {
		JSONObject settingJsonObject = readServerSetting();
		return (Long) settingJsonObject.get("clientPool");
	}
	
	public static String getRootDirectoryName() {
		JSONObject settingJsonObject = readServerSetting();
		return (String) settingJsonObject.get("rootDirectoryName");
	}
	
	public static JSONArray getHostJsonArray() {
		JSONObject settingJsonObject = readServerSetting();
		return (JSONArray) settingJsonObject.get("httpHostList");
	}
}
