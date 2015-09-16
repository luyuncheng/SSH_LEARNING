package com.lyc.util;

import java.io.File;










import org.json.JSONArray;
import org.json.JSONObject;

import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;



/**
 * 图片的识别
 * 
 * */
public class faceMessage {
	
	
	public static void main(String[] args) {
		String f0="D:/nouse/face.jpg";
		String f1="D:/JavaWorkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/CheckFace/upload/IMG_4869.JPG";
		String f2="D:/nouse/face5.jpeg";
		String f3="D:\\nouse\\face7.jpeg";
		String res=getfaceMessage(f3);
		System.out.println(res);
	}
	
	/**
	 * 识别函数
	 * @param String  文件路径
	 * @return String 人脸识别信息
	 * */
	public static String getfaceMessage(String path){
		System.out.println("图像处理的函数传入路径：："+path);
		if(path==null){return null;}
		try {
			/**facepp的HttpRequests*/
			//请求服务器
			//HttpRequests hrs =new HttpRequests("48a68a3a16a8c1d9bdd352d5ed8b7d2b","wz2VeTuW1fgIMBPrOZOLI68QmSUzL8wt");
			
			HttpRequests hrs =new HttpRequests("725417ecb46d440832d43117371b50c0","vNcl2vtuabjodzGxEyDc7XNqW3qn6orh",true,true);
			
			//对请求出传入参数
			PostParameters pps =new PostParameters();
			
			//判断文件小于1MB
			File files =new File(path);
			if(!files.exists() || !files.isFile())
			{
				String re="No File";
				System.out.println(re);
				return  re;
			}
			System.out.println("文件大小"+(files.length()/1024) + "KB" );
			if( (files.length() / 1024) >1024){
				String re="文件压缩后任然太大";
				System.out.println(re);
				return  re;	
			}			

			//吧请求传入过去处理
			pps.setImg(files);
			//传入参数解析
			JSONObject json=hrs.detectionDetect(pps);
			JSONArray array=json.getJSONArray("face");
			System.out.println("解析返回人脸个数："+array.length());
			//遍历解析
			StringBuffer result=new StringBuffer();
			result.append("共分析到："+array.length()+"个人的脸谱<br />");
			for(int i=0;i<array.length();i++){
				result.append("第"+(i+1)+"个人的分析信息: <br />");
				JSONObject jitem = array.getJSONObject(i);
				JSONObject attrjson=jitem.getJSONObject("attribute");
				
				//年龄
				JSONObject jsonAge=attrjson.getJSONObject("age");
				int range=jsonAge.getInt("range");
				int value=jsonAge.getInt("value");				
				result.append("年龄："+(value)+"岁(误差范围："+range+")<br />");
				
				//性别
				String genderStr = attrjson.getJSONObject("gender").getString("value");
				Double confiedence = attrjson.getJSONObject("gender").getDouble("confidence");
				result.append("性别："+genderStr+"(正确率："+confiedence+"%)<br />");
				
				//肤色
				String raceStr = attrjson.getJSONObject("race").getString("value");
				Double raceConfiedence = attrjson.getJSONObject("race").getDouble("confidence");
				result.append("肤色："+raceStr+"(正确率："+raceConfiedence+"%)<br />");
				
				//微笑程度
				Double smiling = attrjson.getJSONObject("smiling").getDouble("value");
				result.append("微笑程度："+smiling+"%<br />");
			}
			System.out.println("图片处理函数返回的结果："+result.toString());
			return result.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
