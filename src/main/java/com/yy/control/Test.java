package com.yy.control;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.yy.web.utils.HttpXmlClient;
import com.yy.web.utils.StringUtil;



public class Test {
	public static void main(String[] args) {
		BigDecimal b=BigDecimal.valueOf(0.1500);
		System.out.print(new BigDecimal("0.1500")); 
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
