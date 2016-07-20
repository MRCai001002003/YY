package com.yy.control;

import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.nio.file.FileSystems;  
import java.nio.file.Path;  
import java.util.HashMap;  
import java.util.Map;  
  
import javax.imageio.ImageIO;  
  
import org.junit.Test;

import com.google.zxing.BarcodeFormat;  
import com.google.zxing.Binarizer;  
import com.google.zxing.BinaryBitmap;  
import com.google.zxing.DecodeHintType;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.LuminanceSource;  
import com.google.zxing.MultiFormatReader;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.NotFoundException;  
import com.google.zxing.Result;  
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.common.HybridBinarizer;

import net.sf.json.JSONObject; 
public class Test2 {

	public static void main(String[] args) {
		String str="<DIV style=\"ZOOM: 1; opacity: 1\" id=J_QRCodeImg class=qrcode-img _ks_data_1468913391422=\"31\"><IMG src=\"//img.alicdn.com/tfscom/TB1QHZcKVXXXXagXVXXwu0bFXXX.png\"></DIV>";
		str=str.substring(str.indexOf("J_QRCodeImg"));
		str=str.substring(str.indexOf("src=\"")+5,str.indexOf("\"></DIV>"));
		System.out.println(str);
//		AlData al = new AlData();
//		decode("D://1.png");

	}

	private static String decode(String imagePath) {
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
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return contents;
	}

}
