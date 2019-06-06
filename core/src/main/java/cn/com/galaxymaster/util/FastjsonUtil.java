package cn.com.galaxymaster.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON工具类
 * 
 * @author yxxcrtd@gmail.com
 */
public class FastjsonUtil extends BaseUtil {

	/**
	 * 解析 JSONObject
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject parseObject(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseObject(json);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析对象
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String json, Class<T> clazz) {
		if (StringUtils.isEmpty(json) || null == clazz) {
			return null;
		}
		try {
			return JSON.parseObject(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析简单无序的 MAP
	 * 
	 * @param json json串
	 * 
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> parseSimpleMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseObject(json, HashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析简单有序的 MAP
	 * 
	 * @param json json串
	 * 
	 * @return LinkedHashMap<String, String>
	 */
	public static LinkedHashMap<String, String> parseSimpleLinkedHashMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseObject(json, LinkedHashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 解析有序的 MAP
	 * 
	 * @param json json串
	 * 
	 * @return LinkedHashMap<?, ?>
	 */
	public static LinkedHashMap<?, ?> parseLinkedMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseObject(json, LinkedHashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析无序的 MAP
	 * 
	 * @param json json串
	 * 
	 * @return HashMap<?, ?>
	 */
	public static Map<?, ?> parseMap(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseObject(json, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析 List
	 * 
	 * @param json
	 * @return
	 */
	public static List<String> parseSimpleList(String json) {
		if (StringUtils.isEmpty(json)) {
			return null;
		}
		try {
			return JSON.parseArray(json, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将 JSON 转化解析成 泛型（支持简单数组、简单集合）
	 * 
	 * @param json json串
	 * @param objClass 对象类型
	 * @return
	 */
	public static <T> List<T> parseList(String json, Class<T> objClass) {
		if (StringUtils.isEmpty(json) || null == objClass) {
			return null;
		}
		try {
			return JSON.parseArray(json, objClass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 对象转化成 JSON
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		if (null == obj) {
			return "";
		}
		try {
			return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteNonStringKeyAsString);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
