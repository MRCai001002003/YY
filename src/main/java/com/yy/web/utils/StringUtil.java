package com.yy.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 字符串工具类
 *
 */
public class StringUtil {
	
	/**
	* @Title: setSession 
	* @Description: 将值放入session中
	* @param @param request
	* @param @param o
	* @param @param sign    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public static void setSession(HttpServletRequest request,Object o,String sign){
		if(o==null)
			return;
		request.getSession().setAttribute(sign, null);//设置为空
		request.getSession().setAttribute(sign, o);
	}
	/**
	* @Title: getSession 
	* @Description: 根据标志获取sesion中的值
	* @param @param request
	* @param @param sign
	* @param @return    设定文件 
	* @return Object    返回类型 
	 */
	public static Object getSession(HttpServletRequest request,String sign){
		return request.getSession().getAttribute(sign);
	}

	private static String[][] FilterChars = { { "%", "/%" },
		{ "/", "//;" }, {"'","''"},{"\\","\\/"}};


	/**
	 * 特殊字符处理
	 * @param content
	 * @return
	 */
	public static String replayStr(String content){
		for(int i = 0; i < FilterChars.length; i++){
			content = content.replace(FilterChars[i][0], FilterChars[i][1]);
		}
		return content;

	}

	/**
	 * 从url获取参数值
	 *
	 * @param url
	 * @return
	 */
	public static String[] getUrlParamVal(String url) {
		String[] param_arr = null;
		String[] param_val = null;
		if (url.indexOf("?") != -1) {
			String params_str = url.substring(url.indexOf("?") + 1,
					url.length());
			if (params_str.indexOf("&") != -1) {
				param_arr = params_str.split("&");
			} else {
				param_arr = new String[] { params_str };
			}

			if (param_arr != null && param_arr.length > 0) {
				param_val = new String[param_arr.length];
				int i = 0;
				for (String key : param_arr) {
					if (key.indexOf("=") != -1) {
						String[] paramAndVal = key.split("=");
						if (paramAndVal.length > 1) {
							param_val[i] = paramAndVal[1];
						} else {
							param_val[i] = "";
						}

					}
					i++;
				}
			}
		}

		return param_val;
	}

	/**
	 * 字符串转换为特定的类型
	 *
	 * @param value
	 * @param type
	 * @param format
	 * @return
	 */
	public static Object stringConvertSpecifiedType(String value, String type,
			String format) {
		if ("".equals(value))
			return null;
		if ("Date".equals(type)) {
			String date = DateUtil.formatDateBySpecified(value, format);
			DateFormat dateform = new SimpleDateFormat(format);
			try {
				return dateform.parse(date);
			} catch (ParseException e) {
				return null;
			}
		}
		if ("Integer".equals(type)) {
			return Integer.parseInt(value);
		}
		if ("Double".equals(type)) {
			return Double.parseDouble(value);
		}
		if ("Boolean".equals(type)) {
			return Boolean.valueOf(type);
		}
		return value;
	}

	/**
	 * 字符串转换为特定的类型
	 *
	 * @param value
	 * @param type
	 * @return
	 */
	public static Object stringConvertSpecifiedType(String value, String type) {
		return stringConvertSpecifiedType(value, type, null);
	}

	/**
	 * 首字母大写
	 *
	 * @param str
	 * @return
	 */
	public static String upperCaseFirst(String str) {
		if (str != null && str.length() > 0) {
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		return str;
	}

	/**
	 * 判断字符串是否匹配
	 *
	 * @param str
	 * @param reg
	 * @return
	 */
	public static boolean match(String str, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 获取匹配的数据
	 *
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String getMatchStr(String str, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;

	}

	/**
	 * 将key value键值对的字符串解析到map中
	 *
	 * @param str
	 *            字符串
	 * @param split
	 *            分隔符
	 * @param reg
	 *            key和值的分隔符
	 * @return
	 */
	public static Map<String, String> parseMap(String str, String split,
			String reg) {
		Map<String, String> map = new HashMap<String, String>();
		String[] key_values = str.split(split);
		if (key_values != null) {
			for (String key_value : key_values) {
				String[] keyval = key_value.split(reg);
				if (keyval != null && keyval.length > 0) {
					map.put(keyval[0].trim(), keyval[1].trim());
				}
			}
		}
		return map;
	}

	/**
	 * 将json转换为数组
	 * @param json_str
	 * @param tag
	 * @return
	 */
	public static List<Map<String, String>> paseJsonToMap(String json_str, String tag,String title,Integer datarow,Integer dataColCount) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		json_str = json_str.trim();
		try {
			if (json_str.indexOf("[") == 0) {
				list = objectMapper.readValue(
						json_str, List.class);
				return list;
			} else {
				//单个对象
				if(tag == null){
					Map<String, String> map = objectMapper.readValue(json_str, Map.class);
					list.add(map);
				}else{
					//列表结构
					Map<String, Object> map = objectMapper.readValue(json_str, Map.class);
					list = (List<Map<String, String>>)map.get(tag);
					//如果title不为空需要转换为特定的列表
					if(title != null && !"".equals(title)){
						list = getSpecifiedMapFromList(title, datarow, dataColCount, list);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}




	/**
	 * 将列表转换为制定的列表格式
	 * @param title
	 * @param datarow
	 * @param dataColCount
	 * @param list
	 * @return
	 */
	public static List<Map<String, String>> getSpecifiedMapFromList(String title,Integer datarow,Integer dataColCount,List<Map<String,String>> list){
		List<Map<String,String>> res = new ArrayList<Map<String,String>>();
		String titles[] = title.split(",");
		if(list != null && list.size() > 0){
			for(Map<String,String> map : list){
				//数据条数
				int count = map.keySet().size()/dataColCount;
				for(int i= 0 ; i < count ; i++){
					Map<String,String> rsMap = new HashMap<String,String>();
					for(String tit : titles){
						rsMap.put(tit.replace("${count}", ""), map.get(tit.replace("${count}", ""+(datarow+i))));
					}
					res.add(rsMap);
				}
			}
		}
		return res;
	}
	/**
	 * 给值为null的字符串赋默认值
	 * @author LuoRuixian
	 */
	public static String trimNull(String str, String defaultStr) {
		return str == null ? defaultStr : str;
	}

	/**
	 * 给值为null的字符串赋值空字符串
	 * @author LuoRuixian
	 */
	public static String trimNull(String str) {
		return trimNull(str, "");
	}
	/**
	 * @Title: randomCode
	 * @Description: 根据length生成n为随机数
	 * @author caizhen
	 * @param @param length
	 * @param @return    设定文件
	 * @return String    返回类型
	 */
    public static String randomCode(int length){
    	StringBuffer sb=new StringBuffer();
    	String chars="0123456789";
    	for(int i=0;i<length;i++){
    		int rand=(int)(Math.random()*10);
    		sb.append(chars.charAt(rand));
    	}
    	return sb.toString();
    }
    /**
     * 根据日期、格式格式化日期
     * @param date
     * @param formatStr
     * @return
     */
    public static String getFormatDate(Date date,String formatStr){
    	SimpleDateFormat s=new SimpleDateFormat(formatStr);
    	return s.format(date);
    }
	public static Date getDayByParam(Date date,int num) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.YEAR, -num);  
        date = calendar.getTime();  
        return date;  
    }

}
