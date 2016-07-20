package com.yy.web.utils;

import java.util.HashMap;
import java.util.Map;

public class BrowserStore {
	public static Map<String,Object> browserStore= new HashMap<String,Object>();

	public static Object setObj(String key,Object object){
		return browserStore.put(key, object);
	}
	
	public static Object getObj(String key){
		return browserStore.get(key);
	}
}
