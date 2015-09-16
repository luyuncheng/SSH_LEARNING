package com.lyc.search;


import java.io.BufferedReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

public class Mysearch2 {
	
	
	
	public static void main(String[] args) throws Exception{
		String url="http://acm.hdu.edu.cn/showproblem.php?pid=2155";
		String encoding="gb2312";
		//1.根据网络和页面的编码集 抓取网页的源代码
		String htmlResouce=GetHtmlResouceByURL(url, encoding);
		//System.out.println(htmlResouce);
		
		//2.解析网页的源代码 
		Document document = Jsoup.parse(htmlResouce);
		//置左显示
		document.select("div").attr("align","left");
		//更改图片的相对路径为绝对路径
		Elements elements=document.getElementsByTag("img");
	    for(Element element : elements) {
	    	String imgSrc=element.attr("src");
	    	imgSrc=imgSrc.substring(8);
	    	imgSrc="http://acm.hdu.edu.cn"+imgSrc;
	    	element.attr("src", imgSrc);
//	    	System.out.println(imgSrc);
	    }
//	    elements=document.getElementsByTag("img");
//	    for(Element element : elements) {
//	    	String imgSrc=element.attr("src");
//	    	System.out.println(imgSrc);
//	    }
//选择tbody段
		document.select("body>table>tbody>tr:not(body>table>tbody>tr:eq(3)").remove();

		System.out.println("标题:"+document.select("h1").text()); 
//		FileWriter firewrite = new FileWriter("D:\\nouse\\"+document.title() + ".html");  
		System.out.println(document.text());
//		firewrite.write(document.html());  
//		PrintWriter outs = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\nouse\\"+document.title() + ".html"),"gb2312")));
		PrintWriter outs = new PrintWriter(new OutputStreamWriter(new FileOutputStream("D:\\nouse\\"+document.title() + ".html"),"gb2312"));
		outs.write(document.html());
		outs.close();
//		firewrite.close();
		
//3.产生pdf
//		ByteArrayInputStream bais =new ByteArrayInputStream(document.html().getBytes("gb2312"));
//		BufferedInputStream bis =new BufferedInputStream(document.html());
		generatePDF(new File("D:\\nouse\\"+document.title()+"p.pdf"),document.html());
		
	}
	/**
	 * 根据网址和页面的编码集 抓取网页
	 * @param url 网址
	 * @param encoding 网页的编码集
	 * @return 源代码
	 * 
	 * */

	public static String GetHtmlResouceByURL(String url,String encoding){
		
			// 建立容器存储网页源代码
		StringBuffer buffer=new StringBuffer();
		URL urlobj=null;
		URLConnection uc =null;
		InputStreamReader isr=null;
		BufferedReader input=null;
		try {
			urlobj =new URL(url);
			uc = urlobj.openConnection();
			isr=new InputStreamReader(uc.getInputStream(),encoding);
			input=new BufferedReader(isr);
			
			String line=null;
			while((line=input.readLine())!=null){
				//添加换行
//				System.out.println(line);
				buffer.append(line+"\n");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			System.out.println("连接源代码失败");
		}finally{
			try {
				if(isr!=null)
					isr.close();
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("关闭失败");				
			}
		}
		
		return buffer.toString();
	}
	/**
	 *  生成pdf
	 * @param PDFpath pdf路径
	 * @param htmlpath html路径
	 * 
	 * */


	public static void generatePDF(File PDFpath,String htmlpath) throws Exception{
		System.out.println("路径："+PDFpath);
		FileOutputStream fos =new FileOutputStream(PDFpath);
		int test=0;
		if(test==1)return;
		PD4ML pd4ml=new PD4ML();
		//设置页眉
		PD4PageMark headMark = new PD4PageMark();
		headMark.setAreaHeight(30);
		headMark.setInitialPageNumber(1);
		headMark.setPagesToSkip(1);
		headMark.setTitleAlignment(PD4PageMark.CENTER_ALIGN);
		headMark.setHtmlTemplate("lyc");
		pd4ml.setPageHeader(headMark);
		//设置页脚
		PD4PageMark footMark =new PD4PageMark();
		footMark.setAreaHeight(30);
		footMark.setHtmlTemplate("LYC_VJ_Problem");
		pd4ml.setPageFooter(footMark);
		//设置文字和字体
		pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
//        pd4ml.useTTF("file:fonts", true);  
		pd4ml.useTTF("c:/Windows/Fonts", true);  
        pd4ml.setDefaultTTFs("KaiTi_GB2312", "Arial", "Courier New"); 

		//pd4ml.useTTF("java:fonts", true);//这一行和下面一行设置中文

//		pd4ml.generatePdfForms(true, "KaiTi_GB2312");
//		pd4ml.setDefaultTTFs("STKAITI.TTF", "STKAITI.TTF", "STKAITI.TTF");

		pd4ml.enableDebugInfo();
		String htmlcode= new String(htmlpath.getBytes("GB2312"));
//		pd4ml.render(new StringReader(htmlcode),fos);
		pd4ml.render(new StringReader(htmlcode),fos);
		
		
	}

}
