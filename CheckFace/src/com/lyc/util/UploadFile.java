package com.lyc.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



/**
 * 上传图像
 * 
 * */
public class UploadFile {
	
	
	public static void main(String[] args) {
		
	}
	/**
	 * 文件上传工具类
	 * @param request 页面的http请求
	 * @param response 响应
	 * @return String 服务器路径
	 * */
	public static String getUploadFile(HttpServletRequest request,HttpServletResponse response){
		String fileName=null;

//		String filePath="D:\\JavaWorkspace\\CheckFace\\WebContent\\upload";
		try {
			request.setCharacterEncoding("utf-8");
			boolean bol=ServletFileUpload.isMultipartContent(request);
			
			if(bol){
				//构建文件上传对象
				DiskFileItemFactory factory=new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				//定义一个迭代器 迭代上传
				Iterator items= upload.parseRequest(request).iterator();
				
				while(items.hasNext()){
					FileItem item = (FileItem)items.next();
					//判断
					boolean ite= item.isFormField();
					if(!ite){
						fileName = item.getName();
						System.out.println("文件名："+fileName);
						if(fileName==null)return null;
						@SuppressWarnings("deprecation")
						/*切记啊！！ 是/upload	 获得要路径的几种方法
 1，request.getRealPath("/");这个方法已不推荐用
 2，在Servlet 里用this.getServletContect().getRealPath("/");获得要路径。
 3，struts里用this.getServlet().getServletContext().getRealPath("/")获得要路径。
 4.request.getSession().getServletContext().getRealPath(“/updload/video/”) 得到项目的绝对路径
						 * */
						String filePath=request.getRealPath("/upload");
						//别人传入的文件本地存储的位置
						System.out.println("我擦擦擦擦擦擦："+filePath);
						File file =new File(filePath);
						if(!file.exists()){
							file.mkdirs();
						}
						File uploadfiFile = new File(filePath+"/"+fileName);
						item.write(uploadfiFile);
						System.out.println("uploadfile文件中文件大小："+uploadfiFile.length()/1024);
						if(uploadfiFile.length()/1024>=1025)
							ScaleImage(filePath+"/"+fileName , filePath+"/"+fileName);
					}
				}
			}
			System.out.println("文件路径："+fileName);
			return fileName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 缩放图片
	 * @param pathIn 源图片路径
	 * @param pathOut 输出图片路径
	 * */
	public static void ScaleImage(String pathIn, String pathOut) {
			try {
				BufferedImage src=ImageIO.read(new File(pathIn));
				double width=src.getWidth();
				double height=src.getHeight();
				double s1=width/505;
				double s2=height/460;
				System.out.println("之前："+width+"/"+height);
				double ss=0;
				if(s1>=1 || s2>=1){
					ss=s1>s2?s1:s2;
					width=width/ss+0.5;
					height=height/ss+0.5;
				}else{
					ss=s1<s2?s1:s2;
					width=width*ss+0.5;
					height=height*ss+0.5;
				}

				System.out.println("之后："+width+"/"+height);
				Image image = src.getScaledInstance((int)width, (int)height, Image.SCALE_DEFAULT);
				BufferedImage tag=new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_BGR);
				Graphics g =tag.getGraphics();
				g.drawImage(image, 0, 0, null);
				//释放图形的资源
				g.dispose();
				ImageIO.write(tag, "jpeg", new File(pathOut));
				System.out.println("缩放成功");
			} catch (Exception e) {	
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("缩放图片异常");
			}
	}	
	
}
