package com.yy.control;

//利用 java.awt.Robot 类来自动完成一些键盘或鼠标的动作,下面是一个小例子
//程序流程:模拟鼠标左键点击 开始-->运行-->CMD-->DIR-->CLS-->EXIT
//模拟右键点击 移动到右下角-->右键点击-->调整日期和时间-->退出
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;


//Test.java
public class KeyRobot {
	
	public static void main(String[] args) throws Exception {
		 final Robot rb = new Robot();
		 
		 //设置开始菜单的大概位置
		 int x = 40;
		 int y = Toolkit.getDefaultToolkit().getScreenSize().height-10;
		 
		 //鼠标移动到开始菜单,
		 rb.mouseMove(x,y);
		 rb.delay(500);
		 pressMouse(rb,InputEvent.BUTTON1_MASK,500);
		 // C:\Users\lhl\AppData\Local\Microsoft\Windows\INetCache
		 int[] ks = {KeyEvent.VK_C};
		 pressKeys(rb,ks,500);
		 pressKeyWithShift(rb,KeyEvent.VK_SEMICOLON);
		 ks = new int[]{KeyEvent.VK_BACK_SLASH,KeyEvent.VK_U,KeyEvent.VK_S,KeyEvent.VK_E,
				 KeyEvent.VK_R,KeyEvent.VK_S,KeyEvent.VK_BACK_SLASH,KeyEvent.VK_L,KeyEvent.VK_H,KeyEvent.VK_L,
				 KeyEvent.VK_BACK_SLASH,KeyEvent.VK_A,KeyEvent.VK_P,KeyEvent.VK_P,KeyEvent.VK_D,KeyEvent.VK_A,
				 KeyEvent.VK_T,KeyEvent.VK_A,KeyEvent.VK_BACK_SLASH,KeyEvent.VK_L,KeyEvent.VK_O,KeyEvent.VK_C,
				 KeyEvent.VK_A,KeyEvent.VK_L,KeyEvent.VK_BACK_SLASH,KeyEvent.VK_M,KeyEvent.VK_I,KeyEvent.VK_C,
				 KeyEvent.VK_R,KeyEvent.VK_O,KeyEvent.VK_S,KeyEvent.VK_O,KeyEvent.VK_F,KeyEvent.VK_T,
				 KeyEvent.VK_BACK_SLASH,KeyEvent.VK_W,KeyEvent.VK_I,KeyEvent.VK_N,KeyEvent.VK_D,KeyEvent.VK_O,
				 KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_BACK_SLASH,KeyEvent.VK_I,KeyEvent.VK_N,KeyEvent.VK_E,
				 KeyEvent.VK_T,KeyEvent.VK_C,KeyEvent.VK_A,KeyEvent.VK_C,KeyEvent.VK_H,KeyEvent.VK_E,KeyEvent.VK_ENTER};
		 pressKeys(rb,ks,500);
		 rb.mouseMove(400,400);
		 rb.delay(500);
		 
		 // ctrl+A
		 pressKeyWithCtrl(rb,KeyEvent.VK_A);
		 // ctrl+c
		 pressKeyWithCtrl(rb,KeyEvent.VK_C);
		 
		 rb.mouseMove(x,y);
		 rb.delay(500);
		 pressMouse(rb,InputEvent.BUTTON1_MASK,500);
		 
		 // D:\test
		 ks = new int[]{KeyEvent.VK_D,};
		 pressKeys(rb,ks,500);
		 pressKeyWithShift(rb,KeyEvent.VK_SEMICOLON);
		 ks = new int[]{KeyEvent.VK_BACK_SLASH,KeyEvent.VK_T,KeyEvent.VK_E,KeyEvent.VK_S,KeyEvent.VK_T,KeyEvent.VK_ENTER};
		 pressKeys(rb,ks,500);
		 
		 // ctrl+V
		 pressKeyWithCtrl(rb,KeyEvent.VK_V);
		
		 
//		 new Thread(){
//			  public void run(){
//				   rb.delay(2000);
//				   //回车
//				   rb.keyPress(KeyEvent.VK_ENTER);
//				   rb.keyRelease(KeyEvent.VK_ENTER);
//			  }
//		 }.start();
//		 JOptionPane.showMessageDialog(null,"以下程序自动执行,包括本对话框,请不必进行人为干预.\n如果不能正常执行程序,请先关闭输入法");
//		 
//		 //设置开始菜单的大概位置
//		 int x = 40;
//		 int y = Toolkit.getDefaultToolkit().getScreenSize().height-10;
//		 
//		 //鼠标移动到开始菜单,
//		 rb.mouseMove(x,y);
//		 rb.delay(500);
	 
	 
//		 //单击三次开始菜单
//		 for(int i=0; i<3; i++)
//		 	pressMouse(rb,InputEvent.BUTTON1_MASK,500);
//		 rb.delay(1000);
//		
//		 //运行CMD命令  r  cmd enter
//		 int[] ks = {KeyEvent.VK_R,KeyEvent.VK_C,KeyEvent.VK_M,KeyEvent.VK_D,KeyEvent.VK_ENTER,};
//		 pressKeys(rb,ks,500);
//		 rb.mouseMove(400,400);
//		 rb.delay(500);
//		 //运行DIR命令  dir enter
//		 ks = new int[]{KeyEvent.VK_D,KeyEvent.VK_I,KeyEvent.VK_R,KeyEvent.VK_ENTER};
//		 pressKeys(rb,ks,500);
//		 rb.delay(1000);
		// //运行CLS命令 cls enter
		// ks = new int[]{KeyEvent.VK_C,KeyEvent.VK_L,KeyEvent.VK_S,KeyEvent.VK_ENTER};
		// pressKeys(rb,ks,500);
		// rb.delay(1000);
		// //运行EXIT命令 exit enter
		// ks = new int[]{KeyEvent.VK_E,KeyEvent.VK_X,KeyEvent.VK_I,KeyEvent.VK_T,KeyEvent.VK_ENTER};
		// pressKeys(rb,ks,500);
		// rb.delay(1000);
		// 
		// //右键测试
		// x=Toolkit.getDefaultToolkit().getScreenSize().width-10;
		// rb.mouseMove(x, y);
		// //如果是双键鼠标,请改用InputEvent.BUTTON2_MASK试试,我没有这种鼠标
		// pressMouse(rb,InputEvent.BUTTON3_MASK,500);
		// //显示日期调整对话框 a
		// pressKeys(rb,new int[]{KeyEvent.VK_A},1000);
		// rb.delay(2000);
		// pressKeys(rb,new int[]{KeyEvent.VK_ESCAPE},0);
		// rb.delay(1000);
		// new Thread(){
		//  public void run(){
		//   rb.delay(1000);
		//   //回车
		//   rb.keyPress(KeyEvent.VK_ENTER);
		//   rb.keyRelease(KeyEvent.VK_ENTER);
		//  }
		// }.start();
		// JOptionPane.showMessageDialog(null,"演示完毕!");
	}
	//鼠标单击,要双击就连续调用
	private static void pressMouse(Robot r,int m,int delay){
		 r.mousePress(m);
		 r.delay(10);
		 r.mouseRelease(m);
		 r.delay(delay);
	}
	//键盘输入
	private static void pressKeys(Robot r,int[] ks,int delay){
		 for(int i=0; i<ks.length; i++){
			  r.keyPress(ks[i]);
			  r.delay(10);
			  r.keyRelease(ks[i]);
			  r.delay(delay);
		 }
	}
	//shift 组合键盘输入
	public static void pressKeyWithShift(Robot r, int keyvalue) {
		 r.keyPress(KeyEvent.VK_SHIFT);
		 r.delay(10);
		 r.keyPress(keyvalue);
		 r.delay(10);
		 r.keyRelease(keyvalue);
		 r.delay(10);
		 r.keyRelease(KeyEvent.VK_SHIFT);
		 r.delay(500);
	}
	//ctrl 组合键盘输入
	public static void pressKeyWithCtrl(Robot r, int keyvalue) {
		 r.keyPress(KeyEvent.VK_CONTROL);
		 r.delay(10);
		 r.keyPress(keyvalue);
		 r.delay(10);
		 r.keyRelease(keyvalue);
		 r.delay(10);
		 r.keyRelease(KeyEvent.VK_CONTROL);
		 r.delay(500);
	}
} 