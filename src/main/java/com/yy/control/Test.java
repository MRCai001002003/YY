package com.yy.control;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.util.Assert;

import com.yy.web.utils.BrowserStore;
import com.yy.web.utils.HttpXmlClient;

public class Test {
	public static void main(String[] args) {
		
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("account", "acc");
//		params.put("pwd", "pwd");
//		HttpXmlClient.post("http://192.168.0.112:8080/YY/index/getAlData.action", params);
//		AlData al = new AlData();
//		al.getAlData("17767173344", "cz_294042223");
//		al.getAlData("partnesse", "legend_fly_lu");
//		b();
//		c();
//		d();
		BrowserStore.setObj("czCode", "123");
		Object a=BrowserStore.getObj("czCode");
		BrowserStore.setObj("czCode", null);
		System.out.println(a);
		
		Object b=BrowserStore.getObj("czCode");
		System.out.println(b);
	}
	public static void a(){
		Map<String, String> params = new HashMap<String, String>();  
		params.put("name", "陈李刚"); 
		params.put("idNo", "");
		params.put("resonCd", "01"); 
		params.put("mobileNo", "13905792575");
		params.put("cardCode", "");
		params.put("edu", "2");
		params.put("company", "");
		      
		HttpXmlClient.post("http://139.196.136.32/captureOL/company_executeAuth.action", params);
	}
	public static void b(){
		Map<String, String> params = new HashMap<String, String>();  
		params.put("name", "吴丽娟"); 
		params.put("idNo", "330902198407170023");
		params.put("resonCd", "01"); 
		params.put("mobileNo", "15356985222");
		params.put("cardCode", "4367480019009862");
		params.put("edu", "2");
		params.put("company", "");
		      
		HttpXmlClient.post("http://139.196.136.32:8888/captureOL/company_executeAuth.action", params);
	}
	public static void c(){
		Map<String, String> params = new HashMap<String, String>();  
		params.put("name", "黄映楠"); 
		params.put("idNo", "330782199202212520");
		params.put("resonCd", "01"); 
		params.put("mobileNo", "15257950799");
		params.put("cardCode", "4581232990988485");
		params.put("edu", "2");
		params.put("company", "");
		      
		HttpXmlClient.post("http://139.196.136.32:8888/captureOL/company_executeAuth.action", params);
	}
	public static void d(){
		Map<String, String> params = new HashMap<String, String>();  
		params.put("name", "王旭珍"); 
		params.put("idNo", "330782199208052521");
		params.put("resonCd", "01"); 
		params.put("mobileNo", "18806791993");
		params.put("cardCode", "6228580799008167641");
		params.put("edu", "2");
		params.put("company", "");
		      
		HttpXmlClient.post("http://139.196.136.32:8888/captureOL/company_executeAuth.action", params);
	}
	public static Date getNextDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.YEAR, -1);  
        date = calendar.getTime();  
        return date;  
    }  
	//DOM4j解析XML   
    public static void parse(String protocolXML) {   
    	JSONObject jObject = new JSONObject();
        try {   
  
             Document doc=(Document)DocumentHelper.parseText(protocolXML);   
             Element books = doc.getRootElement();   
             System.out.println("根节点"+books.getName());   
            // Iterator users_subElements = books.elementIterator("UID");//指定获取那个元素   
             Iterator   Elements = books.elementIterator();   
            while(Elements.hasNext()){   
            	Element e = (Element)Elements.next();   
                System.out.println("节点"+e.getName()+"\ttext="+e.getText());
                if(StringUtils.isNoneBlank(e.getText())){
                	jObject.put(e.getName(), e.getText());
                }  
            //    List user_subElements = user.elements("username");指定获取那个元素   
//              System.out.println("size=="+subElements.size());   
//              for( int i=0;i<subElements.size();i++){   
//                  Element ele = (Element)subElements.get(i);   
//                  System.out.print(ele.getName()+" : "+ele.getText()+" ");   
//              }   
                System.out.println(jObject);   
            }   
         } catch (Exception e) {   
             e.printStackTrace();   
         }           
     }   
	
	 public static String formatXml(String str) throws Exception {
		  Document document = null;
		  document = DocumentHelper.parseText(str);
		  // 格式化输出格式
		  OutputFormat format = OutputFormat.createPrettyPrint();
		  format.setEncoding("gb2312");
		  StringWriter writer = new StringWriter();
		  // 格式化输出流
		  XMLWriter xmlWriter = new XMLWriter(writer, format);
		  // 将document写入到输出流
		  xmlWriter.write(document);
		  xmlWriter.close();
		  return writer.toString();
		 }

}
