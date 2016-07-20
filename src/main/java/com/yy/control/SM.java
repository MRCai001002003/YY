package com.yy.control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.cookie.Cookie;
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

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.yy.domain.entity.CustomerContactor;
import com.yy.web.utils.BrowserStore;
import com.yy.web.utils.HttpConnect;
import com.yy.web.utils.HttpXmlClient;
import com.yy.web.utils.RequestHead;

import net.sf.json.JSONArray;

public class SM {
	public Display display=new Display(); 
	public Shell shell=new Shell(display); 
    public final Browser browser=new Browser(shell,SWT.FILL); 
    
//	public static void main(String[] args) {
//		new SM().openLoginWin();
//	}
	
	private String loginPath_key;
	public SM(){}
	public SM(String loginPath_key){
		this.loginPath_key=loginPath_key;
	}
	public void openLoginWin(){
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
        

        browser.setBounds(5,30,1200,760);
        button.addListener(SWT.Selection, new Listener() 
        { 
            public void handleEvent(Event event) 
            { 
            	
            } 
        });
        
        
        class MyThread  extends Thread {
        	   private Browser myBrowser;
        	   public MyThread(Browser smbrowser){
        		   this.myBrowser = smbrowser;
        	   }
        	   public void run(){
					try {
						Integer iCount = 1;
						while (iCount < 5) {
							
							Thread.sleep(4000);
//							if ( SM.getInstance(0).getImgSrc(new ImgSrc() ) )
//								break;
					    	display.getDefault().syncExec(new  Runnable() {
								public void run() {
									String str = browser.getText();
							    	if ( str.indexOf("src=\"//img.alicdn.com") != -1){
							    		str=str.substring(str.indexOf("J_QRCodeImg"));
							    		str=str.substring(str.indexOf("src=\"")+5,str.indexOf("\"></DIV>"));
										try {
											System.out.println("https:"+str);
											writePNG("https:"+str);
											decode("C://taobaoCode//"+loginPath_key+".png");
//											if(StringUtils.isNotBlank(decode("C://taobaoCode//"+loginPath_key+".png")))
//												BrowserStore.setObj(loginPath_key+"_break", loginPath_key);
										} catch (Exception e) {
											e.printStackTrace();
										}
							    	}
								}
							});
							iCount ++;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	   }
        	}
        
        shell.open(); 
        browser.setUrl("http://member1.taobao.com/member/fresh/deliver_address.htm?spm=a1z08.10.0.0.SxdfME");
        browser.addProgressListener(new ProgressListener() {
        	@Override
        	public void changed(ProgressEvent arg0) {
        	}
        	@Override
        	public void completed(ProgressEvent arg0) {
        		String str=browser.getText();
        		if(StringUtils.isNotBlank(str)&&str.contains("阿里钱盾")){
        			MyThread tmp = new MyThread(browser);
        			tmp.start();
        		}
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
        while (!shell.isDisposed()) { 
            if (!display.readAndDispatch()){
            	display.sleep(); 
            } 
          }
        
        display.dispose(); 
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
			System.out.println(jArray.toString());
			HttpXmlClient.post("http://139.196.136.32:8888/YY/customer/saveCustomerContactor.action", params);
		}
	}
	/**
	 * @ClassName: index
	 * @Description: 输出图片信息
	 * @author caizhen
	 * @date 2016年7月18日 下午3:11:32
	 */
	private void writePNG(String path) throws Exception{
		HttpConnect httpcon = new HttpConnect();
		List<RequestHead> requestHeads = new ArrayList<RequestHead>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		requestHeads.add(new RequestHead("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
		requestHeads
				.add(new RequestHead(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"));
		requestHeads.add(new RequestHead("Cookie", "CmWebtokenid=13735517630,zj; cmtokenid=106291249a174f2a9e2a95dc122e19d5@zj.ac.10086.cn; unity_SAMLart=5eba8505ed374a61bb4a2690ad12da49; citybrand=/8slJ6CBe1jSSnLu2fFsQzrbGuYylaB2; WT_FPC=id=25e9b01659f35b4e9bf1468215129331:lv=1468561243903:ss=1468559137392; WTSESSION=ir7tElN7g8Rmo5fa-eW7zdAWfJQSJJh0yr7BHB--dlFhmzGiC-oj!-455402449"));

		byte[] bytes = (byte[]) httpcon.request(path,
				"get", null, "bytes", "utf-8", cookies, requestHeads);
		//测试用
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		FileOutputStream fileOut = new FileOutputStream("C://taobaoCode//"+loginPath_key+".png");//("D://ss.png");
		fileOut.write(bytes);
		fileOut.close();
	}
	/**
	 * @ClassName: index
	 * @Description: 解析图片信息
	 * @author caizhen
	 * @date 2016年7月18日 下午3:33:21
	 */
	private String decode(String imagePath) {
		String contents = null;

		MultiFormatReader formatReader = new MultiFormatReader();

		BufferedImage image;
		
		try {
			image = ImageIO.read(new File(imagePath));

			// 将图像数据转换为1 bit data
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			// BinaryBitmap是ZXing用来表示1 bit data位图的类，Reader对象将对它进行解析
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

			Map hints = new HashMap();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

			// 对图像进行解码
			Result result = formatReader.decode(binaryBitmap, hints);
			contents = result.toString();

			System.out.println("barcode encoding format :\t " + result.getText());
			BrowserStore.setObj(loginPath_key, result.getText());
			return result.getText();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}
