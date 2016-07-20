package com.yy.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.yy.domain.entity.CustomerContactor;
import com.yy.web.utils.HttpXmlClient;

import net.sf.json.JSONArray;
@Service
public class ALDataService {
	public String getReceiptAddress(HttpServletRequest request){
		final String account = request.getParameter("account");
		final String pwd = request.getParameter("pwd");
		System.out.println("ALDataService----------------"+account);
        Display display=new Display(); 
        Shell shell=new Shell(display); 
        shell.setText("SWT Browser Test"); 
        shell.setSize(1200,800); 
        
        final Text text=new Text(shell,SWT.BORDER); 
        text.setBounds(110,5,560,25); 
        text.setText("http://member1.taobao.com/member/fresh/deliver_address.htm?spm=a1z08.10.0.0.SxdfME");
        
        Button button=new Button(shell,SWT.BORDER); 
        button.setBounds(680,5,100,25);        
        button.setText("go"); 
        
        Label label=new Label(shell,SWT.LEFT); 
        label.setText("输入网址 :"); 
        label.setBounds(5, 5, 100, 25); 
        
        final Browser browser=new Browser(shell,SWT.FILL); 
        browser.setBounds(5,30,1200,760);
        button.addListener(SWT.Selection, new Listener() 
        { 
            public void handleEvent(Event event) 
            { 
                String input=text.getText().trim(); 
                
                if(input.length()==0)return; 
                browser.execute("document.getElementById('TPL_username_1').value='"+account+"';"
                		+ "document.getElementById('TPL_password_1').value='"+pwd+"';"
                		+ "document.getElementById('J_SubmitStatic').click()");
                String browserStr=browser.getText();
                if(browserStr.indexOf("thead-tbl-address")!=-1){
                	try {
						StrToHTML(browserStr);
					} catch (Exception e) {
						e.printStackTrace();
					}
                }
            	
            } 
        });
        shell.open(); 
        browser.setUrl("http://member1.taobao.com/member/fresh/deliver_address.htm?spm=a1z08.10.0.0.SxdfME");
        /**
         * 监听网页执行完成后事件
         */
        browser.addProgressListener(new ProgressListener() {
        	@Override
        	public void changed(ProgressEvent arg0) {
        	}
        	boolean b= false;
        	@Override
        	public void completed(ProgressEvent arg0) {
        		if(!b){
        			browser.execute("document.getElementById('TPL_username_1').value='"+account+"';"
        					+ "document.getElementById('TPL_password_1').value='"+pwd+"';"
        					+ "document.getElementById('J_SubmitStatic').click()");
        			String browserStr=browser.getText();
        			try {
						StrToHTML(browserStr);
					} catch (IOException e) {
						e.printStackTrace();
					}
        			b=true;
        		}
        	}
        }); 
        while (!shell.isDisposed()) { 
            if (!display.readAndDispatch()){
            	display.sleep(); 
            } 
          }
        
        display.dispose(); 
	
        return account;
	}
	/**
	 * 获取网页中的地址信息
	 * @param str
	 * @throws IOException
	 */
	public static void StrToHTML(String str) throws IOException{
		Document doc = Jsoup.parse(str);
		Elements elements = doc.getElementsByClass("thead-tbl-address");
		if(elements.size()>0){
			CustomerContactor cc = null;
			JSONArray jArray = new JSONArray();
			for(Element Element: elements){
				Elements es = Element.select("td");
				cc = new CustomerContactor();
				cc.setContactorName(es.get(0).text());
				cc.setAddress(es.get(1).text()+es.get(2).text());
				cc.setPostCode(es.get(3).text());
				cc.setCellPhone(es.get(4).text());
				jArray.add(cc);
			}
			Map<String, String> params = new HashMap<String, String>();
			params.put("ccList", jArray.toString());
			HttpXmlClient.post("http://192.168.0.106:8080/YY/customer/saveCustomerContactor.action", params);
		}
	}
}
