package com.lyc.util;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.io.File;














import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONObject;






import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.sun.prism.Graphics;



/**
 * 图片的识别
 * 
 * */
public class Test extends JPanel{
	
	
	public static void main(String[] args) {
		String fileName="/Users/luyuncheng/Pictures/com.tencent.ScreenCapture/1.png";
		String res=getfaceMessage(fileName);
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
			//吧请求传入过去处理
			pps.setImg(new File(path));
			//传入参数解析
			JSONObject json=hrs.detectionDetect(pps);;
			
			
			
			//得到面部属性
			JSONArray array=json.getJSONArray("face");
			double width=json.getDouble("img_width");
			double height=json.getDouble("img_height");
			StringBuffer result=new StringBuffer();
			/*返回的是x＝／100*imgwidth   y＝／100*imgheight*/
			Polygon p[] =new Polygon[10];
			//遍历解析
			for(int i=0;i<array.length();i++){
				JSONObject jitem = array.getJSONObject(i);
				JSONObject attrjson=jitem.getJSONObject("attribute");
				p[i]=new Polygon();
				/**start landmark**/
				String id=jitem.getString("face_id");
				
				JSONObject jsonmark = hrs.detectionLandmark(new PostParameters().setFaceId(id)); 
				JSONArray res = jsonmark.getJSONArray("result");
				System.out.println("第： "+i+"个landmark长度："+res.length());
				JSONObject landmark=res.getJSONObject(0).getJSONObject("landmark");
				
				for(int j=1;j<=9;j++)
				{
					JSONObject contour_left = landmark.getJSONObject("contour_left"+j);
					p[i].addPoint((int)(contour_left.getDouble("x")*width/100),(int)(contour_left.getDouble("y")*height/100));
//					System.out.println("left:"+(int)(contour_left.getDouble("x")*width/100));
				}
				double x=landmark.getJSONObject("contour_chin").getDouble("x")*width/100+0.5;
				double y=landmark.getJSONObject("contour_chin").getDouble("y")*height/100+0.5;
//				System.out.println("x:"+x+"  y:"+y);
				p[i].addPoint((int)x, (int)y);
				
				for(int j=9;j>=1;j--)
				{
					JSONObject contour_right = landmark.getJSONObject("contour_right"+j);
					p[i].addPoint((int)(contour_right.getDouble("x")*width/100),(int)(contour_right.getDouble("y")*height/100));
//					System.out.println("right:"+(int)(contour_right.getDouble("x")*width/100));
							
				}
				System.out.println("点数"+p[i].npoints);
				/*********************/
				
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

			ImageDraw imageDraw=new ImageDraw();
			imageDraw.drawLine(path, "/Users/luyuncheng/Pictures/com.tencent.ScreenCapture/456.jpg", p , array.length());
			System.out.println("图片处理函数返回的结果："+result.toString());
			return result.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
