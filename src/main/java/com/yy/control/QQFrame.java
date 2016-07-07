package com.yy.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.yy.domain.entity.CustomerContactor;
import com.yy.service.CustomerService;
import com.yy.web.utils.HttpXmlClient;

import net.sf.json.JSONArray;

import org.eclipse.swt.SWT; 
import org.eclipse.swt.browser.Browser; 
import org.eclipse.swt.widgets.Button; 
import org.eclipse.swt.widgets.Display; 
import org.eclipse.swt.widgets.Event; 
import org.eclipse.swt.widgets.Label; 
import org.eclipse.swt.widgets.Listener; 
import org.eclipse.swt.widgets.Shell; 
import org.eclipse.swt.widgets.Text; 

/**
*TODO java 嵌入浏览器
*date:Oct 21, 2009
*time:10:34:09 AM
*author:Administrator
*email:jxauwxj@126.com
*/
public class QQFrame extends JFrame {
	
	public static void main(String[] args)  throws Exception {
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
        
        Button buttonSave=new Button(shell,SWT.BORDER); 
        buttonSave.setBounds(780,5,100,25);        
        buttonSave.setText("save"); 
        
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
//                if(!input.startsWith("http://")) 
//                { 
//                    input="http://"+input; 
//                    text.setText(input); 
//                } 
//                browser.setUrl("https://login.m.taobao.com/login.htm?redirectURL=http://www.taobao.com/"); 
//                browser.setUrl("http://member1.taobao.com/member/fresh/deliver_address.htm?spm=a1z08.10.0.0.SxdfME");
                
                
              	      //frame.dispose();  
//                browser.execute("alert(document.getElementsByName('TPL_username')[0].value)");
//                browser.execute(" document.getElementsByClassName('thead-tbl-address')[0].cells[0].innerText='123'; "
//                		+ " alert(document.getElementsByClassName('thead-tbl-address')[0].cells[0].innerHTML +"
//                		+ "    document.getElementsByClassName('thead-tbl-address')[0].cells[2].innerHTML )");
                browser.execute("document.getElementById('TPL_username_1').value='17767173344';"
                		+ "document.getElementById('TPL_password_1').value='17767173344';"
                		+ "document.getElementById('J_SubmitStatic').click()");
                String browserStr=browser.getText();
                if(browserStr.indexOf("thead-tbl-address")!=-1){
//                	browserStr = browserStr.substring(browserStr.indexOf("thead-tbl-address"), browserStr.lastIndexOf("thead-tbl-address"));
                	try {
						StrToHTML(browserStr);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            	
            } 
        }); 
        shell.open(); 
        browser.setUrl("http://member1.taobao.com/member/fresh/deliver_address.htm?spm=a1z08.10.0.0.SxdfME");
        while (!shell.isDisposed()) { 
            if (!display.readAndDispatch()) 
              display.sleep(); 
          } 
          display.dispose(); 
		
//        Desktop.browse( new URL("http://www.yahoo.com/ ")  );
	}

//	方式二：用 DJ Native Swing 组件(http://djproject.sourceforge.net/ns/index.html),支持firefox
//	
//	import java.awt.BorderLayout;
//	
//	import javax.swing.JFrame;
//	import javax.swing.JPanel;
//	import javax.swing.SwingUtilities;
//	
//	import chrriis.common.UIUtils;
//	import chrriis.dj.nativeswing.swtimpl.NativeInterface;
//	import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
//	
//	public class EagleBrowser extends JPanel {
//	
//	private JPanel webBrowserPanel;
//	
//	private JWebBrowser webBrowser;
//	
//	private String url;
//	
//	public EagleBrowser(String url) {
//	super(new BorderLayout());
//	this.url = url;
//	webBrowserPanel = new JPanel(new BorderLayout());
//	webBrowser = new JWebBrowser();
//	webBrowser.navigate(url);
//	webBrowser.setButtonBarVisible(false);
//	webBrowser.setMenuBarVisible(false);
//	webBrowser.setBarsVisible(false);
//	webBrowser.setStatusBarVisible(false);
//	webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
//	add(webBrowserPanel, BorderLayout.CENTER);
//	}
//	
//	public static void main(String[] args) {
//	final String url = "http://";
//	final String title = "电信营业厅缴费终端";
//	UIUtils.setPreferredLookAndFeel();
//	NativeInterface.open();
//	
//	SwingUtilities.invokeLater(new Runnable() {
//	public void run() {
//	JFrame frame = new JFrame(title);
//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	frame.getContentPane().add(new EagleBrowser(url), BorderLayout.CENTER);
//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//	frame.setLocationByPlatform(true);
//	//frame.setUndecorated(true);
//	frame.setVisible(true);
//	}
//	});
//	NativeInterface.runEventPump();
//	}
public static void StrToHTML(String str) throws IOException{
//	File input = new File("/tmp/input.html");
//	Document doc = Jsoup.parse(input, "UTF-8", "http://www.jb51.net/");
	Document doc = Jsoup.parse(str);
	Elements elements = doc.getElementsByClass("thead-tbl-address");
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
//		saveCustomerContactor(es);
	}
	Map<String, String> params = new HashMap<String, String>();
	params.put("ccList", jArray.toString());
	HttpXmlClient.post("http://192.168.0.112:8080/YY/customer/saveCustomerContactor.action", params);
//	Elements links = content.getElementsByTag("a");
//	for (Element link : links) {
//	  String linkHref = link.attr("href");
//	  String linkText = link.text();
//	}
}
public static void saveCustomerContactor(Elements es){
	Map<String, String> params = new HashMap<String, String>();  
	params.put("contactorName", es.get(0).text()); 
	params.put("address", es.get(1).text()+es.get(2).text());
	params.put("postCode", es.get(3).text()); 
	params.put("cellPhone", es.get(4).text());
	      
	HttpXmlClient.post("http://192.168.0.112:8080/YY/customer/saveCustomerContactor.action", params);
}
}