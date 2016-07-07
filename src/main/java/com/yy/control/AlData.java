package com.yy.control;

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

import net.sf.json.JSONArray;

public class AlData {
	public void getAlData(final String account,final String pwd){
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
//                browser.setUrl("http://member1.taobao.com/member/fresh/deliver_address.htm?spm=a1z08.10.0.0.SxdfME");
                
                
//                browser.execute("alert(document.getElementsByName('TPL_username')[0].value)");
//                browser.execute(" document.getElementsByClassName('thead-tbl-address')[0].cells[0].innerText='123'; "
//                		+ " alert(document.getElementsByClassName('thead-tbl-address')[0].cells[0].innerHTML +"
//                		+ "    document.getElementsByClassName('thead-tbl-address')[0].cells[2].innerHTML )");
                browser.execute("document.getElementById('TPL_username_1').value='"+account+"';"
                		+ "document.getElementById('TPL_password_1').value='"+pwd+"';"
                		+ "document.getElementById('J_SubmitStatic').click()");
                String browserStr=browser.getText();
                if(browserStr.indexOf("thead-tbl-address")!=-1){
//                	browserStr = browserStr.substring(browserStr.indexOf("thead-tbl-address"), browserStr.lastIndexOf("thead-tbl-address"));
                	try {
//						StrToHTML(browserStr);
                		Document doc = Jsoup.parse(browserStr);
                		Elements elements = doc.getElementsByClass("thead-tbl-address");
                		JSONArray jArray = new JSONArray();
                		for(Element Element: elements){
                			Elements es = Element.select("td");
//                			browser.execute("alert("+es.get(0).text()+")");
//                			browser.execute("alert(123);");
                			System.out.println(es.get(0).text());
//                			saveCustomerContactor(es);
                		}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            	
            } 
        });
        shell.open(); 
        browser.setUrl("http://member1.taobao.com/member/fresh/deliver_address.htm?spm=a1z08.10.0.0.SxdfME");
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
        			b=true;
        			System.out.println("--------------------b========="+b+"--------------------b========="+browserStr);
        		}
        	}
        }); 
        while (!shell.isDisposed()) { 
            if (!display.readAndDispatch()){
            	display.sleep(); 
            } 
          }
        
        display.dispose(); 
	
	}
}
