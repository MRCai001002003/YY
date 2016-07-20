package com.yy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.cookie.Cookie;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.yy.dao.SmsDetailDao;
import com.yy.domain.entity.SmsDetail;
import com.yy.web.utils.HttpConnect;
import com.yy.web.utils.RequestHead;
import com.yy.web.utils.StringUtil;

/**
 * @ClassName: SmsService 
 * @Description: 短信接口
 * @author caiZhen
 * @date 2016年6月5日 下午2:45:11
 */
@Service
public class SmsService {
	@Autowired
	SmsDetailDao smsDetailDao;
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass().getName());
	/**
	 * @Title: sendSms 
	 * @Description: 提交贷款记录时发送短信验证码
	 * @author caiZhen
	 * @date 2016年7月6日 上午10:29:58
	 * @param @param request
	 * @param @param smsDetail
	 */
	public void sendSms(HttpServletRequest request,SmsDetail smsDetail){
		if(smsDetail==null){
			smsDetail.setPhone(request.getParameter("cellPhone"));
		}
		String response="";//"<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms><returnstatus>Success</returnstatus><message>OK</message><remainpoint>1440</remainpoint><taskID>6538308382720020</taskID><resplist><resp>6538308382720020#@#17767173344#@#0#@#</resp></resplist><successCounts>1</successCounts></returnsms>";
		String code=StringUtil.randomCode(6);
		smsDetail.setContent("【联网贷】您的信贷业务验证码是"+code);
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", "send");
		param.put("account", "922012");
		param.put("password", "w8FJXk");
		param.put("extno", "106908012");
		param.put("mobile", smsDetail.getPhone());
		param.put("content", smsDetail.getContent());
//		http://code.58yhkj.com:7862/sms?action=send&account=922012&password=w8FJXk&mobile=17767173344&
//			content=【品信金融】 您的验证码是 112222&extno=106908012
		List<RequestHead> requestHeads = new ArrayList<RequestHead>();
		requestHeads.add(new RequestHead("Content-Type", "application/json"));
		try {
			response = getJsonFromRpc_post("http://code.58yhkj.com:7862/sms",
					param, requestHeads);
			JSONObject jObject = null;
			if (StringUtils.isNoneBlank(response)) {
				jObject=parseXML(response);
				smsDetail.setMessage(jObject.getString("message"));
				if("Success".equals(jObject.getString("returnstatus"))){
					StringUtil.setSession(request, code, "verificationCode");
					StringUtil.setSession(request, smsDetail.getPhone(), "phone");
					
					smsDetail.setReturnstatus(1);//发送成功
				}else{
					log.error("短信调用异常");
					smsDetail.setReturnstatus(0);//发送失败
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		smsDetail.setType(1);
		smsDetail.setAction("send");
		smsDetailDao.insertSelective(smsDetail);
	}
	/**
	 * @Title: sendLoginCode 
	 * @Description: 登陆验证码
	 * @author caiZhen
	 * @date 2016年7月6日 上午14:29:58
	 * @param @param request
	 * @param @param smsDetail
	 */
	public void sendLoginCode(HttpServletRequest request){
		SmsDetail smsDetail = new SmsDetail();
		smsDetail.setPhone(request.getParameter("cellPhone"));
		String response="";//"<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms><returnstatus>Success</returnstatus><message>OK</message><remainpoint>1440</remainpoint><taskID>6538308382720020</taskID><resplist><resp>6538308382720020#@#17767173344#@#0#@#</resp></resplist><successCounts>1</successCounts></returnsms>";
		String code=StringUtil.randomCode(6);
		smsDetail.setContent("【联网贷】您的登陆验证码是"+code+",20分钟内都可以使用该验证码");
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", "send");
		param.put("account", "922012");
		param.put("password", "w8FJXk");
		param.put("extno", "106908012");
		param.put("mobile", smsDetail.getPhone());
		param.put("content", smsDetail.getContent());
//		http://code.58yhkj.com:7862/sms?action=send&account=922012&password=w8FJXk&mobile=17767173344&
//			content=【品信金融】 您的验证码是 112222&extno=106908012
		List<RequestHead> requestHeads = new ArrayList<RequestHead>();
		requestHeads.add(new RequestHead("Content-Type", "application/json"));
		try {
			response = getJsonFromRpc_post("http://code.58yhkj.com:7862/sms",
					param, requestHeads);
			JSONObject jObject = null;
			if (StringUtils.isNoneBlank(response)) {
				jObject=parseXML(response);
				smsDetail.setMessage(jObject.getString("message"));
				if("Success".equals(jObject.getString("returnstatus"))){
					StringUtil.setSession(request, code, "loginVerificationCode");
					StringUtil.setSession(request, smsDetail.getPhone(), "loginPhone");
					
					smsDetail.setReturnstatus(1);//发送成功
				}else{
					log.error("短信调用异常");
					smsDetail.setReturnstatus(0);//发送失败
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		smsDetail.setType(1);
		smsDetail.setAction("send");
		smsDetailDao.insertSelective(smsDetail);
	}
	public void sendSms_async(HttpServletRequest request,SmsDetail smsDetail){
		String response="";//"<?xml version=\"1.0\" encoding=\"utf-8\" ?><returnsms><returnstatus>Success</returnstatus><message>OK</message><remainpoint>1440</remainpoint><taskID>6538308382720020</taskID><resplist><resp>6538308382720020#@#17767173344#@#0#@#</resp></resplist><successCounts>1</successCounts></returnsms>";
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("action", "send");
		param.put("account", "922012");
		param.put("password", "w8FJXk");
		param.put("extno", "106908012");
		param.put("mobile", smsDetail.getPhone());
		param.put("content", smsDetail.getContent());
//		http://code.58yhkj.com:7862/sms?action=send&account=922012&password=w8FJXk&mobile=17767173344&
//			content=【品信金融】 您的验证码是 112222&extno=106908012
		List<RequestHead> requestHeads = new ArrayList<RequestHead>();
		requestHeads.add(new RequestHead("Content-Type", "application/json"));
		try {
			response = getJsonFromRpc_post("http://code.58yhkj.com:7862/sms",
					param, requestHeads);
			JSONObject jObject = null;
			System.out.println(response);
			if (StringUtils.isNoneBlank(response)) {
				jObject=parseXML(response);
				smsDetail.setMessage(jObject.getString("message"));
				if("Success".equals(jObject.getString("returnstatus"))){
					smsDetail.setReturnstatus(1);//发送成功
				}else{
					log.error("短信调用异常");
					smsDetail.setReturnstatus(0);//发送失败
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		smsDetail.setType(1);
		smsDetail.setAction("send");
		smsDetailDao.insertSelective(smsDetail);
	}
	//DOM4j解析XML   
    public static JSONObject parseXML(String protocolXML) throws Exception {   
    	JSONObject jObject = new JSONObject();
        Document doc=(Document)DocumentHelper.parseText(protocolXML);   
        Element books = doc.getRootElement();   
//        System.out.println("根节点"+books.getName());   
       // Iterator users_subElements = books.elementIterator("UID");//指定获取那个元素   
        Iterator  Elements = books.elementIterator();   
        while(Elements.hasNext()){   
            Element e = (Element)Elements.next();   
            if(StringUtils.isNoneBlank(e.getText())){
            	jObject.put(e.getName(), e.getText());
            }
//            List   subElements = user.elements();   
        //    List user_subElements = user.elements("username");指定获取那个元素   
//              System.out.println("size=="+subElements.size());   
//              for( int i=0;i<subElements.size();i++){   
//                  Element ele = (Element)subElements.get(i);   
//                  System.out.print(ele.getName()+" : "+ele.getText()+" ");   
//              }   
        }
        return jObject;
     }   
	public String getJsonFromRpc_post(String url, Map<String, String> param,
			List<RequestHead> requestHeads) {
		HttpConnect httpConnect  = new HttpConnect();
		List<Cookie> cookies = new ArrayList<Cookie>();
		String json = "";
		try {
			json = (String) httpConnect.request(url, "POST", param, "string",
					"UTF-8", cookies, requestHeads);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return json;
	}
}
